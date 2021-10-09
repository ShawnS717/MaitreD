package networkRequestTaking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestSocketListener extends Thread{
	private int port;
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private boolean takingHTTP = false;
	
	//constructors
	public RequestSocketListener(int assignedPort) {
		port = assignedPort;
		super.setName("MontreDSocketListener");
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("SocketListener: \"" + super.getName() + "\" Created Successfully on port: " + port);
		}catch(IOException e) {
			e.printStackTrace();
		}
		setPriority(MAX_PRIORITY);
	}
	
	public RequestSocketListener(int assignedPort, boolean HTTP){
		this(assignedPort);
		takingHTTP = HTTP;
	}
	
	public boolean IsTakingHTTP() {
		return takingHTTP;
	}
	
	public void run() {	
		System.out.println("Listener is now listening");
		while(true) {
			try {
				socket = serverSocket.accept();
				new ClientHandler(socket, this).start();
			}catch(IOException e) {
				e.printStackTrace();
				System.out.print("Server couldn't accept the client");
			}
		}
	}
}
