package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import java.awt.Color;

public class FSGame extends FrameState {

    public FSGame(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);        
        setBackgroundImage(Color.green, Main.getImageResource("bgGame.jpg"));
    }
    
}
