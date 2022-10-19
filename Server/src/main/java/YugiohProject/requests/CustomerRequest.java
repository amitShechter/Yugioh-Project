package YugiohProject.requests;

import YugiohProject.model.Card;
import java.util.ArrayList;

public class CustomerRequest {

    // **************************************************
    // Fields
    // **************************************************

    /**
     * A String which represents the Customer's username in the request
     */
    private String customerUserName;

    /**
     * A String which represents the Customer's user password in the request
     */
    private String customerUserPassword;

    /**
     * A String which represents the Customer's name in the request
     */
    private String customerName;

    /**
     * An ArrayList object which his elements are Card objects.
     * This arraylist represent the Cards Collection which this Customer holds in the request.
     */
    private ArrayList<Card> customerCardsInPossessionList;


    // **************************************************
    // Constructor
    // **************************************************


    /**
     * Parameterized constructor.
     *
     * @param customerUserName              - Contains the customer username in the request
     * @param customerUserPassword          - Contains the customer user password in the request
     * @param customerName                  - Contains the customer name in the request
     * @param customerCardsInPossessionList - Contains the customer cards collection in the request
     */
    public CustomerRequest(String customerUserName, String customerUserPassword,
                           String customerName, ArrayList<Card> customerCardsInPossessionList) {
        this.customerUserName = customerUserName;
        this.customerUserPassword = customerUserPassword;
        this.customerName = customerName;
        this.customerCardsInPossessionList = customerCardsInPossessionList;
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
     * This arraylist represent the cards collection which this Customer holds in his possession.
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
}
