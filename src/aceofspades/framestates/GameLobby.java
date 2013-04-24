package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.framestates.Game;
import aceofspades.handlers.Session;
import aceofspades.handlers.GameManager;
import aceofspades.utils.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class GameLobby extends FrameState {
    
    private DLabel labelTitle;
    private DButton buttonBack;
    private DButton buttonNext;
    private ArrayList<JComboBox> comboBoxPlayerSlots;
    private ArrayList<JButton> buttonSlotOccupys;
    
    public GameLobby(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);
        setBackgroundImage(Color.darkGray, Main.getImageResource("bgMenu.jpg"));
        
        Session session = Main.getActiveSession();
        GameManager game = session.getGameManager();
        
        Font labelFont = new Font("SansSerif", Font.BOLD, 36);
        Color labelFontColor = Color.white;
        Point labelPosition = new Point(paneWidth / 2, 5);
        
        Rectangle playerSlotListBounds = new Rectangle(250, 100, 200, 25);
        Rectangle playerSlotOccupyBounds = new Rectangle(
                playerSlotListBounds.x + playerSlotListBounds.width + 10, 
                playerSlotListBounds.y, 150, playerSlotListBounds.height);
        
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
        labelTitle.setCenterPosition(labelPosition);
        labelTitle.setFont(labelFont, labelFontColor);
        
        /**
         * Create PlayerLists
         */
        comboBoxPlayerSlots = new ArrayList<>();
        buttonSlotOccupys = new ArrayList<>();
        for (int i = 0; i < game.getMaxPlayerCount(); i++) {
            JComboBox cb = new JComboBox();
            cb.setBounds(playerSlotListBounds);
            cb.addItem("Open");
            cb.addItem("Closed");
            cb.addItem("Human(Local)");
            comboBoxPlayerSlots.add(cb);
            
            JButton b = new JButton("Occupy");
            b.setBounds(playerSlotOccupyBounds);
            buttonSlotOccupys.add(b);
            
            playerSlotListBounds.y += playerSlotListBounds.height + 5; 
            playerSlotOccupyBounds.y = playerSlotListBounds.y;
        }
        
        
        /**
         * Back Button
         */
        buttonBack = new DButton("Back");
        buttonBack.setPosition(buttonPosition);
        buttonBack.setDimensions(buttonDimension);
        buttonBack.setFont(buttonFont, buttonFontColor);
        buttonBack.setBackground(buttonColor);
        buttonBack.setHoverBackground(buttonHoverColor);
        buttonBack.setAction(new DAction(null) {

            @Override
            public void run() {
                _frame.setContentManager(new MainMenu(_frame, _paneWidth, _paneHeight));
            }
            
        });
        
        buttonPosition.x = paneWidth - buttonDimension.width -100;
        
        /**
         * Select Game Button
         */
        buttonNext = new DButton("Start Game");
        buttonNext.setPosition(buttonPosition);
        buttonNext.setDimensions(buttonDimension);
        buttonNext.setFont(buttonFont, buttonFontColor);
        buttonNext.setBackground(buttonColor);
        buttonNext.setHoverBackground(buttonHoverColor);
        buttonNext.setAction(new DAction(null) {

            @Override
            public void run() {
                _frame.setContentManager(new Game(_frame, _paneWidth, _paneHeight));
            }
            
        });
        
        addComponent(labelTitle);
        for (JComboBox cb : comboBoxPlayerSlots) {
            _frame.add(cb);
        }
        for (JButton b : buttonSlotOccupys) {
            _frame.add(b);
        }
        addComponent(buttonBack);
        addComponent(buttonNext);
    }
    
    @Override
    public void unload() {
        for (JComboBox cb : comboBoxPlayerSlots) {
            _frame.getContentPane().remove(cb);
        }
        for (JButton b : buttonSlotOccupys) {
            _frame.getContentPane().remove(b);
        }
    }

}
