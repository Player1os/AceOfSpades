package aceofspades.game;

import javax.script.ScriptException;

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
            return "AI Player";
        } else {
            return "Online AI Player";
        }
    }
    
    public void playTurn(GameManager gameManager) throws ScriptException, NoSuchMethodException {
        if (_strategy != null) {
            _strategy.playTurn(gameManager);
        }
    }
}
