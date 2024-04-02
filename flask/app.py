from flask import Flask, jsonify, request
from flask_cors import CORS
from handledata.category.service.CardService import CardService
from handledata.category.service.MemberService import MemberService

app = Flask(__name__)
CORS(app, resources={r"/*": {"origins": "*"}})

card_service = CardService()
member_service = MemberService()


@app.route("/api/card/recommend", methods=['GET'])
def recommend_card():
    try:
        # memberIndex 파라미터를 쿼리 스트링으로부터 받음
        member_index = request.args.get('memberIndex', default=None, type=int)

        # memberIndex가 제공되지 않았을 경우의 처리
        if member_index is None:
            return jsonify({"error": "No memberIndex provided"}), 400

        # Assuming card_service.get_card_by_similarity() is the method that interacts with the database
        result = card_service.get_card_by_similarity(member_index)
        return result
    except Exception as e:
        # Handle the exception when unable to connect to the database
        error_message = "[Error] can't connect mysql or error happened during extract recommendation: {}".format(e)
        return jsonify({"error": error_message}), 500


@app.route("/api/member/mostCategory", methods=['GET'])
def get_most_category():
    try:
        # memberIndex 파라미터를 쿼리 스트링으로부터 받음
        member_index = request.args.get('memberIndex', default=None, type=int)

        # memberIndex가 제공되지 않았을 경우의 처리
        if member_index is None:
            return jsonify({"error": "No memberIndex provided"}), 400

        # Assuming card_service.get_card_by_similarity() is the method that interacts with the database
        result = member_service.getMemberBestCategory(member_index)

        return jsonify({"memberMostCategory": result}), 200
    except Exception as e:
        # Handle the exception when unable to connect to the database
        error_message = "[Error] can't connect mysql or error happened during extract recommendation: {}".format(e)
        return jsonify({"error": error_message}), 500


@app.route("/")
def default_message():
    return "Success to load flask"


if __name__ == '__main__':
    app.run()

    # SSL 설정 ?
    # app.run(debug=True, host='0.0.0.0', port=443, ssl_context=('path/to/certificate.pem', 'path/to/private.key'))
    # print("hello python")
