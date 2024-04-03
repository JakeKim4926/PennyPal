import json

import numpy as np

from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing.sequence import pad_sequences
from tensorflow.keras.preprocessing.text import tokenizer_from_json

class Run():
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance.initialize()
        return cls._instance

    def initialize(self):
        # 모델을 로드합니다.
        self.model = load_model('static/data/result/model_path.keras')

        # 저장된 토크나이저 불러오기
        with open('static/data/result/tokenizer.json') as f:
            json_data = json.load(f)
            self.tokenizer = tokenizer_from_json(json_data)

        with open('static/data/result/max_len.json', 'r', encoding='utf-8') as f:
            max_len_data = json.load(f)
            self.max_len = max_len_data['max_len']

        # 카테고리를 숫자로 변환
        self.category_to_number = {
            "쇼핑": 0,
            "여가/문화": 1,
            "교통": 2,
            "자동차": 3,
            "푸드": 4,
            "교육": 5,
            "주거/통신": 6,
            "여행/항공": 7,
            "의료": 8,
            "금융/보험": 9,
            "기타": 10
        }

    def predict(self, txt):
        new_transactions = [txt]
        new_sequences = self.tokenizer.texts_to_sequences(new_transactions)

        new_X = pad_sequences(new_sequences, maxlen=self.max_len)
        predictions = self.model.predict(new_X)

        predicted_class_number = np.argmax(predictions)
        number_to_category = {v: k for k, v in self.category_to_number.items()}
        predicted_class = number_to_category[predicted_class_number]
        # print("내역 : " + txt)
        # print('예측된 카테고리:', predicted_class)
        return predicted_class
#
# if __name__ == "__main__":
#     run = Run()
#     run.predict('SKT')