package networkRequestTaking.util;

import java.io.IOException;

import networkRequestTaking.lib.SocketIO;
import networkRequestTaking.lib.http.StandardHTTPResponseWithPresets;
import networkRequestTaking.lib.http.StatusCode;

public final class DefaultErrorResponse {
	private static void Send(SocketIO socketIO, StandardHTTPResponseWithPresets response) {
		try {
			socketIO.output.writeBytes(response.FormatToOutputString());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	public static void Send(SocketIO socketIO, StatusCode statusCode) {
		StandardHTTPResponseWithPresets response = new StandardHTTPResponseWithPresets();
		response.SetStatusCode(statusCode);
		Send(socketIO, response);
	}
	public static void Send(SocketIO socketIO, StatusCode statusCode, String body) {
		StandardHTTPResponseWithPresets response = new StandardHTTPResponseWithPresets();
		response.SetStatusCode(statusCode);
		response.SetBody(body);
		Send(socketIO, response);
	}
}
