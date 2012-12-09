package aceofspades.frames;

import aceofspades.Application;
import aceofspades.CardSet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Wryxo
 */
public class DrawGame implements DrawStrategy{
    Frame _frame;
    
    int selectCardSet = -1;
    int selectlzCard = -1;
    int hoverlzCard = -1;
    int selectrzCard = -1;
    int hoverrzCard = -1;
    
    
    public int rightZoom = -1;
    public int leftZoom = -1;
    CardSet rzCS;
    CardSet lzCS;
    
    public Rectangle quitButton;
    public boolean hoverQuitButton;
    
    DrawGame (Frame f) {
        _frame = f;
        f.setBackground(Color.getHSBColor(0.333f, 0.755f, 0.545f));
    }
    
    // ♣
    // ♠
    // ♥
    // ♦
    
    @Override
    public void draw(Graphics g, int width, int height) {
        g.setColor(Color.BLUE);
        g.fillRect(0, _frame._height-100, _frame._width/2, 100);
        g.setColor(Color.ORANGE);
        g.fillRect(_frame._width/2, _frame._height-100, _frame._width/2, 100);
        
        g.setColor(Color.WHITE);
        for (int i=0; i < Application.getCardSetSize(); i++) {
            Application.getCardSet(i).getVisCardSet().draw(g);
        }
        
        if (rightZoom != -1) {
            rzCS = Application.getCardSet(rightZoom);
            for (int i=0; i < rzCS.getCardCount(); i++) {
                /*String tmp1 = rzCS.getCard(i).getSuit();
                String tmp2 = rzCS.getCard(i).getValue();
                boolean tmp3 = rzCS.getCard(i).getVisible();*/
               if (i == hoverrzCard){
                   g.setColor(Color.YELLOW);
               } else if (i == selectrzCard){
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(Color.WHITE);
                }
                if (selectrzCard != i) rzCS.getCard(i).getVisCard().setPosition(_frame._width/2+10+(i*20), _frame._height-90);
                rzCS.getCard(i).getVisCard().draw(g);                
            }
        }   
        if (leftZoom != -1) {
            lzCS = Application.getCardSet(leftZoom);
            for (int i=0; i < lzCS.getCardCount(); i++) {
                 if (i == hoverlzCard){
                   g.setColor(Color.YELLOW);
               } else if (i == selectlzCard){
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(Color.WHITE);
                }
                if (selectlzCard != i) lzCS.getCard(i).getVisCard().setPosition(10+(i*20), _frame._height-90);
                lzCS.getCard(i).getVisCard().draw(g);
            }
        }
        
        //Quit button
        quitButton = new Rectangle(width-150, 25, 100, 25);
        if (!hoverQuitButton) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.ORANGE);
        }
        g.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
        g.setColor(Color.BLACK);
        g.drawString("Quit Game", quitButton.x+18, quitButton.y+17);
        
        
        Application.lsGame.runScriptFunction("gameWinConds", new Application());
        if (Application.getWin()) {
            Font ff = g.getFont();
            g.setColor(Color.magenta);
            g.setFont(new Font(null, Font.BOLD, 40));
            g.drawString("YOU ARE VICTORIOUS", height/2 + 50, width/2 + 40);
            g.setFont(ff);
        }
        
        _frame.repaint();
    }
}
