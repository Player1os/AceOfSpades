package aceofspades.framestates;

import aceofspades.GameException;
import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.components.DAction;
import aceofspades.components.DButton;
import aceofspades.components.DComponent;
import aceofspades.components.DImage;
import aceofspades.components.DLabel;
import aceofspades.game.AIPlayer;
import aceofspades.game.GameData;
import aceofspades.game.HumanPlayer;
import aceofspades.game.SessionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FSJoinOnline extends FrameState {
    
    private DLabel _labelTitle;
    private JList _listSessions;
    private DSession _sessionInfo;
    private DLabel _labelPlayerName;
    private JTextField _editPlayerName;
    private DButton _buttonRefresh;
    private DButton _buttonBack;
    private DButton _buttonJoin;
    
    private BufferedReader _onlineIn;
    private PrintWriter _onlineOut;
    
    private Rectangle editPlayerBounds;
    private Rectangle listSessionBounds;
    
    public FSJoinOnline(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);
        setBackgroundImage(Color.darkGray, Main.getImageResource("bgMenu.jpg"));
        
        DefaultListModel listModel = new DefaultListModel();
        
        Font titleFont = new Font("SansSerif", Font.BOLD, 50);
        Color titleFontColor = Color.white;
        Point titlePosition = new Point(paneWidth / 2, 50);
        
        int sessionInfoWidth = 300;
        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color buttonFontColor = Color.white;
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Dimension buttonDimension = new Dimension(300, 60);
        Point backButtonPosition = new Point(paneWidth / 3 - 
                buttonDimension.width / 2, 
                paneHeight - (buttonDimension.height + 30));
        Point joinButtonPosition = new Point(2 * paneWidth / 3 - 
                buttonDimension.width / 2, 
                paneHeight - (buttonDimension.height + 30));
        
        Color sessionInfoBackgroundColor = new Color(110, 0, 0);
        Font headerFont = new Font("SansSerif", Font.BOLD, 22);
        Color headerFontColor = new Color(220, 220, 220);
        Font contentFont = new Font("SansSerif", Font.BOLD, 18);
        Color contentFontColor = new Color(240, 240, 240);
        Point sessionInfoPosition = new Point(paneWidth - (30 + sessionInfoWidth),
                ((100 + paneHeight - (buttonDimension.height + 30)) - 460) / 2);

        listSessionBounds = new Rectangle(30, 150, paneWidth - (90 +
                sessionInfoWidth), paneHeight - 350 - (buttonDimension.height + 30));
        
        Point labelPlayerPosition = new Point(listSessionBounds.x,
                listSessionBounds.y + listSessionBounds.height + 30);
        
        editPlayerBounds = new Rectangle(listSessionBounds.x + 200,
                listSessionBounds.y + listSessionBounds.height + 16,
                listSessionBounds.width - 200, 30);
        
        Point refreshButtonPosition = new Point(paneWidth - (30 + sessionInfoWidth), 
                (450 + paneHeight - (buttonDimension.height + 30)) / 2);
        
        _onlineIn = Main.getOnlineInStream();
        _onlineOut = Main.getOnlineOutStream();
        
        /**
         * Select Session Title
         */
        _labelTitle = new DLabel("Select Session");
        _labelTitle.setPosition(titlePosition);
        _labelTitle.setAlignment(DLabel.centerAlign);
        _labelTitle.setFont(titleFont, titleFontColor);
        
        /**
         * Session List
         */
        _listSessions = new JList(listModel);
        _listSessions.setBounds(listSessionBounds);
        _listSessions.addListSelectionListener(new SessionListSelection());
        
        /**
         * Session Info
         */
        _sessionInfo = new DSession();
        _sessionInfo.setPosition(sessionInfoPosition);
        _sessionInfo.setWidth(sessionInfoWidth);
        _sessionInfo.setBackgroundColor(sessionInfoBackgroundColor);
        _sessionInfo.setHeaderFont(headerFont, headerFontColor);
        _sessionInfo.setContentFont(contentFont, contentFontColor); 
        
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
        _editPlayerName.setText(Main.getProperty("defaultPlayerName"));
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
         * Join Session Button
         */
        _buttonJoin = new DButton("Join Session");
        _buttonJoin.setPosition(joinButtonPosition);
        _buttonJoin.setDimensions(buttonDimension);
        _buttonJoin.setFont(buttonFont, buttonFontColor);
        _buttonJoin.setBackground(buttonColor);
        _buttonJoin.setHoverBackground(buttonHoverColor);
        _buttonJoin.setAction(new JoinAction());
        
        /**
         * Refresh Button
         */
        RefreshAction refresh = new RefreshAction();
        _buttonRefresh = new DButton("Refresh");
        _buttonRefresh.setPosition(refreshButtonPosition);
        _buttonRefresh.setDimensions(buttonDimension);
        _buttonRefresh.setFont(buttonFont, buttonFontColor);
        _buttonRefresh.setBackground(buttonColor);
        _buttonRefresh.setHoverBackground(buttonHoverColor);
        _buttonRefresh.setAction(refresh);
        refresh.run();

        addComponent(_labelTitle);
        frame.getContentPane().add(_listSessions);
        addComponent(_sessionInfo);
        addComponent(_labelPlayerName);
        frame.getContentPane().add(_editPlayerName);
        addComponent(_buttonRefresh);
        addComponent(_buttonBack);
        addComponent(_buttonJoin);
    }
    
    @Override
    public void unload() {
        super.unload();
        _frame.getContentPane().remove(_listSessions);
        _frame.getContentPane().remove(_editPlayerName);
    }
    
    private class SessionListSelection implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent lse) {
            _buttonJoin.setEnabled((_listSessions.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()));
            Object sel = _listSessions.getSelectedValue();
            if ((sel == null) || !(sel instanceof Session)) {
                return;
            }  
            _sessionInfo.updateSession((Session) sel);
        }
        
    }
    
    private class PlayerNameAction implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent de) {
            _buttonJoin.setEnabled((_listSessions.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()));
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
            _buttonJoin.setEnabled((_listSessions.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()));
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
            _buttonJoin.setEnabled((_listSessions.getSelectedIndex() != -1) && 
                    (!_editPlayerName.getText().isEmpty()));
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
    
    private class JoinAction extends DAction {
        
        @Override
        public void run() {
            Object sel = _listSessions.getSelectedValue();
            if ((sel == null) || !(sel instanceof Session)) {
                return;
            }
            
            Session sessionInfo = (Session)sel;
            
            _onlineOut.println("joinSession");
            _onlineOut.println(sessionInfo.getSessionID());
            _onlineOut.println(_editPlayerName.getText());
            
            try {
                if (_onlineIn.readLine().equals("OK")) {
                    int clientID = Integer.parseInt(_onlineIn.readLine());
                    
                    SessionManager session = new SessionManager(sessionInfo.getGameData(), sessionInfo.getSessionID(), clientID);
                    Main.setSessionManager(session);
                    
                    int n = session.getGameData().getMaxPlayerCount();
                    for (int i = 0; i < n; i++) {
                        String input = _onlineIn.readLine();
                        
                        switch(input) {
                            case "closeSlot": {
                                int slotID = Integer.parseInt(_onlineIn.readLine());
                                session.closeSlot(slotID);
                                break;}
                            case "addPlayer": {
                                int slotID = Integer.parseInt(_onlineIn.readLine());
                                int localID = Integer.parseInt(_onlineIn.readLine());
                                int playerClientID = Integer.parseInt(_onlineIn.readLine());
                                String name = _onlineIn.readLine();
                                String type = _onlineIn.readLine();
                                        
                                if (type.equals("AI")) {
                                    session.addPlayer(slotID, new AIPlayer(session, playerClientID, localID, name, null));
                                } else {
                                    session.addPlayer(slotID, new HumanPlayer(session, playerClientID, localID, name));
                                }
                                break;}
                            case "openSlot":                                
                                break;
                        }
                    }

                    FSOnlineLobby fs = new FSOnlineLobby(_frame, _paneWidth, _paneHeight);
                    fs.startListener();
                    _frame.setFrameState(fs);
                } else {
                    throw new GameException("Cannot join game");
                }
            } catch (GameException | IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private class RefreshAction extends DAction {
        
        @Override
        public void run() {
            try {
                _onlineOut.println("getSessions");
                int n = Integer.parseInt(_onlineIn.readLine());
                
                DefaultListModel listModel = (DefaultListModel)_listSessions.getModel();
                listModel.clear();
                
                for (int i = 0; i < n; i++) {
                    int sessionID = Integer.parseInt(_onlineIn.readLine());
                    int gameID = Integer.parseInt(_onlineIn.readLine());
                    String sessionName = _onlineIn.readLine();
                    int openSlotCount = Integer.parseInt(_onlineIn.readLine());
                    
                    listModel.addElement(new Session(sessionID, 
                            Main.getGameData(gameID), sessionName, openSlotCount));
                }
                
            } catch (IOException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
    }
    
    private class Session {
        
        private int _sessionID;
        private String _sessionName;
        private GameData _gameData;
        private int _openSlotCount;
        
        public Session(int sessionID, GameData gameData, String sessionName, int openSlotCount) {
            _sessionID = sessionID;
            _sessionName = sessionName;
            _gameData = gameData;
            _openSlotCount = openSlotCount;
        }
        
        public int getSessionID() {
            return _sessionID;
        }
        
        public String getSessionName() {
            return _sessionName;
        }
        
        public GameData getGameData() {
            return _gameData;
        }
        
        public int getOpenSlotCount() {
            return _openSlotCount;
        }
        
        @Override
        public String toString() {
            return _sessionName + "  slots : " + Integer.toString(_openSlotCount) 
                    + "/" + Integer.toString(_gameData.getMaxPlayerCount());
        }
    }
    
    private class DSession extends DComponent {
 
        private Point _position;
        private int _width;
        private DLabel _labelName;
        private DImage _imgIcon;
        private DLabel _labelGameName;
        private DLabel _labelPlayerText;
        private DLabel _labelPlayerCount;
        private DLabel _labelOpenSlotsText;
        private DLabel _labelOpenSlotsCount;
        private Font _headerFont;
        private Font _contentFont;
        private Color _backgroundColor;

        public DSession() {
            _labelName = new DLabel("No Session");
            _labelName.setAlignment(DLabel.centerAlign);
            _imgIcon = new DImage(null);
            _labelGameName = new DLabel("");
            _labelPlayerText = new DLabel("");
            _labelPlayerCount = new DLabel("");
            _labelOpenSlotsText = new DLabel("");
            _labelOpenSlotsCount = new DLabel("");
        }

        public void updateSession(Session session) {
            if (session != null) {
                GameData gameData = session.getGameData();
                _labelName.setText(session.getSessionName());
                _imgIcon.setImage(gameData.getIcon());
                _labelGameName.setText("Game : " + gameData.getName());
                _labelPlayerText.setText("Player count :");
                _labelPlayerCount.setText(Integer.toString(gameData.getMaxPlayerCount()));
                _labelOpenSlotsText.setText("Open slot count :");
                _labelOpenSlotsCount.setText(Integer.toString(session.getOpenSlotCount()));
            } else {
                _labelName.setText("No Session");
                _imgIcon.setImage(null);
                _labelGameName.setText("");
                _labelPlayerText.setText("");
                _labelPlayerCount.setText("");
                _labelOpenSlotsText.setText("");
                _labelOpenSlotsCount.setText("");
            }
        }

        public void setPosition(Point position) {
            _position = position;
        }

        public void setWidth(int width) {
            _width = width;
            _imgIcon.setDimensions(new Dimension(_width - 100, _width - 100));
        }

        public void setHeaderFont(Font font, Color color) {
            _labelName.setFont(font, color);
            _headerFont = font;
        }

        public void setContentFont(Font font, Color color) {
            _labelGameName.setFont(font, color);
            _labelPlayerText.setFont(font, color);
            _labelOpenSlotsText.setFont(font, color);
            _labelOpenSlotsCount.setFont(font, color);
            _contentFont = font;
        }

        public void setBackgroundColor(Color color) {
            _backgroundColor = color;
        }

        @Override
        public void draw(Graphics g) {
            int height = g.getFontMetrics(_headerFont).getHeight() + 10 + 3 * 
                    (g.getFontMetrics(_contentFont).getHeight() + 10) + _width - 50;

            g.setColor(_backgroundColor);
            g.fillRect(_position.x, _position.y, _width, height);
            g.setColor(Color.black);
            g.drawRect(_position.x, _position.y, _width, height);

            Point position = new Point(_position.x + _width / 2, _position.y + 
                    g.getFontMetrics(_headerFont).getAscent());
            _labelName.setPosition(position);
            _labelName.draw(g);

            position.x = _position.x + 50;
            position.y += g.getFontMetrics(_headerFont).getDescent() + 25;
            _imgIcon.setPosition(position);
            _imgIcon.draw(g);

            position.x -= 20;
            position.y += _width - 50;
            _labelGameName.setPosition(position);
            _labelGameName.draw(g);

            position.y += g.getFontMetrics(_contentFont).getHeight() + 8;
            _labelPlayerText.setPosition(position);
            _labelPlayerText.draw(g);

            position.x += g.getFontMetrics(_contentFont).stringWidth("Player count :") + 10;
            _labelPlayerCount.setPosition(position);
            _labelPlayerCount.draw(g);

            position.x = _position.x + 30;
            position.y += g.getFontMetrics(_contentFont).getHeight() + 8;
            _labelOpenSlotsText.setPosition(position);
            _labelOpenSlotsText.draw(g);

            position.x += g.getFontMetrics(_contentFont).stringWidth("Open slot count :") + 10;
            _labelOpenSlotsCount.setPosition(position);
            _labelOpenSlotsCount.draw(g);
        }
        
    }

}