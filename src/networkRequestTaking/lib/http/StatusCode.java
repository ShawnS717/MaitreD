package networkRequestTaking.lib.http;

//the response codes are catagorized as so:
//1xx: Informational - Request received, continuing process
//2xx: Success - The action was successfully received, understood, and accepted
//3xx: Redirection - Further action must be taken in order to complete the request
//4xx: Client Error - The request contains bad syntax or cannot be fulfilled
//5xx: Server Error - The server failed to fulfill an apparently valid request
public enum StatusCode {
	//may have local equivalents 
	//all applicable 5xx errors are handled
	ONEHUNDRED(100, "Continue"),
	ONEHUNDREDONE(101, "Switching Protocols"),
	TWOHUNDRED(200, "OK"),
	TWOHUNDREDONE(201, "Created"),
	TWOHUNDREDTWO(202, "Accepted"),
	TWOHUNDREDTHREE(203, "Non-Authoritative Information"),
	TWOHUNDREDFOUR(204, "No Content"),
	TWOHUNDREDFIVE(205, "Reset Content"),
	TWOHUNDREDSIX(206, "Partial Content"),
	THREEHUNDRED(300, "Multiple Choices"),
	THREEHUNDREDONE(301, "Moved Permanently"),
	THREEHUNDREDTWO(302, "Found"),
	THREEHUNDREDTHREE(303, "See Other"),
	THREEHUNDREDFOUR(304, "Not Modified"),
	THREEHUNDREDFIVE(305, "Use Proxy"),
	THREEHUNDREDSEVEN(307, "Temporary Redirect"),
	FOURHUNDRED(400, "Bad Request"),
	FOURHUNDREDONE(401, "Unauthorized"),
	FOURHUNDREDTWO(402, "Payment Required"),
	FOURHUNDREDTHREE(403, "Forbidden"),
	FOURHUNDREDFOUR(404, "Not Found"),
	FOURHUNDREDFIVE(405, "Method Not Allowed"),
	FOURHUNDREDSIX(406, "Not Acceptable"),
	FOURHUNDREDSEVEN(407, "Proxy Authentication Required"),
	FOURHUNDREDEIGHT(408, "Request Time-out"),
	FOURHUNDREDNINE(409, "Conflict"),
	FOURHUNDREDTEN(410, "Gone"),
	FOURHUNDREDELEVEN(411, "Length Required"),
	FOURHUNDREDTWELVE(412, "Precondition Failed"),
	FOURHUNDREDTHIRTEEN(413, "Request Entity Too Large"),
	FOURHUNDREDFOURTEEN(414, "Request-URI Too Large"),
	FOURHUNDREDFIFTEEN(415, "Unsupported Media Type"),
	FOURHUNDREDSIXTEEN(416, "Requested range not satisfiable"),
	FOURHUNDREDSEVENTEEN(417, "Expectation Failed"),
	FIVEHUNDRED(500, "Internal Server Error"),
	FIVEHUNDREDONE(501, "Not Implemented"),
	FIVEHUNDREDTWO(502, "Bad Gateway"),
	FIVEHUNDREDTHREE(503, "Service Unavailable"),
	FIVEHUNDREDFOUR(504, "Gateway Time-out"),
	FIVEHUNDREDFIVE(505, "HTTP Version not supported");
	
	private final int code;
	private final String phrase;
	
	private StatusCode(int Code, String Phrase) {
		code = Code;
		phrase = Phrase;
	}
	
	public int Code() {
		return code;
	}
	
	public String Phrase() {
		return phrase;
	}
	
	public String Full() {
		return (code + " " + phrase); 
	}
}
