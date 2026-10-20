from flask import Flask, request, jsonify
from flask_cors import CORS
from sklearn.linear_model import LinearRegression
import numpy as np

app = Flask(__name__)
CORS(app)

# Sample historical daily sales
days = np.array([1, 2, 3, 4, 5, 6]).reshape(-1, 1)
sales = np.array([2, 3, 2, 4, 3, 4])

model = LinearRegression()
model.fit(days, sales)


@app.route("/predict", methods=["POST"])
def predict():
    data = request.get_json()

    current_stock = float(data["current_stock"])

    # Estimate daily sales for the next day
    next_day = np.array([[7]])
    daily_sales = max(0, float(model.predict(next_day)[0]))

    # Estimate stock remaining after 7 days
    predicted_stock = max(0, current_stock - daily_sales * 7)

    recommendation = (
        "Restock needed"
        if predicted_stock < 10
        else "Stock sufficient"
    )

    return jsonify({
        "predicted_daily_sales": round(daily_sales, 2),
        "predicted_stock": round(predicted_stock, 2),
        "recommendation": recommendation
    })


if __name__ == "__main__":
    app.run(port=5000, debug=True)