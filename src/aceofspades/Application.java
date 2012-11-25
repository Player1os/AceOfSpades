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
        CardSet tmp = new CardSet(200, 200);
        tmp.Cards = new ArrayList<>();
        Card tmp1 = new Card("10", "hearts");
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("A", "spades");
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("2", "clubs");
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("5", "diamonds");
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("Q", "spades");
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        cardSety.add(tmp);
        
        tmp = new CardSet(300, 300);
        tmp.Cards = new ArrayList<>();
        tmp1 = new Card("9", "hearts");
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("K", "spades");
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("3", "clubs");
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("4", "diamonds");
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        tmp1= new Card("J", "spades");
        tmp1.cardSet = tmp;
        tmp.Cards.add(tmp1);
        cardSety.add(tmp);
        
        aceofspades.frames.Frame frame = new aceofspades.frames.Frame();

    }
}
