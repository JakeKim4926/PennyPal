import numpy as np
import pandas as pd
from numpy import dot
from numpy.linalg import norm

# 카드 데이터 및 유저 데이터 전처리

# 카테고리 속성 값 할당

# 유저의 카테고리 값 설정

class Similarity(object):
    def __init__(self):
        self.card_df = None
        self.file_path = "../../../static/data/result/"
        self.card_vectors = []
    def calculate_similarity(user_vector, card_vector):
        return dot(user_vector, card_vector) / (norm(user_vector) * norm(card_vector))

    def load_data(self):
        self.card_df.read_csv(set_card_category.file_path + 'final_card_data.csv', index=False, encoding="utf-8")
    def set_user_vector(self):
        card_vector_list = []
        # 각 카드 데이터에 대해 벡터를 만들어 리스트에 추가
        for card_data in merged_df:
            card_vector = [
                card_data["category_shopping"], card_data["category_culture"], card_data["category_transportation"],
                card_data["category_car"], card_data["category_food"], card_data["category_education"],
                card_data["category_housing_communication"], card_data["category_travel"],
                card_data["category_medical"],
                card_data["category_financial_insurance"]
            ]
            card_vector_list.append(card_vector)

        # numpy 배열로 변환
        self.card_vectors = np.array(card_vector_list)

        return vector

    def set_card_vector(self):
        vector = np.array([])

        return vector

    # 추천
    def recommend_cards(user_vector, card_vectors, top_n=4):
        similarities = [(i, calculate_similarity(user_vector, card_vector)) for i, card_vector in
                        enumerate(card_vectors)]
        similarities.sort(key=lambda x: x[1], reverse=True)

        # you need to get the name from index

        return similarities[:top_n]
# 유사도 계산


if __name__ == '__main__':
    # 유저의 카테고리 값과 카드의 속성 값으로 유사도 계산 및 추천 수행
    # user_vector = np.array([0.2, 0.1, 0.1, 0.05, 0.1, 0.05, 0.1, 0.1, 0.1, 0.1])  # 예시로 임의의 유저 카테고리 값
    # card_vectors = np.array([[0.2, 0.1, 0.1, 0.05, 0.1, 0.05, 0.1, 0.1, 0.1, 0.1] ,  # 예시로 임의의 카드 카테고리 값
    #                          [0.15, 0.2, 0.1, 0.1, 0.1, 0.05, 0.05, 0.1, 0.05, 0.1],
    #                          [0.1, 0.05, 0.2, 0.1, 0.1, 0.1, 0.05, 0.05, 0.1, 0.15],
    #                          [0.1, 0.1, 0.1, 0.05, 0.05, 0.1, 0.1, 0.15, 0.15, 0.1]])
    # recommended_cards = recommend_cards(user_vector, card_vectors)
    # print(recommended_cards)