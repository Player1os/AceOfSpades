package aceofspades.game;

import aceofspades.GameException;
import java.io.File;

public class AIStrategy {
    private String _name;
    
    public AIStrategy(File file) throws GameException, NullPointerException {
        if (file == null) {
           throw new NullPointerException();
        }
        if (!file.exists()) {
            throw new GameException("The AIStrategy path does not exist");
        }
        
        _name = file.getName();
    }
    
    @Override
    public String toString() {
        return "AI : " + _name;
    }
    
}
