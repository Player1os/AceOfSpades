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
import java.util.ArrayList;

public class FSOptions extends FrameState {
    
    private DLabel labelTitle;
    private DLabel labelResolution;
    private DButton buttonBack;

    public FSOptions(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);
        setBackgroundImage(Color.darkGray, Main.getImageResource("bgMenu.jpg"));
        
        Font titleFont = new Font("SansSerif", Font.BOLD, 50);
        Color titleColor = Color.white;
        Point titlePosition = new Point(paneWidth / 2, 50);
        
        Font sectionTitleFont = new Font("SansSerif", Font.BOLD, 28);
        Color sectionTitleColor = new Color(220, 220, 220);
        
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);        
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Color buttonFontColor = Color.white;
        Dimension buttonDimension = new Dimension(300, 60);
        
        Dimension resButtonDimension = new Dimension(200, 40);
        Color resButtonColor = new Color(100, 0, 150);
        Color resButtonHoverColor = new Color(100, 50, 150);
        int resButtonDistance = resButtonDimension.height + 10;
        
        Point backButtonPosition = new Point(paneWidth / 3 - 
                buttonDimension.width / 2, paneHeight - (buttonDimension.height + 30));
        
        ArrayList<Dimension> resolutionList = new ArrayList<>();
        resolutionList.add(new Dimension(1024, 768));
        resolutionList.add(new Dimension(1280, 1024));
        resolutionList.add(new Dimension(1600, 1200));
        resolutionList.add(new Dimension(1280, 720));
        resolutionList.add(new Dimension(1920, 1080));
        
        Point resTitlePosition = new Point(205, ((titlePosition.y + 
                paneHeight - (buttonDimension.height + 30)) - 
                (resButtonDimension.height * resolutionList.size() + 50)) / 2);
        Point resButtonPosition = new Point(100, resTitlePosition.y + 50);
        
        /**
         * Options Title
         */
        labelTitle = new DLabel("Options");
        labelTitle.setPosition(titlePosition);
        labelTitle.setAlignment(DLabel.centerAlign);
        labelTitle.setFont(titleFont, titleColor);

        /**
         * Resolution section Title
         */
        labelResolution = new DLabel("Resolution");
        labelResolution.setPosition(resTitlePosition);
        labelResolution.setAlignment(DLabel.centerAlign);
        labelResolution.setFont(sectionTitleFont, sectionTitleColor);
        
        /**
         * Resolution section Buttons
         */
        for (final Dimension resolution : resolutionList) {
            DButton resolutionButton = new DButton(resolution.width + " x " + resolution.height);
            
            resolutionButton.setPosition(resButtonPosition);
            resolutionButton.setDimensions(resButtonDimension);
            resolutionButton.setFont(buttonFont, buttonFontColor);
            resolutionButton.setBackground(resButtonColor);
            resolutionButton.setHoverBackground(resButtonHoverColor);
            resolutionButton.setAction(new ResolutionChangeAction(resolution));
            
            resButtonPosition.y += resButtonDistance;
            
            addComponent(resolutionButton);
        }
        
        /**
         * Back Button
         */
        buttonBack = new DButton("Back");
        buttonBack.setPosition(backButtonPosition);
        buttonBack.setDimensions(buttonDimension);
        buttonBack.setFont(buttonFont, buttonFontColor);
        buttonBack.setBackground(buttonColor);
        buttonBack.setHoverBackground(buttonHoverColor);
        buttonBack.setAction(new BackAction());
        
        addComponent(labelTitle);
        addComponent(labelResolution);
        addComponent(buttonBack);
    }
    
    @Override
    public void unload() {
    
    }
    
    private class ResolutionChangeAction extends DAction {
        
        private Dimension _resolution;
        
        public ResolutionChangeAction(Dimension resolution) {
            _resolution = resolution;
        }
        
        
        @Override
        public void run() {
            _frame.setResolution(_resolution);
            _frame.setFrameState(new FSOptions(_frame, _frame.getContentPane().getWidth(), 
                        _frame.getContentPane().getHeight()));
            Main.setProperty("mainFrameWidth", Integer.toString(_resolution.width));
            Main.setProperty("mainFrameHeight", Integer.toString(_resolution.height));
            Main.writeProperties();
        }
    }
    
    private class BackAction extends DAction {
        
        @Override
        public void run() {
            _frame.setFrameState(new FSMainMenu(_frame, _paneWidth, _paneHeight));
        }
    }
}
