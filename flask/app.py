from flask import Flask, jsonify
from handledata.category.service.CardService import CardService

app = Flask(__name__)

card_service = CardService()
@app.route("/api/card/recommend", methods=['GET'])
def recommend_card():
    try:
        # Assuming card_service.get_card_by_similarity() is the method that interacts with the database
        result = card_service.get_card_by_similarity()
        return result
    except Exception as e:
        # Handle the exception when unable to connect to the database
        error_message = "[Error] can't connect mysql or error happened during extract recommendation: {}".format(e)
        return jsonify({"error": error_message}), 500

if __name__ == '__main__':
    app.run()

    # SSL 설정 ?
    # app.run(debug=True, host='0.0.0.0', port=443, ssl_context=('path/to/certificate.pem', 'path/to/private.key'))
    # print("hello python")