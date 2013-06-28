package aceofspadesserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class Main {
    
    private static ArrayList<ClientThread> _connections = null;
    private static TreeMap<Integer, Session> _sessions = null;
    private static Properties _gameData = null;
    private static int _sessionIDCounter = 0;
    
    public static void main(String[] args) {
        _gameData = new Properties();
        _sessions = new TreeMap<>();
        
        try (FileInputStream in = new FileInputStream("gameData.prop")) {
            _gameData.load(in);
        } catch (IOException ex) {
            System.out.println("GameData file error");
            System.out.println(ex.getMessage());
        }
        
        ServerSocket serverSocket;
        boolean listening = true;
 
        try {
            serverSocket = new ServerSocket(13337);
        
            _connections = new ArrayList<>();
            while (listening) {
                ClientThread c = new ClientThread(serverSocket.accept());
                _connections.add(c);
                c.start();
                System.out.println("New connection recieved");
            }
        
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Connection error");
            System.out.println(ex.getMessage());
        }
    }
    
    public static int getMaxPlayerCount(int gameID) {
        try {
            int result = Integer.parseInt(_gameData.getProperty(Integer.toString(gameID)));
            return result;
        } catch (NumberFormatException ex) {
            return 0;
        }        
    }
    
    public static ArrayList<Session> getOpenSessions() {
        ArrayList<Session> sessionList = new ArrayList<>(_sessions.values());
        ArrayList<Session> sessions = new ArrayList<>();
        
        for (Session session : sessionList) {
            if (session.isOpen()) {
                sessions.add(session);
            }
        }
        
        return sessions;
    }
    
    public static Session getSession(int sessionID) {
        return _sessions.get(sessionID);
    }
    
    public static Session createSession(String sessionName, int gameID, 
            String creatorName, ClientThread client) throws Exception {
        Session session = new Session(_sessionIDCounter, gameID, sessionName);
        _sessions.put(_sessionIDCounter, session);
        session.addClient(client, creatorName);
        _sessionIDCounter++;
        return session;
    }
}
