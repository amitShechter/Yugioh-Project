package YugiohProject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("customers")
public class Customer {

    // **************************************************
    // Fields
    // **************************************************

    /**
     * A String which represent the Customer's username in the app.
     * This is a unique String which identifies the Customer in the repository
     */
    @Id
    private String customerUserName;

    /**
     * A String which represent the password of a Customer's user in order to login/register to the app
     */
    private String customerUserPassword;

    /**
     * A String which represent the Customer's name
     */
    private String customerName;

    /**
     * An ArrayList object which his elements are Card objects.
     * This arraylist represent the Cards Collection which this Customer holds in his possession.
     */
    private ArrayList<Card> customerCardsInPossessionList;


    // **************************************************
    // Constructor
    // **************************************************

    /**
     * Default constructor. When creating a new Customer:
     * <p>
     * Create a new empty card collection;
     */
    public Customer() {
        this.customerCardsInPossessionList = new ArrayList<>();
    }


    /**
     * Parameterized constructor.
     *
     * @param customerUserName     - Contains the customer username of the new Customer to be created
     * @param customerUserPassword - Contains the customer's user password of the new Customer to be created
     * @param customerName         - Contains the customer name of the new Customer to be created
     */
    public Customer(String customerUserName, String customerUserPassword, String customerName) {
        this.customerUserName = customerUserName;
        this.customerUserPassword = customerUserPassword;
        this.customerName = customerName;
    }

    // **************************************************
    // Public Methods
    // **************************************************


    /**
     * Returns a string which represent this Customer username.
     *
     * @return A string which represent this Customer username.
     */
    public String getCustomerUserName() {
        return customerUserName;
    }


    /**
     * @param customerUserName - A String which represent a new customer username for this Customer
     */
    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }


    /**
     * Returns a string which represent this Customer user password.
     *
     * @return A string which represent this Customer user password.
     */
    public String getCustomerUserPassword() {
        return customerUserPassword;
    }


    /**
     * @param customerUserPassword - A String which represent a new customer user password for this Customer
     */
    public void setCustomerUserPassword(String customerUserPassword) {
        this.customerUserPassword = customerUserPassword;
    }


    /**
     * Returns a string which represent this Customer name.
     *
     * @return A string which represent this Customer name.
     */
    public String getCustomerName() {
        return customerName;
    }


    /**
     * @param customerName - A String which represent a new name for this Customer
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    /**
     * Returns an ArrayList object contains Card as its elements.
     * This arraylist represent the cards collection which this Customer holds in his possesion.
     *
     * @return An ArrayList object contains Card as its elements.
     */
    public ArrayList<Card> getCustomerCardsInPossessionList() {
        return customerCardsInPossessionList;
    }


    /**
     * @param customerCardsInPossessionList - An ArrayList which contains cards that represent the
     *                                      card collection of this customer
     */
    public void setCustomerCardsInPossessionList(ArrayList<Card> customerCardsInPossessionList) {
        this.customerCardsInPossessionList = customerCardsInPossessionList;
    }


    /**
     * A helper method which accepts a card as its parameter and add it to this Customer cards collection.
     *
     * @param card - A Card object which contains all the data of the card.
     */
    public void addNewCardToCustomerCardsList(Card card) {
        this.customerCardsInPossessionList.add(card);
    }


    /**
     * A helper method which accepts a card as its parameter and remove it from this Customer cards collection.
     *
     * @param card - A Card object which contains all the data of the card.
     */
    public void removeCardFromCustomerCardsList(Card card) {
        this.customerCardsInPossessionList.remove(card);
    }


    /**
     * A helper method which remove all the cards from this Customer cards collection.
     */
    public void removeAllCardsFromCollection() {
        this.customerCardsInPossessionList.removeAll(this.customerCardsInPossessionList);
    }


    /**
     * A helper method which accepts a card and a new numberOfCopies.
     * The method updates the amount of copies of this customer's card to the new numberOfCopies
     *
     * @param card           - A Card object which contains all the data of the card.
     * @param numberOfCopies - An integer number which represent the new amount of copies of the card
     *                       in this Customer collection.
     */
    public void updateNumberOfCopies(Card card, int numberOfCopies) {
        for (Card cardToUpdate :
                this.customerCardsInPossessionList) {
            if (card.getCardId().equals(cardToUpdate.getCardId())) cardToUpdate.setNumberOfCopies(numberOfCopies);
        }
    }


    /**
     * A helper method which accept a card ID and return the data of the card with a matching ID in this Customer
     * card collection
     *
     * @param cardId - A unique ID string which identifies the card in the cards repository
     * @return
     */
    public Card getCardFromCollectionById(String cardId) {
        for (Card card :
                customerCardsInPossessionList) {
            if (card.getCardId().equals(cardId)) return card;
        }
        return null;
    }

}
