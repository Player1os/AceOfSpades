package aceofspades.game;

import java.util.ArrayList;

public class SessionManager {
    private int _clientID;
    private String _clientName;
    
    private int _localIDCounter;
    private GameData _gameData;
    private ArrayList<PlayerSlot> _playerSlots;
    private ArrayList<Player> _players;
    
    public SessionManager(GameData gameData, int clientID, String clientName) {
        _gameData = gameData;
        _clientID = clientID;
        _clientName = clientName;
        _localIDCounter = 0;
        _playerSlots = new ArrayList<>();
        
        for (int i = 0; i < gameData.getMaxPlayerCount(); i++) {
            _playerSlots.add(new PlayerSlot());
        }
    }
    
    public int getClientID() {
        return _clientID;
    }
    
    public String getPlayerLocation(Player player) {
        int clientID = player.getClientID();
        
        for (Player p : _players) {
            if ((p.getClientID() == clientID) && p.isMaster()) {
                return p.getName() + "'s machine";
            }
        }
        
        return null;
    }
    
    public boolean addPlayer(int slotID, Player player) {
        if ((slotID < 0) || (slotID >= _playerSlots.size())) {
            return false;
        }
        if (!_playerSlots.get(slotID).isOpen()) {
            return false;
        }
        
        _playerSlots.get(slotID).setOccupied(player);
        _players.add(player);
        return true;
    }
    
    public boolean removePlayer(int slotID) {
        if ((slotID < 0) || (slotID >= _playerSlots.size())) {
            return false;
        }
        if (!_playerSlots.get(slotID).isOccupied()) {
            return false;
        }
        
        Player player = _playerSlots.get(slotID).getPlayer();
        _playerSlots.get(slotID).setOpen();
        _players.remove(player);
        
        return true;
    }
    
    public boolean moveUp(int slotID) {
        if ((slotID < 0) || (slotID >= _playerSlots.size())) {
            return false;
        }
        if (!_playerSlots.get(slotID).isOccupied()) {
            return false;
        }
        
        int prevSlotID = slotID;        
        do {
            prevSlotID = (prevSlotID - 1 + _playerSlots.size()) % _playerSlots.size();
            
            if (_playerSlots.get(prevSlotID).isOpen()) {
                Player player = _playerSlots.get(slotID).getPlayer();
                _playerSlots.get(slotID).setOpen();               
                _playerSlots.get(prevSlotID).setOccupied(player);
                break;
            }            
        } while (slotID != prevSlotID);
        
        return true;
    }
    
    public boolean moveDown(int slotID) {
        if ((slotID < 0) || (slotID >= _playerSlots.size())) {
            return false;
        }
        if (!_playerSlots.get(slotID).isOccupied()) {
            return false;
        }
        
        int nextSlotID = slotID;        
        do {
            nextSlotID = (nextSlotID + 1) % _playerSlots.size();
            
            if (_playerSlots.get(nextSlotID).isOpen()) {
                Player player = _playerSlots.get(slotID).getPlayer();
                _playerSlots.get(slotID).setOpen();
                _playerSlots.get(nextSlotID).setOccupied(player);
                break;
            }            
        } while (slotID != nextSlotID);
        
        return true;
    }
    
    public boolean openSlot(int slotID) {
        if ((slotID < 0) || (slotID >= _playerSlots.size())) {
            return false;
        }
        if (!_playerSlots.get(slotID).isClosed()) {
            return false;
        }
        
        _playerSlots.get(slotID).setOpen();
        
        return true;
    }
    
    public boolean closeSlot(int slotID) {
        if ((slotID < 0) || (slotID >= _playerSlots.size())) {
            return false;
        }
        if (!_playerSlots.get(slotID).isOpen()) {
            return false;
        }
        
        _playerSlots.get(slotID).setClosed();
        
        return true;
    }
    
    public HumanPlayer createHumanPlayer(String name) {
        HumanPlayer player = new HumanPlayer(this, _clientID, _localIDCounter, name);
        _localIDCounter++;
        return player;
    }
    
    public AIPlayer createAIPlayer(String name, AIStrategy strategy) {
        AIPlayer player = new AIPlayer(this, _clientID, _localIDCounter, name, strategy);
        _localIDCounter++;
        return player;
    }

}
