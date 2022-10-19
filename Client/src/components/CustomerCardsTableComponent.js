import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import "./cards-style.css";

function CustomerCardsTableComponent(props) {
  const navigate = useNavigate();
  const { customerUserName } = useParams();

  return (
    // Simple table to display all cards data
    <div>
      <table className="customer-cards-table-container">
        <thead>
          <tr>
            <th className="card-table-header-cell">Card Picture</th>
            <th className="card-table-header-cell">Set ID</th>
            <th className="card-table-header-cell">Name</th>
            <th className="card-table-header-cell">Rarity</th>
            <th className="card-table-header-cell">Copies Num.</th>
            <th className="card-table-header-cell">Actions</th>
          </tr>
        </thead>
        <tbody>
          {/* Mapping each card getting from the response as a table row */}
          {props.customerCards.length > 0 ? (
            props.customerCards.map((customerCard) => (
              <tr key={customerCard.cardId} className="card-table-row">
                <td>
                  <img
                    className="customer-card-image"
                    src={require(`../YgoImages/${customerCard.cardImageSrc}`)}
                  />
                </td>
                <td className="customer-card-table-regular-cell">
                  {customerCard.cardId}
                </td>
                <td className="customer-card-table-regular-cell">
                  {customerCard.cardName}
                </td>
                <td className="customer-card-table-regular-cell">
                  {customerCard.cardRarity}
                </td>
                <td className="customer-card-table-regular-cell">
                  {customerCard.numberOfCopies}
                </td>
                <td className="profile-action-button-container">
                  {/* Delete the card from the customer cards list */}
                  <button
                    onClick={() => props.deleteCard(customerCard)}
                    className="action-button"
                  >
                    Delete
                  </button>

                  {/* Updating the copies number of this card */}
                  <button
                    onClick={() => props.editCardCopies(customerCard)}
                    className="action-button"
                  >
                    Update
                  </button>

                  {/* Displaying price history of the card */}
                  <button
                    onClick={() => {
                      navigate("/card-prices/" + customerCard.cardId, {
                        state: {
                          userName: customerUserName,
                          cardToDisplay: customerCard,
                        },
                      });
                    }}
                    className="action-button"
                  >
                    Prices
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan={3}>List is empty. Add new card!</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default CustomerCardsTableComponent;
