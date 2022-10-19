package YugiohProject.requests;

import YugiohProject.model.Card;
import java.util.List;


/**
 * This class purpose is to allow to send in one HTTP Post request,
 * a list of cards and their data to the server in order to save them in the Card Repository.
 */
public class CardsUploadRequest {

    // **************************************************
    // Fields
    // **************************************************

    /**
     * A List which its elements are Cards. It contains the whole data of the cards.
     */
    private List<Card> cardsToUploadList;

    /**
     * Returns a List of all the cards and their data which appear in this HTTP request.
     *
     * @return A List of all the cards and their data which appear in this HTTP request.
     */
    public List<Card> getCardsToUploadList() {
        return cardsToUploadList;
    }


}
