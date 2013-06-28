package aceofspadesserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread {
    
    private int _clientID;
    private Session _session;
    private Socket _socket = null;
    private PrintWriter _out = null;
    private BufferedReader _in = null;
 
    public ClientThread(Socket socket) {
        super("ConnectionThread");
        _socket = socket;
        _clientID = -1;
    }

    @Override
    public void run() {
        
        if (_socket == null) {
            return;
        }
        
        try {
            String input;
            _out = new PrintWriter(_socket.getOutputStream(), true);
            _in = new BufferedReader(new InputStreamReader(
                        _socket.getInputStream()));

            /**
             * Phase 1
             */
            boolean done = false;
            while (!done) {
                input = _in.readLine();
                
                switch (input) {
                    case "getSessions":
                        ArrayList<Session> sessions = Main.getOpenSessions();
                        _out.println(sessions.size());
                        for (Session session : sessions) {
                            int availableSlots = session.getAvailableSlotCount();
                            if (session.isOpen() && (availableSlots > 0)) {
                                _out.println(session.getSessionID());
                                _out.println(session.getGameID());
                                _out.println(session.getName());
                                _out.println(session.getAvailableSlotCount());
                            }
                        }
                        break;
                    case "createSession":
                        try {
                            System.out.println("Creating session .. ");
                            String sessionName = _in.readLine();
                            String creatorName = _in.readLine();
                            int gameID = Integer.parseInt(_in.readLine());
                            
                            if (Main.getMaxPlayerCount(gameID) == 0) {
                                throw new Exception();
                            }
                            
                            _session = Main.createSession(sessionName, gameID, creatorName, this);
                            _clientID = 0;
                            
                            _out.println("OK");
                            _out.println(_session.getSessionID());
                            System.out.println("Session successfully created");
                            done = true;
                        } catch (Exception ex) {
                            _out.println("FAIL");
                        }
                        
                        
                        break;
                    case "joinSession":
                        try {
                            int sessionID = Integer.parseInt(_in.readLine());
                            String playerName = _in.readLine();
                            
                            _session = Main.getSession(sessionID);
                            if ((_session == null) || (_session.getAvailableSlotCount() == 0)) {
                                throw new Exception();
                            }
                            
                            _clientID = _session.addClient(this, playerName);
                            
                            _out.println("OK");
                            _out.println(_clientID);
                            _session.joinMessage(_out);
                            done = true;
                        } catch (Exception ex) {
                            _out.println("FAIL");
                        }                        
                        
                        
                        break;
                    case "leave":
                        _in.close();
                        _out.close();
                        _socket.close();
                        return;
                }
            }
            
            /**
             * Phase 2
             */
            done = false;
            while (!done) {
                input = _in.readLine();
                
                switch (input) {
                    case "addPlayer":
                        try {
                            int slotID = Integer.parseInt(_in.readLine());
                            int localID = Integer.parseInt(_in.readLine());
                            int clientID = Integer.parseInt(_in.readLine());
                            String name = _in.readLine();
                            String type = _in.readLine();
                
                            _session.addPlayer(slotID, localID, clientID, name, type);
                        } catch (NumberFormatException ex) {}
                        break;
                    case "removePlayer":
                        try {
                            int slotID = Integer.parseInt(_in.readLine());
                            
                            Player player = _session.getSlot(slotID).getPlayer();
                            if (player.getLocalID() == 0) {
                                _session.getClient(player.getClientID()).send("leave");
                            } else {
                                _session.removePlayer(slotID);
                            }
                            
                        } catch (NumberFormatException ex) {}
                        
                        break;
                    case "moveUp":
                        try {
                            int slotID = Integer.parseInt(_in.readLine());                
                            _session.moveUp(slotID);
                        } catch (NumberFormatException ex) {}
                        break;
                    case "moveDown":
                        try {
                            int slotID = Integer.parseInt(_in.readLine());                
                            _session.moveDown(slotID);
                        } catch (NumberFormatException ex) {}
                        break;
                    case "openSlot":
                        try {
                            int slotID = Integer.parseInt(_in.readLine());                
                            _session.openSlot(slotID);
                        } catch (NumberFormatException ex) {}
                        break;
                    case "closeSlot":
                        try {
                            int slotID = Integer.parseInt(_in.readLine());                
                            _session.closeSlot(slotID);
                        } catch (NumberFormatException ex) {}
                        break;
                    case "leave":
                        try {
                            int clientID = Integer.parseInt(_in.readLine());                
                            _session.leave(clientID);
                            _in.close();
                            _out.close();
                            _socket.close();
                            return;
                        } catch (NumberFormatException ex) {}
                        break;
                    case "start":
                        _session.startGame();
                        done = true;
                        break;
                }
                
            }
            
            /**
             * Phase 3
             */
            done = false;
            while (!done) {
                input = _in.readLine();
            
                switch (input) {
                    case "moveCard":
                        try {
                            int clientID = Integer.parseInt(_in.readLine());
                            int cardID = Integer.parseInt(_in.readLine());
                            int deckID = Integer.parseInt(_in.readLine());
                            int deckPos = Integer.parseInt(_in.readLine());
                            
                            ArrayList<ClientThread> clients = _session.getClients();
                            for (ClientThread client : clients) {
                                if (client.getClientID() != clientID) {
                                    client.send("moveCard");
                                    client.send(cardID);
                                    client.send(deckID);
                                    client.send(deckPos);
                                }
                            }
                        } catch (NumberFormatException ex) {}
                        break;
                    case "endTurn":
                        try {
                            int clientID = Integer.parseInt(_in.readLine());

                            ArrayList<ClientThread> clients = _session.getClients();
                            for (ClientThread client : clients) {
                                if (client.getClientID() != clientID) {
                                    client.send("endTurn");
                                }
                            }
                        } catch (NumberFormatException ex) {}
                        break;        
                    case "WIN":
                        done = true;
                        break;
                }
            }
             
            _out.close();
            _in.close();
            _socket.close();
        } catch (IOException e) {
            
        }
    }
    
    public synchronized void send(Integer msg) {
        _out.println(msg);
    }
    
    public synchronized void send(String msg) {
        _out.println(msg);
    }
    
    public int getClientID() {
        return _clientID;
    }
}
