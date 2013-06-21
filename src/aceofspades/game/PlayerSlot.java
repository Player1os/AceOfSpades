package aceofspades.game;

public class PlayerSlot {
    public final static int typeClosed = -1;
    public final static int typeAvailable = 0;
    public final static int typeOccupied = 1;

    private int _type;
    private Player _player;
    
    public PlayerSlot() {
        _type = typeAvailable;
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
    
    public boolean isAvailable() {
        return _type == typeAvailable;
    }
}
