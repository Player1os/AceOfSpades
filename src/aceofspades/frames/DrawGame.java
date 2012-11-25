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
    
    Rectangle dragCard;
    int x = 100;
    int y = 100;
    boolean hoverDragCard;
    boolean selectDragCard;
    
    int selectCardSet = -1;
    int selectCard = -1;
    int hoverCard = -1;
    
    
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
        g.setColor(Color.BLUE);
        g.fillRect(0, _frame._height-100, _frame._width/2, 100);
        g.setColor(Color.ORANGE);
        g.fillRect(_frame._width/2, _frame._height-100, _frame._width/2, 100);
        
        dragCard = new Rectangle (x, y, 50, 80);        
        if (hoverDragCard) {
            g.setColor(Color.YELLOW);
        } else if (selectDragCard) {
            g.setColor(Color.BLUE);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(dragCard.x, dragCard.y, dragCard.width, dragCard.height);
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
               if (i == hoverCard){
                   g.setColor(Color.YELLOW);
               } else if (i == selectCard){
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(Color.WHITE);
                }
                drawCard(g, _frame._width/2+10+(i*20), _frame._height-90, tmp1, tmp2, tmp3);
                
            }
        }   
        if (leftZoom != -1) {
            lzCS = Application.cardSety.get(leftZoom);
            for (int i=0; i < lzCS.getCardCount(); i++) {
                String tmp1 = lzCS.getCard(i).getSuit();
                String tmp2 = lzCS.getCard(i).getValue();
                boolean tmp3 = lzCS.getCard(i).getVisible();
                 if (i == hoverCard){
                   g.setColor(Color.YELLOW);
               } else if (i == selectCard){
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(Color.WHITE);
                }
                drawCard(g, 10+(i*20), _frame._height-90, tmp1, tmp2, tmp3);
            }
        }
        
        _frame.repaint();
    }
    
    void drawCard (Graphics g, int x, int y, String s, String v, boolean w) {
        g.fillRect(x,y,50,80);
        g.setColor(Color.BLACK);
        g.drawRect(x-1,y-1,51,81);
        if (w) {
            switch (s) {
                case "hearts" : g.setColor(Color.RED);
                                g.drawString("♥", x+4, y+20);
                                g.drawString(v, x+4, y+40);
                                break;
                case "diamonds" : g.setColor(Color.RED);
                                g.drawString("♦", x+4, y+20);
                                g.drawString(v, x+4, y+40);
                                break;
                case "spades" : g.setColor(Color.BLACK);
                                g.drawString("♠", x+4, y+20);
                                g.drawString(v, x+4, y+40);
                                break;
                case "clubs" : g.setColor(Color.BLACK);
                                g.drawString("♣", x+4, y+20);
                                g.drawString(v, x+4, y+40);
                                break;
            }
        }
    }
}
