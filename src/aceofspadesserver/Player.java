package aceofspadesserver;

public class Player {
    
    private int _localID;
    private int _clientID;    
    private String _name;
    private String _type;
    
    public Player(int localID, int clientID, String name, String type) {
        _clientID = clientID;
        _localID = localID;
        _name = name;
        _type = type;
    }
    
    public int getLocalID() {
        return _localID;
    }
    
    public int getClientID() {
        return _clientID;
    }
    
    public String getName() {
        return _name;
    }
    
    public String getType() {
        return _type;
    }
}
