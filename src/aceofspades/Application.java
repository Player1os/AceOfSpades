package aceofspades;

import java.util.ArrayList;

/**
 *
 * @author Player1os <player1os at gmail.com>
 */
public class Application {
 
    /**
     * @param args the command line arguments
     */
    
    public static ArrayList<CardSet> cardSety = new ArrayList();
    
    public static void main(String[] args) {
 
        /*
         * Card tmp1 = new Card("as"); Card tmp2 = new Card("ac"); Card tmp3 =
         * new Card("ad"); Card tmp4 = new Card("ah"); Card tmp5 = new
         * Card("nc");
         *
         * System.out.println(tmp1.getZnak() + " of " + tmp1.getFarba());
         * System.out.println(tmp2.getZnak() + " of " + tmp2.getFarba());
         * System.out.println(tmp3.getZnak() + " of " + tmp3.getFarba());
         * System.out.println(tmp4.getZnak() + " of " + tmp4.getFarba());
         * System.out.println(tmp5.getZnak() + " of " + tmp5.getFarba());
         */
        CardSet tmp = new CardSet(100, 100, "hand");
        tmp.Cards = new ArrayList<>();
        Card tmp1 = new Card("2", "hearts", 0, 0, tmp, 0);
        tmp.Cards.add(tmp1);
        tmp1= new Card("3", "hearts", 0, 0, tmp, 1);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("4", "hearts", 0, 0, tmp, 2);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("5", "hearts", 0, 0, tmp, 3);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("6", "hearts", 0, 0, tmp, 4);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("7", "hearts", 0, 0, tmp, 5);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("8", "hearts", 0, 0, tmp, 6);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("9", "hearts", 0, 0, tmp, 7);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("10", "hearts", 0, 0, tmp, 8);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("J", "hearts", 0, 0, tmp, 9);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("Q", "hearts", 0, 0, tmp, 10);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("K", "hearts", 0, 0, tmp, 11);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("A", "hearts", 0, 0, tmp, 12);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        cardSety.add(tmp);
        
        tmp = new CardSet(200, 200, "deck");
        tmp.Cards = new ArrayList<>();
        tmp1 = new Card("2", "spades", 0, 0, tmp, 0);
        tmp.Cards.add(tmp1);
        tmp1= new Card("3", "spades", 0, 0, tmp, 1);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("4", "spades", 0, 0, tmp, 2);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("5", "spades", 0, 0, tmp, 3);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("6", "spades", 0, 0, tmp, 4);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("7", "spades", 0, 0, tmp, 5);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("8", "spades", 0, 0, tmp, 6);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("9", "spades", 0, 0, tmp, 7);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("10", "spades", 0, 0, tmp, 8);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("J", "spades", 0, 0, tmp, 9);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("Q", "spades", 0, 0, tmp, 10);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("K", "spades", 0, 0, tmp, 11);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("A", "spades", 0, 0, tmp, 12);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        cardSety.add(tmp);
        
        tmp = new CardSet(300, 300, "deck");
        tmp.Cards = new ArrayList<>();
        tmp1 = new Card("2", "diamonds", 0, 0, tmp, 0);
        tmp.Cards.add(tmp1);
        tmp1= new Card("3", "diamonds", 0, 0, tmp, 1);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("4", "diamonds", 0, 0, tmp, 2);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("5", "diamonds", 0, 0, tmp, 3);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("6", "diamonds", 0, 0, tmp, 4);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("7", "diamonds", 0, 0, tmp, 5);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("8", "diamonds", 0, 0, tmp, 6);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("9", "diamonds", 0, 0, tmp, 7);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("10", "diamonds", 0, 0, tmp, 8);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("J", "diamonds", 0, 0, tmp, 9);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("Q", "diamonds", 0, 0, tmp, 10);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("K", "diamonds", 0, 0, tmp, 11);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("A", "diamonds", 0, 0, tmp, 12);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        cardSety.add(tmp);
        
        tmp = new CardSet(400, 400, "deck");
        tmp.Cards = new ArrayList<>();
        tmp1 = new Card("2", "clubs", 0, 0, tmp, 0);
        tmp.Cards.add(tmp1);
        tmp1= new Card("3", "clubs", 0, 0, tmp, 1);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("4", "clubs", 0, 0, tmp, 2);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("5", "clubs", 0, 0, tmp, 3);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("6", "clubs", 0, 0, tmp, 4);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("7", "clubs", 0, 0, tmp, 5);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("8", "clubs", 0, 0, tmp, 6);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("9", "clubs", 0, 0, tmp, 7);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("10", "clubs", 0, 0, tmp, 8);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("J", "clubs", 0, 0, tmp, 9);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("Q", "clubs", 0, 0, tmp, 10);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("K", "clubs", 0, 0, tmp, 11);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("A", "clubs", 0, 0, tmp, 12);
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        cardSety.add(tmp);
        
        aceofspades.frames.Frame frame = new aceofspades.frames.Frame();
 
    }
}
