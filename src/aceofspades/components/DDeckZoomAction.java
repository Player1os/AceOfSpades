package aceofspades.components;

public abstract class DDeckZoomAction extends DMouseAction {
    
    protected DCard _selectedCard;
    protected DDeckZoom _dDeckZoom;
    
    public DDeckZoomAction(DDeckZoom dDeckZoom) {
        _dDeckZoom = dDeckZoom;
    }
    
    public void setSelectedCard(DCard selectedCard) {
        _selectedCard = selectedCard;
    }
}
