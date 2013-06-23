package aceofspades.components;

import aceofspades.MainFrame;
import aceofspades.game.GameData;
import aceofspades.game.PlayerSlot;
import aceofspades.game.SessionManager;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class DPlayerSlotManager extends DComponent {

    private ArrayList<DPlayerSlot> _playerSlots;
    private boolean _isMasterClient;
    private int _clientID;
    
    public DPlayerSlotManager(SessionManager sessionManager, MainFrame frame) {
        _isMasterClient = sessionManager.isMasterClient();
        _clientID = sessionManager.getClientID();
        
        _playerSlots = new ArrayList<>();
        ArrayList<PlayerSlot> playerSlots = sessionManager.getPlayerSlots();
        GameData gameData = sessionManager.getGameData();
        for (PlayerSlot slot : playerSlots) {
            _playerSlots.add(new DPlayerSlot(slot, gameData.getAIStrategies(), frame));
        }
    }
    
    public void setPosition(Point position) {
        
    }
    
    public void setWidth(int width) {
        
    }

    public void updateSlots() {
        for (DPlayerSlot dSlot : _playerSlots) {
            PlayerSlot slot = dSlot.getPlayerSlot();
            if (slot.isClosed()) {
                dSlot.setClosed(_isMasterClient);
            } else if (slot.isOpen()) {
                dSlot.setOpen(_isMasterClient);
            } else if (slot.isOccupied()) {
                dSlot.setOccupied(_isMasterClient || 
                        (_clientID == slot.getPlayer().getClientID()));
            }
        }
    }
    
    @Override
    public void draw(Graphics g) {
        
    }
    
    
    
}
