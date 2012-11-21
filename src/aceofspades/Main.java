/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aceofspades;


/**
 *
 * @author Player1os
 */
public class Main {
<<<<<<< HEAD

    public static aceofspades.frames.MenuFrame _menu = null;
    public static aceofspades.frames.GameFrame _game = null;
    public static aceofspades.frames.EditorFrame _editor = null;
    
=======
  
>>>>>>> c5644acce73a0b94e7c5be1adcbee7f17b7e2c45
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
<<<<<<< HEAD
        Card tmp1 = new Card("as");
        Card tmp2 = new Card("ac");
        Card tmp3 = new Card("ad");
        Card tmp4 = new Card("ah");
        Card tmp5 = new Card("nc");
        
        System.out.println(tmp1.getZnak() + " of " + tmp1.getFarba());
        System.out.println(tmp2.getZnak() + " of " + tmp2.getFarba());
        System.out.println(tmp3.getZnak() + " of " + tmp3.getFarba());
        System.out.println(tmp4.getZnak() + " of " + tmp4.getFarba());
        System.out.println(tmp5.getZnak() + " of " + tmp5.getFarba());
        
        _menu = new aceofspades.frames.MenuFrame();
       // _menu.setExtendedState(_menu.getExtendedState() | aceofspades.frames.MenuFrame.MAXIMIZED_BOTH);
        _menu.setVisible(true);
        
=======
//        Card tmp1 = new Card("as");
//        Card tmp2 = new Card("ac");
//        Card tmp3 = new Card("ad");
//        Card tmp4 = new Card("ah");
//        Card tmp5 = new Card("nc");
//        
//        System.out.println(tmp1.getZnak() + " of " + tmp1.getFarba());
//        System.out.println(tmp2.getZnak() + " of " + tmp2.getFarba());
//        System.out.println(tmp3.getZnak() + " of " + tmp3.getFarba());
//        System.out.println(tmp4.getZnak() + " of " + tmp4.getFarba());
//        System.out.println(tmp5.getZnak() + " of " + tmp5.getFarba());
          aceofspades.frames.Frame frame = new aceofspades.frames.Frame();
>>>>>>> c5644acce73a0b94e7c5be1adcbee7f17b7e2c45
    }
}
