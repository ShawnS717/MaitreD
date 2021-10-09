package networkRequestTaking.lib;

import java.io.BufferedReader;
import java.io.DataOutputStream;

public class SocketIO {
	public BufferedReader input;
	public DataOutputStream output;
	
	public SocketIO() {}
	
	public SocketIO(BufferedReader in, DataOutputStream out) {
		input = in;
		output = out;
	}
}
