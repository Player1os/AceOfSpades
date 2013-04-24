package aceofspades;

import aceofspades.framestates.FrameState;
import java.awt.*;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class MainFrame extends JFrame {
    
    private static MainFrame _instance = null;
    private FrameState _contentManager;
    private DrawPane _drawPane;
    
    private class DrawPane extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            if (_contentManager == null) {
                return;
            }
            
            int width = this.getWidth();
            int height = this.getHeight();
            
            Image image = createImage(width, height);
            Graphics graphics = image.getGraphics();
            _contentManager.draw(graphics);
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
        
        _contentManager = null;
        
        Properties prop = aceofspades.Main.getProperties();
        int frameWidth = Integer.parseInt(prop.getProperty("mainFrameWidth"));
        int frameHeight = Integer.parseInt(prop.getProperty("mainFrameHeight"));
        
        setResolution(new Dimension(frameWidth, frameHeight));
        
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
    
    public void setContentManager(FrameState contentManager) {
        if (_contentManager != null) {
            _contentManager.unload();
            _drawPane.removeMouseMotionListener(_contentManager);
            _drawPane.removeMouseListener(_contentManager);
        }        
 
        _contentManager = contentManager;
        
        _drawPane.addMouseMotionListener(_contentManager);
        _drawPane.addMouseListener(_contentManager);
    }
}
