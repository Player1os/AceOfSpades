package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.components.DAction;
import aceofspades.components.DButton;
import aceofspades.components.DLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class FSJoinOnline extends FrameState {
    
    private DLabel labelTitle;
    private JList listSessions;
    private DButton buttonBack;
    private DButton buttonNext;
    
    public FSJoinOnline(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);
        setBackgroundImage(Color.darkGray, Main.getImageResource("bgMenu.jpg"));
        
        DefaultListModel listModel = new DefaultListModel();

        
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
         * Select Session Title
         */
        labelTitle = new DLabel("Select Session");
        labelTitle.setPosition(labelPosition);
        labelTitle.setAlignment(DLabel.centerAlign);
        labelTitle.setFont(labelFont, labelFontColor);
        
        /**
         * Session List
         */
        listSessions = new JList(listModel);
        listSessions.setBounds(30, 30, paneWidth - 65, paneHeight - 200);
        
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
                _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
            }
            
        });
        
        buttonPosition.x = paneWidth - buttonDimension.width -100;
        
        /**
         * Select Session Button
         */
        buttonNext = new DButton("Session Session");
        buttonNext.setPosition(buttonPosition);
        buttonNext.setDimensions(buttonDimension);
        buttonNext.setFont(buttonFont, buttonFontColor);
        buttonNext.setBackground(buttonColor);
        buttonNext.setHoverBackground(buttonHoverColor);
        buttonNext.setAction(new DAction() {

            @Override
            public void run() {
                Object sel = listSessions.getSelectedValue();
                if (!sel.equals(-1)) {
                    
                    
                    _frame.setFrameState(new FSLobby(_frame, _paneWidth, _paneHeight));
                }
            }
            
        });
        
        frame.getContentPane().add(listSessions);
        addComponent(buttonBack);
        addComponent(buttonNext);
    }
    
    @Override
    public void unload() {
        super.unload();
        _frame.getContentPane().remove(listSessions);
    }

}