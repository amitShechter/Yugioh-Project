import http from "../http-common";

// This class goal is to send Http requests to the server
// This class focus on the customer personal cards operations

class CustomerCardsDataService {
  // Adding a new card to the customer card list
  // customerUserName is the identifier, and data is the card data
  createCustomerCard(customerUserName, data) {
    return http.patch(`/customer-cards-add/${customerUserName}`, data);
  }

  // Update the amount of copies of a specific card from the customer card list
  // customerUserName is the identifier, and data is the card data
  updateCustomerCardData(customerUserName, data) {
    return http.patch(`/customer-cards-update/${customerUserName}`, data);
  }

  // Get all cards data from the customer card list
  getAllCustomerCards(customerUserName) {
    return http.get(`/customer-cards/${customerUserName}`);
  }

  // Get a specific card data from the customer card list by giving a card ID
  // customerUserName is the identifier, and cardId is the required card ID
  getCustomerCardById(customerUserName, cardId) {
    return http.get(`/customers-card/${customerUserName}`, cardId);
  }

  // Delete a specific card from the customer card list
  // customerUserName is the identifier, and data is the card to delete
  deleteCustomerCard(customerUserName, data) {
    return http.patch(`/customer-card-remove/${customerUserName}`, data);
  }

  // Delete all cards from the customer card list
  deleteAllCustomerCards(customerUserName) {
    return http.patch(`/customer-cards-remove/${customerUserName}`);
  }
}
export default new CustomerCardsDataService();
