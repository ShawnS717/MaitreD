package networkRequestTaking.lib.http;

public enum HTTPVersion {
	HTTP_1_0("HTTP/1.0"),
	HTTP_1_1("HTTP/1.1"),
	HTTP_2("HTTP/2"),
	HTTP_3("HTTP/3");
	
	public final String Formatted;
	
	private HTTPVersion(String version) {
		Formatted = version;
	}
}
