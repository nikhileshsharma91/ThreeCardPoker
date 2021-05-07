import java.util.HashMap;

public class Card {
    
    //Variables to store the rank and the suit of a card
    private int rank;
    private String suit;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
  
    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }
    
    //Dictionary to map face value of each card
    static HashMap<String, Integer> faceValue = new HashMap<String, Integer>(){{
        put("2", 2);
        put("3", 3);
        put("4", 4);
        put("5", 5);
        put("6", 6);
        put("7", 7);
        put("8", 8);
        put("9", 9);
        put("T", 10);
        put("J", 11);
        put("Q", 12);
        put("K", 13);
        put("A", 14);
    }};

    public Card(String rank, String suit) {
        this.rank = getFaceValue(rank);
        this.suit = suit;
    }
    
    //Returns the face value of the card from the dictionary
    public static int getFaceValue(String rank){
        int x = faceValue.get(rank);
        return x;
    }
}
