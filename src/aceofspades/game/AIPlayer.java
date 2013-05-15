package aceofspades.game;

public class AIPlayer extends Player {
    private AIStrategy _strategy;
    
    public AIPlayer(int playerID, int clientID, String name, AIStrategy strategy) {
        super(playerID, clientID, name);
        _strategy = strategy;
    }

    @Override
    public String getType(int clientID) {
        if (clientID == _clientID) {
            return "Local AI Player";
        } else {
            return "Online AI Player";
        }
    }
}
