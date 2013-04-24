package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.handlers.*;
import aceofspades.utils.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class CreateLocal extends FrameState {
    
    private DLabel labelTitle;
    private JList listGames;
    private DButton buttonBack;
    private DButton buttonNext;
    
    public CreateLocal(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);
        setBackgroundImage(Color.darkGray, Main.getImageResource("bgMenu.jpg"));
        
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<GameManager> gameList = Main.getGameManagers();
        for (GameManager game : gameList) {
            listModel.addElement(game);
        }
        
        Font labelFont = new Font("SansSerif", Font.BOLD, 36);
        Color labelFontColor = Color.white;
        Point labelPosition = new Point(paneWidth / 2, 5);
        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color buttonFontColor = Color.white;
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Dimension buttonDimension = new Dimension(300, 60);
        Point buttonPosition = new Point(150, paneHeight - buttonDimension.height - 35);
        
        /**
         * Select Game Title
         */
        labelTitle = new DLabel("Select Game");
        labelTitle.setCenterPosition(labelPosition);
        labelTitle.setFont(labelFont, labelFontColor);
        
        /**
         * Games List
         */
        listGames = new JList(listModel);
        listGames.setBounds(30, 70, paneWidth - 60, paneHeight - 300);
        
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
        buttonNext = new DButton("Select Game");
        buttonNext.setPosition(buttonPosition);
        buttonNext.setDimensions(buttonDimension);
        buttonNext.setFont(buttonFont, buttonFontColor);
        buttonNext.setBackground(buttonColor);
        buttonNext.setHoverBackground(buttonHoverColor);
        buttonNext.setAction(new DAction(listGames) {

            @Override
            public void run() {
                JList listGames = (JList)_o;
                Object sel = listGames.getSelectedValue();
                if (sel == null) {
                    return;
                }
                
                LocalSession s = new LocalSession();
                s.setGameManager((GameManager) sel);
                Main.setActiveSession(s);
                _frame.setContentManager(new GameLobby(_frame, _paneWidth, _paneHeight));                
            }
            
        });
        
        addComponent(labelTitle);
        frame.getContentPane().add(listGames);
        addComponent(buttonBack);
        addComponent(buttonNext);
    }
    
    @Override
    public void unload() {
        _frame.getContentPane().remove(listGames);
    }

}
