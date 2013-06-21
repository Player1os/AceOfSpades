package aceofspades.game;

abstract public class Player {
    
    public final static int MasterID = 0;
    
    protected int _playerID;
    protected int _clientID;
    protected int _localID;
    protected String _name;
    protected SessionManager _session;
    
    protected Player _nextPlayer;
    
    public Player(SessionManager session, int playerID, int clientID, String name) {
        _session = session;
        _playerID = playerID;
        _clientID = clientID;
        _name = name;
    }
    
    public int getPlayerID() {
        return _playerID;
    }
    
    public String getName() {
        return _name;
    }
    
    public String getLocation() {
        _session.getPlayerLocation(_playerID);
    }
    
    public abstract String getType();
    
    public void setNextPlayer(Player nextPlayer) {
        _nextPlayer = nextPlayer;
    }
    
    public boolean isMaster() {
        return _playerID != MasterID;
    }
    
}
