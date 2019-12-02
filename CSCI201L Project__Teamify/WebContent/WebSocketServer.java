package message;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import servlet.Connector;

/**
 * Servlet implementation class WebSocketEndPoint
 */
@ServerEndpoint("/EndPoint")
public class WebSocketServer {
	
	@SuppressWarnings("unused")	
	
	private static final long serialVersionUID = 1L;
	private Map<String, Session> sessions = new HashMap<String, Session>();
	private Set<Session> temp = new HashSet<Session>();
       
    @OnOpen
    public void onOpen(Session session) {
    	System.out.println("Open: " + session.getId());
    	temp.add(session);
    }
    
    @OnClose
    public void onClose(Session session) {
    	System.out.println("Close: " + session.getId());
    	sessions.remove(session.getId());
    }
    
    @OnMessage
    public void onMessage(String msg, Session session) {
    	if(msg == null) return;
    	if(msg.indexOf("NewProject") == 0) {
    		for(Session s : sessions.values()) {
    			if(s != session) {
    				try { s.getBasicRemote().sendText(msg); }
    				catch(Exception e) { e.printStackTrace(); }
    			}
    		}
    	}
    	else if(msg.indexOf("NAME ") == 0) {
    		temp.remove(session);
    		sessions.put(msg.substring(5, msg.length()), session);
    	}
    	else if(msg.indexOf("Message ") == 0 || msg.indexOf("Invite ") == 0) {
    		String[] parsed = msg.split(" ");
    		String head = (msg.indexOf("Message ") == 0) ? "Message " : "Invite ";
    		String content = (msg.indexOf("Message ") == 0) ? parsed[2] : "";
    		Session s = sessions.get(parsed[1]);
    		if(s != null) {
    			try { 
    				s.getBasicRemote().sendText(head + content); 
    			}
    			catch(Exception e) { e.printStackTrace(); }
    		}
    	}
    }
    
    @OnError
    public void onError(Throwable e) {
    	System.out.println(e.getMessage());
    	e.printStackTrace();
    }
}
