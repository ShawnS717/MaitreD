package networkRequestTaking.lib.http;

import dates.DateFormats;

public class StandardHTTPResponseWithPresets extends StandardHTTPResponse {
	//preset desired properties
	public StandardHTTPResponseWithPresets() {
		SetStatusLine(HTTPVersion.HTTP_1_1, StatusCode.TWOHUNDRED);
		AssignHeader("Connection", "close");
		AssignHeader("ContentLanguage", "en");
		AssignHeader("Date", DateFormats.HTTPDate());
	}
}
