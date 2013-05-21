package aceofspades.game;

import aceofspades.handlers.Session;

public class HumanPlayer extends Player{
    
    public HumanPlayer(int playerID, int clientID, String name) {
        super(playerID, clientID, name);
        
    }
    
    @Override
    public String getType(int clientID) {
        if ((_clientID == Session.MasterID) && (_playerID == Player.MasterID)) {
            return "Session Host";
        } else if (clientID == _clientID) {
            if (_playerID == Player.MasterID) {
                return "Local Master Human Player";
            } else {
                return "Local Human Player";
            }
        } else {
            if (_playerID == Player.MasterID) {
                return "Online Master Human Player";
            } else {
                return "Online Human Player";
            }
        }
    }
    
}