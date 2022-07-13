package selient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class WorkerThread extends Thread {
	private Socket socket;
	private Server server;
	private String uid;
	
	private boolean run = true;
	
	public WorkerThread(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
		this.uid = UUID.randomUUID().toString();
	}
	
	private InputStream inputStream;
	private Scanner scan;
	
	private OutputStream outputStream;
	private PrintWriter print;
	
	public void run() {
		try {
			inputStream = socket.getInputStream();
		    scan = new Scanner(inputStream);
		    
		    outputStream = socket.getOutputStream();
		    print = new PrintWriter(outputStream, true);
		    
		    while(run) {	    	
		    	if(scan.hasNextLine()) {
		    		String strReceive = scan.nextLine();

			        System.out.println("Receive from client: " + strReceive);
			        if(strReceive != null) {
				        parseCommand(strReceive);
			        }
		    	}
		    }
			
		} catch (IOException e) {
			e.printStackTrace();
		}		        
	}
	
	private void parseCommand(String command) throws IOException {
		String[] cmdList = command.split("\\s");
		if(cmdList[0].equals("loadServerAvailable")) {
			loadServerAvailable();
		}
		else if(cmdList[0].equals("newRoom")) {
			newRoom(cmdList[1]);
		}
		else if(cmdList[0].equals("rageByeBye")) {
			rageByeBye();
		}
	}
	
	private void loadServerAvailable() {
		Map<String, String> serverAvailable = server.getServerAvailable();
		
		print.println(serverAvailable.size());
		
		serverAvailable.forEach((name, port) -> {
			print.println(name + " " + port);
		});
	}
	
	private void newRoom(String roomName) throws IOException {
		this.server.openNewServer(roomName);
		
		print.println(this.server.getPort());
	}
	
	private void rageByeBye() throws IOException {
		this.server.closeServer(this.uid);
		
		this.run = false;
	}
	
	private void chillByeBye() {
		this.server.byeClient(this.uid);
		
		this.run = false;
	}

	public String getUid() {
		return uid;
	}
}
