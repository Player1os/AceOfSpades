package aceofspades.components;

import aceofspades.framestates.FrameState;
import aceofspades.game.GameData;
import aceofspades.game.Slot;
import aceofspades.game.SessionManager;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DSlotManager extends DComponent {

    private ArrayList<DSlot> _playerSlots;
    private boolean _isMasterClient;
    
    public DSlotManager(SessionManager sessionManager, FrameState frameState) {
        _isMasterClient = sessionManager.isMasterClient();
        
        _playerSlots = new ArrayList<>();
        ArrayList<Slot> playerSlots = sessionManager.getPlayerSlots();
        GameData gameData = sessionManager.getGameData();
        for (Slot slot : playerSlots) {
            _playerSlots.add(new DSlot(slot, gameData.getAIStrategies(), 
                    frameState.getFrame()));
        }
    }
    
    public void setPosition(Point position) {
        Point slotPosition = new Point(position);
        for (DSlot dSlot : _playerSlots) {
            dSlot.setPosition(slotPosition);
            slotPosition.y += DSlot.getHeight() + 5;
        }
    }
    
    public void setWidth(int width) {
        for (DSlot dSlot : _playerSlots) {
            dSlot.setWidth(width);
        }
    }

    public void update() {
        for (DSlot dSlot : _playerSlots) {
            dSlot.update(_isMasterClient);
        }
    }
    
    public void setMoveUpAction(DSlotsAction a) {
        for (DSlot dSlot : _playerSlots) {
            a.setDSlot(dSlot);
            dSlot.setMoveUpAction(a.clone());
        }
    }
    
    public void setMoveDownAction(DSlotsAction a) {
        for (DSlot dSlot : _playerSlots) {
            a.setDSlot(dSlot);
            dSlot.setMoveDownAction(a.clone());
        }
    }
    
    public void setAddAction(DSlotsAction a) {
        for (DSlot dSlot : _playerSlots) {
            a.setDSlot(dSlot);
            dSlot.setAddAction(a.clone());
        }
    }
    
    public void setRemoveAction(DSlotsAction a) {
        for (DSlot dSlot : _playerSlots) {
            a.setDSlot(dSlot);
            dSlot.setRemoveAction(a.clone());
        }
    }
    
    public void setOpenAction(DSlotsAction a) {
        for (DSlot dSlot : _playerSlots) {
            a.setDSlot(dSlot);
            dSlot.setOpenAction(a.clone());
        }
    }
    
    public void setCloseAction(DSlotsAction a) {
        for (DSlot dSlot : _playerSlots) {
            a.setDSlot(dSlot);
            dSlot.setCloseAction(a.clone());
        }
    }
    
    @Override
    public void draw(Graphics g) {
        for (DSlot dSlot : _playerSlots) {
            dSlot.draw(g);
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        for (DSlot dSlot : _playerSlots) {
            dSlot.mouseMoved(e);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        for (DSlot dSlot : _playerSlots) {
            dSlot.mousePressed(e);
        }
        
        this.update();
    }
    
    @Override
    public void unload() {
        for (DSlot dSlot : _playerSlots) {
            dSlot.unload();
        }
    }
    
}
