package aceofspades;

import aceofspades.framestates.FrameState;
import aceofspades.framestates.FSMainMenu;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class MainFrame extends JFrame {
    
    private static MainFrame _instance = null;
    private FrameState _frameState;
    private DrawPane _drawPane;
    
    private static int _frameWidth = 1024;
    private static int _frameHeight = 800;
    
    private class DrawPane extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            if (_frameState == null) {
                return;
            }
            
            int width = this.getWidth();
            int height = this.getHeight();
            
            Image image = createImage(width, height);
            Graphics graphics = image.getGraphics();
            _frameState.draw(graphics);
            g.drawImage(image, 0, 0, this);
            repaint();
        }
    }
    
    public static MainFrame getInstance() {
        if (_instance == null) {
            _instance = new MainFrame();
        }
        
        return _instance;
    }
    
    private MainFrame() {
        super("Ace of Spades");
        
        _frameState = null;
        
        
        try {
            String strFrameWidth = Main.getProperty("mainFrameWidth");
            if (strFrameWidth == null) {
                throw new NullPointerException();
            }
            _frameWidth = Integer.parseInt(strFrameWidth);
            
            String strFrameHeight = Main.getProperty("mainFrameHeight");
            if (strFrameHeight == null) {
                throw new NullPointerException();
            }
            _frameHeight = Integer.parseInt(strFrameHeight);
            
        } catch (NullPointerException | NumberFormatException ex) {
            Main.setProperty("mainFrameWidth", Integer.toString(_frameWidth));
            Main.setProperty("mainFrameHeight", Integer.toString(_frameHeight));
            Main.writeProperties();
        }
        
        setResolution(new Dimension(_frameWidth, _frameHeight));
        setFrameState(new FSMainMenu(this, getContentPane().getWidth(), 
                        getContentPane().getHeight()));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void setResolution(Dimension d) {
        _drawPane = new DrawPane();
        setContentPane(_drawPane);
        setResizable(false);
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - d.width) / 2, 
                (screenSize.height - d.height) / 2, d.width, d.height);
        validate();
    }
    
    public void setFrameState(FrameState frameState) {
        if (_frameState != null) {
            _frameState.unload();
            _drawPane.removeMouseMotionListener(_frameState);
            _drawPane.removeMouseListener(_frameState);
        }        
 
        _frameState = frameState;
        
        _drawPane.addMouseMotionListener(_frameState);
        _drawPane.addMouseListener(_frameState);
    }
}
