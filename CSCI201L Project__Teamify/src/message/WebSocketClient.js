/**
 * 
 */
class WebSocketClient {
	
	constructor(hostname, port, name) {
		this.hostname = hostname;
		this.port = port;
		this.name = name;
	}
	
	getURL() {
		return this.hostname + ":" + this.port;
	}
	
	connect() {
		
		try {
			this.webSocket = new WebSocket("ws://" + this.getURL() + "/CSCI201L_Project__Teamify/WebSocketServer/EndPoint");
			
			this.webSocket.onopen = function(event) {
				console.log("On Open: " + JSON.stringify(event));
				sendMessage("NAME " + this.name);
			}
			this.webSocket.onclose = function(event) {
				console.log("On Close: " + getURL());
			}
			this.webSocket.onmessage = function(event) {
				var msg = event.data;
				var parsed = msg.split(" ");
				console.log("New " + msg);
			}
			this.webSocket.onerror = function(event) {
				console.log("Error: " + JSON.stringify(event));
			}
		} catch(exception) {
			console.error("Exception: " + exception);
		}
	}
	
	sendMessage(msg) {
		if(this.webSocket.readyState == webSocket.OPEN) {
			this.webSocket.send(msg);
		}
		else { console.log("WebSocket not ready"); }
	}
	
	disconnect() {
		if(this.webSocket.readyState == webSocket.OPEN) {
			this.webSocket.close();
		}
	}
	
}