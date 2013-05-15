package aceofspades.game;

abstract public class Player {
    
    public final static int MasterID = 0;
    
    protected int _playerID;
    protected int _clientID;
    protected String _name;
    
    protected Player _nextPlayer;
    
    public Player(int playerID, int clientID, String name) {
        _playerID = playerID;
        _clientID = clientID;
        _name = name;
    }
    
    public int getPlayerID() {
        return _playerID;
    }
    
    public int getClientID() {
        return _clientID;
    }
    
    public String getName() {
        return _name;
    }
    
    public abstract String getType(int clientID);
    
    public void setNextPlayer(Player nextPlayer) {
        _nextPlayer = nextPlayer;
    }
    
    public boolean isMaster() {
        return _playerID != MasterID;
    }
    
}
