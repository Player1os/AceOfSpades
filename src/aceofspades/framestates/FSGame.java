package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.components.DAction;
import aceofspades.components.DButton;
import aceofspades.components.DMouseAction;
import aceofspades.components.DDeck;
import aceofspades.components.DDeckZoom;
import aceofspades.components.DDeckZoomAction;
import aceofspades.game.Card;
import aceofspades.game.Deck;
import aceofspades.game.GameManager;
import aceofspades.game.Player;
import aceofspades.game.SessionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.script.ScriptException;
import javax.swing.JOptionPane;

public class FSGame extends FrameState {
    
    private ArrayList<DDeck> _dDecks;
    private DDeckZoom _rightDeckZoom;
    private DDeckZoom _leftDeckZoom;    
    
    private GameManager _gameManager;
    private SessionManager _sessionManager;
    private DButton _endTurnButton;
    private DButton _quitButton;
    
    private static Color rightDeckZoomColor = Color.blue;
    private static Color leftDeckZoomColor = Color.yellow;

    public FSGame(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);        
        setBackgroundImage(Color.green, Main.getImageResource("bgGame.jpg"));
        
        _gameManager = Main.getGameManager();
        _sessionManager = Main.getSessionManager();
        
        Point rightDeckZoomPosition = new Point();
        Point leftDeckZoomPosition = new Point();
        Dimension deckZoomDimension = new Dimension();        
        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color buttonFontColor = Color.white;
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Dimension buttonDimension = new Dimension(100, 50);
        Point quitButtonPosition = new Point(10, 10);
        Point endTurnButtonPosition = 
                new Point(paneWidth - buttonDimension.width - 10, 10);
        
        _dDecks = new ArrayList<>();
        ArrayList<DDeck> dDecks = 
                _gameManager.getDDecks(_gameManager.getActivePlayer());
        
        for (DDeck dDeck : dDecks) {
            dDeck.setAction(new DDeckAction(dDeck));
            addComponent(dDeck);
            _dDecks.add(dDeck);
        }
        
        _rightDeckZoom = new DDeckZoom(this);
        _rightDeckZoom.setPosition(rightDeckZoomPosition);
        _rightDeckZoom.setDimensions(deckZoomDimension);
        _rightDeckZoom.setBackgroundColor(rightDeckZoomColor);
        _rightDeckZoom.setAction(new RightZoomAction(_rightDeckZoom));
        
        _leftDeckZoom = new DDeckZoom(this);
        _leftDeckZoom.setPosition(leftDeckZoomPosition);
        _leftDeckZoom.setDimensions(deckZoomDimension);
        _leftDeckZoom.setBackgroundColor(leftDeckZoomColor);
        _leftDeckZoom.setAction(new LeftZoomAction(_leftDeckZoom));
        
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
        
        addComponent(_leftDeckZoom);
        addComponent(_rightDeckZoom);
        addComponent(_endTurnButton);
        addComponent(_quitButton);
    }
    
    public void updateDDecks(Player player) {
        for (DDeck dDeck : _dDecks) {
            removeComponent(dDeck);
        }
        
        ArrayList<DDeck> dDecks = 
                _gameManager.getDDecks(_gameManager.getActivePlayer());
        
        for (DDeck dDeck : dDecks) {
            dDeck.setAction(new DDeckAction(dDeck));
            addComponent(dDeck);
            _dDecks.add(dDeck);
        }
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
            try {
                if (_gameManager.canEndTurn())  {
                    //_gameManager.endTurn();
                }
            } catch (ScriptException | NoSuchMethodException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Game error", JOptionPane.ERROR_MESSAGE);
                _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
            }            
            
        }
    }
    
    public class DDeckAction extends DMouseAction {
        protected DDeck _dDeck;

        public DDeckAction(DDeck dDeck) {
            _dDeck = dDeck;
        }
        
        @Override
        public void run() {
            for (DDeck dDeck : _dDecks) {
                dDeck.setHighlightColor(null);
            }
            if (_e == null) {
                System.out.println("gg");
            } else {
                System.out.println("hh");
            }
            
            if (_e.getButton() == MouseEvent.BUTTON1) {
                _dDeck.setHighlightColor(rightDeckZoomColor);
                _rightDeckZoom.loadDeck(_dDeck.getDeck(), _gameManager);

            } else if (_e.getButton() == MouseEvent.BUTTON2) {
                _dDeck.setHighlightColor(leftDeckZoomColor);
                _leftDeckZoom.loadDeck(_dDeck.getDeck(), _gameManager);
            }
        }
    }
    
    public class RightZoomAction extends DDeckZoomAction {

        public RightZoomAction(DDeckZoom dDeckZoom) {
            super(dDeckZoom);
        }
        
        @Override
        public void run() {
            int deckPosition = _leftDeckZoom.getDeckPosition(_e.getPoint());
            if (deckPosition > -1) {
                Card card = _selectedCard.getCard();
                Deck deck = _leftDeckZoom.getDeck();
                
                try {
                    _gameManager.moveCard(card, deck, deckPosition);
                } catch (ScriptException | NoSuchMethodException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),
                            "Game error", JOptionPane.ERROR_MESSAGE);
                    _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
                }
                
            } else {
                _dDeckZoom.loadDeck(_dDeckZoom.getDeck(), _gameManager);
            }
        }
        
    }
    
    
    public class LeftZoomAction extends DDeckZoomAction {

        public LeftZoomAction(DDeckZoom dDeckZoom) {
            super(dDeckZoom);
        }
        
        @Override
        public void run() {
            int deckPosition = _rightDeckZoom.getDeckPosition(_e.getPoint());
            if (deckPosition > -1) {
                Card card = _selectedCard.getCard();
                Deck deck = _rightDeckZoom.getDeck();
                
                try {
                    _gameManager.moveCard(card, deck, deckPosition);
                } catch (ScriptException | NoSuchMethodException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),
                            "Game error", JOptionPane.ERROR_MESSAGE);
                    _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
                }
                
            } else {
                _dDeckZoom.loadDeck(_dDeckZoom.getDeck(), _gameManager);
            }            
        }
        
    }
    
}
