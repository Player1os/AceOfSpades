package aceofspades.utils;

public abstract class DAction {

    public Object _o;
   
    public DAction(Object o) {
        _o = o;
    }
    
    public abstract void run();
}
