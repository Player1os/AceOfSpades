package aceofspades.components;

import aceofspades.MainFrame;
import aceofspades.game.AIStrategy;
import aceofspades.game.Player;
import aceofspades.game.PlayerSlot;
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
    private PlayerSlot _playerSlot;
    private int _state;
    
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
    private DButton _btnMoveUp;
    private DButton _btnMoveDown;
    private DButton _btnRemove;

    private JTextField _edtName;
    private JComboBox _combType;
    private DButton _btnAdd;
    private DButton _btnClose;

    private DLabel _lblClosed;
    private DButton _btnOpen;

    private ArrayList<DComponent> _activeComponents;
    
    public DPlayerSlot(PlayerSlot playerSlot, ArrayList<AIStrategy> AIStrategies, MainFrame frame) {
        _state = playerSlot.getType();
        _playerSlot = playerSlot;
        _AIStrategies = AIStrategies;
        _frame = frame;
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

        _btnMoveDown = new DButton("\\/");
        _btnMoveDown.setDimensions(smallButtonDimension);
        _btnMoveDown.setFont(font, fontColor);
        _btnMoveDown.setBackground(buttonColor);
        _btnMoveDown.setHoverBackground(buttonHoverColor);
        
        _btnMoveUp = new DButton("/\\");
        _btnMoveUp.setDimensions(smallButtonDimension);
        _btnMoveUp.setFont(font, fontColor);
        _btnMoveUp.setBackground(buttonColor);
        _btnMoveUp.setHoverBackground(buttonHoverColor);
        
        _btnRemove = new DButton("X");
        _btnRemove.setDimensions(smallButtonDimension);
        _btnRemove.setFont(font, fontColor);
        _btnRemove.setBackground(buttonColor);
        _btnRemove.setHoverBackground(buttonHoverColor);
        
        
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
        
        _btnClose = new DButton("Close");
        _btnClose.setDimensions(largeButtonDimension);
        _btnClose.setFont(font, fontColor);
        _btnClose.setBackground(buttonColor);
        _btnClose.setHoverBackground(buttonHoverColor);
        
        
        _lblClosed = new DLabel("-- Closed --");
        _lblClosed.setDimensions(new Dimension(labelWidth, controlHeight));
        _lblClosed.setFont(font, fontColor);
        
        _btnOpen = new DButton("Open");
        _btnOpen.setDimensions(largeButtonDimension);
        _btnOpen.setFont(font, fontColor);
        _btnOpen.setBackground(buttonColor);
        _btnOpen.setHoverBackground(buttonHoverColor);
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
        _btnMoveDown.setPosition(basePos);        
        basePos.x += smallButtonDimension.width + margin;
        _btnMoveUp.setPosition(basePos);        
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
    
    public void setOccupied(Player player, String type, 
            String location, boolean isRemovable) {
        if (_state == PlayerSlot.typeOpen) {
            _frame.getContentPane().remove(_edtName);
            _frame.getContentPane().remove(_combType);
        }

        _activeComponents.clear();
        _activeComponents.add(_lblName);
        _activeComponents.add(_lblType);
        _activeComponents.add(_lblLocation);
        if (isRemovable) {
            _activeComponents.add(_btnMoveUp);
            _activeComponents.add(_btnMoveDown);
            _activeComponents.add(_btnRemove);
        }
        
        _lblName.setText(_playerSlot.getPlayer().getName());
        _lblType.setText(_playerSlot.getPlayer().getType());
        _lblLocation.setText(_playerSlot.getPlayer().getLocation());
        
        _state = PlayerSlot.typeOccupied;
    }
    
    public void setAvailable(boolean isCloseable) {
        _activeComponents.clear();
        _frame.getContentPane().add(_edtName);
        _frame.getContentPane().add(_combType);
        _activeComponents.add(_btnAdd);
        if (isCloseable) {
            _activeComponents.add(_btnClose);
        }
        
        _state = PlayerSlot.typeOpen;
    }

    public void setClosed(boolean isOpenable) {
        if (_playerSlot.getType() == PlayerSlot.typeOpen) {
            _frame.getContentPane().remove(_edtName);
            _frame.getContentPane().remove(_combType);
        }

        _activeComponents.clear();
        _activeComponents.add(_lblClosed);
        if (isOpenable) {
            _activeComponents.add(_btnOpen);
        }
        
        _state = PlayerSlot.typeClosed;
    }
    
    public void setMoveUpAction(DAction a) {
        _btnMoveUp.setAction(a);
    }
    
    public void setMoveDownAction(DAction a) {
        _btnMoveDown.setAction(a);
    }
    
    public void setAddAction(DAction a) {
        _btnAdd.setAction(a);
    }
    
    public void setRemoveAction(DAction a) {
        _btnRemove.setAction(a);
    }
    
    public void setOpenAction(DAction a) {
        _btnOpen.setAction(a);
    }
    
    public void setCloseAction(DAction a) {
        _btnClose.setAction(a);
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
        
        for (DComponent component : _activeComponents) {
            component.draw(g);
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        for (DComponent component : _activeComponents) {
            component.mouseMoved(e);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        for (DComponent component : _activeComponents) {
            component.mousePressed(e);
        }
    }
}
