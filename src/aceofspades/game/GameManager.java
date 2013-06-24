package aceofspades.game;

import java.util.ArrayList;
import javax.script.Invocable;

public class GameManager {
    
    private Invocable _engine;
    private ArrayList<Player> _players;
    
    public GameManager(GameData gameData, ArrayList<Player> players) {
        _engine = gameData.getEngine();
        _players = players;
    }
    
    public ArrayList<Deck> getDecks(Player owner, String type) {
        return null;
    }
    
}
