package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import java.awt.Color;

public class Game extends FrameState {

    public Game(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);        
        setBackgroundImage(Color.green, Main.getImageResource("bgGame.jpg"));
    }
    
}
