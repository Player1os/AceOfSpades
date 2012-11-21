/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aceofspades.frames;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;

/**
 *
 * @author Wryxo
 */

interface DrawStrategy {
    public void draw(Graphics g, int width, int height);
 }


public class Frame extends JFrame {
    int _width = 1280;
    int _height = 720;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Image _image;
    Graphics _graphics;
    DrawStrategy _draw = new DrawMenu(this);
    MouseListener _mouseClick = new DrawMenuMouse(this, _draw);
    MouseMotionListener _mouseMove = new DrawMenuMouse(this, _draw);
        
    public Frame() {
        this.setTitle("Ace of Spades");
        this.setBounds((screenSize.width/2)-(_width/2), (screenSize.height/2)-(_height/2), _width, _height);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.DARK_GRAY);
        this.setVisible(true);
        this.addMouseListener(_mouseClick);
        this.addMouseMotionListener(_mouseMove);
    }
    
    @Override
    public void paint(Graphics g) {
        _image = createImage(getWidth(), getHeight());
        _graphics = _image.getGraphics();
        _draw.draw(_graphics, _width, _height);
        g.drawImage(_image, 0, 0, this);
    }
    
    void setDrawStrategy (DrawStrategy draw) {
        _draw = draw;
    }
    
    void setMouseListener (MouseListener mouse) {
        this.removeMouseListener(_mouseClick);
        _mouseClick = mouse;
        this.addMouseListener(mouse);
    }
    
    void setMouseMotionListener (MouseMotionListener mouse) {
        this.removeMouseMotionListener(_mouseMove);
        _mouseMove = mouse;
        this.addMouseMotionListener(mouse);
    }
}