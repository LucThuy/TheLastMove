package selient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class RageThread extends Thread {
	private Socket socket;
	private Client client;
	private int port;
	
	public RageThread(Client client, int port) {
		this.client = client;
		this.port = port;
	}
	
	private InputStream inputStream;
	private Scanner scan;
	
	private OutputStream outputStream;
	private PrintWriter print;
	
	public void run() {
		try {
			socket = new Socket("127.0.0.1", port);
			
			inputStream = socket.getInputStream();
		    scan = new Scanner(inputStream);
		    
		    outputStream = socket.getOutputStream();
		    print = new PrintWriter(outputStream, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void byeBye() throws IOException {
		print.println("byeBye");
		
		socket.close();
	}
}
