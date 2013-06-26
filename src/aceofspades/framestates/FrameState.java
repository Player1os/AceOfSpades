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
        g.drawImage(_imgBG, 0,0, _paneWidth, _paneHeight, null);
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
    
    public void addFirstComponent(DComponent component) {
        _DComponents.add(0, component);
    }
    
    
    public int getHeight() {
        return _paneHeight;
    }
    
    public int getWidth() {
        return _paneWidth;
    }
    
    
    @Override
    public void mouseMoved(MouseEvent e) {
        ArrayList<DComponent> dComponents = new ArrayList<>(_DComponents);
        
        for (DComponent component : dComponents) {
            component.mouseMoved(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ArrayList<DComponent> dComponents = new ArrayList<>(_DComponents);
        
        for (DComponent component : dComponents) {
            component.mousePressed(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ArrayList<DComponent> dComponents = new ArrayList<>(_DComponents);
        
        for (DComponent component : dComponents) {
            component.mouseDragged(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ArrayList<DComponent> dComponents = new ArrayList<>(_DComponents);
        
        for (DComponent component : dComponents) {
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
