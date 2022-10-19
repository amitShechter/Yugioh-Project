import http from "../http-common";

// This class goal is to send Http GET requests to the server
// This class focus on getting data and prices history of cards

class CardsPricesService {
  getCardPricesHistoryById(cardId) {
    return http.get(`/get-prices/${cardId}`);
  }
  getCardDataById(cardId) {
    return http.get(`/card-data/${cardId}`);
  }
}
export default new CardsPricesService();
