package aceofspades.game;

public class HumanPlayer extends Player{
    
    public HumanPlayer(SessionManager session, int clientID, 
            int localID, String name) {
        super(session, clientID, localID, name);        
    }
    
    @Override
    public String getType() {
        if (this.isCreator()) {
            return "Session Host";
        } else if (this.isLocal()) {
            if (this.isMaster()) {
                return "Master Human";
            } else {
                return "Human";
            }
        } else {
            if (this.isMaster()) {
                return "Online Master Human";
            } else {
                return "Online Human";
            }
        }
    }
    
}
