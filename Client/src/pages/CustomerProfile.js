import React, { useState, useEffect, Fragment } from "react";
import { useParams, useNavigate } from "react-router-dom";
import CustomerCardsTableComponent from "../components/CustomerCardsTableComponent";
import EditCardForm from "../components/EditCardForm";
import CustomerCardsDataService from "../services/CustomerCardsDataService";

// This is the Login page for the user
function CustomerProfile() {
  // Some initial card data utilizing when declaring for a current customer card
  const initialCardData = {
    cardId: "",
    cardName: "",
    cardRarity: "",
    numberOfCopies: 0,
    cardPricesArray: [],
    cardImageSrc: "",
    cardAvgPrice: 0,
    cardMaxPrice: 0,
    cardMinPrice: 0,
    cardExpectedPrice: 0,
  };

  // customerCards       - Contains a list of all cards (A card collection) which this
  //                       customer holds in his possession
  // currentCustomerCard - Contain the data of a specific card from the above list.
  // editing             - An indicator for enabling the user to update amount of copies
  //                       from a currentCustomerCard
  const navigate = useNavigate();
  const { customerUserName } = useParams();
  const [customerCards, setCustomerCards] = useState([]);
  const [currentCustomerCard, setCurrentCustomerCard] =
    useState(initialCardData);
  const [editing, setEditing] = useState(false);

  // Using the following service to fetch a list of all cards of this customer from the server
  useEffect(() => {
    CustomerCardsDataService.getAllCustomerCards(customerUserName)
      .then((res) => {
        // console.log(res.data);
        setCustomerCards(res.data);
      })
      .catch((err) => {
        console.log("Error");
      });
  }, []);

  // Handler for editing number of copies for specific card in this customer card's list.
  // Used when clicking on "Update"
  const editCardCopies = (customerCard) => {
    // setting editing to true in order to show EditFormComponent
    setEditing(true);
    setCurrentCustomerCard({
      cardId: customerCard.cardId,
      cardName: customerCard.cardName,
      cardRarity: customerCard.cardRarity,
      numberOfCopies: customerCard.numberOfCopies,
      cardPricesArray: customerCard.cardPricesArray,
      cardImageSrc: customerCard.cardImageSrc,
    });
  };

  // Handler for sending the edited data
  // Used when clicking on "Update Card"
  const updateCardData = (cardId, updatedCard) => {
    // Using the following service to send the edited data to the server
    CustomerCardsDataService.updateCustomerCardData(
      customerUserName,
      updatedCard
    )
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
    setEditing(false);
    setCustomerCards(
      customerCards.map((customerCard) =>
        customerCard.cardId === cardId ? updatedCard : customerCard
      )
    );
  };

  // Handler for deleting a specific card from tgus customer card's list
  const deleteCard = (cardToDelete) => {
    CustomerCardsDataService.deleteCustomerCard(customerUserName, cardToDelete)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
    // Setting editing to false, to reset the component.
    setEditing(false);
    // Updating the card list in profile to show all but the deleted one
    setCustomerCards(
      customerCards.filter(
        (customerCard) => customerCard.cardId !== cardToDelete.cardId
      )
    );
  };

  return (
    <div className="container">
      <h1 className="h1">My Card Collection!</h1>
      <div>
        {/* Displaying the cards list as a table component */}
        <CustomerCardsTableComponent
          customerCards={customerCards}
          editCardCopies={editCardCopies}
          deleteCard={deleteCard}
        />
        <br /> <br />
        <div className="flex-large">
          {/* If editing = false, display the regular profile page
          Otherwise, display the EditFormComponent  in order to enable the user
           edit his number of copies of a specific card */}
          {editing ? (
            <Fragment>
              <h2>Edit Number of copies</h2>
              <EditCardForm
                className="edit-card-form"
                editing={editing}
                setEditing={setEditing}
                currentCustomerCard={currentCustomerCard} // curentUser
                updateCardData={updateCardData} // updateUser
              />
            </Fragment>
          ) : (
            <p />
          )}
        </div>
      </div>
      {/* When clicking this button, move the customer to the Marketplace page */}
      <div className="button-container">
        <button
          className="action-button"
          onClick={() => navigate("/marketplace/" + customerUserName)}
        >
          Move to marketplace
        </button>
      </div>
    </div>
  );
}

export default CustomerProfile;
