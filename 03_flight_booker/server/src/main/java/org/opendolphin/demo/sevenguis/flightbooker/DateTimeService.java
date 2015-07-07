package org.opendolphin.demo.sevenguis.flightbooker;

import java.time.LocalDate;
import java.time.format.*;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

public class DateTimeService {

	DateTimeFormatter formatter = dateFormatter();

	public boolean isValidDate(String dateString) {
		if (dateString == null) {
			return false;
		}
		try {
			dateFromString(dateString);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public LocalDate dateFromString(String input) {
		return LocalDate.parse(input, formatter);
	}

	public String format(LocalDate localDate) {
		return formatter.format(localDate);
	}

	public static DateTimeFormatter dateFormatter() {
		return new DateTimeFormatterBuilder()
			.appendValue(DAY_OF_MONTH, 1, 2, SignStyle.NEVER)
			.appendLiteral(".")
			.appendValue(MONTH_OF_YEAR, 1, 2, SignStyle.NEVER)
			.appendLiteral(".")
			.appendValue(YEAR, 4)
			.optionalStart().toFormatter()
			.withResolverStyle(ResolverStyle.STRICT);
	}


}
