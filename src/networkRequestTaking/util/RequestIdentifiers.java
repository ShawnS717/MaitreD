package networkRequestTaking.util;

import networkRequestTaking.lib.http.HTTPVersion;

public final class RequestIdentifiers {
	public static boolean IsHTTPRequest(String line) {
		line = line.substring(line.lastIndexOf(" ")+1);
		for(HTTPVersion httpVersion : HTTPVersion.values()) {
			if(line.equals(httpVersion.Formatted)) {
				return true;
			}
		}
		return false;
	}
}