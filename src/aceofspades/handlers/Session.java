package aceofspades.handlers;

import java.util.ArrayList;

public abstract class Session {
    
    protected GameManager _gameManager;
    protected ArrayList<Player> _playerList;
    
    public Session() {
        _playerList = new ArrayList<>();
    }
    
    public void setGameManager(GameManager g) {
        _gameManager = g;
    }
    
    public GameManager getGameManager() {
        return _gameManager;
    }
    
    public void addPlayer(Player p) {
        _playerList.add(p);
    }
    
    public ArrayList<Player> getPlayers() {
        return _playerList;
    }
    
    public void removePlayer(Player p) {
        _playerList.remove(p);
    }
    
}
