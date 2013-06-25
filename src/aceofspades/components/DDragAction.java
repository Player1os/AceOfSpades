package aceofspades.components;

public abstract class DDragAction extends DAction {
    
    protected boolean _isClicked;
    
    public void setClicked(boolean isClicked) {
        _isClicked = isClicked;
    }
    
    public abstract void onDrag();
    

}
