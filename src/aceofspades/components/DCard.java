package aceofspades.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DCard extends DComponent{

    public Rectangle position;
    public String s;
    public String v;
    public boolean w;
    BufferedImage back;

    public DCard(int x, int y, String suit, String value, boolean visible) {
        position = new Rectangle(x, y, 50, 80);
        s = suit;
        v = value;
        w = visible;
        try {
            back = ImageIO.read(new File("res/card_back.jpg"));
        } catch (IOException ex) {
        }
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(position.x, position.y, 50, 80);
        g.setColor(Color.BLACK);
        g.drawRect(position.x - 1, position.y - 1, 51, 81);
        if (w) {
            switch (s) {
                case "hearts":
                    g.setColor(Color.RED);
                    g.drawString("♥", position.x + 4, position.y + 20);
                    g.drawString(v, position.x + 4, position.y + 40);
                    break;
                case "diamonds":
                    g.setColor(Color.RED);
                    g.drawString("♦", position.x + 4, position.y + 20);
                    g.drawString(v, position.x + 4, position.y + 40);
                    break;
                case "spades":
                    g.setColor(Color.BLACK);
                    g.drawString("♠", position.x + 4, position.y + 20);
                    g.drawString(v, position.x + 4, position.y + 40);
                    break;
                case "clubs":
                    g.setColor(Color.BLACK);
                    g.drawString("♣", position.x + 4, position.y + 20);
                    g.drawString(v, position.x + 4, position.y + 40);
                    break;
            }
        } else {
            g.drawImage(back, position.x, position.y, null);
        }
    }

    public void setPosition(int _x, int _y) {
        position.x = _x;
        position.y = _y;
    }
}
