package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.components.DAction;
import aceofspades.components.DButton;
import aceofspades.components.DComponentDrawer;
import aceofspades.components.DMouseAction;
import aceofspades.components.DDeck;
import aceofspades.components.DDeckZoom;
import aceofspades.components.DDeckZoomAction;
import aceofspades.components.DLabel;
import aceofspades.game.AIPlayer;
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
    
    private DLabel _playerLabel;
    
    private GameManager _gameManager;
    private SessionManager _sessionManager;
    
    private DButton _endTurnButton;
    private DButton _quitButton;
    private DComponentDrawer _dCompDraw;
    private Font _dDeckFont;
    
    private int _rightSelect;
    private int _leftSelect;
    
    private static Color rightDeckZoomColor = Color.blue;
    private static Color leftDeckZoomColor = Color.yellow;

    public FSGame(MainFrame frame, int paneWidth, int paneHeight) throws ScriptException, NoSuchMethodException {
        super(frame, paneWidth, paneHeight);        
        setBackgroundImage(Color.green, Main.getImageResource("bgGame.jpg"));
        
        _gameManager = Main.getGameManager();
        _sessionManager = Main.getSessionManager();
        
        Point rightDeckZoomPosition = new Point(paneWidth / 2, paneHeight - 100);
        Point leftDeckZoomPosition = new Point(0, paneHeight - 100);
        Dimension deckZoomDimension = new Dimension(paneWidth / 2, 100);
        
        DAction endTurnAction = new EndTurnAction();
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color buttonFontColor = Color.white;
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Dimension buttonDimension = new Dimension(100, 50);
        Point quitButtonPosition = new Point(10, 10);
        Point endTurnButtonPosition = 
                new Point(paneWidth - buttonDimension.width - 10, 10);
        
         _rightSelect = -1;
        _leftSelect = -1;
        
        _dDeckFont = new Font("SansSerif", Font.BOLD, 15);
        Font _dDeckZoomFont = new Font("SansSerif", Font.BOLD, 15);
        
        _dDecks = new ArrayList<>();
        
        _gameManager.setFrameState(this);
        
        _gameManager.startGame();
        _gameManager.startTurn();
        
        ArrayList<DDeck> dDecks =
                _gameManager.getDDecks(_gameManager.getActivePlayer());
        
        for (DDeck dDeck : dDecks) {
            dDeck.setAction(new DDeckAction(dDeck));
            dDeck.setFont(_dDeckFont);
            addComponent(dDeck);
            _dDecks.add(dDeck);
        }
        
        _dCompDraw = new DComponentDrawer();
        
        _playerLabel = new DLabel(_gameManager.getActivePlayer().getName() + "'s Turn");
        _playerLabel.setFont(new Font("SansSerif", Font.BOLD, 50), Color.ORANGE);
        _playerLabel.setAlignment(DLabel.centerAlign);
        _playerLabel.setPosition(new Point(paneWidth / 2, 50));
        
        _rightDeckZoom = new DDeckZoom();
        _rightDeckZoom.setPosition(rightDeckZoomPosition);
        _rightDeckZoom.setDimensions(deckZoomDimension);
        _rightDeckZoom.setBackgroundColor(rightDeckZoomColor);
        _rightDeckZoom.setGameManager(_gameManager);
        _rightDeckZoom.setAction(new RightZoomAction(_rightDeckZoom));
        _rightDeckZoom.setFont(_dDeckZoomFont);
        _rightDeckZoom.setDCompDraw(_dCompDraw);
        
        _leftDeckZoom = new DDeckZoom();
        _leftDeckZoom.setPosition(leftDeckZoomPosition);
        _leftDeckZoom.setDimensions(deckZoomDimension);
        _leftDeckZoom.setBackgroundColor(leftDeckZoomColor);
        _leftDeckZoom.setGameManager(_gameManager);
        _leftDeckZoom.setAction(new LeftZoomAction(_leftDeckZoom));
        _leftDeckZoom.setFont(_dDeckZoomFont);
        _leftDeckZoom.setDCompDraw(_dCompDraw);
        
        _endTurnButton = new DButton("End Turn");
        _endTurnButton.setBackground(buttonColor);
        _endTurnButton.setHoverBackground(buttonHoverColor);
        _endTurnButton.setPosition(endTurnButtonPosition);
        _endTurnButton.setDimensions(buttonDimension);
        _endTurnButton.setFont(buttonFont, buttonFontColor);
        _endTurnButton.setAction(endTurnAction);
        _endTurnButton.setEnabled(_gameManager.canEndTurn());
        
        _quitButton = new DButton("Quit");
        _quitButton.setBackground(buttonColor);
        _quitButton.setHoverBackground(buttonHoverColor);
        _quitButton.setPosition(quitButtonPosition);
        _quitButton.setDimensions(buttonDimension);
        _quitButton.setFont(buttonFont, buttonFontColor);
        _quitButton.setAction(new QuitAction());
        
        addComponent(_playerLabel);
        addComponent(_leftDeckZoom);
        addComponent(_rightDeckZoom);
        addComponent(_endTurnButton);
        addComponent(_quitButton);
        addComponent(_dCompDraw);
        
        Player player = _gameManager.getActivePlayer();
        if (player instanceof AIPlayer) {
            AIPlayer aiPlayer = (AIPlayer)player;
            aiPlayer.playTurn(_gameManager);
            endTurnAction.run();
        }
    }
    
    public void updateDDecks(Player player) {
        for (DDeck dDeck : _dDecks) {
            removeComponent(dDeck);
        }
        
        ArrayList<DDeck> dDecks = 
                _gameManager.getDDecks(_gameManager.getActivePlayer());
        
        for (DDeck dDeck : dDecks) {
            dDeck.setAction(new DDeckAction(dDeck));
            dDeck.setFont(_dDeckFont);
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
                if (_gameManager.isWinCondition()) {
                    DLabel label = new DLabel(_gameManager.getActivePlayer().getName()  +  " is Victorious");
                    label.setAlignment(DLabel.centerAlign);
                    label.setFont(new Font("Sansserif", Font.BOLD, 80), Color.blue);
                    label.setPosition(new Point(_paneWidth / 2, _paneHeight / 2));
                    addComponent(label);
                    
                    for (DDeck dDeck :_dDecks) {
                        dDeck.setAction(null);
                    }
                    _rightDeckZoom.setAction(null);
                    _leftDeckZoom.setAction(null);
                    _endTurnButton.setAction(null);
                    _endTurnButton.setEnabled(false);
                    return;
                }
                
                _gameManager.endTurn();
                _gameManager.startTurn();
                Player player = _gameManager.getActivePlayer();
                if (player instanceof AIPlayer) {
                    AIPlayer aiPlayer = (AIPlayer)player;
                    aiPlayer.playTurn(_gameManager);
                    run();
                }
        
                _rightSelect = -1;
                _leftSelect = -1;
                
                for (DDeck dDeck : _dDecks) {
                    dDeck.setHighlightColor(null);
                }
                
                _playerLabel.setText(_gameManager.getActivePlayer().getName() + "'s Turn");
                _leftDeckZoom.loadDeck(null);
                _rightDeckZoom.loadDeck(null);
                
                _endTurnButton.setEnabled(_gameManager.canEndTurn());
                updateDDecks(_gameManager.getActivePlayer());
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
            if (_e.getButton() == MouseEvent.BUTTON3) {
                if (_dDeck.getDeck().getDeckID() != _leftSelect) {
                    for (DDeck dDeck : _dDecks) {
                        if (_rightSelect == dDeck.getDeck().getDeckID()) {
                             dDeck.setHighlightColor(null);
                        }
                    }

                    _dDeck.setHighlightColor(rightDeckZoomColor);
                    _rightDeckZoom.loadDeck(_dDeck.getDeck());
                    _rightSelect = _dDeck.getDeck().getDeckID();
                }
            } else if (_e.getButton() == MouseEvent.BUTTON1) {
                if (_dDeck.getDeck().getDeckID() != _rightSelect) {
                    for (DDeck dDeck : _dDecks) {
                        if (_leftSelect == dDeck.getDeck().getDeckID()) {
                             dDeck.setHighlightColor(null);
                        }
                    }

                    _dDeck.setHighlightColor(leftDeckZoomColor);
                    _leftDeckZoom.loadDeck(_dDeck.getDeck());
                    _leftSelect = _dDeck.getDeck().getDeckID();
                }
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
                
                updateDDecks(_gameManager.getActivePlayer());
                _leftDeckZoom.loadDeck(deck);
            }
            
            _dDeckZoom.loadDeck(_dDeckZoom.getDeck());
            
            try {
                _endTurnButton.setEnabled(_gameManager.canEndTurn());
            } catch (ScriptException | NoSuchMethodException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                            "Game error", JOptionPane.ERROR_MESSAGE);
                _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
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
                
                updateDDecks(_gameManager.getActivePlayer());
                _rightDeckZoom.loadDeck(deck);
            }
            
            _dDeckZoom.loadDeck(_dDeckZoom.getDeck());
            
            try {
                _endTurnButton.setEnabled(_gameManager.canEndTurn());
            } catch (ScriptException | NoSuchMethodException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                            "Game error", JOptionPane.ERROR_MESSAGE);
                _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
            }
        }
        
    }
    
}
