package aceofspades.framestates;

import aceofspades.GameException;
import aceofspades.game.SessionManager;
import aceofspades.components.DPlayerSlot;
import aceofspades.components.DAction;
import aceofspades.components.DLabel;
import aceofspades.components.DButton;
import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.components.DGameInfo;
import aceofspades.components.DPlayerSlotManager;
import aceofspades.components.DSessionInfo;
import aceofspades.game.AIStrategy;
import aceofspades.game.GameData;
import aceofspades.game.PlayerSlot;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FSLobby extends FrameState {
    
    private DLabel _labelTitle;
    private DPlayerSlotManager _playerSlotManager;
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
        Point playerSlotsPosition = new Point(30, ((100 + paneHeight - 
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
        _playerSlotManager = new DPlayerSlotManager(_sessionManager, _frame);
        _playerSlotManager.setPosition(playerSlotsPosition);
        _playerSlotManager.setWidth(playerSlotsWidth);
        
        /**
         * Session Info
         */
        _sessionInfo = new DSessionInfo();
        _sessionInfo.setPosition(sessionInfoPosition);
        _sessionInfo.setWidth(sessionInfoWidth);
        _sessionInfo.setBackgroundColor(sessionInfoBackgroundColor);
        _sessionInfo.setHeaderFont(headerFont, headerFontColor);
        _sessionInfo.setContentFont(contentFont, contentFontColor);        
        
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
            /*try {
                Main.getActiveSession().startGame();
                _frame.setFrameState(new FSGame(_frame, _paneWidth, _paneHeight));
            } catch (GameException ex) {
                JOptionPane.showMessageDialog(_frame,
                    "Game files are corrupted.",
                    "Game file error",
                    JOptionPane.ERROR_MESSAGE);
                _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
            }                
        */
        }
    }
    
    private class AddPlayerAction extends DAction {

        @Override
        public void run() {
            
        }
        
    }
    
}
