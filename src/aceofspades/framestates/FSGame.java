package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.components.DAction;
import aceofspades.components.DButton;
import aceofspades.components.DDeck;
import aceofspades.components.DDeckZoom;
import aceofspades.game.GameManager;
import aceofspades.game.Player;
import aceofspades.game.SessionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

public class FSGame extends FrameState {
    
    private ArrayList<DDeck> _decks;
    private DDeckZoom _leftDeckZoom;
    private DDeckZoom _rightDeckZoom;
    
    private GameManager _gameManager;
    private SessionManager _sessionManager;
    private DButton _endTurnButton;
    private DButton _quitButton;

    public FSGame(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);        
        setBackgroundImage(Color.green, Main.getImageResource("bgGame.jpg"));
        
        _gameManager = Main.getGameManager();
        _sessionManager = Main.getSessionManager();
        
        Color rightDeckZoomColor = Color.blue;
        Color leftDeckZoomColor = Color.yellow;
        Point rightDeckZoomPosition = new Point();
        Point leftDeckZoomPosition = new Point();
        Dimension deckZoomDimension = new Dimension();
        
        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color buttonFontColor = Color.white;
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Dimension buttonDimension = new Dimension(100, 50);
        Point quitButtonPosition = new Point(150, paneHeight - buttonDimension.height - 35);
        Point endTurnButtonPosition = new Point(150, paneHeight - buttonDimension.height - 35);
        
        _decks = new ArrayList<>();
        
        _leftDeckZoom = new DDeckZoom();
        _leftDeckZoom.setPosition(leftDeckZoomPosition);
        _leftDeckZoom.setDimensions(deckZoomDimension);
        _leftDeckZoom.setBackgroundColor(leftDeckZoomColor);
        
        _rightDeckZoom = new DDeckZoom();
        _rightDeckZoom.setPosition(rightDeckZoomPosition);
        _rightDeckZoom.setDimensions(deckZoomDimension);
        _rightDeckZoom.setBackgroundColor(rightDeckZoomColor);
        
        _endTurnButton = new DButton("End Turn");
        _endTurnButton.setBackground(buttonColor);
        _endTurnButton.setHoverBackground(buttonHoverColor);
        _endTurnButton.setPosition(endTurnButtonPosition);
        _endTurnButton.setDimensions(buttonDimension);
        _endTurnButton.setFont(buttonFont, buttonFontColor);
        _endTurnButton.setAction(new EndTurnAction());
        
        _quitButton = new DButton("Quit");
        _quitButton.setBackground(buttonColor);
        _quitButton.setHoverBackground(buttonHoverColor);
        _quitButton.setPosition(quitButtonPosition);
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
    
    private class EndTurnAction extends DAction {
        
        @Override
        public void run() {
            if (_gameManager.canEndTurn())  {
                _gameManager.endTurn();
            }
            
        }
    }
    
    private
}
