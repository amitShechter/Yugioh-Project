package YugiohProject.requests;

public class CardRequest {

    // **************************************************
    // Fields
    // **************************************************

    /**
     * A String which represents the card's ID in the request
     */
    private String cardId;

    /**
     * A String which represents the card's name in the request
     */
    private String cardName;

    /**
     * A String which represent the card's rarity (how rare the card is) in the request.
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
     * A String which represent the file name of this card image in the request.
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
     * A double number which represent the predicted price for this card in the request.
     * This value was calculated separately by models and matrices which represent this card characteristics
     * The model and matrices can be found on the attached Excel file
     */
    private double cardExpectedPrice;


    // **************************************************
    // Constructor
    // **************************************************


    /**
     * Parameterized constructor.
     *
     * @param cardId          - Contains the card ID of the card in the request
     * @param cardName        - Contains the card Name of the card in the request
     * @param cardRarity      - Contains the card Rarity of the card in the request
     * @param numberOfCopies  - Contains the Number Of Copies of the card in the request
     * @param cardPricesArray - Contains the price history of the card in the request
     */
    public CardRequest(String cardId, String cardName, String cardRarity,
                       int numberOfCopies, double[] cardPricesArray) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.cardRarity = cardRarity;
        this.numberOfCopies = numberOfCopies;
    }


    // **************************************************
    // Public Methods
    // **************************************************

    /**
     * Returns this card ID in the request.
     *
     * @return This card ID in the request.
     */
    public String getCardId() {
        return cardId;
    }


    /**
     * Returns this card Name in the request.
     *
     * @return This card Name in the request.
     */
    public String getCardName() {
        return cardName;
    }


    /**
     * Returns this card Rarity in the request.
     *
     * @return This card Rarity in the request.
     */
    public String getCardRarity() {
        return cardRarity;
    }


    /**
     * Returns the number of copies of this card in the user collection in the request.
     *
     * @return The number of copies of this card in the user collection in the request.
     */
    public int getNumberOfCopies() {
        return numberOfCopies;
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
     * Returns a String which represent this card image file name.
     *
     * @return A String which represent this card image file name.
     */
    public String getCardImageSrc() {
        return cardImageSrc;
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
     * Returns a double number which represent this card highest price on that period of time.
     * * i.e. 01/05/22 to 31/08/22
     *
     * @return A double number which represent this card highest price on that period of time.
     */
    public double getCardMaxPrice() {
        return cardMaxPrice;
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
     * Returns a double number which represent this card predicted price on that period of time.
     *
     * @return A double number which represent this card predicted price on that period of time.
     */
    public double getCardExpectedPrice() {
        return cardExpectedPrice;
    }
}
