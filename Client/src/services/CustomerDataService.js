import http from "../http-common";

// This class goal is to send Http requests to the server
// This class focus on the customer general operations

class CustomterDataService {
  // Create a new customer user
  // data is the customer data
  createCustomer(data) {
    return http.post("/customers", data);
  }

  // Get all customers data from the database repository
  getAllCustomers() {
    return http.get("/customers");
  }

  // Get a specific customer data (with the required username) from the database repository
  // customerUserName is the identifier
  getCustomer(customerUserName) {
    return http.get(`/customers/${customerUserName}`);
  }

  // Update the customer personal info (Will be implemented maybe in the future)
  // customerUserName is the identifier, and data is the card data
  // updateCustomerData(customerUserName, data) {
  //   return http.patch(`/customers/${customerUserName}`, data);
  // }

  // Delete a specific customer from the database repository
  // customerUserName is the customer username to delete
  deleteCustomer(customerUserName) {
    return http.delete(`/customers/${customerUserName}`);
  }

  // Delete all customers from the database repository
  deleteAllCustomers() {
    return http.delete("/customers");
  }
}

export default new CustomterDataService();
