package aceofspades;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Properties;
import javax.swing.JFrame;

public class EditorFrame extends JFrame {
    
    public EditorFrame() {
        super("Game Editor");
        
        Properties prop = aceofspades.Main.getProperties();
        int frameWidth = Integer.parseInt(prop.getProperty("gameEditorWidth"));
        int frameHeight = Integer.parseInt(prop.getProperty("gameEditorHeight"));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - frameWidth) / 2,
                (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
        
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
}
