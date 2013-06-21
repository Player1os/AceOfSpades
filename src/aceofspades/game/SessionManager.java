package aceofspades.game;

import java.util.ArrayList;

public class SessionManager {
    
    public final static int MasterID = 0;
    private int _clientID;
    private int _clientName;
    
    private GameData _gameData;
    private ArrayList<PlayerSlot> _playerSlots;
    private ArrayList<Player> _players;
    
    public SessionManager(GameData gameData) {
        _gameData = gameData;
        _playerSlots = new ArrayList<>();
        
        for (int i = 0; i < gameData.getMaxPlayerCount(); i++) {
            _playerSlots.add(new PlayerSlot());
        }
    }
    
    public int getClientID() {
        return _clientID;
    }
    
    public boolean addPlayer(int slotID, Player p) {
        if ((slotID < 0) || (slotID >= _playerSlots.size())) {
            return false;
        }
        if (!_playerSlots.get(slotID).isAvailable()) {
            return false;
        }
        
        
        _players.add(p);
        return true;
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
                _frame.setFrameState(new FSMainMenu(_frame));
            } else {
                _playerSlotManager.removePlayers(clientID);
            }
        } else {
            _playerSlotManager.removePlayer(playerID, clientID);
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
    
    
   /* 
    * public PlayerSlotManager(int slotCount, SessionManager session, 
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
    * 
    * public final static int MasterID = 0;
    
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
    
    public SessionManager(int clientID, MainFrame frame) {
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
    

    
    
    public void startGame() throws Exception {
        if (_playerSlotManager != null) {
            _playerList = _playerSlotManager.getPlayerList();
        }
        
        for (int i = 0; i < _playerList.size(); i++) {
            _playerList.get(i).setID(i);
            _playerList.get(i).setNextPlayer(_playerList.get((i + 1) % _playerList.size()));
        }

        _gameManager.startGame(_playerList.size());
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
    }*/
   
}
