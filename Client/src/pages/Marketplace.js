import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import MarketService from "../services/MarketService";
import MarketplaceTableComponent from "../components/MarketplaceTableComponent";
import CustomerCardsDataService from "../services/CustomerCardsDataService";
import "../components/cards-style.css";

// This is the Marketplace page for the user
function Marketplace() {
  // Extracting the customerUserName from the URL
  const { customerUserName } = useParams();
  const navigate = useNavigate();
  // marketCards         - Contains a list of all cards in the marketplace
  // customerCards       - Contains a list of all cards (A card collection) which this customer holds in his possession
  // addToCustomerFlag   - An indicator for adding a card from the marketplace to this customer card collection
  const [marketCards, setMarketCards] = useState([]);
  const [customerCards, setCustomerCards] = useState([]);
  const [addToCustomerFlag, setAddToCustomerFlag] = useState(false);

  // Using the following service to fetch a list of all cards in the marketplace from the server
  useEffect(() => {
    MarketService.getAllCardsInMarketPlace(customerUserName)
      .then((res) => {
        setMarketCards(res.data);
      })
      .catch((err) => {
        console.log("Error");
      });
    // Gathering data of all current user cards if he would wish to add another one to collection
    CustomerCardsDataService.getAllCustomerCards(customerUserName)
      .then((res) => {
        setCustomerCards(res.data);
      })
      .catch((err) => {
        console.log("Error");
      });
  }, []);

  // Handler for adding a specific card from the marketplace into this customer card's list.
  // Used when clicking on "Add to my list"
  const addNewCardToCustomerFromMarket = (newCard) => {
    MarketService.addCardToCustomerListFromMarket(customerUserName, newCard)
      .then((response) => {
        alert("Added to your collection");
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
    // Updating this customer cards list after adding the new card
    setCustomerCards([...customerCards, newCard]);
  };

  return (
    <div>
      {/* Displaying the cards list as a table component */}
      <MarketplaceTableComponent
        marketCards={marketCards}
        addNewCardToCustomerFromMarket={addNewCardToCustomerFromMarket}
        addToCustomerFlag={addToCustomerFlag}
        setAddToCustomerFlag={setAddToCustomerFlag}
      />
      {/* When clicking this button, move the customer to his own profile page */}
      <div className="button-container">
        <button
          className="action-button"
          onClick={() => navigate("/profile/" + customerUserName)}
        >
          Move to profile
        </button>
      </div>
    </div>
  );
}

export default Marketplace;
