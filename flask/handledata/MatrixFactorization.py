import numpy as np
from tqdm import trange
from sklearn.metrics import mean_squared_error

class MatrixFactorization:
    # --k 300 --lr 0.01 --beta 0.01 --n_epochs 100

    def __init__(self, sparse_matrix, K, lr, beta, n_epochs):
        """
        Arguments
        - sparse_matrix : user-item rating matrix
        - K (int)       : number of latent dimensions
        - lr (float) : learning rate
        - beta (float)  : regularization parameter
        - n_epochs (int) : Num of Iteration
        """

        self.pred_matrix = None
        self.card_bias = None
        self.user = None
        self.card = None
        self.total_mean = None
        self.user_bias = None

        # convert ndArray
        self.sparse_matrix = sparse_matrix.fillna(0).to_numpy()
        self.card_n, self.user_n = sparse_matrix.shape
        self.K = K
        self.lr = lr
        self.beta = beta
        self.n_epochs = n_epochs

    def train(self):
        # Initialize user and item latent feature matrice
        self.card = np.random.normal(scale=1. / self.K, size=(self.card_n, self.K))
        self.user = np.random.normal(scale=1. / self.K, size=(self.user_n, self.K))

        # Init biases
        self.card_bias = np.zeros(self.card_n)
        self.user_bias = np.zeros(self.user_n)
        self.total_mean = np.mean(self.sparse_matrix[np.where(self.sparse_matrix != 0)])

        # Create training Samples
        idx, jdx = self.sparse_matrix.nonzero()
        samples = list(zip(idx, jdx))

        # Train start
        training_log = []
        progress = trange(self.n_epochs, desc="train-rmse: nan")
        for idx in progress:
            np.random.shuffle(samples)

            # Gradient Descent
            for c, u in samples:
                # get error
                y = self.sparse_matrix[c, u]
                pred = self.predict(c, u)
                error = y - pred
                # update bias
                self.card_bias[c] += self.lr * (error - self.beta * self.card_bias[c])
                self.user_bias[u] += self.lr * (error - self.beta * self.user_bias[u])
                # update latent factors
                card_i = self.card[c, :][:]
                self.card[c, :] += self.lr * (error * self.user[u, :] - self.beta * self.card[c, :])
                self.user[u, :] += self.lr * (error * card_i - self.beta * self.user[u, :])

            # Evaluate Current Epoch
            rmse = self.evaluate()
            progress.set_description("train-rmse: %0.6f" % rmse)
            progress.refresh()
            training_log.append((idx, rmse))

        # Train end, save the matrix
        self.pred_matrix = self.get_pred_matrix()

    def predict(self, c, u):
        """
        :param c: card index
        :param u: user index
        :return: predicted rating
        """
        # set the hypothesis function
        return (
                self.total_mean
                + self.card_bias[c]
                + self.user_bias[u]
                + self.user[u, :].dot(self.card[c, :].T)
        )

    def evaluate(self):
        idx, jdx = self.sparse_matrix.to_numpy().nonzero()
        origin, expect = [], []

        for i, j in zip(idx, jdx):                          # card i 에대한 user j의 유사도
            origin.append(self.sparse_matrix.iloc[i,j])     # 원본 행렬
            expect.append(self.pred_matrix.iloc[i,j])       # 예측 행렬

        error = mean_squared_error(origin, expect)
        return np.sqrt(error)

    def get_pred_matrix(self):
        pred_matrix = np.zeros_like(self.sparse_matrix)
        for c in range(self.card_n):
            for u in range(self.user_n):
                pred_matrix[c, u] = self.predict(c, u)
        return pred_matrix

