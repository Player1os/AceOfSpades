package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.game.Player;
import aceofspades.game.GameManager;
import aceofspades.components.DAction;
import aceofspades.components.DButton;
import aceofspades.components.DDeck;
import aceofspades.components.DDeckZoom;
import aceofspades.game.SessionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

public class FSGame extends FrameState {
    
    ArrayList<DDeck> _decks;
    DDeckZoom _leftDeckZoom;
    DDeckZoom _rightDeckZoom;
    
    GameManager _gameManager;
    SessionManager _sessionManager;
    DButton _quitButton;    

    public FSGame(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);        
        setBackgroundImage(Color.green, Main.getImageResource("bgGame.jpg"));
        
        _gameManager = Main.getGameManager();
        _sessionManager = Main.getSessionManager();
        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color buttonFontColor = Color.white;
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Dimension buttonDimension = new Dimension(300, 60);
        Point buttonPosition = new Point(150, paneHeight - buttonDimension.height - 35);
        
        _decks = new ArrayList<>();
        
        _leftDeckZoom = new DDeckZoom();
        
        _rightDeckZoom = new DDeckZoom();
        
        _quitButton = new DButton("Quit");
        _quitButton.setBackground(buttonColor);
        _quitButton.setHoverBackground(buttonHoverColor);
        _quitButton.setPosition(buttonPosition);
        _quitButton.setDimensions(buttonDimension);
        _quitButton.setFont(buttonFont, buttonFontColor);
        _quitButton.setAction(new QuitAction());
        
        for (DDeck deck : _decks) {
            addComponent(deck);
        }
        addComponent(_leftDeckZoom);
        addComponent(_rightDeckZoom);
        addComponent(_quitButton);
    }
    
    public void update(Player p) {
        
    }
    
    private class QuitAction extends DAction {
        
        @Override
        public void run() {
            Main.setSessionManager(null);
            Main.setGameManager(null);
            _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
        }
    }
}
