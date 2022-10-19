package YugiohProject.controller;

import YugiohProject.model.Card;
import YugiohProject.model.Customer;
import YugiohProject.repository.CardRepository;
import YugiohProject.repository.CustomerRepository;
import YugiohProject.requests.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
This class goal is maintaining requests to the following endpoints,
dealing with the customer profile page/operations.
These endpoints focus on CRUD (create, read, update, delete) operations of the as well as more functionalities
Most of these endpoints would contain a unique customerUserName which allow the app to keep track with
the required data need to retrieve and to send (from and to) the server & DB
 */

@CrossOrigin(origins = "http://localhost:3000/") //Consider change this
@RestController
@RequestMapping("/api") //Consider change this
public class CustomerController {

    // **************************************************
    // Fields
    // **************************************************

    /**
     * Contains a reference to the repository that store all customers data.
     */
    @Autowired
    CustomerRepository customerRepository;

    /**
     * Contains a reference to the repository that store all cards data.
     */
    @Autowired
    CardRepository cardRepository;

    // **************************************************
    // Public Methods
    // **************************************************

    /**
     * This method takes as parameters a customerRequest (containing the customer information).
     * The method adds the customer from the request to the customer repository.
     *
     * @param customerRequest - A request containing the customer information
     * @return an HttpStatus.CREATED if created successfully and save the new customer to the repository,
     */
    @RequestMapping(path = "/customers", method = RequestMethod.POST)
    public ResponseEntity<Customer> addNewCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerUserName(customerRequest.getCustomerUserName());
        newCustomer.setCustomerUserPassword(customerRequest.getCustomerUserPassword());
        newCustomer.setCustomerName(customerRequest.getCustomerName());
        newCustomer.setCustomerCardsInPossessionList(customerRequest.getCustomerCardsInPossessionList());
        customerRepository.save(newCustomer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }


    /**
     * Returns all customers data from the repository.
     *
     * @return All customers data from the repository
     */
    @RequestMapping(path = "/customers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }


    /**
     * This method takes as parameter a customerUserName
     * Returns the data of the customer with the required customer username.
     *
     * @param customerUserName - A String containing the current customer username
     * @return The data of the customer with the required customer username.
     */
    @RequestMapping(path = "/customers/{customerUserName}", method = RequestMethod.GET)
    public ResponseEntity getCustomerByUserName(@PathVariable String customerUserName) {
        Optional<Customer> customer = customerRepository.findById(customerUserName);
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } else {

            return ResponseEntity.ok("The customer with user name: " + customerUserName + " does NOT exist in database");
        }
    }


    /**
     * This method takes as parameter a customerUserName
     * Returns HTTP status indicating if deletion has been successfully.
     *
     * @param customerUserName - A String containing the current customer username
     * @return A HttpStatus.NO_CONTENT if the customer was removed successfully,
     * otherwise HttpStatus.INTERNAL_SERVER_ERROR
     */
    @RequestMapping(path = "/customers/{customerUserName}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteCustomerByUserName(@PathVariable String customerUserName) {
        try {
            customerRepository.deleteById(customerUserName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * This method remove all customers that exist on the customer repository
     * Returns HTTP status indicating if deletion has been successfully.
     *
     * @return A HttpStatus.NO_CONTENT if the customer was removed successfully,
     * otherwise HttpStatus.INTERNAL_SERVER_ERROR
     */
    @RequestMapping(path = "/customers", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteAllCustomers() {
        try {
            customerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * This method takes as parameter a card ID string
     * Returns an array representing the price history of the card with cardId
     *
     * @param cardId - A String containing the card ID
     * @return An array representing the price history of the card with cardId
     */
    @RequestMapping(path = "/get-prices/{cardId}", method = RequestMethod.GET)
    public ResponseEntity getCardPricesHistoryById(@PathVariable String cardId) {
        Optional<Card> cardOptional = cardRepository.findById(cardId);
        if (cardOptional.isPresent()) {
            return new ResponseEntity<>(cardOptional.get().getCardPricesArray(), HttpStatus.OK);
        }
        return ResponseEntity.ok("Couldn't found card with ID: " + cardId + ". Please try again.");

    }


    /**
     * This method takes as parameters a cardId.
     * Returns the card data with the required card ID.
     *
     * @param cardId - A String containing the card ID
     * @return The card data with the required card ID.
     */
    @RequestMapping(path = "/card-data/{cardId}", method = RequestMethod.GET)
    public ResponseEntity getCardDataById(@PathVariable String cardId) {
        Optional<Card> cardOptional = cardRepository.findById(cardId);
        if (cardOptional.isPresent()) {
            cardOptional.get().setCardThreePricesPoints();
            return new ResponseEntity<>(cardOptional.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.ok("The card with ID: " + cardId + " does NOT exist in collection");
        }
    }


    /*
    An (offline) helper method used to set all cards price points (average, highest, lowest) during the
    data set period of time i.e. 01/05/22 - 31/08/22
     */
    @RequestMapping(path = "/card-prices-points", method = RequestMethod.PATCH)
    public ResponseEntity setAllCardsPricePoints() {
        for (Card card :
                cardRepository.findAll()) {
            card.setCardThreePricesPoints();
            cardRepository.save(card);
        }
        return ResponseEntity.ok("Done successfully");

    }


    /*
     A helper method for future in case of wish to update customer user information.
     Currently, method is unused
     */
    @RequestMapping(path = "/customers/{customerUserName}", method = RequestMethod.PATCH)
    public ResponseEntity updateCustomerData(@RequestBody CustomerRequest customerRequest,
                                             @PathVariable String customerUserName) {
        Optional<Customer> customerOptional = customerRepository.findById(customerUserName);
        if (customerOptional.isPresent()) {
            Customer customerToUpdate = customerOptional.get();
            customerToUpdate.setCustomerUserPassword(customerRequest.getCustomerUserPassword());
            customerToUpdate.setCustomerName(customerRequest.getCustomerName());
            customerToUpdate.setCustomerCardsInPossessionList(customerRequest.getCustomerCardsInPossessionList());
            return new ResponseEntity<>(customerRepository.save(customerToUpdate), HttpStatus.OK);
        }
        return ResponseEntity.ok("Couldn't found customer user name: " + customerUserName + ". Please try again.");
    }

}
