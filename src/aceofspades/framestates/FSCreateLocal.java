package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.components.DAction;
import aceofspades.components.DButton;
import aceofspades.components.DLabel;
import aceofspades.components.DGameInfo;
import aceofspades.game.GameData;
import aceofspades.game.SessionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FSCreateLocal extends FrameState {
    
    private DLabel _labelTitle;
    private JList _listGames;
    private DGameInfo _gameInfo;
    private DLabel _labelPlayerName;
    private JTextField _editPlayerName;
    private DButton _buttonBack;
    private DButton _buttonCreate;
    
    public FSCreateLocal(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);
        setBackgroundImage(Color.darkGray, Main.getImageResource("bgMenu.jpg"));
        
        Font titleFont = new Font("SansSerif", Font.BOLD, 50);
        Color titleFontColor = Color.white;
        Point titlePosition = new Point(paneWidth / 2, 50);
        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color buttonFontColor = Color.white;
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Dimension buttonDimension = new Dimension(300, 60);
        Point backButtonPosition = new Point(paneWidth / 3 - 
                buttonDimension.width / 2, 
                paneHeight - (buttonDimension.height + 30));
        Point createButtonPosition = new Point(2 * paneWidth / 3 - 
                buttonDimension.width / 2, 
                paneHeight - (buttonDimension.height + 30));
        
        int gameInfoWidth = 300;
        Color gameInfoBackgroundColor = new Color(110, 0, 0);
        Font headerFont = new Font("SansSerif", Font.BOLD, 22);
        Color headerFontColor = new Color(220, 220, 220);
        Font contentFont = new Font("SansSerif", Font.BOLD, 18);
        Color contentFontColor = new Color(240, 240, 240);
        Point gameInfoPosition = new Point(paneWidth - (30 + gameInfoWidth), 
                ((100 + paneHeight - (buttonDimension.height + 30)) - 460) / 2);

        Rectangle listGamesBounds = new Rectangle(30, 150, paneWidth - (90 + 
                gameInfoWidth), paneHeight - 250 - (buttonDimension.height + 30));
        
        Point labelPlayerPosition = new Point(listGamesBounds.x, 
                listGamesBounds.y + listGamesBounds.height + 30);        
        
        Rectangle editPlayerBounds = new Rectangle(listGamesBounds.x + 200, 
                listGamesBounds.y + listGamesBounds.height + 16, 
                listGamesBounds.width - 200, 30);
        
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<GameData> gameDataList = Main.getGameDataList();
        for (GameData gameData : gameDataList) {
            listModel.addElement(gameData);
        }        
        
        /**
         * Create Local Game Title
         */
        _labelTitle = new DLabel("Create Local Game");
        _labelTitle.setPosition(titlePosition);
        _labelTitle.setAlignment(DLabel.centerAlign);
        _labelTitle.setFont(titleFont, titleFontColor);
        
        /**
         * Games List
         */
        _listGames = new JList(listModel);
        _listGames.setBounds(listGamesBounds);
        _listGames.addListSelectionListener(new GameListSelection());
        
        /**
         * Game Info
         */
        _gameInfo = new DGameInfo();
        _gameInfo.setPosition(gameInfoPosition);
        _gameInfo.setWidth(gameInfoWidth);
        _gameInfo.setBackgroundColor(gameInfoBackgroundColor);
        _gameInfo.setHeaderFont(headerFont, headerFontColor);
        _gameInfo.setContentFont(contentFont, contentFontColor);
        
        /**
         * Player Name Label
         */
        _labelPlayerName = new DLabel("Player Name");
        _labelPlayerName.setPosition(labelPlayerPosition);
        _labelPlayerName.setFont(headerFont, headerFontColor);
        
        /**
         * Player Name Edit
         */
        _editPlayerName = new JTextField();
        _editPlayerName.setBounds(editPlayerBounds);
        _editPlayerName.getDocument().addDocumentListener(new PlayerNameAction());
        
        /**
         * Back Button
         */
        _buttonBack = new DButton("Back");
        _buttonBack.setPosition(backButtonPosition);
        _buttonBack.setDimensions(buttonDimension);
        _buttonBack.setFont(buttonFont, buttonFontColor);
        _buttonBack.setBackground(buttonColor);
        _buttonBack.setHoverBackground(buttonHoverColor);
        _buttonBack.setAction(new BackAction());
        
        /**
         * Create Game Button
         */
        _buttonCreate = new DButton("Create Game");
        _buttonCreate.setEnabled(false);
        _buttonCreate.setPosition(createButtonPosition);
        _buttonCreate.setDimensions(buttonDimension);
        _buttonCreate.setFont(buttonFont, buttonFontColor);
        _buttonCreate.setBackground(buttonColor);
        _buttonCreate.setHoverBackground(buttonHoverColor);
        _buttonCreate.setAction(new CreateGameAction());
        
        addComponent(_labelTitle);
        frame.getContentPane().add(_listGames);
        addComponent(_gameInfo);
        addComponent(_labelPlayerName);
        frame.getContentPane().add(_editPlayerName);
        addComponent(_buttonBack);
        addComponent(_buttonCreate);
    }
    
    @Override
    public void unload() {
        super.unload();
        _frame.getContentPane().remove(_listGames);
        _frame.getContentPane().remove(_editPlayerName);
    }
    
    private class GameListSelection implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent lse) {
            _buttonCreate.setEnabled((_listGames.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()));
            Object sel = _listGames.getSelectedValue();
            if ((sel == null) || !(sel instanceof GameData)) {
                return;
            }  
            _gameInfo.updateGameData((GameData) sel);
        }
        
    }
    
    private class PlayerNameAction implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent de) {
            _buttonCreate.setEnabled((_listGames.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()));
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
            _buttonCreate.setEnabled((_listGames.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()));
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
            _buttonCreate.setEnabled((_listGames.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()));
        }
        
    }

    private class BackAction extends DAction {

        @Override
        public void run() {
            _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
        }
    }
    
    private class CreateGameAction extends DAction {

        @Override
        public void run() {
            Object sel = _listGames.getSelectedValue();
            if ((sel == null) || !(sel instanceof GameData)) {
                return;
            }      
            
            SessionManager session = new SessionManager((GameData)sel, 0, 0);
            session.addPlayer(0, session.createHumanPlayer(_editPlayerName.getText()));            
            Main.setSessionManager(session);            
            
            _frame.setFrameState(new FSLobby(_frame, _paneWidth, _paneHeight));                
        }
    }
}
