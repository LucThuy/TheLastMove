package selient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class WorkerThread extends Thread {
	private Socket socket;
	private String id;
	private Server server;
	
	private boolean run = true;
	
	public WorkerThread(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
		this.id = UUID.randomUUID().toString();
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
	
	private void parseCommand(String command) {
		String[] cmdList = command.split("\\s");
		if(cmdList[0].equals("loadServerAvailable")) {
			loadServerAvailable();
		}
		else if(cmdList[0].equals("byeBye")) {
			byeBye();
		}
	}
	
	private void loadServerAvailable() {
//		String re = "";
		
//		int n = serverAvailable.size();
//		String num = String.valueOf(n);
//		re = re + num + "\n";
		
		print.println(server.getServerAvailable().size());
		
		server.getServerAvailable().forEach((port, name) -> {
			print.println(port + " " + name);
		});
	}
	
	private void byeBye() {
		this.run = false;
	}

	public String getUid() {
		return id;
	}
}
