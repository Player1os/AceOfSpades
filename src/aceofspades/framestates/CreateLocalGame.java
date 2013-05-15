package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.handlers.*;
import aceofspades.utils.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;

public class CreateLocalGame extends FrameState {
    
    private DLabel labelTitle;
    private JList listGames;
    private DLabel labelPlayerName;
    private JTextField editPlayerName;
    private DButton buttonBack;
    private DButton buttonNext;
    
    public CreateLocalGame(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);
        setBackgroundImage(Color.darkGray, Main.getImageResource("bgMenu.jpg"));
        
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<GameManager> gameList = Main.getGameManagers();
        for (GameManager game : gameList) {
            listModel.addElement(game);
        }
        
        Font labelFont = new Font("SansSerif", Font.BOLD, 36);
        Color labelFontColor = Color.white;
        Point labelPosition = new Point(paneWidth / 2, 25);
        
        Rectangle listGamesBounds = new Rectangle(30, 70, paneWidth - 60, paneHeight - 400);
        
        Point labelPlayerPosition = new Point(listGamesBounds.x, 
                listGamesBounds.y + listGamesBounds.height + 40);
        Rectangle editPlayerBounds = new Rectangle(listGamesBounds.x + 300, 
                listGamesBounds.y + listGamesBounds.height + 25, 300, 30);
        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color buttonFontColor = Color.white;
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Dimension buttonDimension = new Dimension(300, 60);
        Point buttonPosition = new Point(150, paneHeight - buttonDimension.height - 35);
        
        /**
         * Create Local Game Title
         */
        labelTitle = new DLabel("Create Local Game");
        labelTitle.setPosition(labelPosition);
        labelTitle.setAlignment(DLabel.centerAlign);
        labelTitle.setFont(labelFont, labelFontColor);
        
        /**
         * Games List
         */
        listGames = new JList(listModel);
        listGames.setBounds(listGamesBounds);
        
        /**
         * Player Name Label
         */
        labelPlayerName = new DLabel("Player Name");
        labelPlayerName.setPosition(labelPlayerPosition);
        labelPlayerName.setFont(buttonFont, buttonFontColor);
        
        /**
         * Player Name Edit
         */
        editPlayerName = new JTextField();
        editPlayerName.setBounds(editPlayerBounds);
        
        /**
         * Back Button
         */
        buttonBack = new DButton("Back");
        buttonBack.setPosition(buttonPosition);
        buttonBack.setDimensions(buttonDimension);
        buttonBack.setFont(buttonFont, buttonFontColor);
        buttonBack.setBackground(buttonColor);
        buttonBack.setHoverBackground(buttonHoverColor);
        buttonBack.setAction(new DAction() {

            @Override
            public void run() {
                _frame.setFrameState(new MainMenu(_frame, _paneWidth, _paneHeight));
            }
            
        });
        
        buttonPosition.x = paneWidth - buttonDimension.width -100;
        
        /**
         * Select Game Button
         */
        buttonNext = new DButton("Select Game");
        buttonNext.setPosition(buttonPosition);
        buttonNext.setDimensions(buttonDimension);
        buttonNext.setFont(buttonFont, buttonFontColor);
        buttonNext.setBackground(buttonColor);
        buttonNext.setHoverBackground(buttonHoverColor);
        buttonNext.setAction(new DAction() {

            @Override
            public void run() {
                Object sel = listGames.getSelectedValue();
                if (sel == null) {
                    return;
                }
                
                LocalSession s = new LocalSession(_frame);
                s.setGameManager((GameManager) sel);
                Main.setActiveSession(s);
                s.addPlayer(s.createHumanPlayer(editPlayerName.getText()));
                _frame.setFrameState(new GameLobby(_frame, _paneWidth, _paneHeight));                
            }
            
        });
        
        addComponent(labelTitle);
        frame.getContentPane().add(listGames);
        addComponent(labelPlayerName);
        frame.getContentPane().add(editPlayerName);
        addComponent(buttonBack);
        addComponent(buttonNext);
    }
    
    @Override
    public void unload() {
        super.unload();
        _frame.getContentPane().remove(listGames);
        _frame.getContentPane().remove(editPlayerName);
    }

}
