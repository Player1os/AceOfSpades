package aceofspades.game;

import java.util.ArrayList;
import javax.script.Invocable;

public class GameManager {
    
    private Invocable _engine;
    
    public GameManager(GameData gameData) {
        _engine = gameData.getEngine();
    }
    
    public ArrayList<Deck> getDecks(Player owner, String type) {
        return null;
    }
    
}
