package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.components.DAction;
import aceofspades.components.DButton;
import aceofspades.components.DLabel;
import aceofspades.components.DSessionInfo;
import aceofspades.components.DSlotManager;
import aceofspades.components.DSlotsAction;
import aceofspades.game.AIPlayer;
import aceofspades.game.AIStrategy;
import aceofspades.game.GameManager;
import aceofspades.game.HumanPlayer;
import aceofspades.game.Player;
import aceofspades.game.SessionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.script.ScriptException;
import javax.swing.JOptionPane;

public class FSOnlineLobby extends FrameState {
    
    private DLabel _labelTitle;
    private DSlotManager _playerSlotManager;
    private DSessionInfo _sessionInfo;
    private DButton _buttonLeave;
    private DButton _buttonStart;
    
    private BufferedReader _onlineIn;
    private PrintWriter _onlineOut;
    private ServerListener _listener;
    
    private LeaveAction _leaveAction;
    private StartAction _startAction;    
    
    private SessionManager _sessionManager;
    
    public FSOnlineLobby(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);
        setBackgroundImage(Color.darkGray, Main.getImageResource("bgMenu.jpg"));
        
        _sessionManager = Main.getSessionManager();
        
        Font titleFont = new Font("SansSerif", Font.BOLD, 50);
        Color titleFontColor = Color.white;
        Point titlePosition = new Point(paneWidth / 2, 50);
        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color buttonFontColor = Color.white;
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Dimension buttonDimension = new Dimension(300, 60);
        Point leaveButtonPosition = new Point(paneWidth / 3 - 
                buttonDimension.width / 2, 
                paneHeight - (buttonDimension.height + 30));
        Point startButtonPosition = new Point(2 * paneWidth / 3 - 
                buttonDimension.width / 2, 
                paneHeight - (buttonDimension.height + 30));
        
        int sessionInfoWidth = 300;
        Color sessionInfoBackgroundColor = new Color(110, 0, 120);
        Font headerFont = new Font("SansSerif", Font.BOLD, 22);
        Color headerFontColor = new Color(220, 220, 220);
        Font contentFont = new Font("SansSerif", Font.BOLD, 18);
        Color contentFontColor = new Color(240, 240, 240);
        Point sessionInfoPosition = new Point(paneWidth - (30 + sessionInfoWidth), 
                ((100 + paneHeight - (buttonDimension.height + 30)) - 460) / 2);
        
        int playerSlotsHeight = 60 * _sessionManager.getPlayerSlots().size();
        int playerSlotsWidth = paneWidth - sessionInfoWidth - 90;
        Point playerSlotsPosition = new Point(30, ((50 + paneHeight - 
                (buttonDimension.height + 30)) - playerSlotsHeight) / 2);
        
        _onlineIn = Main.getOnlineInStream();
        _onlineOut = Main.getOnlineOutStream();
        _listener = new ServerListener();
        
        /**
         * Game Lobby Title
         */
        _labelTitle = new DLabel("Game Lobby");
        _labelTitle.setPosition(titlePosition);
        _labelTitle.setAlignment(DLabel.centerAlign);
        _labelTitle.setFont(titleFont, titleFontColor);
        
        /**
         * Player Slots
         */
        _playerSlotManager = new DSlotManager(_sessionManager, this);
        _playerSlotManager.setPosition(playerSlotsPosition);
        _playerSlotManager.setWidth(playerSlotsWidth);
        _playerSlotManager.setAddAction(new AddPlayerAction(_playerSlotManager));
        _playerSlotManager.setRemoveAction(new RemovePlayerAction(_playerSlotManager));
        _playerSlotManager.setMoveUpAction(new MoveUpAction(_playerSlotManager));
        _playerSlotManager.setMoveDownAction(new MoveDownAction(_playerSlotManager));
        _playerSlotManager.setOpenAction(new OpenSlotAction(_playerSlotManager));
        _playerSlotManager.setCloseAction(new CloseSlotAction(_playerSlotManager));
        _playerSlotManager.update();
        
        /**
         * Session Info
         */
        _sessionInfo = new DSessionInfo();
        _sessionInfo.setPosition(sessionInfoPosition);
        _sessionInfo.setWidth(sessionInfoWidth);
        _sessionInfo.setBackgroundColor(sessionInfoBackgroundColor);
        _sessionInfo.setHeaderFont(headerFont, headerFontColor);
        _sessionInfo.setContentFont(contentFont, contentFontColor); 
        _sessionInfo.updateSessionManager(_sessionManager);
        
        /**
         * Leave Game Button
         */
        _leaveAction = new LeaveAction();    
        _buttonLeave = new DButton("Leave Game");
        _buttonLeave.setPosition(leaveButtonPosition);
        _buttonLeave.setDimensions(buttonDimension);
        _buttonLeave.setFont(buttonFont, buttonFontColor);
        _buttonLeave.setBackground(buttonColor);
        _buttonLeave.setHoverBackground(buttonHoverColor);
        _buttonLeave.setAction(_leaveAction);
        
        /**
         * Start Game Button
         */
        _startAction = new StartAction();
        _buttonStart = new DButton("Start Game");
        _buttonStart.setPosition(startButtonPosition);
        _buttonStart.setDimensions(buttonDimension);
        _buttonStart.setFont(buttonFont, buttonFontColor);
        _buttonStart.setBackground(buttonColor);
        _buttonStart.setHoverBackground(buttonHoverColor);
        _buttonStart.setAction(_startAction);
        _buttonStart.setEnabled(!(_sessionManager.getPlayers().size() < 
                    _sessionManager.getGameData().getMinPlayerCount()));
        
        addComponent(_labelTitle);
        addComponent(_playerSlotManager);
        addComponent(_sessionInfo);        
        addComponent(_buttonLeave);
        if (_sessionManager.isMasterClient()) {
            addComponent(_buttonStart);
        }
        
        
    }
    
    public void startListener() {
        _listener.start();
    }
    
    @Override
    public void unload() {
        super.unload();
        _playerSlotManager.unload();
    }
    
    private class LeaveAction extends DAction {

        @Override
        public synchronized void run() {
            try {
                Main.setSessionManager(null);
                _onlineOut.println("leave");
                _onlineOut.println(_sessionManager.getClientID());
                Main.disconnectOnline();
            } catch (IOException ex) {}
            _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
        }

    }
    
    private class StartAction extends DAction {
        @Override
        public synchronized void run() {
            try {
                _onlineOut.println("start");
                GameManager gameManager = _sessionManager.createGameManager();
                Main.setGameManager(gameManager);
                _frame.setFrameState(new FSOnlineGame(_frame, _paneWidth, _paneHeight));
            } catch (IOException | NoSuchMethodException | ScriptException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Game error", JOptionPane.ERROR_MESSAGE);
                _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
            }
        }
    }
    
    private class AddPlayerAction extends DSlotsAction {

        public AddPlayerAction(DSlotManager playerSlotManager) {
            super(playerSlotManager);
        }
        
        @Override
        public void run() {
            AIStrategy selection = _playerSlot.getSelectedAIStrategy();
            
            _onlineOut.println("addPlayer");
            _onlineOut.println(_playerSlot.getPlayerSlot().getSlotID());
            
            Player player;
            if (selection == null) {
                player = _sessionManager.createHumanPlayer(_playerSlot.getName());
                
                _onlineOut.println(player.getLocalID());
                _onlineOut.println(player.getClientID());
                _onlineOut.println(player.getName());
                _onlineOut.println("Human");
                
            } else {
                player = _sessionManager.createAIPlayer(_playerSlot.getName(), selection);
                
                _onlineOut.println(player.getLocalID());
                _onlineOut.println(player.getClientID());
                _onlineOut.println(player.getName());
                _onlineOut.println(selection.getAIID());
            }
        }
        
        @Override
        public AddPlayerAction clone() {
            AddPlayerAction a = new AddPlayerAction(_playerSlotManager);
            a.setDSlot(_playerSlot);
            return a;            
        }
        
    }
    
    private class RemovePlayerAction extends DSlotsAction {

        public RemovePlayerAction(DSlotManager playerSlotManager) {
            super(playerSlotManager);
        }
        
        @Override
        public void run() {
            _onlineOut.println("removePlayer");
            _onlineOut.println(_playerSlot.getPlayerSlot().getSlotID());
            
        }
        
        @Override
        public RemovePlayerAction clone() {
            RemovePlayerAction a = new RemovePlayerAction(_playerSlotManager);
            a.setDSlot(_playerSlot);
            return a;            
        }
    }
    
    private class MoveUpAction extends DSlotsAction {
        
        public MoveUpAction(DSlotManager playerSlotManager) {
            super(playerSlotManager);
        }

        @Override
        public void run() {
            _onlineOut.println("moveUp");
            _onlineOut.println(_playerSlot.getPlayerSlot().getSlotID());
        }
        
        @Override
        public MoveUpAction clone() {
            MoveUpAction a = new MoveUpAction(_playerSlotManager);
            a.setDSlot(_playerSlot);
            return a;            
        }
    }
    
    private class MoveDownAction extends DSlotsAction {
        
        public MoveDownAction(DSlotManager playerSlotManager) {
            super(playerSlotManager);
        }

        @Override
        public void run() {
            _onlineOut.println("moveDown");
            _onlineOut.println(_playerSlot.getPlayerSlot().getSlotID());
        }
        
        @Override
        public MoveDownAction clone() {
            MoveDownAction a = new MoveDownAction(_playerSlotManager);
            a.setDSlot(_playerSlot);
            return a;            
        }
    }
    
    private class CloseSlotAction extends DSlotsAction {
        
        public CloseSlotAction(DSlotManager playerSlotManager) {
            super(playerSlotManager);
        }

        @Override
        public void run() {
            _onlineOut.println("closeSlot");
            _onlineOut.println(_playerSlot.getPlayerSlot().getSlotID());
        }
        
        @Override
        public CloseSlotAction clone() {
            CloseSlotAction a = new CloseSlotAction(_playerSlotManager);
            a.setDSlot(_playerSlot);
            return a;            
        }
    }
    
    private class OpenSlotAction extends DSlotsAction {
        
        public OpenSlotAction(DSlotManager playerSlotManager) {
            super(playerSlotManager);
        }

        @Override
        public void run() {
            _onlineOut.println("openSlot");
            _onlineOut.println(_playerSlot.getPlayerSlot().getSlotID());
        }
        
        @Override
        public OpenSlotAction clone() {
            OpenSlotAction a = new OpenSlotAction(_playerSlotManager);
            a.setDSlot(_playerSlot);
            return a;            
        }
    }
    
    private class ServerListener extends Thread {
        
        private boolean _done;
        
        public ServerListener() {
            _done = false;
        }
        
        @Override
        public void run() {
            String input;
            
            try {
                while (!_done) {
                    input = _onlineIn.readLine();

                    switch (input) {
                        case "addPlayer":
                            try {
                                int slotID = Integer.parseInt(_onlineIn.readLine());
                                int localID = Integer.parseInt(_onlineIn.readLine());
                                int clientID = Integer.parseInt(_onlineIn.readLine());
                                String name = _onlineIn.readLine();
                                String type = _onlineIn.readLine();

                                if (type.equals("Human")) {
                                    _sessionManager.addPlayer(slotID, new HumanPlayer(_sessionManager, clientID, localID, name));
                                } else {
                                    AIStrategy strategy = _sessionManager.getGameData().getAIStrategy(Integer.parseInt(type));
                                    _sessionManager.addPlayer(slotID, new AIPlayer(_sessionManager, clientID, localID, name, strategy));
                                }
                                
                                _sessionInfo.updateSessionManager(_sessionManager);
            
                                _buttonStart.setEnabled(!(_sessionManager.getPlayers().size() < 
                                        _sessionManager.getGameData().getMinPlayerCount()));
                            } catch (NumberFormatException ex) {}
                            _playerSlotManager.update();
                            break;
                        case "removePlayer":
                            try {
                                int slotID = Integer.parseInt(_onlineIn.readLine());                
                                _sessionManager.removePlayer(slotID);
                                _sessionInfo.updateSessionManager(_sessionManager);
            
                                _buttonStart.setEnabled(!(_sessionManager.getPlayers().size() < 
                                        _sessionManager.getGameData().getMinPlayerCount()));
                            } catch (NumberFormatException ex) {}
                            _playerSlotManager.update();
                            break;
                        case "moveUp":
                            try {
                                int slotID = Integer.parseInt(_onlineIn.readLine());                
                                _sessionManager.moveUp(slotID);
                            } catch (NumberFormatException ex) {}
                            _playerSlotManager.update();
                            break;
                        case "moveDown":
                            try {
                                int slotID = Integer.parseInt(_onlineIn.readLine());                
                                _sessionManager.moveDown(slotID);
                            } catch (NumberFormatException ex) {}
                            _playerSlotManager.update();
                            break;
                        case "openSlot":
                            try {
                                int slotID = Integer.parseInt(_onlineIn.readLine());                
                                _sessionManager.openSlot(slotID);
                                _sessionInfo.updateSessionManager(_sessionManager);
                            } catch (NumberFormatException ex) {}
                            _playerSlotManager.update();
                            break;
                        case "closeSlot":
                            try {
                                int slotID = Integer.parseInt(_onlineIn.readLine());                
                                _sessionManager.closeSlot(slotID);
                                _sessionInfo.updateSessionManager(_sessionManager);
                            } catch (NumberFormatException ex) {}
                            _playerSlotManager.update();
                            break;
                        case "leave":
                            _leaveAction.run();
                            break;
                        case "start":
                            _startAction.run();
                            break;
                    }
                }
            } catch (IOException ex) {
                
            }
        }
    }
    
}
