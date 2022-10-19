import http from "../http-common";

// This class goal is to send Http requests to the server
// This class focus on the market cards operations
// customerUserName in the URL allows to track which customer data we need maintain in the app

class MarketService {
  // Get all cards data from the marketplace card list
  getAllCardsInMarketPlace(customerUserName) {
    return http.get(`/marketplace/${customerUserName}`);
  }

  // Get a specific card data from the marketplace card list by giving a card ID
  // cardId is the required card ID
  getCardInMarketPlaceById(customerUserName, data) {
    return http.get(`/marketplace-card/${customerUserName}`, data);
  }

  // Add a new card to the customer card list
  // customerUserName is the identifier, and data is the new card data to add
  addCardToCustomerListFromMarket(customerUserName, data) {
    return http.post(`/marketplace-add-card/${customerUserName}`, data);
  }
}
export default new MarketService();