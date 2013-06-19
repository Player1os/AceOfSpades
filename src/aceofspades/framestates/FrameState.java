package aceofspades.framestates;

import aceofspades.MainFrame;
import aceofspades.components.DComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class FrameState extends MouseAdapter {

    protected ArrayList<DComponent> _DComponents;
    protected MainFrame _frame;
    protected BufferedImage _imgBG;
    protected int _paneWidth;
    protected int _paneHeight;
    
    public FrameState(MainFrame frame, int paneWidth, int paneHeight) {
        _frame = frame;
        _DComponents = new ArrayList<>();
        _imgBG = null;
        _paneWidth = paneWidth;
        _paneHeight = paneHeight;
    }

    public void setBackgroundImage(Color c, BufferedImage imgBG) {
        _frame.setBackground(c);
        _imgBG = imgBG;        
    }
    
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawImage(_imgBG, 0,0, _paneWidth, _paneHeight, _frame);
        for (DComponent component : _DComponents) {
            component.draw(g);
        }
    }
    
    public void addComponent(DComponent component) {
        _DComponents.add(component);
    }
    
    public void removeComponent(DComponent component) {
        if (_DComponents.contains(component)) {
            _DComponents.remove(component);
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        for (DComponent component : _DComponents) {
            component.mouseMoved(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (DComponent component : _DComponents) {
            component.mousePressed(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (DComponent component : _DComponents) {
            component.mouseDragged(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (DComponent component : _DComponents) {
            component.mouseReleased(e);
        }
    }
    
    public MainFrame getFrame() {
        return _frame;
    }
    
    public void unload() {
        for (DComponent component : _DComponents) {
            component.unload();
        }
    }
}
