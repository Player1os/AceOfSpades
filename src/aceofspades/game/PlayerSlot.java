package aceofspades.game;

public class PlayerSlot {
    public final static int typeClosed = -1;
    public final static int typeOpen = 0;
    public final static int typeOccupied = 1;

    private int _type;
    private Player _player;
    
    public PlayerSlot() {
        _type = typeOpen;
        _player = null;
    }
    
    public int getType() {
        return _type;
    }
    
    public Player getPlayer() {
        return _player;
    }
    
    public void setPlayer(Player player) {
        _player = player;
    }
    
    public boolean isOpen() {
        return _type == typeOpen;
    }
    
    public void setOpen() {
        _type = typeOpen;
        _player = null;        
    }
    
    public boolean isClosed() {
        return _type == typeClosed;
    }
    
    public void setClosed() {
        _type = typeClosed;
        _player = null;
    }
    
    public boolean isOccupied() {
        return _type == typeOccupied;
    }
    
    public void setOccupied(Player player) {
        _type = typeOccupied;
        _player = player;
    }
}
