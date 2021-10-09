package dates;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class DateFormats {
	public static String HTTPDate() {
		return DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss O", Locale.ENGLISH).format(ZonedDateTime.now(ZoneOffset.UTC));
	}
}
