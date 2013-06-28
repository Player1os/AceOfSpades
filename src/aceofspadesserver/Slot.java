package aceofspadesserver;

public class Slot {
    
    private final static int stateClosed = 0;
    private final static int stateOpen = 1;
    private final static int stateOccupied = 2;
    
    private int _slotID;
    private Player _player;
    private int _state;    
    
    public Slot(int slotID) {
        _slotID = slotID;
        _state = stateOpen;
        _player = null;
    }
    
    public int getSlotID() {
        return _slotID;
    }
    
    public boolean isClosed() {
        return _state == stateClosed;
    }
    
    public boolean isOpen() {
        return _state == stateOpen;
    }
    
    public boolean isOccupied() {
        return _state == stateOccupied;
    }
    
    public void setClosed() {
        _state = stateClosed;
        _player = null;
    }
    
    public void setOpen() {
        _state = stateOpen;
        _player = null;
    }
    
    public void setOccupied(Player player) {
        _state = stateOccupied;
        _player = player;
    }
    
    public Player getPlayer() {
        return _player;
    }
    
}
