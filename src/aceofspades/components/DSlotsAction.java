package aceofspades.components;

public abstract class DSlotsAction extends DAction {

    protected DSlot _playerSlot;
    protected DSlotManager _playerSlotManager;
    
    public DSlotsAction(DSlotManager playerSlotManager) {
        _playerSlotManager = playerSlotManager;
    }
    
    public void setDSlot(DSlot playerSlot) {
        _playerSlot = playerSlot;
    }

    @Override
    public abstract DSlotsAction clone();
}
