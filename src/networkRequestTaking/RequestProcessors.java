package networkRequestTaking;

import networkRequestTaking.lib.http.HTTPMethod;
import networkRequestTaking.lib.http.HTTPVersion;
import networkRequestTaking.lib.http.StandardHTTPRequest;
import networkRequestTaking.lib.http.StandardHTTPResponseWithPresets;
import networkRequestTaking.lib.http.StatusCode;
import networkRequestTaking.util.DefaultErrorResponse;
import serverFileIO.WebFiles;

public final class RequestProcessors {
	//for HTTPRequests
	public static void HTTPRequest(ClientHandler clientHandler, String readLine) throws Exception {
		//first build a StandardHTTPRequest object to make data management and decision making easier
		StandardHTTPRequest request = new StandardHTTPRequest();
		request.MapFromSocketInput(clientHandler, readLine);
		StandardHTTPResponseWithPresets response = new StandardHTTPResponseWithPresets();
		String RequestedFile = null;
		
		//if it's not a 1.1 request then tell them we can't do that
		if(request.GetVersion() != HTTPVersion.HTTP_1_1) {
			clientHandler.FormatedPrintln("Request was not http/1.1");
			DefaultErrorResponse.Send(clientHandler.socketIO, StatusCode.FIVEHUNDREDFIVE, "This server can only do HTTP/1.1 requests");
			return;
		}

		//note, at this point all 5xx errors are handled.
		//3xx we don't deal in them
		//1xx un-neccesary
		//now check for any 4xx errors
		if(request.GetMethod() != HTTPMethod.GET) { //find a way to do more than gets later
			DefaultErrorResponse.Send(clientHandler.socketIO, StatusCode.FOURHUNDREDFIVE);
		}
		if(!WebFiles.IsURIlengthGood(request.GetRequestURI())) {
			DefaultErrorResponse.Send(clientHandler.socketIO, StatusCode.FOURHUNDREDFOURTEEN);
		}
		//due to this being a get only server, these are the only errors really needed to handle
		//use this try/catch to do 404 errors while attempting to respond
		try {
			RequestedFile = WebFiles.FindFileByURI(request.GetRequestURI());
		} catch(Exception e) {
			clientHandler.FormatedPrintln("Couldn't find the requested resource. Sending 404");
			DefaultErrorResponse.Send(clientHandler.socketIO, StatusCode.FOURHUNDREDFOUR);
			return;
		}
		response.AssignHeader("contentType", WebFiles.GetContentType(RequestedFile));
		response.SetBody(WebFiles.Read(RequestedFile));
		response.Send(clientHandler.socketIO);
		clientHandler.FormatedPrintln("Response Successfull");
	}
}
