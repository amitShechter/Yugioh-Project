import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import "./cards-style.css";

function MarketplaceTableComponent(props) {
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
            <th className="card-table-header-cell">Actions</th>
          </tr>
        </thead>
        <tbody>
          {/* Mapping each card getting from the response as a table row */}
          {props.marketCards.length > 0 ? (
            props.marketCards.map((marketCard) => (
              <tr key={marketCard.cardId} className="card-table-row">
                <td>
                  <img
                    className="customer-card-image"
                    src={require(`../YgoImages/${marketCard.cardImageSrc}`)}
                  />
                </td>
                <td className="customer-card-table-regular-cell">
                  {marketCard.cardId}
                </td>
                <td className="customer-card-table-regular-cell">
                  {marketCard.cardName}
                </td>
                <td className="customer-card-table-regular-cell">
                  {marketCard.cardRarity}
                </td>
                <td>
                  {/* Adding a card from the marketplace to the customer card list (in the profile) */}
                  <button
                    className="action-button"
                    onClick={() => {
                      props.addNewCardToCustomerFromMarket(marketCard);
                    }}
                  >
                    Add to my collection
                  </button>

                  {/* Displaying price history of the card */}
                  <button
                    onClick={() => {
                      navigate("/card-prices/" + marketCard.cardId, {
                        state: {
                          userName: customerUserName,
                          cardToDisplay: marketCard,
                        },
                      });
                    }}
                    className="action-button"
                  >
                    Show Prices
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

export default MarketplaceTableComponent;
