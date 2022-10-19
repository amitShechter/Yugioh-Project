package YugiohProject.controller;

import YugiohProject.repository.CustomerRepository;
import YugiohProject.model.Card;
import YugiohProject.model.Customer;
import YugiohProject.repository.CardRepository;
import YugiohProject.requests.CardsUploadRequest;
import YugiohProject.requests.CardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
This class goal is maintaining requests to the following endpoints,
dealing with the marketplace cards operations.
These endpoints focus on CRUD (create, read, update, delete) operations of the as well as more functionalities
Most of these endpoints would contain a unique customerUserName which allow the app to keep track with
the required data need to retrieve and to send (from and to) the server & DB
 */

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class MarketCardController {


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
     * This method takes as parameter a cardsUploadRequest.
     *
     * @param cardsUploadRequest - Containing a list of cards to POST to the database
     * @return an HttpStatus.CREATED if all list was posted successfully
     */
    @RequestMapping(path = "/marketplace-add-all", method = RequestMethod.POST)
    public ResponseEntity<List<Card>> addListOfCardsToMarket(@RequestBody CardsUploadRequest cardsUploadRequest) {
        cardRepository.saveAll(cardsUploadRequest.getCardsToUploadList());
        return new ResponseEntity<>(cardRepository.findAll(), HttpStatus.CREATED);
    }


    /**
     * This method takes as parameters a cardRequest (containing the card information).
     * The method adds the card from the request to the card repository.
     *
     * @param cardRequest - A request containing the card information
     * @return an HttpStatus.CREATED if created successfully and save the new card to the repository,
     */
    @RequestMapping(path = "/marketplace", method = RequestMethod.POST)
    public ResponseEntity<Card> addNewCardToMarket(@RequestBody CardRequest cardRequest) {
        Card newCard = new Card();
        newCard.setCardId(cardRequest.getCardId());
        newCard.setCardName(cardRequest.getCardName());
        newCard.setCardRarity(cardRequest.getCardRarity());
        newCard.setCardPricesArray(cardRequest.getCardPricesArray());
        newCard.setCardImageSrc(cardRequest.getCardImageSrc());
        newCard.setCardAvgPrice(cardRequest.getCardAvgPrice());
        newCard.setCardMaxPrice(cardRequest.getCardMaxPrice());
        newCard.setCardMinPrice(cardRequest.getCardMinPrice());
        newCard.setCardExpectedPrice(cardRequest.getCardExpectedPrice());
        cardRepository.save(newCard);
        return new ResponseEntity<>(newCard, HttpStatus.CREATED);
    }


    /**
     * This method takes as parameter a customerUserName
     * Returns all cards data that appear in the marketplace
     *
     * @param customerUserName - A String containing the current customer username
     * @return All cards data that appear in the marketplace
     */
    @RequestMapping(path = "/marketplace/{customerUserName}", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> getAllCardsInMarketPlace(@PathVariable String customerUserName) {
        return new ResponseEntity<>(cardRepository.findAll(), HttpStatus.OK);
    }


    /**
     * This method takes as parameters a cardRequest (containing the card information).
     * and a customerUserName (containing the current Customer identifier)
     * Returns the card data with the required card ID within the card request from marketplace.
     *
     * @param cardRequest      - Containing the card information from the request
     * @param customerUserName - A String containing the current customer username
     * @return The card data with the required card ID within the card request from marketplace.
     */
    @RequestMapping(path = "/marketplace-card/{customerUserName}", method = RequestMethod.GET)
    public ResponseEntity getCardInMarketPlaceById(@RequestBody CardRequest cardRequest,
                                                   @PathVariable String customerUserName) {
        Optional<Card> card = cardRepository.findById(cardRequest.getCardId());
        if (card.isPresent()) {
            return new ResponseEntity<>(card.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.ok("The card with ID: " + cardRequest.getCardId() + " does NOT exist in database");
        }
    }


    /**
     * This method takes as parameters a cardRequest (containing the card information).
     * and a customerUserName (containing the current Customer identifier)
     * The method removes the card from within the request from the card repository.
     *
     * @param cardRequest      - Containing the card information from the request
     * @param customerUserName - A String containing the current customer username
     * @return an HttpStatus.NO_CONTENT if the card was removed successfully,
     * otherwise HttpStatus.INTERNAL_SERVER_ERROR
     */
    @RequestMapping(path = "/marketplace-card-remove/{customerUserName}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCardFromMarketById(@RequestBody CardRequest cardRequest,
                                                   @PathVariable String customerUserName) {
        try {
            cardRepository.deleteById(cardRequest.getCardId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * The method removes all cards from the card repository
     *
     * @return A HttpStatus.NO_CONTENT if all cards from the card repository were removed successfully,
     * otherwise return HttpStatus.INTERNAL_SERVER_ERROR
     */
    @RequestMapping(path = "/marketplace", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteAllCards() {
        try {
            cardRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * This method takes as parameters a cardRequest (containing the card information).
     * and a customerUserName (containing the current Customer identifier)
     * The method adds the card from the marketplace to the customer (with the required username) card collection.
     *
     * @param cardRequest      - Containing the card information from the request
     * @param customerUserName - A String containing the current customer username
     * @return an HttpStatus.OK if current customer exist and save the new card to customer card list,
     * otherwise return an error message response
     */
    @PostMapping("/marketplace-add-card/{customerUserName}")
    public ResponseEntity addCardToCustomerListFromMarket(@RequestBody CardRequest cardRequest,
                                                          @PathVariable String customerUserName) {
        Optional<Customer> customer = customerRepository.findById(customerUserName);
        if (customer.isPresent()) {
            Customer customerToUpdate = customer.get();
            Optional<Card> card = cardRepository.findById(cardRequest.getCardId());
            if (card.isPresent()) {
                customerToUpdate.addNewCardToCustomerCardsList(card.get());
                customerRepository.save(customerToUpdate);
            }
            return new ResponseEntity<>("Added successfully", HttpStatus.OK);
        }
        return ResponseEntity.ok("No such user");
    }


    /*
    A helper method for future use in order to update card data from the marketplace
    currently this method is unused
     */
    @RequestMapping(path = "/marketplace-card-update/{customerUserName}", method = RequestMethod.PATCH)
    public ResponseEntity updateMarketCardData(@RequestBody CardRequest cardRequest,
                                               @PathVariable String customerUserName) {
        Optional<Card> cardOptional = cardRepository.findById(cardRequest.getCardId());
        if (cardOptional.isPresent()) {
            Card cardToUpdate = cardOptional.get();
            cardToUpdate.setCardName(cardRequest.getCardName());
            cardToUpdate.setCardRarity(cardRequest.getCardRarity());
            cardRepository.save(cardToUpdate);
            return new ResponseEntity<>(cardRepository.save(cardToUpdate), HttpStatus.OK);
        }
        return ResponseEntity.ok("Couldn't found a card with ID: " + cardRequest.getCardId() + ". Please try again.");
    }

}
