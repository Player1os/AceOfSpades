package aceofspades.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.*;

/**
 *
 * @author Player1os <player1os at gmail.com>
 */
public class DrawEditor extends JFrame {

    int _width = 1280;
    int _height = 720;    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();    
    
    JPanel MainPanel;
    JPanel EditorPanel;
    JPanel SettingsPanel;
    JMenuBar MenuBar;
    
    JMenu mFile;    
    JMenuItem miNew;
    JMenuItem miOpen;
    JMenuItem miSave;
    JMenuItem miSaveAs;
    JMenuItem miExit;
    
    JMenu mEdit;
    JMenuItem miAddCardSetClass;
    JMenuItem miTemplates;
    
    JTextArea taGameInit;
    JScrollPane spGameInit;
    
    JTextArea taGameRules;
    JScrollPane spGameRules;
    
    JTextArea taGameWinCond;
    JScrollPane spGameWinCond;

    public DrawEditor() {
        final JFrame _frame = this;
        
        this.setTitle("Ace of Spades : Game Editor");
        this.setBounds((screenSize.width / 2) - (_width / 2), (screenSize.height / 2) - (_height / 2), _width, _height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBackground(Color.DARK_GRAY);

        MainPanel = new JPanel();
        MainPanel.setLayout(null);        
        this.add(MainPanel, BorderLayout.CENTER);

        MenuBar = new JMenuBar();

        mFile = new JMenu("File");

        miNew = new JMenuItem("New");
        miNew.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,
                java.awt.Event.CTRL_MASK));
        miNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                //empty the TEXT EDITOR
            }
        });
        mFile.add(miNew);

        miOpen = new JMenuItem("Open");
        miOpen.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O,
                java.awt.Event.CTRL_MASK));
        miOpen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    JFileChooser fc = new JFileChooser(".lua");

                    if (fc.showSaveDialog(_frame) == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        //Open file and write contents to TEXT EDITOR
                    }
                } catch (Exception e) {
                    
                }

            }
        });        
        mFile.add(miOpen);
        
        miSave = new JMenuItem("Save");
        miSave.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
                java.awt.Event.CTRL_MASK));
        miSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    JFileChooser fc = new JFileChooser(".lua");

                    if (fc.showSaveDialog(_frame) == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        //Open file and write TEXT EDITOR contents to current FILE
                    }
                } catch (Exception e) {
                    
                }

            }
        });        
        mFile.add(miSave);
        
        miSaveAs = new JMenuItem("Save As ...");
        miSaveAs.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D,
                java.awt.Event.CTRL_MASK));
        miSaveAs.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    JFileChooser fc = new JFileChooser(".lua");

                    if (fc.showSaveDialog(_frame) == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        //Open file and write TEXT EDITOR contents to current FILE
                    }
                } catch (Exception e) {
                    
                }

            }
        });        
        mFile.add(miSaveAs);
        
        miExit = new JMenuItem("Exit");
        miExit.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X,
                java.awt.Event.CTRL_MASK));
        miExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                _frame.dispose();
            }
        });
        mFile.add(miExit);


        MenuBar.add(mFile);

        this.add(MenuBar, BorderLayout.NORTH);

        EditorPanel = new JPanel();
        EditorPanel.setLayout(null);
        EditorPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        EditorPanel.setLocation(5, 5);
        EditorPanel.setSize(1265, 665);
                  
        taGameInit = new JTextArea();
        taGameInit.setEditable(true);
        spGameInit = new JScrollPane(taGameInit);
        spGameInit.setLocation(5, 5);
        spGameInit.setSize(EditorPanel.getWidth() - 10, 100);
        
        taGameRules = new JTextArea();
        taGameRules.setEditable(true);
        spGameRules = new JScrollPane(taGameRules);
        spGameRules.setLocation(5, 105);
        spGameRules.setSize(EditorPanel.getWidth() - 10, EditorPanel.getHeight() - 210);
        
        taGameWinCond = new JTextArea();
        taGameWinCond.setEditable(true);
        spGameWinCond = new JScrollPane(taGameWinCond);
        spGameWinCond.setLocation(5, EditorPanel.getHeight() - 105);
        spGameWinCond.setSize(EditorPanel.getWidth() - 10, 100);
        
        EditorPanel.add(spGameInit);
        EditorPanel.add(spGameRules);
        EditorPanel.add(spGameWinCond);
        
        MainPanel.add(EditorPanel);

        setDefault();

        this.setVisible(true);
    }
    
    private void setDefault() {
        String text = "";
        try {
            FileReader fr = new FileReader("scripts/templates/tmpFile.lua");
            BufferedReader reader = new BufferedReader(fr);
            String textLine = reader.readLine();
            while (!"-- GameRules BEGIN".equals(textLine)) {
                text = text + textLine + "\n";
                textLine = reader.readLine();
                
                
            }
            taGameInit.setText(text);
            text = "";
            textLine = reader.readLine();
            while (!"-- GameRules END".equals(textLine)) {
                text = text + textLine + "\n";
                textLine = reader.readLine();
            }
            taGameRules.setText(text);
            text = "";
            textLine = reader.readLine();
            while (textLine != null) {
                text = text + textLine + "\n";
                textLine = reader.readLine();
            }
            taGameWinCond.setText(text);            
        } catch(Exception e) {
            
        }        
    }
    
    private void openFile(File file) {
        
    }
}
