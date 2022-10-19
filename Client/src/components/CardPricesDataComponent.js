import React, { useState, useEffect } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import CardsPricesService from "../services/CardsPricesService";
import { Line } from "react-chartjs-2";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  LineElement,
  Legend,
  CategoryScale,
  LinearScale,
  PointElement,
  Filler,
} from "chart.js";
// Register the plugins globally
ChartJS.register(
  Title,
  Tooltip,
  LineElement,
  Legend,
  CategoryScale,
  LinearScale,
  PointElement,
  Filler
);

// This component displaying the data of each card and his price history as function of time
// Starting from 01/05/22 to 31/08/22
function CardPricesDataComponent() {
  const { cardId } = useParams();
  const navigate = useNavigate();
  const location = useLocation();
  const [customerCard, setCustomerCard] = useState(
    location.state.cardToDisplay
  );
  // Creating array to hold all dates in the period stated above
  const expectedValueArr = Array(123).fill(
    location.state.cardToDisplay.cardExpectedPrice
  );

  // Fetching card data by sending a request to the server passing the cardId
  useEffect(() => {
    CardsPricesService.getCardDataById(cardId)
      .then((response) => {
        setCustomerCard(response.data);
      })
      .catch((err) => {
        console.log("Error");
      });
  }, []);

  // Creating the data for the graph
  const data = {
    labels: [
      "May-1st Week",
      "May-2nd Week",
      "May-3rd Week",
      "May-4th Week",
      "June-1st Week",
      "June-2nd Week",
      "June-3rd Week",
      "June-4th Week",
      "July-1st Week",
      "July-2nd Week",
      "July-3rd Week",
      "July-4th Week",
      "Aug-1st Week",
      "Aug-2nd Week",
      "Aug-3rd Week",
      "Aug-4th Week",
    ],
    datasets: [
      {
        label: "Real Prices Dataset",
        // The price history of the current card
        data: location.state.cardToDisplay.cardPricesArray,
        backgroundColor: "white",
        borderColor: "black",
        tension: 0.4,
        fill: false,
        pointStyle: "rect",
        pointBorderColor: "blue",
        pointBackgroundColor: "#fff",
        showLine: true,
      },
      {
        label: "Price calculated by Models",
        // The price that I was expecting using my own personal models
        data: expectedValueArr,
        backgroundColor: "white",
        borderColor: "red",
        tension: 0.4,
        fill: false,
        pointStyle: "rect",
        pointBorderColor: "blue",
        pointBackgroundColor: "#fff",
        showLine: true,
      },
    ],
  };

  // Setting readable scales to the graph by taking the maximal value
  const options = {
    scales: {
      y: {
        min: 0,
        max:
          1.2 *
          Math.max(
            location.state.cardToDisplay.cardMaxPrice,
            location.state.cardToDisplay.cardExpectedPrice
          ),
      },
    },
  };

  return (
    // Displaying the data and the graph
    <div className="cards-data-container">
      <div>
        <div class="container__text">
          <h1>{customerCard.cardName}</h1>
          <div />
        </div>
        <div className="card-data-categories-container">
          <div className="card-data-image-contianer">
            <img
              alt="Card"
              className="card-data-image"
              src={require(`../YgoImages/${customerCard.cardImageSrc}`)}
            />
          </div>
          <div className="data-container-text">
            <div class="container__text__upper">
              <div class="container__text__upper_data">
                <h2>Card-ID</h2>
                <p>{customerCard.cardId}</p>
              </div>
              <div class="container__text__upper_data">
                <h2>Card-Rarity</h2>
                <p>{customerCard.cardRarity}</p>
              </div>
              <div class="container__text__upper_data">
                <h2>Expected Price by Metrics</h2>
                <p>{customerCard.cardExpectedPrice}$</p>
              </div>
            </div>
            <div class="container__text__lower">
              <div class="container__text__lower_data">
                <h2>Average Price</h2>
                <p>{customerCard.cardAvgPrice}$</p>
              </div>
              <div class="container__text__lower_data">
                <h2>Highest Price</h2>
                <p>{customerCard.cardMaxPrice}$</p>
              </div>
              <div class="container__text__lower_data">
                <h2>Lowest Price</h2>
                <p>{customerCard.cardMinPrice}$</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div>
        <div
          className="line-chart-container"
          style={{ width: "800px", height: "400px" }}
        >
          <Line data={data} options={options} />
        </div>
      </div>
      <div className="button-container">
        {/* Moving to customer profile page button */}
        <button
          className="action-button"
          onClick={() => navigate("/profile/" + location.state.userName)}
        >
          Move to profile
        </button>
      </div>


      {/* Moving to marketplace page button */}
      <div className="button-container">
        <button
          className="action-button"
          onClick={() => navigate("/marketplace/" + location.state.userName)}
        >
          Move to marketplace
        </button>
      </div>
    </div>
  );
}
export default CardPricesDataComponent;
