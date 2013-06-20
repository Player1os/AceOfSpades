package aceofspades.components;

import aceofspades.MainFrame;
import aceofspades.game.AIStrategy;
import aceofspades.game.Player;
import aceofspades.game.SessionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class DPlayerSlot extends DComponent {
    public final static int typeClosed = -1;
    public final static int typeAvailable = 0;
    public final static int typeOccupied = 1;

    private int _type;
    private Player _player;

    private int _slotID;
    private SessionManager _session;
    private Point _position;
    private ArrayList<AIStrategy> _AIStrategies;
    private MainFrame _frame;

    private static Color borderColor = new Color(40, 40, 40);
    private static int margin = 10;
    private static int controlHeight = 35;
    private static int nameWidth = 200;
    private static int typeWidth = 200;
    private static int locationWidth = 300;
    private static int labelWidth = 700;
    private static Dimension slotDimention = new Dimension(850, 50);
    private static Dimension smallButtonDimension = new Dimension(25, 25);
    private static Dimension largeButtonDimension = new Dimension(70, 30);

    private DLabel _lblName;
    private DLabel _lblType;
    private DLabel _lblLocation;
    private DButton _btnChangePositionUp;
    private DButton _btnChangePositionDown;
    private DButton _btnRemove;

    private JTextField _edtName;
    private JComboBox _combType;
    private DButton _btnAdd;
    private DButton _btnClose;

    private DLabel _lblClosed;
    private DButton _btnOpen;

    private ArrayList<DComponent> _activeComponents;
    
    public DPlayerSlot(int slotID, SessionManager session, 
            ArrayList<AIStrategy> AIStrategies, MainFrame frame) {
        _slotID = slotID;
        _session = session;
        _AIStrategies = AIStrategies;
        _frame = frame;
        _player = null;
        _activeComponents = new ArrayList<>();
        
        Font font = new Font("SansSerif", Font.BOLD, 20);
        Color fontColor = Color.white;
        Color buttonColor = new Color(213, 85, 0);
        Color buttonHoverColor = new Color(234, 115, 35);
        
        Dimension nameDimension = new Dimension(nameWidth, controlHeight);
        Dimension typeDimension = new Dimension(typeWidth, controlHeight);
        Dimension locationDimension = new Dimension(locationWidth, controlHeight);
        
        _lblName = new DLabel();
        _lblName.setBorderColor(borderColor);
        _lblName.showBorder(true);
        _lblName.setDimensions(nameDimension);
        _lblName.setFont(font, fontColor);
        
        _lblType = new DLabel();
        _lblType.setBorderColor(borderColor);
        _lblType.showBorder(true);
        _lblType.setDimensions(typeDimension);
        _lblType.setFont(font, fontColor);
        
        _lblLocation = new DLabel();
        _lblLocation.setBorderColor(borderColor);
        _lblLocation.showBorder(true);
        _lblLocation.setDimensions(locationDimension);
        _lblLocation.setFont(font, fontColor);

        _btnChangePositionDown = new DButton("\\/");
        _btnChangePositionDown.setDimensions(smallButtonDimension);
        _btnChangePositionDown.setFont(font, fontColor);
        _btnChangePositionDown.setBackground(buttonColor);
        _btnChangePositionDown.setHoverBackground(buttonHoverColor);
        _btnChangePositionDown.setAction(new DAction() {

            @Override
            public void run() {
                if (_player != null) {
                    _session.moveDown(_player.getPlayerID(), _player.getClientID());
                }
            }

        });
        
        _btnChangePositionUp = new DButton("/\\");
        _btnChangePositionUp.setDimensions(smallButtonDimension);
        _btnChangePositionUp.setFont(font, fontColor);
        _btnChangePositionUp.setBackground(buttonColor);
        _btnChangePositionUp.setHoverBackground(buttonHoverColor);
        _btnChangePositionUp.setAction(new DAction() {

            @Override
            public void run() {
                if (_player != null) {
                    _session.moveUp(_player.getPlayerID(), _player.getClientID());
                }
            }

        });
        
        _btnRemove = new DButton("X");
        _btnRemove.setDimensions(smallButtonDimension);
        _btnRemove.setFont(font, fontColor);
        _btnRemove.setBackground(buttonColor);
        _btnRemove.setHoverBackground(buttonHoverColor);
        _btnRemove.setAction(new DAction() {

            @Override
            public void run() {
                if (_player != null) {
                    _session.removePlayer(_player.getPlayerID(), _player.getClientID());
                }    
            }

        });
        
        
        _edtName = new JTextField();
        
        ArrayList<Object> items = new ArrayList<>();
        items.add("Human Player");
        for (AIStrategy item : _AIStrategies) {
            items.add(item);
        }
        _combType = new JComboBox(items.toArray());
        
        _btnAdd = new DButton("+");
        _btnAdd.setDimensions(smallButtonDimension);
        _btnAdd.setFont(font, fontColor);
        _btnAdd.setBackground(buttonColor);
        _btnAdd.setHoverBackground(buttonHoverColor);
        _btnAdd.setAction(new DAction() {

            @Override
            public void run() {
                Object o = _combType.getSelectedItem();
                if (o instanceof AIStrategy) {
                    _session.addPlayer(_session.createAIPlayer(
                            _edtName.getText(), (AIStrategy)o));
                } else {
                    _session.addPlayer(_session.createHumanPlayer(
                            _edtName.getText()));
                }
            }

        });
        
        _btnClose = new DButton("Close");
        _btnClose.setDimensions(largeButtonDimension);
        _btnClose.setFont(font, fontColor);
        _btnClose.setBackground(buttonColor);
        _btnClose.setHoverBackground(buttonHoverColor);
        _btnClose.setAction(new DAction() {

            @Override
            public void run() {
                _session.closeSlot(_slotID);
            }

        });
        
        
        _lblClosed = new DLabel("-- Closed --");
        _lblClosed.setDimensions(new Dimension(labelWidth, controlHeight));
        _lblClosed.setFont(font, fontColor);
        
        _btnOpen = new DButton("Open");
        _btnOpen.setDimensions(largeButtonDimension);
        _btnOpen.setFont(font, fontColor);
        _btnOpen.setBackground(buttonColor);
        _btnOpen.setHoverBackground(buttonHoverColor);
        _btnOpen.setAction(new DAction() {

            @Override
            public void run() {
                _session.openSlot(_slotID);
            }

        });
    }
    
    public void setPosition(Point position) {
        _position = new Point(position);
        
        Point basePos = new Point(_position.x + margin, _position.y + 
                (getHeight() - controlHeight) / 2);
        _lblName.setPosition(basePos);        
        basePos.x += nameWidth + margin;
        _lblType.setPosition(basePos);        
        basePos.x += typeWidth + margin;
        _lblLocation.setPosition(basePos);      
        basePos.x += locationWidth + margin;
        basePos.y = _position.y + (getHeight() - smallButtonDimension.height) / 2;
        _btnChangePositionDown.setPosition(basePos);        
        basePos.x += smallButtonDimension.width + margin;
        _btnChangePositionUp.setPosition(basePos);        
        basePos.x += smallButtonDimension.width + margin;        
        _btnRemove.setPosition(basePos);

        
        basePos = new Point(_position.x + margin, _position.y + 
                (getHeight() - controlHeight) / 2);
        _edtName.setBounds(basePos.x, basePos.y, nameWidth, controlHeight);
        basePos.x += nameWidth + margin;        
        _combType.setBounds(basePos.x, basePos.y, typeWidth, controlHeight);
        basePos.x += typeWidth + margin;
        basePos.y = _position.y + (getHeight() - smallButtonDimension.height) / 2;
        _btnAdd.setPosition(basePos);
        basePos.x += smallButtonDimension.width + margin;
        basePos.y = _position.y + (getHeight() - largeButtonDimension.height) / 2;
        _btnClose.setPosition(basePos);
        
        basePos = new Point(_position.x + margin, _position.y + 
                (getHeight() - controlHeight) / 2);
        _lblClosed.setPosition(basePos);        
        basePos.x += labelWidth + margin;
        basePos.y = _position.y + (getHeight() - largeButtonDimension.height) / 2;
        _btnOpen.setPosition(basePos);
    }

    public void clearComponents() {
        if (_type == typeAvailable) {
            _frame.getContentPane().remove(_edtName);
            _frame.getContentPane().remove(_combType);
        }
    }
    
    public void setOccupied(Player player, String type, 
            String location, boolean isRemovable) {
        clearComponents();

        _activeComponents.clear();
        _activeComponents.add(_lblName);
        _activeComponents.add(_lblType);
        _activeComponents.add(_lblLocation);
        _activeComponents.add(_btnChangePositionUp);
        _activeComponents.add(_btnChangePositionDown);
        _activeComponents.add(_btnRemove);
        
        _lblName.setText(player.getName());
        _lblType.setText(type);
        _lblLocation.setText(location);
        
        _player = player;
        _type = typeOccupied;
    }
    
    public void setAvailable(boolean isCloseable) {
        _player = null;
        clearComponents();

        _activeComponents.clear();
        _frame.getContentPane().add(_edtName);
        _frame.getContentPane().add(_combType);
        _activeComponents.add(_btnAdd);
        _activeComponents.add(_btnClose);

        _type = typeAvailable;
    }

    public void setClosed(boolean isOpenable) {
        _player = null;
        clearComponents();

        _activeComponents.clear();
        _activeComponents.add(_lblClosed);
        _activeComponents.add(_btnOpen);
        
        _type = typeClosed;
    }

    public boolean isClosed() {
        return _type == typeClosed;
    }

    public boolean isAvailable() {
        return _type == typeAvailable;
    }

    public boolean isOccupied() {
        return _type > typeAvailable;
    }
    
    public void setPlayer(Player p) {
        if (_type == typeAvailable) {
            _player = p;
        }
    }

    public Player getPlayer() {
        return _player;
    }
    
    public static int getWidth() {
        return slotDimention.width;
    }

    public static int getHeight() {
        return slotDimention.height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(_position.x, _position.y, 
                DPlayerSlot.getWidth(), DPlayerSlot.getHeight());
        g.setColor(borderColor);
        g.drawRect(_position.x, _position.y, 
                DPlayerSlot.getWidth(), DPlayerSlot.getHeight());
        
        int type = _type;
        for (DComponent component : _activeComponents) {
            component.draw(g);
            if (type != _type) {
                break;
            }
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        int type = _type;
        for (DComponent component : _activeComponents) {
            component.mouseMoved(e);
            if (type != _type) {
                break;
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int type = _type;
        for (DComponent component : _activeComponents) {
            component.mousePressed(e);
            if (type != _type) {
                break;
            }
        }
    }
}
