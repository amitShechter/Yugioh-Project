package YugiohProject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cards")
public class Card {

    // **************************************************
    // Constants
    // **************************************************
    /**
     * Contains a value which represent the size of the price history dataset of each card.
     * Represent a size of 4 months on the period of: 01/05/22 - 31/08/22
     */
    private static final int CARDS_DATASET_SIZE = 123;


    // **************************************************
    // Fields
    // **************************************************

    /**
     * A unique ID string which identifies the card in the cards repository
     */
    @Id
    private String cardId;

    /**
     * A String which represents the card's name
     */
    private String cardName;

    /**
     * A String which represent how rare the card is.
     * Possible rarities are: Common/Rare/Super Rare/Gold Rare/Ultra Rare/Secret Rare etc.
     * State if the card is 1st Edition/ Unlimited Edition
     */
    private String cardRarity;

    /**
     * An integer which represent how many copies of this card the user holds in his collection
     * Possible amounts are: 0/1/2 ...
     */
    private int numberOfCopies;

    /**
     * An array that contains the price history of this card. The data is stored as:
     * cardPricesArray[0] = card price at 01/05/22
     * cardPricesArray[1] = card price at 02/05/22,
     * ..
     * cardPricesArray[122] = card price at 31/08/22,
     */
    private double[] cardPricesArray;

    /**
     * A String which represent the file name of this card image.
     * format would be: CardNameExample.jpg
     */
    private String cardImageSrc;

    /**
     * A double number which represent the average price of this card on that period of time.
     * i.e. 01/05/22 to 31/08/22
     */
    private double cardAvgPrice;

    /**
     * A double number which represent the highest price of this card on that period of time.
     * i.e. 01/05/22 to 31/08/22
     */
    private double cardMaxPrice;

    /**
     * A double number which represent the lowest price of this card on that period of time.
     * i.e. 01/05/22 to 31/08/22
     */
    private double cardMinPrice;

    /**
     * A double number which represent the predicted price for this card.
     * This value was calculated separately by models and matrices which represent this card characteristics
     * The model and matrices can be found on the attached Excel file
     */
    private double cardExpectedPrice;


    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor. When creating a new Card:
     * <p>
     * The number of copies of this card will be initialized to 1;
     * Will create a new double array of size: {@value #CARDS_DATASET_SIZE}
     */
    public Card() {
        this.numberOfCopies = 1;
        this.cardPricesArray = new double[CARDS_DATASET_SIZE];
    }


    /**
     * Parameterized constructor.
     *
     * @param cardId         - Contains the card ID of the new Card to be created
     * @param cardName       - Contains the card Name of the new Card to be created
     * @param cardRarity     - Contains the card Rarity of the new Card to be created
     * @param numberOfCopies - Contains the Number Of Copies of the new Card to be created
     *                       <p>
     *                       Creating the file name of the Card image by concatenating 'jpg' to the card's name.
     *                       Initializing the average, highest and lowest prices of the card to 0.
     *                       Price would be updated later in the flow
     */
    public Card(String cardId, String cardName, String cardRarity, int numberOfCopies) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.cardRarity = cardRarity;
        this.numberOfCopies = numberOfCopies;
        this.cardImageSrc = cardId + ".jpg";
        this.cardAvgPrice = 0;
        this.cardMaxPrice = 0;
        this.cardMinPrice = 0;
        this.cardExpectedPrice = 0;
    }


    // **************************************************
    // Public Methods
    // **************************************************

    /**
     * Returns this card ID
     *
     * @return This card ID.
     */
    public String getCardId() {
        return cardId;
    }


    /**
     * @param cardId - A String which represent a new ID for this card
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }


    /**
     * Returns this card Name
     *
     * @return This card Name.
     */
    public String getCardName() {
        return cardName;
    }


    /**
     * @param cardName - A String which represent a new Name for this card
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }


    /**
     * Returns this card Rarity
     *
     * @return This card Rarity.
     */
    public String getCardRarity() {
        return cardRarity;
    }


    /**
     * @param cardRarity - A String which represent a new Rarity (upgrade/downgrade) for this card
     */
    public void setCardRarity(String cardRarity) {
        this.cardRarity = cardRarity;
    }


    /**
     * Returns the number of copies of this card in the user collection
     *
     * @return The number of copies of this card in the user collection.
     */
    public int getNumberOfCopies() {
        return numberOfCopies;
    }


    /**
     * @param numberOfCopies - An integer which represent a new number of copies value for this card
     */
    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }


    /**
     * Returns this card price history which is represented as array of doubles.
     * Represented the period of 01/05/22 to 31/08/22
     *
     * @return This card price history which is represented as array of doubles.
     */
    public double[] getCardPricesArray() {
        return cardPricesArray;
    }


    /**
     * @param cardPricesArray - A double array which represent a new price history for this card
     */
    public void setCardPricesArray(double[] cardPricesArray) {
        this.cardPricesArray = cardPricesArray;
    }


    /**
     * Returns a String which represent this card image file name.
     *
     * @return A String which represent this card image file name.
     */
    public String getCardImageSrc() {
        return cardImageSrc;
    }


    /**
     * @param cardImageSrc - A String which represent a new image file name for this card
     */
    public void setCardImageSrc(String cardImageSrc) {
        this.cardImageSrc = cardImageSrc;
    }


    /**
     * Returns a double number which represent this card average price on that period of time.
     * * i.e. 01/05/22 to 31/08/22
     *
     * @return A double number which represent this card average price on that period of time.
     */
    public double getCardAvgPrice() {
        return cardAvgPrice;
    }


    /**
     * @param cardAvgPrice- A double value which represent a new average price for this card
     */
    public void setCardAvgPrice(double cardAvgPrice) {
        this.cardAvgPrice = cardAvgPrice;
    }


    /**
     * Returns a double number which represent this card highest price on that period of time.
     * * i.e. 01/05/22 to 31/08/22
     *
     * @return A double number which represent this card highest price on that period of time.
     */
    public double getCardMaxPrice() {
        return cardMaxPrice;
    }


    /**
     * @param cardMaxPrice- A double value which represent a new highest price for this card
     */
    public void setCardMaxPrice(double cardMaxPrice) {
        this.cardMaxPrice = cardMaxPrice;
    }


    /**
     * Returns a double number which represent this card lowest price on that period of time.
     * * i.e. 01/05/22 to 31/08/22
     *
     * @return A double number which represent this card lowest price on that period of time.
     */
    public double getCardMinPrice() {
        return cardMinPrice;
    }


    /**
     * @param cardMinPrice- A double value which represent a new lowest price for this card
     */
    public void setCardMinPrice(double cardMinPrice) {
        this.cardMinPrice = cardMinPrice;
    }


    /**
     * Returns a double number which represent this card predicted price on that period of time.
     *
     * @return A double number which represent this card predicted price on that period of time.
     */
    public double getCardExpectedPrice() {
        return cardExpectedPrice;
    }


    /**
     * @param cardExpectedPrice - A double value which represent the new predicted (expected) price for this card
     */
    public void setCardExpectedPrice(double cardExpectedPrice) {
        this.cardExpectedPrice = cardExpectedPrice;
    }


    /**
     * This method calculates the average, highest and lowest prices of this card to their correct value.
     * Then the method sets these values.
     */
    public void setCardThreePricesPoints() {
        double avgPrice = cardPricesArray[0], minPrice = cardPricesArray[0], maxPrice = cardPricesArray[0];
        for (int i = 1; i < cardPricesArray.length; i++) {
            if (cardPricesArray[i] < minPrice) minPrice = cardPricesArray[i];
            if (cardPricesArray[i] > maxPrice) maxPrice = cardPricesArray[i];
            avgPrice += cardPricesArray[i];
        }
        this.cardAvgPrice = avgPrice / cardPricesArray.length;
        this.cardMaxPrice = maxPrice;
        this.cardMinPrice = minPrice;
    }

}