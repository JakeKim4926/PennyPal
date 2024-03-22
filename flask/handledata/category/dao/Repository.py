import mysql.connector
import pandas as pd
import os
from dotenv import load_dotenv

class Repository:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance.initialize()
        return cls._instance

    def initialize(self):
        # .env 파일에서 환경 변수 로드
        load_dotenv()

        # Access to dao
        self.mydb = mysql.connector.connect(
            host=os.getenv("MYSQL_DATASOURCE_ADDRESS"),
            user=os.getenv("MYSQL_DATASOURCE_USERNAME"),
            password=os.getenv("MYSQL_DATASOURCE_PASSWORD"),
            port=os.getenv("MYSQL_DATASOURCE_PORT"),
            database=os.getenv("MYSQL_DATASOURCE_DATABASE"),
        )

        self.file_path = '../../../static/data/result/'

        try:
            self.category_csv = pd.read_csv(self.file_path + 'final_card_data.csv')
        except FileNotFoundError:
            print("파일의 경로를 확인해주세요")

        # insertQuery
        self.insert_queries = []
        # 커서 생성
        self.my_cursor = self.mydb.cursor()

    def create_insert_category_query(self):

        for index, row in self.category_csv.iterrows():

            category_values = ", ".join(map(lambda x: str(float(x)), row[self.category_csv.columns[10:]]))
            query = f"""
                INSERT INTO category (
                    category_shopping,
                    category_culture,
                    category_transportation,
                    category_car,
                    category_food,
                    category_education,
                    category_housing_communication,
                    category_travel,
                    category_medical,
                    category_financial_insurance,
                    category_others
                ) VALUES (
                    {category_values}
                );
            """
            self.insert_queries.append(query)

        self.insert_execute()

    def create_insert_card_query(self):
        for index, row in self.category_csv.iterrows():
            category_id = index + 1  # 인덱스는 0부터 시작하므로 1을 더합니다.

            query = "SELECT * FROM category WHERE category_id={}".format(category_id)

            b_result = self.select_execute(query)

            # Category_id를 찾지 못한 경우 처리
            if not b_result:
                print(f"No category found for category_id {category_id}. Skipping insertion for this row.")
                continue

            query = f"""
                INSERT INTO card (
                    card_type,
                    card_company,
                    card_name,
                    card_benefit_type,
                    card_img,
                    card_top_category,
                    card_category,
                    card_required,
                    card_domestic,
                    card_abroad,
                    category_id
                ) VALUES (
                    '{row['card_type']}',
                    '{row['card_company']}',
                    '{row['card_name']}',
                    '{row['card_benefit_type']}',
                    '{row['card_img']}',
                    '{row['card_top_category']}',
                    '{row['card_category']}',
                    {int(row['card_required'])},
                    {int(row['card_domestic'])},
                    {int(row['card_abroad'])},
                    {category_id}
                );
            """
            self.insert_queries.append(query)
            if (index + 1) % 300 == 0 or len(self.category_csv) - 1:
                self.insert_execute()

    def select_execute(self, query):
        self.my_cursor.execute(query)

        result = self.my_cursor.fetchall()
        if not result:
            print("결과가 비어있습니다.")
            return False

        return result

    def insert_execute(self):
        for query in self.insert_queries:
            try:
                self.my_cursor.execute(query)
            except Exception as e:
                print(f"Error occurred while executing query: {query}")
                print(f"Error details: {str(e)}")
            # else:
            #     print("Query executed successfully.")

        self.mydb.commit()
        self.insert_queries = []
        print('Insert - query executed successfully.')