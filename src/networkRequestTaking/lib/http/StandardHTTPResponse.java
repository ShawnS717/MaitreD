package networkRequestTaking.lib.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import networkRequestTaking.lib.SocketIO;

public class StandardHTTPResponse {
	//status line
	//preset to avoid errors
	private HTTPVersion httpVersion = HTTPVersion.HTTP_1_1;
	private StatusCode statusCode = StatusCode.FIVEHUNDRED;
	
	//headers
	public HashMap<String, String> headers;

	//body (put file contents/response body here)
	public String body;
	
	public StandardHTTPResponse() {
		headers = new HashMap<>();
	}
	
	public void SetHTTPVersion(HTTPVersion version) {
		httpVersion = version;
	}
	
	public HTTPVersion GetHTTPVersion() {
		return httpVersion;
	}
	
	public void SetStatusCode(StatusCode code) {
		statusCode = code;
	}
	
	public StatusCode GetStatusCode() {
		return statusCode;
	}
	
	public void SetStatusLine(HTTPVersion version, StatusCode code) {
		httpVersion = version;
		statusCode = code;
	}

	public String GetStatusLine() {
		return (httpVersion.Formatted + " " + statusCode.Full());
	}
	
	public void AssignHeader(String key, String value) {
		headers.put(key, value);
	}
	
	public void SetBody(String content) {
		body = content;
	}
	
	public void AddToBody(String line) {
		if(body != null) {
			body += line;
		}else {
			SetBody(line);
		}
	}
	
	public String FormatToOutputString() {
		StringBuilder output = new StringBuilder();
		output.append(GetStatusLine() + "\r\n");
		
		for(Entry<String, String> entry : headers.entrySet()) {
			output.append(entry.getKey() + ":" + entry.getValue() + "\r\n");
		}
		
		if(body != null) {
			output.append("\r\n");
			output.append(body);
		}
		
		return output.toString();
	}

	public void Send(SocketIO socketIO) {
		try {
			socketIO.output.writeBytes(this.FormatToOutputString());
		} catch (IOException e) {}
	}
}

