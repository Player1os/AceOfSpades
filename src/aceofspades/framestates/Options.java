package aceofspades.framestates;

import aceofspades.Main;
import aceofspades.MainFrame;
import aceofspades.utils.DAction;
import aceofspades.utils.DButton;
import aceofspades.utils.DLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

public class Options extends FrameState {
    
    private DLabel labelOptions;
    private DLabel labelResolution;
    private DButton buttonBack;

    public Options(MainFrame frame, int paneWidth, int paneHeight) {
        super(frame, paneWidth, paneHeight);
        setBackgroundImage(Color.darkGray, Main.getImageResource("bgMenu.jpg"));
        
        Font bigLabelFont = new Font("SansSerif", Font.BOLD, 36);
        Font smallLabelFont = new Font("SansSerif", Font.BOLD, 28);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Dimension buttonDimension = new Dimension(300, 60);
        Dimension buttonResDimension = new Dimension(200, 40);
        Point controlPosition = new Point(paneWidth / 2, 5);
        Color buttonResColor = new Color(100, 0, 150);
        Color buttonResHoverColor = new Color(100, 50, 150);
        Color buttonColor = new Color(150, 0, 0);
        Color buttonHoverColor = new Color(150, 50, 50);
        Color buttonFontColor = Color.white;
        ArrayList<Dimension> resolutionList = new ArrayList<>();
        resolutionList.add(new Dimension(800, 600));
        resolutionList.add(new Dimension(1024, 768));
        resolutionList.add(new Dimension(1280, 1024));
        resolutionList.add(new Dimension(1600, 1200));
        resolutionList.add(new Dimension(1280, 720));
        resolutionList.add(new Dimension(1920, 1080));
        
        /**
         * Options Title
         */
        labelOptions = new DLabel("Options");
        labelOptions.setCenterPosition(controlPosition);
        labelOptions.setFont(bigLabelFont, Color.white);
        
        controlPosition.x = 255;
        controlPosition.y += 100;

        /**
         * Resolution Title
         */
        labelResolution = new DLabel("Resolution");
        labelResolution.setCenterPosition(controlPosition);
        labelResolution.setFont(smallLabelFont, Color.white);
        
        controlPosition.x = 150;
        controlPosition.y += 50;
        
        /**
         * Resolution Buttons
         */
        for (Dimension r : resolutionList) {
            DButton resolutionButton = new DButton(r.width + " x " + r.height);
            
            resolutionButton.setPosition(controlPosition);
            resolutionButton.setDimensions(buttonResDimension);
            resolutionButton.setFont(buttonFont, buttonFontColor);
            resolutionButton.setBackground(buttonResColor);
            resolutionButton.setHoverBackground(buttonResHoverColor);
            resolutionButton.setAction(new DAction(r) {
              
                @Override
                public void run() {
                    Dimension d = (Dimension)_o;
                    _frame.setResolution(d);
                    _frame.setContentManager(new Options(_frame, d.width, d.height));
                }
                
            });          
            
            controlPosition.y += 50;
            
            addComponent(resolutionButton);
        }
        
        controlPosition.x = 100;
        controlPosition.y += 25;
        
        /**
         * Back Button
         */
        buttonBack = new DButton("Back");
        buttonBack.setPosition(controlPosition);
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
        
        addComponent(labelOptions);
        addComponent(labelResolution);
        addComponent(buttonBack);
    }
}
