package aceofspades.framestates;

import aceofspades.EditorFrame;
import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.utils.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

public class FSMainMenu extends FrameState {

    private DLabel labelTitle;
    private DButton buttonLocalGame;
    private DButton buttonHostOnlineGame;
    private DButton buttonJoinOnlineGame;
    private DButton buttonOptions;
    private DButton buttonEditor;
    private DButton buttonQuit;
    
    public FSMainMenu(MainFrame frame) {
        this(frame, frame.getContentPane().getWidth(), 
                frame.getContentPane().getHeight());
    }

    public FSMainMenu(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);
        setBackgroundImage(Color.darkGray, Main.getImageResource("bgMenu.jpg"));
        
        Font labelFont = new Font("SansSerif", Font.BOLD, 36);
        Color labelFontColor = Color.white;
        Point labelPosition = new Point(paneWidth / 2, 25);
        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color buttonFontColor = Color.white;
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Dimension buttonDimension = new Dimension(300, 60);
        int buttonDistance = buttonDimension.height + 15;
        Point buttonPosition = 
                new Point(80, (paneHeight - buttonDistance * 6) / 2);
        
        /**
         * Main Menu Title
         */
        labelTitle = new DLabel("Main Menu");
        labelTitle.setPosition(labelPosition);
        labelTitle.setAlignment(DLabel.centerAlign);
        labelTitle.setFont(labelFont, labelFontColor);

        /**
         * Local Game Button
         */
        buttonLocalGame = new DButton("Local Game");
        buttonLocalGame.setPosition(buttonPosition);
        buttonLocalGame.setDimensions(buttonDimension);
        buttonLocalGame.setFont(buttonFont, buttonFontColor);
        buttonLocalGame.setBackground(buttonColor);
        buttonLocalGame.setHoverBackground(buttonHoverColor);
        buttonLocalGame.setAction(new DAction() {

            @Override
            public void run() {
                _frame.setFrameState(
                        new FSCreateLocal(_frame, _paneWidth, _paneHeight));
            }
            
        });

        buttonPosition.y += buttonDistance;
        
        /**
         * Create Online Game Button
         */
        buttonHostOnlineGame = new DButton("Create Online Game");
        buttonHostOnlineGame.setPosition(buttonPosition);
        buttonHostOnlineGame.setDimensions(buttonDimension);
        buttonHostOnlineGame.setFont(buttonFont, buttonFontColor);
        buttonHostOnlineGame.setBackground(buttonColor);
        buttonHostOnlineGame.setHoverBackground(buttonHoverColor);
        buttonHostOnlineGame.setAction(new DAction() {

            @Override
            public void run() {
                //_frame.setFrameState(
                  //      new SelectSession(_frame, _paneWidth, _paneHeight));
            }
            
        });
        
        buttonPosition.y += buttonDistance;
        
        /**
         * Join Online Game Button
         */
        buttonJoinOnlineGame = new DButton("Join Online Game");
        buttonJoinOnlineGame.setPosition(buttonPosition);
        buttonJoinOnlineGame.setDimensions(buttonDimension);
        buttonJoinOnlineGame.setFont(buttonFont, buttonFontColor);
        buttonJoinOnlineGame.setBackground(buttonColor);
        buttonJoinOnlineGame.setHoverBackground(buttonHoverColor);
        buttonJoinOnlineGame.setAction(new DAction() {

            @Override
            public void run() {
                //_frame.setFrameState(
                  //      new SelectSession(_frame, _paneWidth, _paneHeight));
            }
            
        });        

        buttonPosition.y += buttonDistance;
        
        /**
         * Options Button
         */
        buttonOptions = new DButton("Options");
        buttonOptions.setPosition(buttonPosition);
        buttonOptions.setDimensions(buttonDimension);
        buttonOptions.setFont(buttonFont, buttonFontColor);
        buttonOptions.setBackground(buttonColor);
        buttonOptions.setHoverBackground(buttonHoverColor);
        buttonOptions.setAction(new DAction() {

            @Override
            public void run() {
                _frame.setFrameState(
                        new FSOptions(_frame, _paneWidth, _paneHeight));
            }
            
        });

        buttonPosition.y += buttonDistance;
        
        /**
         * Game Editor Button
         */
        buttonEditor = new DButton("Game Editor");
        buttonEditor.setPosition(buttonPosition);
        buttonEditor.setDimensions(buttonDimension);
        buttonEditor.setFont(buttonFont, buttonFontColor);
        buttonEditor.setBackground(buttonColor);
        buttonEditor.setHoverBackground(buttonHoverColor);
        buttonEditor.setAction(new DAction() {

            @Override
            public void run() {
                EditorFrame frame = new EditorFrame();
            }
            
        });

        buttonPosition.y += buttonDistance;
        
        /**
         * Quit Button
         */
        buttonQuit = new DButton("Quit Game");
        buttonQuit.setPosition(buttonPosition);
        buttonQuit.setDimensions(buttonDimension);
        buttonQuit.setFont(buttonFont, buttonFontColor);
        buttonQuit.setBackground(buttonColor);
        buttonQuit.setHoverBackground(buttonHoverColor);
        buttonQuit.setAction(new DAction() {

            @Override
            public void run() {
                WindowEvent wev = new WindowEvent(_frame, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
            }
            
        });
        
        addComponent(labelTitle);
        addComponent(buttonLocalGame);
        addComponent(buttonHostOnlineGame);
        addComponent(buttonJoinOnlineGame);
        addComponent(buttonOptions);
        addComponent(buttonEditor);
        addComponent(buttonQuit);
    }

}
