package aceofspades.components;

import aceofspades.MainFrame;
import aceofspades.game.AIStrategy;
import aceofspades.game.Player;
import aceofspades.game.Slot;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DSlot extends DComponent {
    private Slot _playerSlot;
    private int _state;
    
    private Point _position;    
    private ArrayList<AIStrategy> _AIStrategies;
    private MainFrame _frame;

    private int _nameWidth = 100;
    private int _typeWidth = 100;
    private int _locationWidth = 200;
    private int _closeLabelWidth = 600;
    private int _slotWidth;
    
    private static int _slotHeight = 50;
    private static int _margin = 10;
    private static Dimension _smallButtonDimension = new Dimension(25, 25);
    private static Dimension _largeButtonDimension = new Dimension(70, 30);
    private static Color _borderColor = new Color(40, 40, 40);

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
    
    public DSlot(Slot playerSlot, ArrayList<AIStrategy> AIStrategies, MainFrame frame) {
        _state = playerSlot.getType();
        _playerSlot = playerSlot;
        _AIStrategies = AIStrategies;
        _frame = frame;
        _activeComponents = new ArrayList<>();
        
        Font font = new Font("SansSerif", Font.BOLD, 13);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
        Color fontColor = Color.white;       
        
        Color buttonColor = new Color(213, 85, 0);
        Color buttonHoverColor = new Color(234, 115, 35);
        
        _lblName = new DLabel();
        _lblName.setBorderColor(_borderColor);
        _lblName.showBorder(true);
        
        _lblName.setFont(font, fontColor);
        
        _lblType = new DLabel();
        _lblType.setBorderColor(_borderColor);
        _lblType.showBorder(true);
        
        _lblType.setFont(font, fontColor);
        
        _lblLocation = new DLabel();
        _lblLocation.setBorderColor(_borderColor);
        _lblLocation.showBorder(true);
        
        _lblLocation.setFont(font, fontColor);

        _btnMoveDown = new DButton("\\/");
        _btnMoveDown.setDimensions(_smallButtonDimension);
        _btnMoveDown.setFont(buttonFont, fontColor);
        _btnMoveDown.setBackground(buttonColor);
        _btnMoveDown.setHoverBackground(buttonHoverColor);
        
        _btnMoveUp = new DButton("/\\");
        _btnMoveUp.setDimensions(_smallButtonDimension);
        _btnMoveUp.setFont(buttonFont, fontColor);
        _btnMoveUp.setBackground(buttonColor);
        _btnMoveUp.setHoverBackground(buttonHoverColor);
        
        _btnRemove = new DButton("X");
        _btnRemove.setDimensions(_smallButtonDimension);
        _btnRemove.setFont(buttonFont, fontColor);
        _btnRemove.setBackground(buttonColor);
        _btnRemove.setHoverBackground(buttonHoverColor);
        
        
        _edtName = new JTextField();
        _edtName.getDocument().addDocumentListener(new NameAction());
        
        ArrayList<Object> items = new ArrayList<>();
        items.add("Human Player");
        for (AIStrategy item : _AIStrategies) {
            items.add(item);
        }
        _combType = new JComboBox(items.toArray());
        
        _btnAdd = new DButton("+");
        _btnAdd.setDimensions(_smallButtonDimension);
        _btnAdd.setFont(buttonFont, fontColor);
        _btnAdd.setBackground(buttonColor);
        _btnAdd.setHoverBackground(buttonHoverColor);
        _btnAdd.setEnabled(false);
        
        _btnClose = new DButton("Close");
        _btnClose.setDimensions(_largeButtonDimension);
        _btnClose.setFont(buttonFont, fontColor);
        _btnClose.setBackground(buttonColor);
        _btnClose.setHoverBackground(buttonHoverColor);
        
        
        _lblClosed = new DLabel("-- Closed --");
        _lblClosed.setFont(font, fontColor);
        
        _btnOpen = new DButton("Open");
        _btnOpen.setDimensions(_largeButtonDimension);
        _btnOpen.setFont(buttonFont, fontColor);
        _btnOpen.setBackground(buttonColor);
        _btnOpen.setHoverBackground(buttonHoverColor);       
    }
    
    public AIStrategy getSelectedAIStrategy() {
        Object obj = _combType.getSelectedItem();
        if (obj instanceof AIStrategy) {
            return (AIStrategy) obj;
        } else {
            return null;
        }
    }
    
    public String getName() {
        return _edtName.getText();
    }
    
    public Slot getPlayerSlot() {
        return _playerSlot;
    }
    
    public void setPosition(Point position) {
        _position = new Point(position);
        
        int controlHeight = _slotHeight - 10;
        Dimension nameDimension = new Dimension(_nameWidth, controlHeight);
        Dimension typeDimension = new Dimension(_typeWidth, controlHeight);
        Dimension locationDimension = new Dimension(_locationWidth, controlHeight);
        
        Point basePos = new Point(_position.x + _margin, _position.y + 
                (getHeight() - controlHeight) / 2);
        _lblName.setPosition(basePos);
        _lblName.setDimensions(nameDimension);
        basePos.x += _nameWidth + _margin;
        _lblType.setPosition(basePos);
        _lblType.setDimensions(typeDimension);
        basePos.x += _typeWidth + _margin;
        _lblLocation.setPosition(basePos);
        _lblLocation.setDimensions(locationDimension);
        basePos.x += _locationWidth + _margin;
        basePos.y = _position.y + (getHeight() - _smallButtonDimension.height) / 2;
        _btnMoveDown.setPosition(basePos);        
        basePos.x += _smallButtonDimension.width + _margin;
        _btnMoveUp.setPosition(basePos);        
        basePos.x += _smallButtonDimension.width + _margin;        
        _btnRemove.setPosition(basePos);

        
        basePos = new Point(_position.x + _margin, _position.y + 
                (getHeight() - controlHeight) / 2);
        _edtName.setBounds(basePos.x, basePos.y, _nameWidth, controlHeight);
        basePos.x += _nameWidth + _margin;        
        _combType.setBounds(basePos.x, basePos.y, _typeWidth, controlHeight);
        basePos.x += _typeWidth + _margin;
        basePos.y = _position.y + (getHeight() - _smallButtonDimension.height) / 2;
        _btnAdd.setPosition(basePos);
        basePos.x += _smallButtonDimension.width + _margin;
        basePos.y = _position.y + (getHeight() - _largeButtonDimension.height) / 2;
        _btnClose.setPosition(basePos);
        
        basePos = new Point(_position.x + _margin, _position.y + 
                (getHeight() - controlHeight) / 2);
        _lblClosed.setPosition(basePos);        
        _lblClosed.setDimensions(new Dimension(_closeLabelWidth, controlHeight));
        basePos.x += _closeLabelWidth + _margin;
        basePos.y = _position.y + (getHeight() - _largeButtonDimension.height) / 2;
        _btnOpen.setPosition(basePos);
    }
    
    public void setWidth(int width) {
        _slotWidth = width;
        int diffWidth = width - 7 * _margin - _largeButtonDimension.width - 
                2 * _smallButtonDimension.width;
        _nameWidth = 2 * diffWidth / 7;
        _typeWidth = 2 * diffWidth / 7;
        _locationWidth = 3 * diffWidth / 7;
        _closeLabelWidth = width - 3 * _margin - _largeButtonDimension.width;
        
        setPosition(_position);
    }
    
    public int getWidth() {
        return _slotWidth;
    }

    public static int getHeight() {
        return _slotHeight;
    }
    
    public void setOccupied(boolean isMoveable, boolean isRemovable) {
        unload();

        _activeComponents.clear();
        _activeComponents.add(_lblName);
        _activeComponents.add(_lblType);
        _activeComponents.add(_lblLocation);
        if (isMoveable) {
            _activeComponents.add(_btnMoveUp);
            _activeComponents.add(_btnMoveDown);
        }
        if (isRemovable) {
            _activeComponents.add(_btnRemove);
        }
        
        _lblName.setText(_playerSlot.getPlayer().getName());
        _lblType.setText(_playerSlot.getPlayer().getType());
        _lblLocation.setText(_playerSlot.getPlayer().getLocation());
        
        _state = Slot.typeOccupied;
    }
    
    public void setOpen(boolean isCloseable) {
        unload();
        
        _activeComponents.clear();
        _frame.getContentPane().add(_edtName);
        _frame.getContentPane().add(_combType);
        _activeComponents.add(_btnAdd);
        if (isCloseable) {
            _activeComponents.add(_btnClose);
        }
        
        _state = Slot.typeOpen;
    }

    public void setClosed(boolean isOpenable) {
        unload();

        _activeComponents.clear();
        _activeComponents.add(_lblClosed);
        if (isOpenable) {
            _activeComponents.add(_btnOpen);
        }
        
        _state = Slot.typeClosed;
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
    
    @Override
    public void unload() {
        if (_state == Slot.typeOpen) {
            _frame.getContentPane().remove(_edtName);
            _frame.getContentPane().remove(_combType);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(_position.x, _position.y, this.getWidth(), _slotHeight);
        g.setColor(_borderColor);
        g.drawRect(_position.x, _position.y, this.getWidth(), _slotHeight);
        
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
    
    public void update(boolean isMasterClient) {
        if (_playerSlot.isClosed()) {
            this.setClosed(isMasterClient);
        } else if (_playerSlot.isOpen()) {
            this.setOpen(isMasterClient);
        } else if (_playerSlot.isOccupied()) {
            Player player = _playerSlot.getPlayer();          
            
            this.setOccupied(isMasterClient || player.isLocal(), 
                    (isMasterClient && !player.isCreator()) || 
                    (player.isLocal() && !player.isMaster()));
        }
    }
    
    private class NameAction implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent de) {
            _btnAdd.setEnabled(!_edtName.getText().isEmpty());
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
            _btnAdd.setEnabled(!_edtName.getText().isEmpty());
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
            _btnAdd.setEnabled(!_edtName.getText().isEmpty());
        }
    }
}
