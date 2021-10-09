package networkRequestTaking;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import networkRequestTaking.lib.SocketIO;
import networkRequestTaking.lib.http.StatusCode;
import networkRequestTaking.util.DefaultErrorResponse;
import networkRequestTaking.util.RequestIdentifiers;

public class ClientHandler extends Thread{
	private RequestSocketListener socketListener;
	private Socket clientSocket;
	public SocketIO socketIO;
	
	//constructor
	public ClientHandler(Socket ClientSocket, RequestSocketListener listener) {
		clientSocket = ClientSocket;
		socketListener = listener;
		this.setName("Client_Handler_Thread" + this.getName().substring(this.getName().indexOf("-")));
	}
	
	public void FormatedPrintln(String toOutput) {
		System.out.println(this.getName() + ": " + toOutput);
	}
	
	public void run() {
		//attempt to get and map the input and output of the socket
		try {
			socketIO = new SocketIO(new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8")), new DataOutputStream(clientSocket.getOutputStream()));
			FormatedPrintln("Connection made. Client is: " + clientSocket.getInetAddress().getHostAddress());
		} catch (IOException e) {
			return;
		}
		
		//Security: see if we can do buisness with the client (is their ip address banned?)
		
		
		//find out what kind of request it is and perform an action based on it
		//(note. the first line will no longer be in the buffered reader anymore)
		String readLine = null;
		try {
			readLine = socketIO.input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		//to create your own request: create a method to tell what the request is and add it to the else if statement. then, have a method to handle it
		if(RequestIdentifiers.IsHTTPRequest(readLine)) {
			FormatedPrintln("Request was HTTP");
			//if taking http requests then accept it otherwise give the 503 response
			if(socketListener.IsTakingHTTP()) {
				try {
					RequestProcessors.HTTPRequest(this, readLine);
				} catch (Exception e) {
					e.printStackTrace();
					FormatedPrintln("Error occured. sending 500 response");
					FormatedPrintln("Reason: " + e.getMessage());
					DefaultErrorResponse.Send(socketIO, StatusCode.FIVEHUNDRED);
				}
			}else {
				DefaultErrorResponse.Send(socketIO, StatusCode.FIVEHUNDREDTHREE);
			}
		} else {
			FormatedPrintln("request could not be identified");
		}

		//close the connection
		if(!clientSocket.isClosed()) {
			try {
				clientSocket.close();
				FormatedPrintln("Closed connection");
			} catch (IOException e) {
				FormatedPrintln("Could not close connection. Most likely reason: Connection closed by client or loss of connection");
				return;
			}
		}
	}
}