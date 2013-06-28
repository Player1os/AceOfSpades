package aceofspadesserver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;

public class Session {

    private int _clientIDCounter;
    
    private int _sessionID;
    private int _gameID;
    private boolean _isOpen;
    private String _name;    
    private ArrayList<Slot> _slots;
    private TreeMap<Integer, ClientThread> _clients;
    
    public Session(int sessionID, int gameID, String name) {
        _sessionID = sessionID;
        _gameID = gameID;
        _name = name;
        
        _slots = new ArrayList<>();
        int slotCount = Main.getMaxPlayerCount(_gameID);
        _slots.ensureCapacity(slotCount);
        for (int i = 0; i < slotCount; i++) {
            _slots.add(new Slot(i));
        }
        
        _isOpen = true;
        
        _clients = new TreeMap<>();
        _clientIDCounter = 0;
    }
    
    public int getSessionID() {
        return _sessionID;
    }
    
    public int getGameID() {
        return _gameID;
    }
    
    public String getName() {
        return _name;
    }
    
    public int getAvailableSlotCount() {
        int result = 0;
        for (Slot slot : _slots) {
            if (slot.isOpen()) {
                result++;
            }
        }
        
        return result;
    }
    
    public boolean isOpen() {
        return _isOpen;
    }
    
    public synchronized int addClient(ClientThread client, String name) throws Exception {
        if (!_isOpen) {
            return -1;
        }
        boolean done = false;
        for (Slot slot : _slots) {
            if (slot.isOpen()) {
                addPlayer(slot.getSlotID(), 0, _clientIDCounter, name, "Human");
                done = true;
                break;
            }
        }
        if (!done) {
            throw new Exception();
        }
        
        _clients.put(_clientIDCounter, client);
        _clientIDCounter++;
        
        return _clientIDCounter - 1;
    }
    
    public synchronized boolean addPlayer(int slotID, int localID, 
            int clientID, String name, String type) {
        if (!_isOpen) {
            return false;
        }
        if ((slotID < 0) || (slotID >= _slots.size())) {
            return false;
        }
        if (!_slots.get(slotID).isOpen()) {
            return false;
        }
        
        _slots.get(slotID).setOccupied(new Player(localID, clientID, name, type));

        ArrayList<ClientThread> clients = new ArrayList<>(_clients.values());
        for (ClientThread client : clients) {
            client.send("addPlayer");
            client.send(slotID);
            client.send(localID);
            client.send(clientID);
            client.send(name);
            client.send(type);
        }

        return true;
    }
    
    public synchronized ClientThread getClient(int clientID) {
        return _clients.get(clientID);
    }
    
    public synchronized boolean removePlayer(int slotID) {
        if (!_isOpen) {
            return false;
        }
        if ((slotID < 0) || (slotID >= _slots.size())) {
            return false;
        }
        if (!_slots.get(slotID).isOccupied()) {
            return false;
        }
        
        _slots.get(slotID).setOpen();

        ArrayList<ClientThread> clients = new ArrayList<>(_clients.values());
        for (ClientThread client : clients) {
            client.send("removePlayer");
            client.send(slotID);
        }           

        return true;
    }
    
    public synchronized boolean moveUp(int slotID) {
        if (!_isOpen) {
            return false;
        }
        if ((slotID < 0) || (slotID >= _slots.size())) {
            return false;
        }
        if (!_slots.get(slotID).isOccupied()) {
            return false;
        }

        int prevSlotID = slotID;  
        do {
            prevSlotID = (prevSlotID - 1 + _slots.size()) % _slots.size();

            if (_slots.get(prevSlotID).isOpen()) {
                Player player = _slots.get(slotID).getPlayer();
                _slots.get(slotID).setOpen();
                _slots.get(prevSlotID).setOccupied(player);
                break;
            }
        } while (slotID != prevSlotID);

        ArrayList<ClientThread> clients = new ArrayList<>(_clients.values());
        for (ClientThread client : clients) {
            client.send("moveUp");
            client.send(slotID);
        }

        return true;
    }
    
    public synchronized boolean moveDown(int slotID) {
        if (!_isOpen) {
            return false;
        }
        if ((slotID < 0) || (slotID >= _slots.size())) {
            return false;
        }
        if (!_slots.get(slotID).isOccupied()) {
            return false;
        }
        
        int nextSlotID = slotID;
        do {
            nextSlotID = (nextSlotID + 1) % _slots.size();
            
            if (_slots.get(nextSlotID).isOpen()) {
                Player player = _slots.get(slotID).getPlayer();
                _slots.get(slotID).setOpen();
                _slots.get(nextSlotID).setOccupied(player);
                break;
            }
        } while (slotID != nextSlotID);
        
        ArrayList<ClientThread> clients = new ArrayList<>(_clients.values());
        for (ClientThread client : clients) {
            client.send("moveDown");
            client.send(slotID);
        }

        return true;
    }
    
    public synchronized boolean closeSlot(int slotID) {
        if (!_isOpen) {
            return false;
        }
        if ((slotID < 0) || (slotID >= _slots.size())) {
            return false;
        }
        if (!_slots.get(slotID).isOpen()) {
            return false;
        }
        
        _slots.get(slotID).setClosed();
        
        ArrayList<ClientThread> clients = new ArrayList<>(_clients.values());
        for (ClientThread client : clients) {
            client.send("closeSlot");
            client.send(slotID);
        }
        
        return true;
    }
    
    public synchronized boolean openSlot(int slotID) {
        if (!_isOpen) {
            return false;
        }
        if ((slotID < 0) || (slotID >= _slots.size())) {
            return false;
        }
        if (!_slots.get(slotID).isClosed()) {
            return false;
        }
        
        _slots.get(slotID).setOpen();
                
        ArrayList<ClientThread> clients = new ArrayList<>(_clients.values());
        for (ClientThread client : clients) {
            client.send("openSlot");
            client.send(slotID);
        }
        
        return true;
    }
    
    public synchronized boolean leave(int clientID) {
        if (_clients.get(clientID) == null) {
            return false;
        }
        
        if (clientID == 0) {
            _isOpen = false;
            ArrayList<ClientThread> clients = new ArrayList<>(_clients.values());
            for (ClientThread client : clients) {
                client.send("leave");
            }
        } else {
            for (Slot slot : _slots) {
                if (slot.isOccupied()) {
                    if (slot.getPlayer().getClientID() == clientID) {
                        removePlayer(slot.getSlotID());
                    }
                }
            }
        }
        
        return true;
    }
    
    public void joinMessage(PrintWriter out) {
        for (Slot slot : _slots) {
            if (slot.isClosed()) {
                out.println("closeSlot");
                out.println(slot.getSlotID());
            } else if (slot.isOccupied()) {
                Player player = slot.getPlayer();
                out.println("addPlayer");
                out.println(slot.getSlotID());
                out.println(player.getLocalID());
                out.println(player.getClientID());
                out.println(player.getName());
                out.println(player.getType());
            } else if (slot.isOpen()) {
                out.println("openSlot");
            }
        }
    }
    
    public void startGame() {
        if (_isOpen) {
            _isOpen = false;
            ArrayList<ClientThread> clients = new ArrayList<>(_clients.values());
            for (ClientThread client : clients) {
                client.send("start");
            }
        }
    }
    
    public Slot getSlot(int slotID) {
        return _slots.get(slotID);
    }
    
    public ArrayList<ClientThread> getClients() {
        return new ArrayList<>(_clients.values());
    }
}
