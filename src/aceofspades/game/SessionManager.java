package aceofspades.game;

import aceofspades.framestates.FrameState;
import java.io.IOException;
import java.util.ArrayList;
import javax.script.ScriptException;

public class SessionManager {
    
    private final static int MasterID = 0;
    
    private int _sessionID;
    private int _clientID;
    
    private int _localIDCounter;
    private GameData _gameData;
    private ArrayList<Slot> _playerSlots;
    private ArrayList<Player> _players;
    
    public SessionManager(GameData gameData, int sessionID, int clientID) {
        _gameData = gameData;
        _sessionID = sessionID;
        _clientID = clientID;
        _localIDCounter = 0;
        _players = new ArrayList<>();
        
        _playerSlots = new ArrayList<>();
        _playerSlots.ensureCapacity(gameData.getMaxPlayerCount());
        for (int i = 0; i < gameData.getMaxPlayerCount(); i++) {
            _playerSlots.add(new Slot(i));
        }
    }
    
    public int getSessionID() {
        return _sessionID;
    }
    
    public int getClientID() {
        return _clientID;
    }
    
    public boolean isMasterClient() {
        return _clientID == MasterID;
    }
    
    public ArrayList<Slot> getPlayerSlots() {
        return _playerSlots;
    }
    
    public ArrayList<Player> getPlayers() {
        return _players;
    }
    
    public int getOpenSlotCount() {
        int result = 0;
        
        for (Slot s : _playerSlots) {
            if (s.isOpen()) {
                result++;
            }
        }
        
        return result;
    }
    
    public GameData getGameData() {
        return _gameData;
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
    
    public GameManager createGameManager(FrameState frameState) throws IOException, ScriptException {
        if (_players.size() < _gameData.getMinPlayerCount()) {
            return null;
        }
        
        ArrayList<Player> players = new ArrayList<>();
        
        for (int i = 0; i < _playerSlots.size(); i++) {
            Slot s = _playerSlots.get(i);
            if (s.isOccupied()) {
                Player p = s.getPlayer();
                p.setPlayerID(i);
                players.add(p);
            }
        }        
        
        return new GameManager(_gameData, players, frameState);
    }

}
