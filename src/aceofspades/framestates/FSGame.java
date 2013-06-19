package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.game.Player;
import aceofspades.GameManager;
import aceofspades.SessionManager;
import aceofspades.components.DAction;
import aceofspades.components.DButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

public class FSGame extends FrameState {
    SessionManager _session;
    GameManager _gameManager;
    DButton _quitButton;    

    public FSGame(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);        
        setBackgroundImage(Color.green, Main.getImageResource("bgGame.jpg"));
        
        _session = Main.getActiveSession();
        _gameManager = _session.getGameManager();
        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color buttonFontColor = Color.white;
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Dimension buttonDimension = new Dimension(300, 60);
        Point buttonPosition = new Point(150, paneHeight - buttonDimension.height - 35);
        
        _quitButton = new DButton("Quit");
        _quitButton.setBackground(buttonColor);
        _quitButton.setHoverBackground(buttonHoverColor);
        _quitButton.setPosition(buttonPosition);
        _quitButton.setDimensions(buttonDimension);
        _quitButton.setFont(buttonFont, buttonFontColor);
        _quitButton.setAction(new DAction() {
            
            @Override
            public void run() {
                Main.getActiveSession().leaveGame();
                Main.setActiveSession(null);
                _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
            }
            
        });
        
        
    }
    
    public void update(Player p) {
        _gameManager.updateFSGame(this, p);
    }
    
}
