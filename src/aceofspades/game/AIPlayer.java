package aceofspades.game;

public class AIPlayer extends Player {
    private AIStrategy _strategy;
    
    public AIPlayer(SessionManager session, int clientID, 
            int localID, String name, AIStrategy strategy) {
        super(session, clientID, localID, name);
        _strategy = strategy;
    }

    @Override
    public String getType() {
        if (this.isLocal()) {
            return "Local AI Player";
        } else {
            return "Online AI Player";
        }
    }
}
