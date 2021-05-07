import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    
    private ArrayList<Card> cards;
    private int playerID;
    private final int highestCard;
    private final HandType handType;
    private final ArrayList<Integer> cardValues;
            
    public ArrayList<Integer> getCards() {
        return cardValues;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getHighestCard() {
        return highestCard;
    }

    public int getHandType() {
        return handType.getHandTypeValue();
    }

    //Constructor
    public Hand(ArrayList<Card> cards, int playerID, PokerHandEvaluator evaluator) {
        this.cards = cards;
        this.playerID = playerID;
        handType = evaluator.evaluate(cards);
        highestCard = evaluator.findHighestCard(cards);
        cardValues = evaluator.getHandValue(cards);
        //reverse sort the card values so it can be used for the comparison of second hisghest card, third highest card etc
        Collections.sort(cardValues, Collections.reverseOrder());

    }
    
}
