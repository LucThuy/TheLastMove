package selient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class WorkerThread extends Thread {
	private Socket socket;
	private String id;
	
	public WorkerThread(Socket socket) {
		this.socket = socket;
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
		    
		    while (true) {
		    	String strReceive = null;
		    	if(scan.hasNextLine())
		    		strReceive = scan.nextLine();

		        System.out.println("Receive from client: " + strReceive);
		        
		        print.println(strReceive);

		        if(strReceive.equals("gg")) {
		        	break;
		        }
		    }
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
          
            
	}

	public String getUid() {
		return id;
	}
}
