/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aceofspades.frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Wryxo
 */
public class DrawGame implements DrawStrategy{
    Frame _frame;
    boolean drawCascade;
    ArrayList<Rectangle> a = new ArrayList<>();
    
    Rectangle dragCard;
    int x = 100;
    int y = 100;
    boolean hoverDragCard;
    boolean selectDragCard;
    int selectCard = -1;
    int hoverCard = -1;
    
    DrawGame (Frame f) {
        _frame = f;
        f.setBackground(Color.getHSBColor(0.333f, 0.755f, 0.545f));
        fillA(100,50);
    }
    
    // ♣
    // ♠
    // ♥
    // ♦
    
    @Override
    public void draw(Graphics g, int width, int height) {
        dragCard = new Rectangle (x, y, 50, 80);
        if (hoverDragCard) {
            g.setColor(Color.YELLOW);
        } else if (selectDragCard) {
            g.setColor(Color.BLUE);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(dragCard.x, dragCard.y, dragCard.width, dragCard.height);
        for (int i=0; i < a.size(); i++) {
            g.setColor(Color.BLACK);
            g.fillRect(a.get(i).x-2, a.get(i).y-2, a.get(i).width+4, a.get(i).height+4);
            if (i == hoverCard) {
                g.setColor(Color.YELLOW);
            } else if (i == selectCard) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(a.get(i).x, a.get(i).y, a.get(i).width, a.get(i).height);
        }
        
        _frame.repaint();
    }
    
    private void fillA(int x, int y) {
        a.add(new Rectangle(x, y, 50, 80));
        for (int i=2; i<11; i++) {
            a.add(new Rectangle(x+(i-2)*20, y, 50, 80));
        }
        a.add(new Rectangle(x+180, y, 50, 80));
        a.add(new Rectangle(x+200, y, 50, 80));
        a.add(new Rectangle(x+220, y, 50, 80));
        a.add(new Rectangle(x+240, y, 50, 80));
    }

}
