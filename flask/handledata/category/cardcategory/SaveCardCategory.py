import pandas as pd

##############################################################################################################
#
# 1. 여기서 카드 카테고리의 속성과 키워드들을 저장합니다.
#
##############################################################################################################



class SaveCardCategory:

    def __init__(self):
        self.category_column_df = None
        self.extract_categories = set()
        self.data_df = None
        self.file_path = "../../../static/data/result/"

    def load_card_data(self):
        try:
            # DataFrame으로 데이터 불러오기
            self.data_df = pd.read_csv(self.file_path + "card_db.csv")
        except FileNotFoundError:
            print("Error: 'card_db.csv' 파일을 찾을 수 없습니다.")

    def load_categorized_data(self):
        try:
            self.category_column_df = pd.read_csv(self.file_path + "categories.csv")
        except FileNotFoundError:
            print("Error: 'categories.csv' 파일을 찾을 수 없습니다.")

    def save_category(self):
        # 카테고리 딕셔너리
        categories = {
            "category_shopping": ['쇼핑', '백화점', '마트/편의점', '온라인쇼핑', '소셜커머스', '해외직구', '홈쇼핑', 'SPA브랜드', '아울렛', '대형마트',
                                  'SSM',
                                  '전통시장', '면세점', '모든가맹점', '국내외가맹점', '화장품', '드럭스토어'],
            "category_culture": ['문화', '여가', '문화/여가', '영화', '공연/전시', '문화센터', '도서', '음원사이트', '영화/문화', '디지털구독', '골프'],
            "category_transportation": ['교통', '고속버스', '항공권', '기차', '대중교통', '렌터카', '택시', '교통'],
            "category_car": ['자동차', '주유', '자동차', '정비', '주유소', '충전소', '하이패스', '자동차/하이패스'],
            "category_food": ['음식', '푸드', '카페', '패밀리레스토랑', '배달앱', '베이커리', '점심', '저녁', '일반음식점', '패스트푸드', '카페/디저트', '푸드'],
            "category_education": ['교육', '학습지', '유치원', '어린이집', '학원', '교육/육아'],
            "category_housing_communication": ['주거', '통신', '주거/통신', '공과금', '공과금/렌탈', '통신', 'KT', 'SKT', 'LGU+'],
            "category_travel": ['여행', '항공', '여행/항공', '진에어', '대한항공', '아시아나항공', '제주항공', '항공마일리지', '온라인 여행사', '여행/숙박',
                                '공항라운지', '리조트', '공항',
                                '공항라운지/PP', '여행사', '호텔', 'PP', '라운지키', '해외이용', '저가항공', '해외'],
            "category_medical": ['의료', '약국', '병원', '병원/약국'],
            "category_financial_insurance": ['금융', '금융/보험', '은행사', '보험', '보험사'],
            "category_others": ['애완동물', '비즈니스', '동물병원', '지역', '생활', '렌탈', '무실적']
        }
        # 카테고리를 담은 리스트
        category_list = []
        for key, values in categories.items():
            category_list.append((key, ','.join(values)))

        # DataFrame 생성
        benefit_df = pd.DataFrame(category_list, columns=['category', 'benefit'])

        benefit_df.to_csv(self.file_path + 'categories.csv', index=False, encoding="utf-8")
        benefit_df.to_excel(self.file_path + 'categories.xlsx', index=False)

if __name__ == '__main__':
    set_card_category = SaveCardCategory()

    # 카테고리 저장
    set_card_category.save_category()
