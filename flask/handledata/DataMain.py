import numpy as np
from numpy import dot
from numpy.linalg import norm

# 카드 데이터 및 유저 데이터 전처리

# 카테고리 속성 값 할당

# 유저의 카테고리 값 설정

# 유사도 계산
def calculate_similarity(user_vector, card_vector):
    return dot(user_vector, card_vector) / (norm(user_vector) * norm(card_vector))

# 추천
def recommend_cards(user_vector, card_vectors, top_n=4):
    similarities = [(i, calculate_similarity(user_vector, card_vector)) for i, card_vector in enumerate(card_vectors)]
    similarities.sort(key=lambda x: x[1], reverse=True)
    return similarities[:top_n]

if __name__ == '__main__':
    # 유저의 카테고리 값과 카드의 속성 값으로 유사도 계산 및 추천 수행
    user_vector = np.array([0.2, 0.1, 0.1, 0.05, 0.1, 0.05, 0.1, 0.1, 0.1, 0.1])  # 예시로 임의의 유저 카테고리 값
    card_vectors = np.array([[0.2, 0.1, 0.1, 0.05, 0.1, 0.05, 0.1, 0.1, 0.1, 0.1] ,  # 예시로 임의의 카드 카테고리 값
                             [0.15, 0.2, 0.1, 0.1, 0.1, 0.05, 0.05, 0.1, 0.05, 0.1],
                             [0.1, 0.05, 0.2, 0.1, 0.1, 0.1, 0.05, 0.05, 0.1, 0.15],
                             [0.1, 0.1, 0.1, 0.05, 0.05, 0.1, 0.1, 0.15, 0.15, 0.1]])
    recommended_cards = recommend_cards(user_vector, card_vectors)
    print(recommended_cards)