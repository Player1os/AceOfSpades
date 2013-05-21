package aceofspades.framestates;

import aceofspades.handlers.PlayerSlotManager;
import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.handlers.*;
import aceofspades.utils.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

public class FSLobby extends FrameState {
    
    private DLabel labelTitle;
    private DButton buttonBack;
    private DButton buttonNext;
    private PlayerSlotManager _playerSlotManager;
    
    public FSLobby(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);
        setBackgroundImage(Color.darkGray, Main.getImageResource("bgMenu.jpg"));
        
        Session activeSession = Main.getActiveSession();
        GameManager activeGame = activeSession.getGameManager();
        
        Font labelFont = new Font("SansSerif", Font.BOLD, 36);
        Color labelFontColor = Color.white;
        Point labelPosition = new Point(paneWidth / 2, 25);
        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color buttonFontColor = Color.white;
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Dimension buttonDimension = new Dimension(300, 60);
        Point buttonPosition = new Point(150, paneHeight - buttonDimension.height - 35);
        
        /**
         * Game Lobby Title
         */
        labelTitle = new DLabel("Game Lobby");
        labelTitle.setPosition(labelPosition);
        labelTitle.setAlignment(DLabel.centerAlign);
        labelTitle.setFont(labelFont, labelFontColor);
        
        /**
         * Game Name Label
         */
        
        
        /**
         * Create PlayerList
         */
        _playerSlotManager = activeSession.createPlayerSlotManager();
        _playerSlotManager.setPosition(new Point((paneWidth - 
                _playerSlotManager.getWidth()) / 2, 100));
        
        /**
         * Leave Game Button
         */
        buttonBack = new DButton("Leave Game");
        buttonBack.setPosition(buttonPosition);
        buttonBack.setDimensions(buttonDimension);
        buttonBack.setFont(buttonFont, buttonFontColor);
        buttonBack.setBackground(buttonColor);
        buttonBack.setHoverBackground(buttonHoverColor);
        buttonBack.setAction(new DAction() {

            @Override
            public void run() {
                _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
            }
            
        });
        
        buttonPosition.x = paneWidth - buttonDimension.width - 100;
        
        /**
         * Select Game Button
         */
        buttonNext = new DButton("Start Game");
        buttonNext.setPosition(buttonPosition);
        buttonNext.setDimensions(buttonDimension);
        buttonNext.setFont(buttonFont, buttonFontColor);
        buttonNext.setBackground(buttonColor);
        buttonNext.setHoverBackground(buttonHoverColor);
        buttonNext.setAction(new DAction() {

            @Override
            public void run() {
                _frame.setFrameState(new FSGame(_frame, _paneWidth, _paneHeight));
            }
            
        });
        
        addComponent(labelTitle);
        ArrayList<DPlayerSlot> slots = _playerSlotManager.getSlots();
        for (DPlayerSlot slot : slots) {
            addComponent(slot);
        }
        addComponent(buttonBack);
        if (activeSession.isMasterClient()) {
            addComponent(buttonNext);
        }
    }
    
    @Override
    public void unload() {
        ArrayList<DPlayerSlot> slots = _playerSlotManager.getSlots();
        for (DPlayerSlot slot : slots) {
            slot.clearComponents();
        }
        super.unload();
    }

}
