package networkRequestTaking.lib.http;

import java.io.IOException;
import java.util.HashMap;

import networkRequestTaking.ClientHandler;

//this object contains everything sent in a http 1.1 request
public class StandardHTTPRequest {
	//request line
	private HTTPMethod method;
	private String requestURI;
	private HTTPVersion httpVersion;
	
	//Headers
	public HashMap<String, String> headers;

	//request body
	public String body;
	
	//constructor
	public StandardHTTPRequest() {
		headers = new HashMap<>();
	}
	
	public void SetMethod(String line) {
		switch(line) {
		case "GET":
			method = HTTPMethod.GET;
			break;
		case "HEAD":
			method = HTTPMethod.HEAD;
			break;
		case "POST":
			method = HTTPMethod.POST;
			break;
		case "PUT":
			method = HTTPMethod.PUT;
			break;
		case "DELETE":
			method = HTTPMethod.DELETE;
			break;
		case "CONNECT":
			method = HTTPMethod.CONNECT;
			break;
		case "OPTIONS":
			method = HTTPMethod.OPTIONS;
			break;
		case "TRACE":
			method = HTTPMethod.TRACE;
			break;
		case "PATCH":
			method = HTTPMethod.PATCH;
			break;
		}
	}

	public HTTPMethod GetMethod() {
		return method;
	}
	
	public void SetRequestURI(String line) {
		requestURI = line;
	}
	
	public String GetRequestURI() {
		return requestURI;
	}
	
	public void SetVersion(String line) {
		switch(line) {
		case "HTTP/1.0":
			httpVersion = HTTPVersion.HTTP_1_0;
			break;
		case "HTTP/1.1":
			httpVersion = HTTPVersion.HTTP_1_1;
			break;
		case "HTTP/2":
			httpVersion = HTTPVersion.HTTP_2;
			break;
		case "HTTP/3":
			httpVersion = HTTPVersion.HTTP_3;
		}
	}

	public HTTPVersion GetVersion() {
		return httpVersion;
	}
	
	public void SetRequestLine(String line) {
		String remainingLine = line;
		String method = remainingLine.substring(0, remainingLine.indexOf(" "));
		remainingLine = remainingLine.substring(remainingLine.indexOf(" ") + 1);
		String uri = remainingLine.substring(0, remainingLine.indexOf(" "));
		remainingLine = remainingLine.substring(remainingLine.indexOf(" ") + 1);
		String version = remainingLine;
		
		SetRequestLine(method, uri, version);
	}
	
	public void SetRequestLine(String method, String uri, String version) {
		SetMethod(method);
		SetRequestURI(uri);
		SetVersion(version);
	}
	
	public String GetRequestLine() {
		return (method.toString() + " " + requestURI + " " + GetVersionFormated());
	}
	
	private String GetVersionFormated() {
		switch(httpVersion) {
		case HTTP_1_0:
			return "HTTP/1.0";
		case HTTP_1_1:
			return "HTTP/1.1";
		case HTTP_2:
			return "HTTP/2";
		case HTTP_3:
			return "HTTP/3";
		default:
			return "version unknown";
		}
	}
	
	public void AssignHeader(String line) {
		String key = line.substring(0, line.indexOf(":"));
		String value = line.substring(line.indexOf(":") + 1);
		AssignHeader(key, value);
	}
	
	public void AssignHeader(String key, String value) {
		headers.put(key, value);
	}
	
	public void SetBody(String line) {
		body = line;
	}
	
	public void AddToBody(String line) {
		if(body != null) {
			body += line;
		}else {
			body = line;
		}
	}

	
	public void MapFromSocketInput(ClientHandler clientHandler, String readLine) throws IOException {
		//get the request line
		SetRequestLine(readLine);

		//due to websites returning index if blank ("/") set the uri to whatever the default is ("/index") if empty
		if(GetRequestURI().equals("/")) {
			SetRequestURI("/index");
		}
		clientHandler.FormatedPrintln("Client is asking for: " + GetRequestURI());
		
		//get the headers
		do {
			readLine = clientHandler.socketIO.input.readLine();
			//is it the breakpoint before the body?
			if(readLine.equals("")) { break; }
			//then it's a header
			AssignHeader(readLine);
		}while(!readLine.equals(""));
		
		//get the request body
		//TODO:
	}
}
