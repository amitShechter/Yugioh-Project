package YugiohProject.controller;

import YugiohProject.model.Card;
import YugiohProject.model.Customer;
import YugiohProject.repository.CustomerRepository;
import YugiohProject.requests.CardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/*
This class goal is maintaining requests to the following endpoints,
dealing with the customer cards operations.
These endpoints focus on CRUD (create, read, update, delete) operations of the as well as more functionalities
Most of these endpoints would contain a unique customerUserName which allow the app to keep track with
the required data need to retrieve and to send (from and to) the server & DB
 */

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class CustomerCardsController {

    // **************************************************
    // Fields
    // **************************************************

    /**
     * Contains a reference to the repository that store all customers data.
     */
    @Autowired
    CustomerRepository customerRepository;


    // **************************************************
    // Public Methods
    // **************************************************


    /**
     * This method takes as parameters a cardRequest (containing the card information).
     * and a customerUserName (containing the current Customer identifier)
     * The method adds the card from the request to the customer (with the required username) card collection.
     *
     * @param cardRequest      - Containing the card information from the request
     * @param customerUserName - A String containing the current customer username
     * @return an HttpStatus.OK if current customer exist and save the new card to customer card list,
     * otherwise return an error message response
     */
    @RequestMapping(path = "/customer-cards-add/{customerUserName}", method = RequestMethod.PATCH)
    public ResponseEntity addCardToCustomerCardsList(@RequestBody CardRequest cardRequest,
                                                     @PathVariable String customerUserName) {
        Optional<Customer> customerOptional = customerRepository.findById(customerUserName);
        if (customerOptional.isPresent()) {
            Customer customerToUpdate = customerOptional.get();
            Card cardToAdd = new Card();
            cardToAdd.setCardId(cardRequest.getCardId());
            cardToAdd.setCardName(cardRequest.getCardName());
            cardToAdd.setCardRarity(cardRequest.getCardRarity());
            cardToAdd.setNumberOfCopies(cardRequest.getNumberOfCopies());
            cardToAdd.setCardImageSrc(cardRequest.getCardImageSrc());
            cardToAdd.setCardAvgPrice(cardRequest.getCardAvgPrice());
            cardToAdd.setCardMaxPrice(cardRequest.getCardMaxPrice());
            cardToAdd.setCardMinPrice(cardRequest.getCardMinPrice());
            cardToAdd.setCardExpectedPrice(cardRequest.getCardExpectedPrice());
            customerToUpdate.addNewCardToCustomerCardsList(cardToAdd);
            return new ResponseEntity<>(customerRepository.save(customerToUpdate), HttpStatus.OK);
        }
        return ResponseEntity.ok("The customer with user name: " + customerUserName + " does NOT exist in database");
    }


    /**
     * This method takes as parameters a cardRequest (containing the card information).
     * and a customerUserName (containing the current Customer identifier)
     * The method updates the copies number of the required card from the request
     * to the customer(with the required username) card collection.
     *
     * @param cardRequest      - Containing the card information from the request
     * @param customerUserName - A String containing the current customer username
     * @return an HttpStatus.OK if current customer exist and save the new copies number of the required card,
     * otherwise return an error message response
     */
    @RequestMapping(path = "/customer-cards-update/{customerUserName}", method = RequestMethod.PATCH)
    public ResponseEntity updateCopiesNumber(@RequestBody CardRequest cardRequest,
                                             @PathVariable String customerUserName) {

        Optional<Customer> customerOptional = customerRepository.findById(customerUserName);
        if (customerOptional.isPresent()) {
            Customer customerToUpdate = customerOptional.get();
            customerToUpdate.updateNumberOfCopies(customerToUpdate.getCardFromCollectionById(cardRequest.getCardId()),
                    cardRequest.getNumberOfCopies());

            return new ResponseEntity<>(customerRepository.save(customerToUpdate), HttpStatus.OK);
        }
        return ResponseEntity.ok("The customer with user name: " + customerUserName + " does NOT exist in database");

    }


    /**
     * This method takes as parameter a customerUserName (containing the current Customer identifier)
     * Returns all cards data that the current customer (with the required username) has in his card collection.
     *
     * @param customerUserName - A String containing the current customer username
     * @return all cards data that the current customer holds in his card collection with HttpStatus.OK.
     * otherwise return an error message response
     */
    @RequestMapping(path = "/customer-cards/{customerUserName}", method = RequestMethod.GET)
    public ResponseEntity getAllCustomerCards(@PathVariable String customerUserName) {
        Optional<Customer> customer = customerRepository.findById(customerUserName);
        if (customer.isPresent()) {
            ArrayList<Card> customerCardsList = customer.get().getCustomerCardsInPossessionList();
            return new ResponseEntity(customerCardsList, HttpStatus.OK);
        } else {
            return ResponseEntity.ok("The customer with user name: " + customerUserName + " does NOT exist in database");
        }
    }


    /**
     * This method takes as parameters a cardRequest (containing the card information).
     * and a customerUserName (containing the current Customer identifier)
     * Returns the card data with the required card ID from the card request from the customer card collection.
     *
     * @param cardRequest      - Containing the card information from the request
     * @param customerUserName - A String containing the current customer username
     * @return the card data with the required card ID from the card request from the customer card collection.
     */
    @RequestMapping(path = "/customer-card/{customerUserName}", method = RequestMethod.GET)
    public ResponseEntity getCustomerCardById(@RequestBody CardRequest cardRequest,
                                              @PathVariable String customerUserName) {
        Optional<Customer> customer = customerRepository.findById(customerUserName);
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get().getCardFromCollectionById(cardRequest.getCardId()), HttpStatus.OK);
        } else {
            return ResponseEntity.ok("The card with ID: " + cardRequest.getCardId() + " does NOT exist in collection");
        }
    }


    /**
     * This method takes as parameters a cardRequest (containing the card information).
     * and a customerUserName (containing the current Customer identifier)
     * The method removes the card from within the request from the customer (with the required username) card collection.
     *
     * @param cardRequest      - Containing the card information from the request
     * @param customerUserName - A String containing the current customer username
     * @return an HttpStatus.NO_CONTENT if the card was removed successfully,
     * otherwise HttpStatus.INTERNAL_SERVER_ERROR
     */
    @RequestMapping(path = "/customer-card-remove/{customerUserName}", method = RequestMethod.PATCH)
    public ResponseEntity<HttpStatus> removeCardFromCustomerList(@RequestBody CardRequest cardRequest,
                                                                 @PathVariable String customerUserName) {
        Optional<Customer> customerOptional = customerRepository.findById(customerUserName);
        if (customerOptional.isPresent()) {
            Customer customerToUpdate = customerOptional.get();
            for (Card cardToRemove :
                    customerToUpdate.getCustomerCardsInPossessionList()) {
                if (cardRequest.getCardId().equals(cardToRemove.getCardId())) {
                    customerToUpdate.removeCardFromCustomerCardsList(cardToRemove);
                    customerRepository.save(customerToUpdate);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * This method takes as parameters a cardRequest (containing the card information).
     * and a customerUserName (containing the current Customer identifier)
     * The method removes the entire card collection of the customer with the required username
     *
     * @param customerUserName - A String containing the current customer username
     * @return an HttpStatus.NO_CONTENT if the whole collection was removed successfully,
     * otherwise return HttpStatus.INTERNAL_SERVER_ERROR
     */
    @PatchMapping("/customer-cards-remove/{customerUserName}")
    public ResponseEntity<HttpStatus> deleteAllCustomerCards(@PathVariable String customerUserName) {
        try {
            Optional<Customer> customerOptional = customerRepository.findById(customerUserName);
            if (customerOptional.isPresent()) {
                Customer customerToUpdate = customerOptional.get();
                customerToUpdate.removeAllCardsFromCollection();
                customerRepository.save(customerToUpdate);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
