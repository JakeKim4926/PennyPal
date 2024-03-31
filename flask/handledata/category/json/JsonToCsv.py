import pandas as pd
import numpy as np
import json
import re

def json_to_dataframe(strPath):
    with open(strPath, 'r', encoding='utf-8') as f:
        data = json.load(f)

    # 필요한 정보 추출하여 리스트에 저장
    extracted_data_for_view = []
    extracted_data_for_db = []

    for item in data['data']:
        card_domestic_str = re.search(r'국내전용 \[(\d+(,\d+)?)\]원', item['annual_fee_basic'])
        card_abroad_str = re.search(r'해외겸용 \[(\d+(,\d+)?)\]원', item['annual_fee_basic'])

        card_domestic_price = 0
        card_abroad_price = 0
        if card_domestic_str:
            card_domestic_price = int(card_domestic_str.group(1).replace(',', ''))
        if card_abroad_str:
            card_abroad_price = int(card_abroad_str.group(1).replace(',', ''))


        # 데이터 추출 for showing
        extracted_data_for_view.append({
            '신용/체크': item['cate_txt'],
            '카드사': item['corp_txt'],
            '카드 이름': item['name'],
            '카드 타입': item['c_type_txt'],
            '카드 이미지 URL': item['card_img']['url'],
            'Top3 혜택': ', '.join([benefit['title'] for benefit in item['top_benefit']]),
            '혜택 종류': ', '.join([benefit['title'] for benefit in item['search_benefit']]),
            '전월 금액': item['pre_month_money'],
            '연회비-국내전용': card_domestic_price,
            '연회비-해외겸용': card_abroad_price,
        })

        # 데이터 추출 for inserting db
        extracted_data_for_db.append({
            'card_type': item['cate_txt'],
            'card_company': item['corp_txt'],
            'card_name': item['name'],
            'card_benefit_type': item['c_type_txt'],
            'card_img': item['card_img']['url'],
            'card_top_category': ', '.join([benefit['title'] for benefit in item['top_benefit']]),
            'card_category': ', '.join([benefit['title'] for benefit in item['search_benefit']]),
            'card_required': item['pre_month_money'],
            'card_domestic': card_domestic_price,
            'card_abroad': card_abroad_price,
        })

    # DataFrame 생성
    df_view = pd.DataFrame(extracted_data_for_view)
    df_db = pd.DataFrame(extracted_data_for_db)

    df_db.to_csv('../static/data/result/card_db.csv', index=False, encoding="utf-8")

    df_view.to_excel('../static/data/result/card_view.xlsx', index=False)
    df_db.to_excel('../static/data/result/card_db.xlsx', index=False)


if __name__ == '__main__':
    # you can type path here
    json_file = '../../../static/data/origin/credit_card.json'

    json_to_dataframe(json_file)
    print("done")