import pandas as pd

def SetCardCategory():
    file_path = "../static/data/result/card_db.csv"

    # DataFrame으로 데이터 불러오기
    data = pd.read_csv(file_path)

    # 중복을 제거한 카테고리를 저장할 집합
    unique_categories = set()

    # 각 행의 card_category 값을 확인하여 중복을 제거한 후 unique_categories 집합에 추가
    for category_row in data['card_category']:
        if pd.isnull(category_row):  # 빈 값인 경우 건너뜀
            continue
        # 만약 값이 숫자라면 문자열로 변환하여 처리
        if isinstance(category_row, float):
            category_row = str(category_row)
        categories = category_row.split(',')
        for category in categories:
            category = category.strip()  # 공백 제거
            if category != '':
                unique_categories.add(category)
    # 중복을 제거하고 정렬된 카테고리 리스트 출력
    print("before add top category")
    print(unique_categories)
    print("=======================")

    for category_row in data['card_top_category']:
        if pd.isnull(category_row):  # 빈 값인 경우 건너뜀
            continue
        # 만약 값이 숫자라면 문자열로 변환하여 처리
        if isinstance(category_row, float):
            category_row = str(category_row)
        categories = category_row.split(',')
        for category in categories:
            category = category.strip()  # 공백 제거
            if category != '':
                unique_categories.add(category)
    print("after add top category")
    print(unique_categories)
    print("=======================")

if __name__ == '__main__':
    SetCardCategory()