import json

import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from tensorflow.keras.layers import LSTM, Dense, Embedding
from tensorflow.keras.models import Sequential
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.preprocessing.sequence import pad_sequences
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.text import tokenizer_from_json
from tensorflow.keras.utils import to_categorical

# 데이터 로드
data = pd.read_csv('data.csv', encoding='cp949')

# 토큰화 및 시퀀스 변환
tokenizer = Tokenizer()
tokenizer.fit_on_texts(data['transaction_details'])
sequences = tokenizer.texts_to_sequences(data['transaction_details'])

# 토크나이저 저장
tokenizer_json = tokenizer.to_json()
with open('../../../static/data/result/tokenizer.json', 'w', encoding='utf-8') as f:
    f.write(json.dumps(tokenizer_json, ensure_ascii=False))

# 패딩
max_len = max(len(x) for x in sequences)

# max_len 값을 JSON 파일로 저장
max_len_data = {'max_len': max_len}
with open('../../../static/data/result/max_len.json', 'w', encoding='utf-8') as f:
    json.dump(max_len_data, f, ensure_ascii=False)

X = pad_sequences(sequences, maxlen=max_len)

# 카테고리를 숫자로 변환
category_to_number = {
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
data['category_number'] = data['category'].map(category_to_number)

# 레이블 원-핫 인코딩
Y = to_categorical(data['category_number'])

# 훈련/테스트 데이터셋 분리
X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.2)

# 모델 생성
model = Sequential()
model.add(Embedding(input_dim=len(tokenizer.word_index) + 1, output_dim=64, input_length=max_len))
model.add(LSTM(64))
model.add(Dense(len(category_to_number), activation='softmax'))

optimizer = Adam(learning_rate=0.001)  # 학습률 조정
# 모델 컴파일
model.compile(loss='categorical_crossentropy', optimizer=optimizer, metrics=['accuracy'])

# 모델 학습
model.fit(X_train, y_train, epochs=300, validation_split=0.1)

# 모델 평가
test_loss, test_acc = model.evaluate(X_test, y_test)
print('Test accuracy:', test_acc)

# 저장된 토크나이저 불러오기
with open('../../../static/data/result/tokenizer.json') as f:
    json_data = json.load(f)
    tokenizer = tokenizer_from_json(json_data)

# 새로운 데이터에 대해 예측
new_transactions = ['롯데-종진유통']
new_sequences = tokenizer.texts_to_sequences(new_transactions)
new_X = pad_sequences(new_sequences, maxlen=max_len)
predictions = model.predict(new_X)

predicted_class_number = np.argmax(predictions)
number_to_category = {v: k for k, v in category_to_number.items()}
predicted_class = number_to_category[predicted_class_number]
print('예측된 카테고리:', predicted_class)

model.save('model_path.keras')
