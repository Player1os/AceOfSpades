package aceofspades.game;

public class AIStrategy {
    private String _name;
    
    public AIStrategy(String name) {
        _name = name;
    }
    
    @Override
    public String toString() {
        return "AI Player : " + _name;
    }
    
}
