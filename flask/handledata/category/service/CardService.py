from handledata.category.service.Similarity import Similarity
class CardService:
    _instance = None
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance.initialize()
        return cls._instance

    def initialize(self):
        self.similarity = Similarity()

    def get_card_by_similarity(self, index):
        return self.similarity.get_json(index)


