import numpy as np
import pandas as pd
from numpy import dot
from numpy.linalg import norm

from handledata.category.dao import Repository


# 카드 데이터 및 유저 데이터 전처리

# 카테고리 속성 값 할당

# 유저의 카테고리 값 설정

class Similarity(object):
    def __init__(self):
        self.card_df = None
        self.file_path = "../../../static/data/result/"
        self.card_dict = {}
        self.user_vector = np.array([0.2, 0.1, 0.1, 0.05, 0.1, 0.05, 0.1, 0.1, 0.1, 0.1, 0])
        self.repository = Repository.Repository()

    def calculate_similarity(self, card_vector):
        if norm(self.user_vector) * norm(card_vector) == 0:
            return 0

        return dot(self.user_vector, card_vector) / (norm(self.user_vector) * norm(card_vector))

    def load_card_category(self):
        # query = 'SELECT category_shopping,category_culture,category_transportation,category_car,category_food,category_education,category_housing_communication,category_travel,category_medical,category_financial_insurance,category_others FROM penny_pal.category;'
        query = 'SELECT* FROM category'
        # Repository.select_execute(query)
        result = self.repository.select_execute(query)

        # result의 각 원소를 순회하면서 사전에 추가
        for item in result:
            index = item[0]  # 첫 번째 원소를 인덱스로 사용
            vector = np.array(item[1:], dtype=float)  # 나머지 원소를 NumPy 배열로 변환
            self.card_dict[index] = vector  # 인덱스를 키로 하고 NumPy 배열을 값으로 사전에 추가

        # 결과 출력
        # print(self.card_dict)

    # 추천
    def recommend_cards(self, top_n=4):
        # similarities = [(i, self.calculate_similarity(self.user_vector, card_vector)) for i, card_vector in
        #                 enumerate(self.card_vectors)]
        # similarities.sort(key=lambda x: x[1], reverse=True)

        # 유사도를 저장할 리스트 초기화
        similarities = []

        # 각 인덱스에 대해 유사도를 계산하여 리스트에 저장
        for index, vector in self.card_dict.items():
            similarity = self.calculate_similarity(vector)  # 유사도 계산 함수 호출
            similarities.append((index, similarity))

        # 유사도를 내림차순으로 정렬
        similarities.sort(key=lambda x: x[1], reverse=True)

        # you need to get the name from index
        print(similarities[:top_n])
        return similarities[:top_n]
# 유사도 계산


if __name__ == '__main__':
    sim = Similarity()

    sim.load_card_category()
    sim.recommend_cards()

    # 유저의 카테고리 값과 카드의 속성 값으로 유사도 계산 및 추천 수행
    # recommended_cards = recommend_cards(user_vector, card_vectors)
    # print(recommended_cards)