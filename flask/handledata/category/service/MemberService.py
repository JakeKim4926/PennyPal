from handledata.category.dao import Repository
import requests
import os
from dotenv import load_dotenv
from datetime import datetime
from dateutil.relativedelta import relativedelta

import numpy as np

from handledata.category.service.Run import Run

class MemberService:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance.initialize()
        return cls._instance

    def initialize(self):
        self.repository = Repository.Repository()
        self.bank_code = None
        self.account_no = None
        self.member_email = None
        self.transaction_type = 'A'
        self.start_date = (datetime.now() - relativedelta(months=3)).strftime("%Y%m%d")
        self.end_date = datetime.now().strftime("%Y%m%d")
        self.categories_keywords = {
            "category_shopping": ["쇼핑", "백화점", "마트", "온라인쇼핑", "소셜커머스"],
            "category_culture": ["문화", "여가", "영화", "공연", "전시"],
            "category_transportation": ["교통", "고속버스", "항공권", "기차", "대중교통"],
            "category_car": ["자동차", "주유", "정비"],
            "category_food": ["음식", "푸드", "카페", "패밀리레스토랑"],
            "category_education": ["교육", "학습지", "유치원", "어린이집"],
            "category_housing_communication": ["주거", "통신", "공과금"],
            "category_travel": ["여행", "항공", "호텔"],
            "category_medical": ["의료", "약국", "병원"],
            "category_financial_insurance": ["금융", "보험"],
            "category_others": ["애완동물", "비즈니스"]
        }
        self.user_vector = None
        self.run_instance = Run()

    def getBankInfo(self, index):
        try:
            self.member_email = self.repository.getEmail(index)
            # Replace this URL with the actual URL you need to call
            request_url = os.getenv("SPRING_API") + 'api/bank/user/account/' + self.member_email

            response_data = requests.get(request_url)

            if response_data.status_code != 200:
                request_url = os.getenv("SPRING_API") + 'api/bank/user/account/' + self.member_email
                response_data = requests.get(request_url)

            response = response_data.json()

            rec = response['data']['rec']

            self.bank_code = rec[0]['bankCode']
            self.account_no = rec[0]['accountNo']


        except requests.exceptions.RequestException as e:
            # Handle any exceptions that occur during the request
            return {"error": str(e)}

    def getMemberReceipts(self, index):
        res = self.getBankInfo(index)
        data = {
            "userEmail": self.member_email,
            "bankCode": self.bank_code,
            "transactionType": self.transaction_type,
            "accountNo": self.account_no,
            "startDate": self.start_date,
            "endDate": self.end_date
        }

        response = requests.post(os.getenv("SPRING_API") + 'api/bank/user/account/transaction', json=data)

        if response.status_code == 200:
            result_data = response.json()
            transaction_summaries = [rec["transactionSummary"] for rec in result_data["data"]["rec"]]

            category_distribution = {
                "쇼핑": 0,
                "여가/문화": 0,
                "교통": 0,
                "자동차": 0,
                "푸드": 0,
                "교육": 0,
                "주거/통신": 0,
                "여행/항공": 0,
                "의료": 0,
                "금융/보험": 0,
                "기타": 0
            }

            for summary in transaction_summaries:
                predicted_category = self.run_instance.predict(summary)  # 각 요약에 대한 카테고리를 예측합니다.

                if predicted_category in category_distribution:
                    category_distribution[predicted_category] += 1  # 예측된 카테고리의 빈도수를 업데이트합니다.
                else:
                    category_distribution["기타"] += 1  # 정의되지 않은 카테고리는 '기타'로 분류합니다.

            # 여기서부터는 category_distribution을 사용하여 필요한 처리를 수행하면 됩니다.
            # 예를 들어, 백분율 계산 또는 결과 출력 등
            # 각 카테고리의 백분율을 계산
            total_summaries = sum(category_distribution.values())
            category_percentages = {category: (count / total_summaries) * 100 for category, count in
                                    category_distribution.items()}
            # category_result에 매핑
            category_result = {
                "category_shopping": category_percentages.get("쇼핑", 0),
                "category_culture": category_percentages.get("여가/문화", 0),
                "category_transportation": category_percentages.get("교통", 0),
                "category_car": category_percentages.get("자동차", 0),
                "category_food": category_percentages.get("푸드", 0),
                "category_education": category_percentages.get("교육", 0),
                "category_housing_communication": category_percentages.get("주거/통신", 0),
                "category_travel": category_percentages.get("여행/항공", 0),
                "category_medical": category_percentages.get("의료", 0),
                "category_financial_insurance": category_percentages.get("금융/보험", 0),
                "category_others": category_percentages.get("기타", 0)
            }

            # 사용자 벡터 (위에서 정의한 것을 사용)
            category_order = [
                "쇼핑", "여가/문화", "교통", "자동차", "푸드",
                "교육", "주거/통신", "여행/항공", "의료", "금융/보험", "기타"
            ]

            # category_percentages 딕셔너리에서 각 카테고리의 백분율을 가져와서 user_vector 배열을 생성합니다.
            # 카테고리가 없는 경우 기본값으로 0을 사용합니다.
            self.user_vector = np.array([category_percentages.get(category, 0) for category in category_order])

        else:
            # 오류 처리
            print(f"Failed with status code: {response.status_code}")
            print(response.text)

    def getMemberBestCategory(self, index):
        self.getMemberReceipts(index)

        category_names = ["쇼핑", "여가/문화", "교통", "자동차", "푸드", "교육", "주거/통신", "여행/항공", "의료", "금융/보험", "기타"]

        # 각 카테고리의 소수 비율에 따라 사용자 벡터를 생성합니다.
        max_index = np.argmax(self.user_vector)

        return category_names[max_index]

    def getMemberCategoryPercentage(self, index):
        res = self.getBankInfo(index)
        data = {
            "userEmail": self.member_email,
            "bankCode": self.bank_code,
            "transactionType": self.transaction_type,
            "accountNo": self.account_no,
            "startDate": self.start_date,
            "endDate": self.end_date
        }

        response = requests.post(os.getenv("SPRING_API") + 'api/bank/user/account/transaction', json=data)

        if response.status_code == 200:
            result_data = response.json()
            transaction_summaries = [rec["transactionSummary"] for rec in result_data["data"]["rec"]]

            category_distribution = {
                "쇼핑": 0,
                "여가/문화": 0,
                "교통": 0,
                "자동차": 0,
                "푸드": 0,
                "교육": 0,
                "주거/통신": 0,
                "여행/항공": 0,
                "의료": 0,
                "금융/보험": 0,
                "기타": 0
            }

            for summary in transaction_summaries:
                predicted_category = self.run_instance.predict(summary)  # 각 요약에 대한 카테고리를 예측합니다.

                if predicted_category in category_distribution:
                    category_distribution[predicted_category] += 1  # 예측된 카테고리의 빈도수를 업데이트합니다.
                else:
                    category_distribution["기타"] += 1  # 정의되지 않은 카테고리는 '기타'로 분류합니다.

            # 여기서부터는 category_distribution을 사용하여 필요한 처리를 수행하면 됩니다.
            # 예를 들어, 백분율 계산 또는 결과 출력 등
            # 각 카테고리의 백분율을 계산
            total_summaries = sum(category_distribution.values())
            category_percentages = {category: (count / total_summaries) * 100 for category, count in
                                    category_distribution.items()}
            # category_result에 매핑
            category_result = {
                "category_shopping": category_percentages.get("쇼핑", 0),
                "category_culture": category_percentages.get("여가/문화", 0),
                "category_transportation": category_percentages.get("교통", 0),
                "category_car": category_percentages.get("자동차", 0),
                "category_food": category_percentages.get("푸드", 0),
                "category_education": category_percentages.get("교육", 0),
                "category_housing_communication": category_percentages.get("주거/통신", 0),
                "category_travel": category_percentages.get("여행/항공", 0),
                "category_medical": category_percentages.get("의료", 0),
                "category_financial_insurance": category_percentages.get("금융/보험", 0),
                "category_others": category_percentages.get("기타", 0)
            }

            return category_result

        else:
            print(f"Failed with status code: {response.status_code}")
            print(response.text)

# if __name__ == '__main__':
#     member_service = MemberService()
#     index = 19
#     print(member_service.getMemberBestCategory(index))