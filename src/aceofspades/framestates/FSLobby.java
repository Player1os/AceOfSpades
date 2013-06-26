package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.components.DAction;
import aceofspades.components.DButton;
import aceofspades.components.DLabel;
import aceofspades.components.DSessionInfo;
import aceofspades.components.DSlotManager;
import aceofspades.components.DSlotsAction;
import aceofspades.game.AIStrategy;
import aceofspades.game.GameManager;
import aceofspades.game.SessionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.io.IOException;
import javax.script.ScriptException;
import javax.swing.JOptionPane;

public class FSLobby extends FrameState {
    
    private DLabel _labelTitle;
    private DSlotManager _playerSlotManager;
    private DSessionInfo _sessionInfo;
    private DButton _buttonLeave;
    private DButton _buttonStart;
    
    private SessionManager _sessionManager;
    
    public FSLobby(MainFrame frame, int paneWidth, int paneHeight) {
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
        _buttonLeave = new DButton("Leave Game");
        _buttonLeave.setPosition(leaveButtonPosition);
        _buttonLeave.setDimensions(buttonDimension);
        _buttonLeave.setFont(buttonFont, buttonFontColor);
        _buttonLeave.setBackground(buttonColor);
        _buttonLeave.setHoverBackground(buttonHoverColor);
        _buttonLeave.setAction(new LeaveAction());
        
        /**
         * Start Game Button
         */
        _buttonStart = new DButton("Start Game");
        _buttonStart.setPosition(startButtonPosition);
        _buttonStart.setDimensions(buttonDimension);
        _buttonStart.setFont(buttonFont, buttonFontColor);
        _buttonStart.setBackground(buttonColor);
        _buttonStart.setHoverBackground(buttonHoverColor);
        _buttonStart.setAction(new StartAction());
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
    
    @Override
    public void unload() {
        super.unload();
        _playerSlotManager.unload();
    }
    
    private class LeaveAction extends DAction {

        @Override
        public void run() {
            Main.setSessionManager(null);
            _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
        }

    }
    
    private class StartAction extends DAction {
        @Override
        public void run() {
            try {
                FrameState fs = new FSGame(_frame, _paneWidth, _paneHeight);                
                GameManager gameManager = _sessionManager.createGameManager(fs);
                gameManager.startGame();
                Main.setGameManager(gameManager);
                _frame.setFrameState(fs);
            } catch (IOException | ScriptException | NoSuchMethodException ex) {
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
            if (selection == null) {
                _sessionManager.addPlayer(_playerSlot.getPlayerSlot().getSlotID(),
                        _sessionManager.createHumanPlayer(_playerSlot.getName()));
            } else {
                _sessionManager.addPlayer(_playerSlot.getPlayerSlot().getSlotID(), 
                        _sessionManager.createAIPlayer(_playerSlot.getName(), selection));
            }
            _sessionInfo.updateSessionManager(_sessionManager);
            
            _buttonStart.setEnabled(!(_sessionManager.getPlayers().size() < 
                    _sessionManager.getGameData().getMinPlayerCount()));
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
            _sessionManager.removePlayer(_playerSlot.getPlayerSlot().getSlotID());
            _sessionInfo.updateSessionManager(_sessionManager);
            
            _buttonStart.setEnabled(!(_sessionManager.getPlayers().size() < 
                    _sessionManager.getGameData().getMinPlayerCount()));
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
            _sessionManager.moveUp(_playerSlot.getPlayerSlot().getSlotID());
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
            _sessionManager.moveDown(_playerSlot.getPlayerSlot().getSlotID());
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
            _sessionManager.closeSlot(_playerSlot.getPlayerSlot().getSlotID());
            _sessionInfo.updateSessionManager(_sessionManager);
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
            _sessionManager.openSlot(_playerSlot.getPlayerSlot().getSlotID());
            _sessionInfo.updateSessionManager(_sessionManager);
        }
        
        @Override
        public OpenSlotAction clone() {
            OpenSlotAction a = new OpenSlotAction(_playerSlotManager);
            a.setDSlot(_playerSlot);
            return a;            
        }
    }
    
}
