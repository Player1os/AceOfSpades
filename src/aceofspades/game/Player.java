package aceofspades.game;

public abstract class Player {
    
    private final static int MasterID = 0;
    
    protected int _playerID;
    protected int _clientID;
    protected int _localID;
    protected String _name;
    protected SessionManager _session;
    
    protected Player _nextPlayer;
    
    public Player(SessionManager session, int clientID, int localID, String name) {
        _session = session;
        _clientID = clientID;
        _localID = localID;
        _name = name;
    }
    
    public int getPlayerID() {
        return _playerID;
    }
    
    public void setPlayerID(int playerID) {
        _playerID = playerID;
    }
    
    public int getClientID() {
        return _clientID;
    }
    
    public int getLocalID() {
        return _localID;
    }
    
    public String getName() {
        return _name;
    }
    
    public String getLocation() {
        return _session.getPlayerLocation(this);
    }
    
    public abstract String getType();
    
    public void setNextPlayer(Player nextPlayer) {
        _nextPlayer = nextPlayer;
    }
    
    public boolean isMaster() {
        return _localID == MasterID;
    }
    
    public boolean isCreator() {
        return (_localID == MasterID) && (_clientID == MasterID);
    }
    
    public boolean isLocal() {
        return _clientID == _session.getClientID();
    }
}
