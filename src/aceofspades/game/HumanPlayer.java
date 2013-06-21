package aceofspades.game;

public class HumanPlayer extends Player{
    
    public HumanPlayer(SessionManager session, int playerID, int clientID, String name) {
        super(session, playerID, clientID, name);
        
    }
    
    @Override
    public String getType() {
        if ((_clientID == SessionManager.MasterID) && (_playerID == Player.MasterID)) {
            return "Session Host";
        } else if (_session.getClientID() == _clientID) {
            if (_playerID == Player.MasterID) {
                return "Local Master Human Player";
            } else {
                return "Local Human Player";
            }
        } else {
            if (_session.getClientID() == Player.MasterID) {
                return "Online Master Human Player";
            } else {
                return "Online Human Player";
            }
        }
    }
    
}
