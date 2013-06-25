package aceofspades.framestates;

import aceofspades.EditorFrame;
import aceofspades.GameException;
import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.components.DAction;
import aceofspades.components.DButton;
import aceofspades.components.DLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class FSMainMenu extends FrameState {

    private DLabel _labelTitle;
    private DButton _buttonLocalGame;
    private DButton _buttonHostOnlineGame;
    private DButton _buttonJoinOnlineGame;
    private DButton _buttonOptions;
    private DButton _buttonEditor;
    private DButton _buttonQuit;
    
    public FSMainMenu(MainFrame frame) {
        this(frame, frame.getContentPane().getWidth(), 
                frame.getContentPane().getHeight());
    }

    public FSMainMenu(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);
        setBackgroundImage(Color.darkGray, Main.getImageResource("bgMenu.jpg"));
        
        Font titleFont = new Font("SansSerif", Font.BOLD, 50);
        Color titleFontColor = Color.white;
        Point titlePosition = new Point(paneWidth / 2, 50);
        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color buttonFontColor = Color.white;
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Dimension buttonDimension = new Dimension(300, 60);
        int buttonDistance = buttonDimension.height + 15;
        Point buttonPosition = 
                new Point(80, (titlePosition.y + paneHeight - buttonDistance * 6) / 2);
        
        /**
         * Main Menu Title
         */
        _labelTitle = new DLabel("Main Menu");
        _labelTitle.setPosition(titlePosition);
        _labelTitle.setAlignment(DLabel.centerAlign);
        _labelTitle.setFont(titleFont, titleFontColor);

        /**
         * Local Game Button
         */
        _buttonLocalGame = new DButton("Create Local Game");
        _buttonLocalGame.setPosition(buttonPosition);
        _buttonLocalGame.setDimensions(buttonDimension);
        _buttonLocalGame.setFont(buttonFont, buttonFontColor);
        _buttonLocalGame.setBackground(buttonColor);
        _buttonLocalGame.setHoverBackground(buttonHoverColor);
        _buttonLocalGame.setAction(new CreateLocalGameAction());

        buttonPosition.y += buttonDistance;
        
        /**
         * Create Online Game Button
         */
        _buttonHostOnlineGame = new DButton("Create Online Game");
        _buttonHostOnlineGame.setPosition(buttonPosition);
        _buttonHostOnlineGame.setDimensions(buttonDimension);
        _buttonHostOnlineGame.setFont(buttonFont, buttonFontColor);
        _buttonHostOnlineGame.setBackground(buttonColor);
        _buttonHostOnlineGame.setHoverBackground(buttonHoverColor);
        _buttonHostOnlineGame.setAction(new CreateOnlineGameAction());
        
        buttonPosition.y += buttonDistance;
        
        /**
         * Join Online Game Button
         */
        _buttonJoinOnlineGame = new DButton("Join Online Game");
        _buttonJoinOnlineGame.setPosition(buttonPosition);
        _buttonJoinOnlineGame.setDimensions(buttonDimension);
        _buttonJoinOnlineGame.setFont(buttonFont, buttonFontColor);
        _buttonJoinOnlineGame.setBackground(buttonColor);
        _buttonJoinOnlineGame.setHoverBackground(buttonHoverColor);
        _buttonJoinOnlineGame.setAction(new JoinOnlineGameAction());

        buttonPosition.y += buttonDistance;
        
        /**
         * Options Button
         */
        _buttonOptions = new DButton("Options");
        _buttonOptions.setPosition(buttonPosition);
        _buttonOptions.setDimensions(buttonDimension);
        _buttonOptions.setFont(buttonFont, buttonFontColor);
        _buttonOptions.setBackground(buttonColor);
        _buttonOptions.setHoverBackground(buttonHoverColor);
        _buttonOptions.setAction(new OptionsAction());

        buttonPosition.y += buttonDistance;
        
        /**
         * Game Editor Button
         */
        _buttonEditor = new DButton("Game Editor");
        _buttonEditor.setPosition(buttonPosition);
        _buttonEditor.setDimensions(buttonDimension);
        _buttonEditor.setFont(buttonFont, buttonFontColor);
        _buttonEditor.setBackground(buttonColor);
        _buttonEditor.setHoverBackground(buttonHoverColor);
        _buttonEditor.setAction(new EditorAction());

        buttonPosition.y += buttonDistance;
        
        /**
         * Quit Button
         */
        _buttonQuit = new DButton("Quit Game");
        _buttonQuit.setPosition(buttonPosition);
        _buttonQuit.setDimensions(buttonDimension);
        _buttonQuit.setFont(buttonFont, buttonFontColor);
        _buttonQuit.setBackground(buttonColor);
        _buttonQuit.setHoverBackground(buttonHoverColor);
        _buttonQuit.setAction(new QuitAction());
        
        addComponent(_labelTitle);
        addComponent(_buttonLocalGame);
        addComponent(_buttonHostOnlineGame);
        addComponent(_buttonJoinOnlineGame);
        addComponent(_buttonOptions);
        addComponent(_buttonEditor);
        addComponent(_buttonQuit);
    }
    
    private class CreateLocalGameAction extends DAction {

        @Override
        public void run() {
            try {
                Main.loadGameDataList();
                _frame.setFrameState(new FSCreateLocal(_frame, _paneWidth, _paneHeight));
            } catch (GameException ex) {
                JOptionPane.showMessageDialog(_frame, ex.getMessage(), 
                        "Fatal error", JOptionPane.ERROR_MESSAGE);
                WindowEvent wev = new WindowEvent(_frame, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
            }
        }

    }
    
    private class CreateOnlineGameAction extends DAction {
        
        @Override
        public void run() {

        }
    }
    
    private class JoinOnlineGameAction extends DAction {
        
        @Override
        public void run() {

        }
    }
    
    private class OptionsAction extends DAction {
        
        @Override
        public void run() {
            _frame.setFrameState(new FSOptions(_frame, _paneWidth, _paneHeight));
        }
    }
    
    private class EditorAction extends DAction {
        
        @Override
        public void run() {
            JOptionPane.showMessageDialog(_frame, "Functionality not yet implemented", 
                    "Feature unavailable", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private class QuitAction extends DAction {
        
        @Override
        public void run() {
            WindowEvent wev = new WindowEvent(_frame, WindowEvent.WINDOW_CLOSING);
            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
        }
    }

}
