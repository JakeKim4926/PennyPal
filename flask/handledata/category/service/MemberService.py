from handledata.category.dao import Repository
import requests
import os
from dotenv import load_dotenv
from datetime import datetime
from dateutil.relativedelta import relativedelta

import numpy as np


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

        # POST 요청 보내기
        response = requests.post(os.getenv("SPRING_API") + 'api/bank/user/account/transaction', json=data)

        if response.status_code != 200:
            response = requests.post(os.getenv("SPRING_API") + 'api/bank/user/account/transaction', json=data)


        # 응답 확인
        if response.status_code == 200:
            # 성공적으로 데이터를 받았을 경우의 처리
            result_data = response.json()
            total_summaries = len(result_data["data"]["rec"])
            transaction_summaries = [rec["transactionSummary"] for rec in result_data["data"]["rec"]]

            # 각 카테고리에 대한 트랜잭션 요약 빈도수 계산
            category_distribution = {category: 0 for category in self.categories_keywords}

            # 각 트랜잭션 요약에 대해 카테고리를 판별하고 빈도수를 업데이트
            for summary in transaction_summaries:
                for category, keywords in self.categories_keywords.items():
                    if any(keyword in summary for keyword in keywords):
                        category_distribution[category] += 1
                        break  # 각 요약은 하나의 카테고리에만 속한다고 가정
                        # # 카테고리 분석이 안됬으니 기타에 1 추가
                        # category_distribution['category_others'] += 1

            # 각 카테고리의 백분율을 계산
            category_percentages = {category: (count / total_summaries) * 100 for category, count in
                                    category_distribution.items()}

            # 사용자 벡터 (위에서 정의한 것을 사용)
            user_vector = np.array([0.2, 0.2, 0.1, 0, 0.1, 0, 0.1, 0.1, 0.1, 0.1, 0])
            self.user_vector = np.array(
                [category_percentages.get(f"category_{i + 1}", 0) for i in range(len(user_vector))])

            category_percentages_decimal = {category: count / total_summaries for category, count in
                                            category_distribution.items()}
            category_indices = {
                "category_shopping": 0,
                "category_culture": 1,
                "category_transportation": 2,
                "category_car": 3,
                "category_food": 4,
                "category_education": 5,
                "category_housing_communication": 6,
                "category_travel": 7,
                "category_medical": 8,
                "category_financial_insurance": 9,
                "category_others": 10
            }

            # 각 카테고리의 소수 비율에 따라 사용자 벡터를 생성합니다.
            category_vector = np.zeros(len(user_vector))
            for category, index in category_indices.items():
                category_vector[index] = category_percentages_decimal.get(category, 0)

            self.user_vector = category_vector

        else:
            # 오류 처리
            print(f"Failed with status code: {response.status_code}")
            print(response.text)

    def getMemberBestCategory(self, index):
        res = self.getBankInfo(index)
        data = {
            "userEmail": self.member_email,
            "bankCode": self.bank_code,
            "transactionType": self.transaction_type,
            "accountNo": self.account_no,
            "startDate": self.start_date,
            "endDate": self.end_date
        }
        max_index = 0

        # POST 요청 보내기
        response = requests.post(os.getenv("SPRING_API") + 'api/bank/user/account/transaction', json=data)

        if response.status_code != 200:
            response = requests.post(os.getenv("SPRING_API") + 'api/bank/user/account/transaction', json=data)

        # 응답 확인
        if response.status_code == 200:
            # 성공적으로 데이터를 받았을 경우의 처리
            result_data = response.json()
            total_summaries = len(result_data["data"]["rec"])
            transaction_summaries = [rec["transactionSummary"] for rec in result_data["data"]["rec"]]

            # 각 카테고리에 대한 트랜잭션 요약 빈도수 계산
            category_distribution = {category: 0 for category in self.categories_keywords}

            # 각 트랜잭션 요약에 대해 카테고리를 판별하고 빈도수를 업데이트
            for summary in transaction_summaries:
                for category, keywords in self.categories_keywords.items():
                    if any(keyword in summary for keyword in keywords):
                        category_distribution[category] += 1
                        break  # 각 요약은 하나의 카테고리에만 속한다고 가정
                        # # 카테고리 분석이 안됬으니 기타에 1 추가
                        # category_distribution['category_others'] += 1

            # 각 카테고리의 백분율을 계산
            category_percentages = {category: (count / total_summaries) * 100 for category, count in
                                    category_distribution.items()}

            # 사용자 벡터 (위에서 정의한 것을 사용)
            user_vector = np.array([0.2, 0.2, 0.1, 0, 0.1, 0, 0.1, 0.1, 0.1, 0.1, 0])
            self.user_vector = np.array(
                [category_percentages.get(f"category_{i + 1}", 0) for i in range(len(user_vector))])

            category_percentages_decimal = {category: count / total_summaries for category, count in
                                            category_distribution.items()}
            category_indices = {
                "category_shopping": 0,
                "category_culture": 1,
                "category_transportation": 2,
                "category_car": 3,
                "category_food": 4,
                "category_education": 5,
                "category_housing_communication": 6,
                "category_travel": 7,
                "category_medical": 8,
                "category_financial_insurance": 9,
                "category_others": 10
            }

            # 각 카테고리의 소수 비율에 따라 사용자 벡터를 생성합니다.
            category_vector = np.zeros(len(user_vector))
            max_val = 0
            for category, index in category_indices.items():
                category_vector[index] = category_percentages_decimal.get(category, 0)
                if max_val < category_vector[index]:
                    max_val = category_vector[index]
                    max_index = index
        else:
            # 오류 처리
            print(f"Failed with status code: {response.status_code}")
            print(response.text)

        category_names = ["쇼핑", "여가/문화", "교통", "자동차", "푸드", "교육", "주거/통신", "여행/항공", "의료", "금융/보험", "기타"]

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
        max_index = 0

        category_result = {
            "category_shopping": 0,
            "category_culture": 0,
            "category_transportation": 0,
            "category_car": 0,
            "category_food": 0,
            "category_education": 0,
            "category_housing_communication": 0,
            "category_travel": 0,
            "category_medical": 0,
            "category_financial_insurance": 0,
            "category_others": 0
        }

        # POST 요청 보내기
        response = requests.post(os.getenv("SPRING_API") + 'api/bank/user/account/transaction', json=data)

        if response.status_code != 200:
            response = requests.post(os.getenv("SPRING_API") + 'api/bank/user/account/transaction', json=data)

        # 응답 확인
        if response.status_code == 200:
            # 성공적으로 데이터를 받았을 경우의 처리
            result_data = response.json()
            total_summaries = len(result_data["data"]["rec"])
            transaction_summaries = [rec["transactionSummary"] for rec in result_data["data"]["rec"]]

            # 각 카테고리에 대한 트랜잭션 요약 빈도수 계산
            category_distribution = {category: 0 for category in self.categories_keywords}

            # 각 트랜잭션 요약에 대해 카테고리를 판별하고 빈도수를 업데이트
            for summary in transaction_summaries:
                for category, keywords in self.categories_keywords.items():
                    if any(keyword in summary for keyword in keywords):
                        category_distribution[category] += 1
                        break  # 각 요약은 하나의 카테고리에만 속한다고 가정
                        # # 카테고리 분석이 안됬으니 기타에 1 추가
                        # category_distribution['category_others'] += 1

            # 각 카테고리의 백분율을 계산
            category_percentages = {category: (count / total_summaries) * 100 for category, count in
                                    category_distribution.items()}

            # 사용자 벡터 (위에서 정의한 것을 사용)
            user_vector = np.array([0.2, 0.2, 0.1, 0, 0.1, 0, 0.1, 0.1, 0.1, 0.1, 0])
            self.user_vector = np.array(
                [category_percentages.get(f"category_{i + 1}", 0) for i in range(len(user_vector))])

            category_percentages_decimal = {category: count / total_summaries for category, count in
                                            category_distribution.items()}
            category_indices = {
                "category_shopping": 0,
                "category_culture": 1,
                "category_transportation": 2,
                "category_car": 3,
                "category_food": 4,
                "category_education": 5,
                "category_housing_communication": 6,
                "category_travel": 7,
                "category_medical": 8,
                "category_financial_insurance": 9,
                "category_others": 10
            }

            # 각 카테고리의 소수 비율에 따라 사용자 벡터를 생성합니다.
            category_vector = np.zeros(len(user_vector))
            for category, index in category_indices.items():
                category_vector[index] = category_percentages_decimal.get(category, 0)

            category_result = {
                "category_shopping": category_vector[0],
                "category_culture": category_vector[1],
                "category_transportation": category_vector[2],
                "category_car": category_vector[3],
                "category_food": category_vector[4],
                "category_education": category_vector[5],
                "category_housing_communication": category_vector[6],
                "category_travel": category_vector[7],
                "category_medical": category_vector[8],
                "category_financial_insurance": category_vector[9],
                "category_others": category_vector[10]
            }

        print(f"Failed with status code: {response.status_code}")
        print(response.text)

        category_names = ["쇼핑", "여가/문화", "교통", "자동차", "푸드", "교육", "주거/통신", "여행/항공", "의료", "금융/보험", "기타"]

        return category_result

if __name__ == '__main__':
    setUserCategory = MemberService()
    index = 96
    setUserCategory.getMemberReceipts(index)