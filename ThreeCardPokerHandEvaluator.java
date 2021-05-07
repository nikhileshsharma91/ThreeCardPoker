import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import javafx.util.Pair;

public class ThreeCardPokerHandEvaluator implements PokerHandEvaluator {

    /**
     * Evaluates the hand type i.e. Straight Flush, Three of a Kind etc
     *
     * @param cards
     * @return HandType
     */
    @Override
    public HandType evaluate(ArrayList<Card> cards) {

        if (isStraightFlush(cards)) {
            return HandType.STRAIGHTFLUSH;
        } else if (isThreeOfAKind(cards)) {
            return HandType.THREEOFAKIND;
        } else if (isStraight(cards)) {
            return HandType.STRAIGHT;
        } else if (isFlush(cards)) {
            return HandType.FLUSH;
        } else if (isPair(cards).getValue()) {
            return HandType.PAIR;
        } else {
            return HandType.HIGHCARD;
        }
    }

    /**
     * Checks if a hand is a Straight Flush
     *
     * @param cards
     * @return boolean
     */
    @Override
    public boolean isStraightFlush(ArrayList<Card> cards) {

        if (isStraight(cards) && isFlush(cards)) {
            return true;
        }
        return false;
    }

    /**
     * Check if a hand is Three of a Kind Example: T,T,T; 2,2,2; K,K,K
     *
     * @param cards
     * @return boolean
     */
    @Override
    public boolean isThreeOfAKind(ArrayList<Card> cards) {

        ArrayList<Integer> cardValues = getHandValue(cards);
        //check if all elements of the list are equal; making it a three of a kind
        boolean allEqual = cardValues.isEmpty() || cardValues.stream().allMatch(cardValues.get(0)::equals);
        return allEqual;
    }

    /**
     * Checks for a straight; example 2-3-4, 6-7-8, J-Q-K Including wheel A-2-3
     *
     * @param cards
     * @return
     */
    @Override
    public boolean isStraight(ArrayList<Card> cards) {

        ArrayList<Integer> cardValues = getHandValue(cards);
        //Sort the card value list
        Collections.sort(cardValues);

        //Check for a wheel A, 2, 3
        if (cardValues.get(2) == 14 && cardValues.get(1) == 3 && cardValues.get(0) == 2) {
            return true;
        } else {
            int previousNum = cardValues.get(0);

            for (int i = 1; i < cardValues.size(); i++) {

                if (previousNum + 1 == cardValues.get(i)) {

                    previousNum = cardValues.get(i);
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Checks if the hand is a flush Example: All Hearts, All Clubs etc
     *
     * @param cards
     * @return
     */
    @Override
    public boolean isFlush(ArrayList<Card> cards) {

        ArrayList<String> suits = getHandSuit(cards);
        //check if all values are the same as the first
        boolean allEqual = suits.isEmpty() || suits.stream().allMatch(suits.get(0)::equals);
        return allEqual;
    }

    /**
     * Check if the hand has a pair and also return the value of the card
     *
     * @param cards
     * @return Integer(value of the pair card) and Boolean if isPair
     */
    @Override
    public Pair<Integer, Boolean> isPair(ArrayList<Card> cards) {

        ArrayList<Integer> cardValues = getHandValue(cards);
        //Create a HashMap to store the frequency of the card ranks
        HashMap<Integer, Integer> cardFrequency = new HashMap<>();
        int currentCount;
        boolean isPair = false;

        for (Integer cardValue : cardValues) {
            if (!cardFrequency.containsKey(cardValue)) {
                //adds key and value = 0 if key doesn't already exist in dictionary
                cardFrequency.put(cardValue, 0);
            }
            //increment the value by 1
            currentCount = cardFrequency.get(cardValue);
            cardFrequency.replace(cardValue, currentCount + 1);
        }

        //check which card rank has a frequency of 2 i.e. is a pair
        int x = 0;
        for (int key : cardFrequency.keySet()) {

            if (cardFrequency.get(key) == 2) {
                x = key;
                isPair = true;
            }
        }
        //store result in a pair
        Pair<Integer, Boolean> result = new Pair<>(x, isPair);
        return result;
    }

    /**
     * Finds the highest card in a hand
     *
     * @param cards
     * @return highestCard
     */
    @Override
    public int findHighestCard(ArrayList<Card> cards) {
        int highestCard;

        //check is there is a pair present
        if (!isPair(cards).getValue()) {
            ArrayList<Integer> cardValues = getHandValue(cards);
            //sort the list to obtain the highest card
            highestCard = Collections.max(cardValues);
        } else {
            highestCard = isPair(cards).getKey();
        }

        return highestCard;
    }

    /**
     * Creates a list of the card values in a hand
     *
     * @param cards
     * @return A list of all the card values in hand
     */
    @Override
    public ArrayList<Integer> getHandValue(ArrayList<Card> cards) {

        //Create a list of all ranks
        ArrayList<Integer> values = new ArrayList<>();

        cards.forEach((card) -> {
            values.add(card.getRank());
        });

        return values;
    }

    /**
     * Creates a list of card suits in a hand
     *
     * @param cards
     * @return A list of suits
     */
    private ArrayList<String> getHandSuit(ArrayList<Card> cards) {

        //Create a list of all suits
        ArrayList<String> suits = new ArrayList<>();

        cards.forEach((card) -> {
            suits.add(card.getSuit());
        });

        return suits;
    }

    /**
     * Finds the winner by comparing all the hands
     *
     * @param hands: List of all hands
     * @return A list with all the winners
     */
    @Override
    public ArrayList<Integer> findWinner(ArrayList<Hand> hands) {
        ArrayList<Integer> winners = new ArrayList<>();
        Hand previousHand = hands.get(0);
        //Initialize the winner as the first hand
        winners.add(previousHand.getPlayerID());
        int counter = 0;
        for (Hand hand : hands) {
            //counter not to comparet the first hand
            if (counter > 0) {
                //bigger hand
                if (hand.getHandType() > previousHand.getHandType()) {
                    previousHand = hand;
                    winners.clear();
                    winners.add(hand.getPlayerID());
                } //equal hands - compare highest card of hands
                else if (hand.getHandType() == previousHand.getHandType() && hand.getHighestCard() > previousHand.getHighestCard()) {
                    previousHand = hand;
                    winners.clear();
                    winners.add(hand.getPlayerID());
                } //equal hands - equal highest card; tie
                else if (hand.getHandType() == previousHand.getHandType() && hand.getHighestCard() == previousHand.getHighestCard()) {
                    //Loop to check the second highest, third highest etc
                    for (int i = 0; i < hand.getCards().size(); i++) {

                        if (hand.getCards().get(i) > previousHand.getCards().get(i)) {
                            previousHand = hand;
                            winners.clear();
                            winners.add(hand.getPlayerID());
                            break;
                        } else if (i == hand.getCards().size() - 1 && Objects.equals(hand.getCards().get(i), previousHand.getCards().get(i))) {
                            winners.add(hand.getPlayerID());
                        }
                    }
                }
            }
            counter++;
        }
        return winners;
    }
}
