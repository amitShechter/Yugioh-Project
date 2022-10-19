import React, { useState, useEffect } from "react";

// This Component displaying a form to the user in order to update the copies number of a specific card

const EditCardForm = (props) => {
  // The customer card to update
  const [customerCard, setCustomerCard] = useState(props.currentCustomerCard);

  useEffect(() => {
    setCustomerCard(props.currentCustomerCard);
  }, [props]);

  // Handles the input change
  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setCustomerCard({ ...customerCard, [name]: value });
  };

  return (
    // Simple form containing the cardId and copies number text inputs
    <form
      className="edit-card-form"
      onSubmit={(event) => {
        event.preventDefault();

        props.updateCardData(customerCard.cardId, customerCard);
      }}
    >
      {/* Let Edit of only number of copies */}
      <label>Card-ID</label>
      <input
        type="text"
        name="name"
        value={customerCard.cardId}
        onChange={handleInputChange}
      />
      <label>New Number of copies</label>
      <input
        type="number"
        name="numberOfCopies"
        value={customerCard.numberOfCopies}
        onChange={handleInputChange}
      />
      <button className="action-button">Update Card</button>

      {/* By pressing Cancel buttomn, this component will not be displayed.
      Used conditional rendering */}
      <button className="action-button" onClick={() => props.setEditing(false)}>
        Cancel
      </button>
    </form>
  );
};

export default EditCardForm;
