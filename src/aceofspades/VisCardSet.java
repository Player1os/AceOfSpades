package aceofspades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class VisCardSet {

    public Rectangle position;
    String setClass;
    public int focus = 0;
    Color color;
    BufferedImage back;
    BufferedImage backo;
    BufferedImage backb;

    public VisCardSet(int _x, int _y, String s, Color c) {
        position = new Rectangle(_x, _y, 50, 80);
        setClass = s;
        color = c;
        try {
            back = ImageIO.read(new File("res/card_back.jpg"));
            backo = ImageIO.read(new File("res/card_backo.jpg"));
            backb = ImageIO.read(new File("res/card_backb.jpg"));
        } catch (IOException ex) {
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x - 3, position.y - 3, 56, 86);
        g.setColor(Color.BLACK);
        g.drawRect(position.x - 1, position.y - 1, 51, 81);
        if (focus == 0) {
            g.drawImage(back, position.x, position.y, null);
        }
        if (focus > 0) {
            g.drawImage(backo, position.x, position.y, null);
        }
        if (focus < 0) {
            g.drawImage(backb, position.x, position.y, null);
        }
    }

    public void setPosition(int _x, int _y) {
        position.x = _x;
        position.y = _y;
    }

    public int getXPos() {
        return position.x;
    }

    public int getYPos() {
        return position.y;
    }
}
