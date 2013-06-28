package aceofspades.framestates;

import aceofspades.GameException;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FSCreateOnline extends FrameState {
    
    private DLabel _labelTitle;
    private JList _listGames;
    private DGameInfo _gameInfo;
    private DLabel _labelPlayerName;
    private JTextField _editPlayerName;
    private DLabel _labelSessionName;
    private JTextField _editSessionName;
    private DButton _buttonBack;
    private DButton _buttonCreate;
    
    private BufferedReader _onlineIn;
    private PrintWriter _onlineOut;
    
    public FSCreateOnline(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);
        setBackgroundImage(Color.darkGray, Main.getImageResource("bgMenu.jpg"));
        
        Font titleFont = new Font("SansSerif", Font.BOLD, 50);
        Color titleFontColor = Color.white;
        Point titlePosition = new Point(paneWidth / 2, 50);
        
        int gameInfoWidth = 300;
        
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
        
        Color gameInfoBackgroundColor = new Color(110, 0, 0);
        Font headerFont = new Font("SansSerif", Font.BOLD, 22);
        Color headerFontColor = new Color(220, 220, 220);
        Font contentFont = new Font("SansSerif", Font.BOLD, 18);
        Color contentFontColor = new Color(240, 240, 240);
        Point gameInfoPosition = new Point(paneWidth - (30 + gameInfoWidth),
                ((100 + paneHeight - (buttonDimension.height + 30)) - 460) / 2);

        Rectangle listGamesBounds = new Rectangle(30, 150, paneWidth - (90 +
                gameInfoWidth), paneHeight - 350 - (buttonDimension.height + 30));
        
        Point labelPlayerPosition = new Point(listGamesBounds.x,
                listGamesBounds.y + listGamesBounds.height + 30);
        
        Rectangle editPlayerBounds = new Rectangle(listGamesBounds.x + 200,
                listGamesBounds.y + listGamesBounds.height + 16,
                listGamesBounds.width - 200, 30);
        
        Point labelSessionPosition = new Point(listGamesBounds.x,
                labelPlayerPosition.y + 50);
        
        Rectangle editSessionBounds = new Rectangle(listGamesBounds.x + 200, 
                labelPlayerPosition.y + 36, listGamesBounds.width - 200, 30);
        
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<GameData> gameDataList = Main.getGameDataList();
        for (GameData gameData : gameDataList) {
            listModel.addElement(gameData);
        }
        
        _onlineIn = Main.getOnlineInStream();
        _onlineOut = Main.getOnlineOutStream();
        
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
        _editPlayerName.setText(Main.getProperty("defaultPlayerName"));
        _editPlayerName.getDocument().addDocumentListener(new PlayerNameAction());
        
        /**
         * Session Name Label
         */
        _labelSessionName = new DLabel("Session Name");
        _labelSessionName.setPosition(labelSessionPosition);
        _labelSessionName.setFont(headerFont, headerFontColor);
        
        /**
         * Session Name Edit
         */
        _editSessionName = new JTextField();
        _editSessionName.setBounds(editSessionBounds);
        _editSessionName.getDocument().addDocumentListener(new SessionNameAction());
        
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
        _buttonCreate = new DButton("Create Session");
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
        addComponent(_labelSessionName);
        frame.getContentPane().add(_editSessionName);
        addComponent(_buttonBack);
        addComponent(_buttonCreate);
    }
    
    @Override
    public void unload() {
        super.unload();
        _frame.getContentPane().remove(_listGames);
        _frame.getContentPane().remove(_editPlayerName);
        _frame.getContentPane().remove(_editSessionName);
    }
    
    private class GameListSelection implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent lse) {
            _buttonCreate.setEnabled((_listGames.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()) && 
                    (!_editSessionName.getText().isEmpty()));
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
                    (!_editPlayerName.getText().isEmpty()) && 
                    (!_editSessionName.getText().isEmpty()));
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
            _buttonCreate.setEnabled((_listGames.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()) && 
                    (!_editSessionName.getText().isEmpty()));
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
            _buttonCreate.setEnabled((_listGames.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()) && 
                    (!_editSessionName.getText().isEmpty()));
        }
        
    }
    
    private class SessionNameAction implements DocumentListener {
        
        @Override
        public void insertUpdate(DocumentEvent de) {
            _buttonCreate.setEnabled((_listGames.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()) && 
                    (!_editSessionName.getText().isEmpty()));
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
            _buttonCreate.setEnabled((_listGames.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()) && 
                    (!_editSessionName.getText().isEmpty()));
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
            _buttonCreate.setEnabled((_listGames.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()) && 
                    (!_editSessionName.getText().isEmpty()));
        }
        
    }
    
    private class BackAction extends DAction {

        @Override
        public void run() {
            try {
                _onlineOut.println("leave");
                Main.disconnectOnline();
            } catch (IOException ex) {}
            
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
            
            GameData gameData = (GameData)sel;
            
            _onlineOut.println("createSession");
            _onlineOut.println(_editSessionName.getText());
            _onlineOut.println(_editPlayerName.getText());
            _onlineOut.println(gameData.getGameID());
            try {
                if (_onlineIn.readLine().equals("OK")) {
                    int sessionID = Integer.parseInt(_onlineIn.readLine());
                    
                    SessionManager session = new SessionManager((GameData)sel, sessionID, 0);
                    session.addPlayer(0, session.createHumanPlayer(_editPlayerName.getText()));            
                    Main.setSessionManager(session);            

                    FSOnlineLobby fs = new FSOnlineLobby(_frame, _paneWidth, _paneHeight);
                    fs.startListener();
                    _frame.setFrameState(fs);
                } else {
                    throw new GameException("Cannot create game");
                }
            } catch (GameException | IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}