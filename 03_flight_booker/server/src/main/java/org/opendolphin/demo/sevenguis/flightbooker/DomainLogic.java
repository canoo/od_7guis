package org.opendolphin.demo.sevenguis.flightbooker;

import java.time.LocalDate;
import java.util.function.Supplier;

import static org.opendolphin.demo.sevenguis.flightbooker.ApplicationConstants.VAL_FT_RETURN;

public class DomainLogic {

	private final DateTimeService dateTimeService;

	private DomainLogic(DateTimeService dateTimeService) {

		this.dateTimeService = dateTimeService;
	}

	public boolean isDateStringValid(String dateString) {
		boolean validDate = dateTimeService.isValidDate(dateString);
		return validDate;
	}

	public boolean isValidDateSequence(String dateString1, String dateString2) {
		LocalDate d1 = dateTimeService.dateFromString(dateString1);
		LocalDate d2 = dateTimeService.dateFromString(dateString2);
		return !d2.isBefore(d1);
	}

	// Builder:
	public static IDateTimeServiceBuilder builder() {
		return dateTimeService -> startDateSupplier -> new DomainLogic(dateTimeService);
	}

	public boolean isReturnFlight(String value) {
		return VAL_FT_RETURN.equals(value);
	}

	public String getMessageFor(boolean returnFlight, String startDate, String endDate) {
		return returnFlight ?
			String.format("You have booked a return flight leaving on %s, returning on %s", startDate, endDate)
			: String.format("You have booked a one-way flight on %s", startDate);
	}

	public interface IDateTimeServiceBuilder { IStartDateBuilder dateTimeService(DateTimeService dateTimeService);}
	public interface IStartDateBuilder { DomainLogic startDate(Supplier<String> startDateSupplier);}


}
