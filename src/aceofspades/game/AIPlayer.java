package aceofspades.game;

public class AIPlayer extends Player {
    private AIStrategy _strategy;
    
    public AIPlayer(SessionManager session, int playerID, int clientID, String name, AIStrategy strategy) {
        super(session, playerID, clientID, name);
        _strategy = strategy;
    }

    @Override
    public String getType() {
        if (_session.getClientID() == _clientID) {
            return "Local AI Player";
        } else {
            return "Online AI Player";
        }
    }
}
