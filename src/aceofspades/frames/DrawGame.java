package aceofspades.frames;

import aceofspades.Application;
import aceofspades.CardSet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Wryxo
 */
public class DrawGame implements DrawStrategy{
    Frame _frame;
    Graphics _g;
    
    int selectCardSet = -1;
    int selectlzCard = -1;
    int hoverlzCard = -1;
    int selectrzCard = -1;
    int hoverrzCard = -1;
    
    
    public int rightZoom = -1;
    public int leftZoom = -1;
    CardSet rzCS;
    CardSet lzCS;
    
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
        _g = g;
        g.setColor(Color.BLUE);
        g.fillRect(0, _frame._height-100, _frame._width/2, 100);
        g.setColor(Color.ORANGE);
        g.fillRect(_frame._width/2, _frame._height-100, _frame._width/2, 100);
        
        g.setColor(Color.WHITE);
        for (int i=0; i < Application.cardSety.size(); i++) {
            Application.cardSety.get(i).getVisCardSet().draw(g);
        }
        
        if (rightZoom != -1) {
            rzCS = Application.cardSety.get(rightZoom);
            for (int i=0; i < rzCS.getCardCount(); i++) {
                String tmp1 = rzCS.getCard(i).getSuit();
                String tmp2 = rzCS.getCard(i).getValue();
                boolean tmp3 = rzCS.getCard(i).getVisible();
               if (i == hoverrzCard){
                   g.setColor(Color.YELLOW);
               } else if (i == selectrzCard){
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(Color.WHITE);
                }
                drawCard(_frame._width/2+10+(i*20), _frame._height-90, tmp1, tmp2, tmp3);
                
            }
        }   
        if (leftZoom != -1) {
            lzCS = Application.cardSety.get(leftZoom);
            for (int i=0; i < lzCS.getCardCount(); i++) {
                String tmp1 = lzCS.getCard(i).getSuit();
                String tmp2 = lzCS.getCard(i).getValue();
                boolean tmp3 = lzCS.getCard(i).getVisible();
                 if (i == hoverlzCard){
                   g.setColor(Color.YELLOW);
               } else if (i == selectlzCard){
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(Color.WHITE);
                }
                drawCard(10+(i*20), _frame._height-90, tmp1, tmp2, tmp3);
            }
        }
        
        _frame.repaint();
    }
    
    void drawCard (int x, int y, String s, String v, boolean w) {
        _g.fillRect(x,y,50,80);
        _g.setColor(Color.BLACK);
        _g.drawRect(x-1,y-1,51,81);
        if (w) {
            switch (s) {
                case "hearts" : _g.setColor(Color.RED);
                                _g.drawString("♥", x+4, y+20);
                                _g.drawString(v, x+4, y+40);
                                break;
                case "diamonds" : _g.setColor(Color.RED);
                                _g.drawString("♦", x+4, y+20);
                                _g.drawString(v, x+4, y+40);
                                break;
                case "spades" : _g.setColor(Color.BLACK);
                                _g.drawString("♠", x+4, y+20);
                                _g.drawString(v, x+4, y+40);
                                break;
                case "clubs" : _g.setColor(Color.BLACK);
                                _g.drawString("♣", x+4, y+20);
                                _g.drawString(v, x+4, y+40);
                                break;
            }
        }
    }
}
