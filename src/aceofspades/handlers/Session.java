package aceofspades.handlers;

import aceofspades.MainFrame;
import aceofspades.framestates.MainMenu;
import aceofspades.game.AIPlayer;
import aceofspades.game.AIStrategy;
import aceofspades.game.HumanPlayer;
import aceofspades.game.Player;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Session {
    
    public final static int MasterID = 0;
    
    protected MainFrame _frame;
    protected int _clientID;
    protected HashMap<Integer, String> _clientList;
    protected int _playerIDCounter;
    protected PlayerSlotManager _playerSlotManager;
    protected ArrayList<Player> _playerList;
    
    protected GameManager _gameManager;    
    
    public HumanPlayer createHumanPlayer(String name) {
        HumanPlayer player = new HumanPlayer(_playerIDCounter, _clientID, name);
        _playerIDCounter++;
        return player;
    }
    
    public AIPlayer createAIPlayer(String name, AIStrategy strategy) {
        AIPlayer player = new AIPlayer(_playerIDCounter, _clientID, name, strategy);
        _playerIDCounter++;
        return player;
    }
    
    public Session(int clientID, MainFrame frame) {
        _frame = frame;
        _clientID = clientID;
        _gameManager = null;
        _playerSlotManager = null;
        _playerList = new ArrayList<>();
        _playerIDCounter = 0;
    }
    
    public void setGameManager(GameManager g) {
        _gameManager = g;
    }
    
    public GameManager getGameManager() {
        return _gameManager;
    }
    
    public PlayerSlotManager createPlayerSlotManager() {
        if (_gameManager == null) {
            return null;
        }
        
        int playerSlotCount = _gameManager.getMaxPlayerCount();
        ArrayList<AIStrategy> AIStrategies = _gameManager.getAIStrategies();
        _playerSlotManager = new PlayerSlotManager(playerSlotCount, this, 
                AIStrategies, _frame);
        for (Player p : _playerList) {
            _playerSlotManager.addPlayer(p);
        }
        
        return _playerSlotManager;
    }
    
    public void addPlayer(Player p) {
        _playerList.add(p);
        if (_playerSlotManager != null) {
            _playerSlotManager.addPlayer(p);
        }
    }
    
    public void removePlayer(int playerID, int clientID) {
        for (Player p : _playerList) {
            if ((p.getClientID() == clientID) && (p.getPlayerID() == playerID)) {
                _playerList.remove(p);
                break;
            }
        }
        if (_playerSlotManager == null) {
            return;
        }
        if (playerID == Player.MasterID) {
            if ((clientID == _clientID) || (clientID == MasterID)) {
                _frame.setFrameState(new MainMenu(_frame));
            } else {
                _playerSlotManager.removePlayers(clientID);
            }
        } else {
            _playerSlotManager.removePlayer(playerID, clientID);
        }
    }
    
    public void openSlot(int slotID) {
        if (_playerSlotManager != null) {
            _playerSlotManager.openSlot(slotID);
        }
    }
    
    public void closeSlot(int slotID) {
        if (_playerSlotManager != null) {
            _playerSlotManager.closeSlot(slotID);
        }
    }
    
    public void moveUp(int playerID, int clientID) {
        if (_playerSlotManager != null) {
            _playerSlotManager.moveUp(playerID, clientID);
        }
    }
    
    public void moveDown(int playerID, int clientID) {
        if (_playerSlotManager != null) {
            _playerSlotManager.moveDown(playerID, clientID);
        }
    }
    
    public void leaveGame() {
        removePlayer(Player.MasterID, _clientID);
    }
    
    protected ArrayList<Player> getLocalPlayers() {
        ArrayList<Player> plist = new ArrayList<>();
        for (Player p : _playerList) {
            if (isPlayerLocal(p)) {
                plist.add(p);
            }
        }
        return plist;
    }
    
    public int getClientID() {
        return _clientID;
    }
    
    public boolean isMasterClient() {
        return _clientID == MasterID;
    }

    public boolean isPlayerLocal(Player p) {
        return _clientID == p.getClientID();
    }
   
}
