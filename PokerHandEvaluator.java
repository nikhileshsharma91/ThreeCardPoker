import java.util.ArrayList;
import javafx.util.Pair;

//Interface that contains abstract methods used to evaluate a hand
public interface PokerHandEvaluator {
    
    public HandType evaluate(ArrayList<Card> cards);
    public int findHighestCard(ArrayList<Card> cards);
    public boolean isStraightFlush(ArrayList<Card> cards);
    public boolean isThreeOfAKind(ArrayList<Card> cards);
    public boolean isStraight(ArrayList<Card> cards);
    public boolean isFlush(ArrayList<Card> cards);
    public Pair<Integer, Boolean> isPair(ArrayList<Card> cards);
    public ArrayList<Integer> getHandValue(ArrayList<Card> cards);
    public ArrayList<Integer> findWinner(ArrayList<Hand> hands);
}
