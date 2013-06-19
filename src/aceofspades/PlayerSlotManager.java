package aceofspades;

import aceofspades.SessionManager;
import aceofspades.MainFrame;
import aceofspades.game.AIStrategy;
import aceofspades.game.Player;
import aceofspades.components.DPlayerSlot;
import java.awt.Point;
import java.util.ArrayList;

public class PlayerSlotManager {
    private ArrayList<DPlayerSlot> _playerSlots;
    private SessionManager _session;
    
    public PlayerSlotManager(int slotCount, SessionManager session, 
            ArrayList<AIStrategy> AIStrategies, MainFrame frame) {
        _playerSlots = new ArrayList<>(slotCount);
        _session = session;
        
        for (int i = 0; i < slotCount; i++) {
            DPlayerSlot slot = new DPlayerSlot(i, session, AIStrategies, frame);
            slot.setAvailable(_session.isMasterClient());
            _playerSlots.add(slot);
        }
    }
    
    private DPlayerSlot find(int playerID, int clientID) {
        for (DPlayerSlot slot : _playerSlots) {
            if (slot.isOccupied()) {
                Player p = slot.getPlayer();
                if (p != null) {
                    if ((playerID == p.getPlayerID()) && 
                            (clientID == p.getClientID())) {
                        return slot;
                    }
                }
            }
        }
        return null;
    }
    
    private String getLocation(Player player) {
        Player p = player;
        if (!(player.getPlayerID() == Player.MasterID)) {
            p = find(0, player.getClientID()).getPlayer();
        }
        return p.getName() + "'s Machine";
    }
    
    public void addPlayer(Player p) {
        for (DPlayerSlot slot : _playerSlots) {
            if (slot.isAvailable()) {
                Boolean isLocal = _session.isPlayerLocal(p);
                Boolean isRemovable = (isLocal && !p.isMaster()) ||
                    (_session.isMasterClient() && !(p.isMaster() && isLocal));
                slot.setOccupied(p, p.getType(_session.getClientID()), 
                        getLocation(p), isRemovable);
                
                return;
            }
        }
    }
    
    public void removePlayer(int playerID, int clientID) {
        find(playerID, clientID).setAvailable(_session.isMasterClient());
    }
    
    public void removePlayers(int clientID) {
        for (DPlayerSlot slot : _playerSlots) {
            if (slot.isOccupied()) {
                Player p = slot.getPlayer();
                if (p != null) {
                    if (clientID == p.getClientID()) {
                        slot.setAvailable(_session.isMasterClient());
                    }
                }
            }
        }
    }
    
    public void openSlot(int slotID) {
        _playerSlots.get(slotID).setAvailable(_session.isMasterClient());
    }

    public void closeSlot(int slotID) {
        _playerSlots.get(slotID).setClosed(_session.isMasterClient());
    }
    
    public void moveUp(int playerID, int clientID) {
        DPlayerSlot slot1 = find(playerID, clientID);
        Player p = slot1.getPlayer();
        int id1 = _playerSlots.indexOf(slot1);
        int id2 = id1 - 1;
        if (id2 < 0) {
            id2 = _playerSlots.size() - 1;
        }
        do {
            DPlayerSlot slot2 = _playerSlots.get(id2);
            if (slot2.isAvailable()) {
                Boolean isLocal = _session.isPlayerLocal(p);
                Boolean isRemovable = (isLocal && !p.isMaster()) ||
                    (_session.isMasterClient() && !(p.isMaster() && isLocal));
                slot2.setOccupied(p, p.getType(_session.getClientID()), 
                        getLocation(p), isRemovable);
                slot1.setAvailable(_session.isMasterClient());
                return;
            }
            id2 = id2 - 1;
            if (id2 < 0) {
                id2 = _playerSlots.size() - 1;
            }
        } while (id2 != id1);
    }
    
    public void moveDown(int playerID, int clientID) {
        DPlayerSlot slot1 = find(playerID, clientID);
        Player p = slot1.getPlayer();
        int id1 = _playerSlots.indexOf(slot1);
        int id2 = (id1 + 1) % _playerSlots.size();
        do {
            DPlayerSlot slot2 = _playerSlots.get(id2);
            if (slot2.isAvailable()) {
                Boolean isLocal = _session.isPlayerLocal(p);
                Boolean isRemovable = (isLocal && !p.isMaster()) ||
                    (_session.isMasterClient() && !(p.isMaster() && isLocal));
                slot2.setOccupied(p, p.getType(_session.getClientID()), 
                        getLocation(p), isRemovable);
                slot1.setAvailable(_session.isMasterClient());
                return;
            }
            id2 = (id2 + 1) % _playerSlots.size();
        } while (id2 != id1);
    }
    
    public void setPosition(Point position) {
        Point p = new Point(position);
        for (DPlayerSlot slot : _playerSlots) {
            slot.setPosition(p);
            p.y += DPlayerSlot.getHeight() + 5;
        }
    }
    
    public int getWidth() {
        return DPlayerSlot.getWidth();
    }
    
    public ArrayList<DPlayerSlot> getSlots() {
        return _playerSlots;
    }
    
    public ArrayList<Player> getPlayerList() {
        ArrayList<Player> playerList = new ArrayList<>();
        for (DPlayerSlot playerSlot : _playerSlots) {
            playerList.add(playerSlot.getPlayer());
        }
        
        return playerList;
    }
    
}
