package aceofspades;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class EditorFrame extends JFrame {
    
    public EditorFrame() {
        super("Game Editor");
        
        int frameWidth = Integer.parseInt(Main.getProperty("gameEditorWidth"));
        int frameHeight = Integer.parseInt(Main.getProperty("gameEditorHeight"));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - frameWidth) / 2,
                (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
        
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
}
