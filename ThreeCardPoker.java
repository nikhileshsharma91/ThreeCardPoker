import java.util.ArrayList;
import java.util.Scanner;

public class ThreeCardPoker {

    public static void main(String[] args) {
        
        PokerHandEvaluator evaluator = new ThreeCardPokerHandEvaluator();
        ArrayList<Hand> hands = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String line;
        int x = 0;

        try {
            int numPlayers = scanner.nextInt();

            while (x < numPlayers) {
                line = scanner.nextLine();
                String[] arr = line.split(" ");

                if (arr.length == 4) {
                    int playerID = Integer.parseInt(arr[0]);

                    ArrayList<Card> cards = new ArrayList<>();
                    String[] cardDetails;

                    for (int i = 1; i < arr.length; i++) {
                        cardDetails = arr[i].split("");
                        Card card = new Card(cardDetails[0], cardDetails[1]);
                        cards.add(card);
                    }
                    //create a hand
                    Hand hand = new Hand(cards, playerID, evaluator);
                    hands.add(hand);
                    x++;
                }
            }
        } catch (NumberFormatException ex) {
        }

        //print the result
        String separator = "";  // separator here is " "
        for (int winner : evaluator.findWinner(hands)) {
            System.out.print(separator + winner);
            separator = " ";
        }

//        PokerHandEvaluator evaluator = new ThreeCardPokerHandEvaluator();
//        ArrayList<Hand> hands = new ArrayList<>();
//        //Scanner scanner = new Scanner(System.in);
//        String line;
//        int x = 0;
//
//        try {
//            File f = new File("C:\\Users\\Nikhil\\Documents\\NetBeansProjects\\ThreeCardPoker_v2\\src\\07");
//            Scanner scanner = new Scanner(f);
//            //int numPlayers = scanner.nextInt();
//            scanner.nextLine();
//
//            while (scanner.hasNextLine()) {
//                line = scanner.nextLine();
//                String[] arr = line.split(" ");
//
//                if (arr.length == 4) {
//                    int playerID = Integer.parseInt(arr[0]);
//
//                    ArrayList<Card> cards = new ArrayList<>();
//                    String[] cardDetails;
//
//                    for (int i = 1; i < arr.length; i++) {
//                        cardDetails = arr[i].split("");
//                        Card card = new Card(cardDetails[0], cardDetails[1]);
//                        cards.add(card);
//                    }
//                    //create a hand
//                    Hand hand = new Hand(cards, playerID, evaluator);
//                    hands.add(hand);
//                    x++;
//                }
//            }
//        } catch (FileNotFoundException e) {
//        }
//
//        //print the result
//        String separator = "";  // separator here is " "
//        for (int winner : findWinner(hands)) {
//            System.out.print(separator + winner);
//            separator = " ";
//        }
    }

}
