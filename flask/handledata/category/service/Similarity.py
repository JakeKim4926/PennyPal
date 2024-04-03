import numpy as np
import pandas as pd
import json
from numpy import dot
from numpy.linalg import norm

from handledata.category.dao import Repository
from handledata.category.service.MemberService import MemberService


class Similarity(object):
    _instance = None
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance.initialize()
        return cls._instance

    def initialize(self):
        self.card_df = None
        self.file_path = "../../../static/data/result/"
        self.card_dict = {}
        self.repository = Repository.Repository()
        self.card_result = None
        self.user_vector = np.array([0.2, 0.2, 0.1, 0, 0.1, 0, 0.1, 0.1, 0.1, 0.1, 0])
        self.member_service = MemberService()

    def calculate_similarity(self, card_vector):

        if norm(self.user_vector) * norm(card_vector) == 0:
            return 0

        return dot(self.user_vector, card_vector) / (norm(self.user_vector) * norm(card_vector))

    def load_card_category(self):
        query = 'SELECT * FROM category'
        # Repository.select_execute(query)
        result = self.repository.select_execute(query)

        # result의 각 원소를 순회하면서 사전에 추가
        for item in result:
            index = item[0]  # 첫 번째 원소를 인덱스로 사용
            vector = np.array(item[1:], dtype=float)  # 나머지 원소를 NumPy 배열로 변환
            self.card_dict[index] = vector  # 인덱스를 키로 하고 NumPy 배열을 값으로 사전에 추가


    # 추천
    def recommend_cards(self, index):
        # similarities = [(i, self.calculate_similarity(self.user_vector, card_vector)) for i, card_vector in
        #                 enumerate(self.card_vectors)]
        # similarities.sort(key=lambda x: x[1], reverse=True)
        top_n = 4
        # 먼저 사용자의 카테고리 부터 계산하자
        self.member_service.getMemberReceipts(index)
        self.user_vector = self.member_service.user_vector / 100.0

        # 유사도를 저장할 리스트 초기화
        similarities = []

        # 각 인덱스에 대해 유사도를 계산하여 리스트에 저장
        for index, vector in self.card_dict.items():
            similarity = self.calculate_similarity(vector)  # 유사도 계산 함수 호출
            similarities.append((index, similarity))

        # 유사도를 내림차순으로 정렬
        similarities.sort(key=lambda x: x[1], reverse=True)
        top_n_similarities = similarities[:top_n]

        cards_data = []
        # Iterate over sorted similarities and construct and execute SQL queries
        for similarity in top_n_similarities:
            category_id = similarity[0]
            query = 'SELECT card_company, card_name, card_top_category, card_img FROM card WHERE category_id = {}'.format(category_id)
            query_result = self.repository.select_execute(query)

            # change it as a json

            for card_tuple in query_result:
                card_data = {
                    'cardCompany': card_tuple[0],
                    'cardName': card_tuple[1],
                    'cardTopCategory': card_tuple[2],
                    'cardImg': card_tuple[3]
                }
                cards_data.append(card_data)

        self.card_result = json.dumps(cards_data, ensure_ascii=False)

    def get_json(self, index):
        self.load_card_category()
        self.recommend_cards(index)

        return self.card_result





