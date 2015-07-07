package org.opendolphin.demo.sevenguis.flightbooker;

import org.opendolphin.core.server.ServerPresentationModel;

import java.time.LocalDate;

public class PMInitializer {

	public static void initializePMs(ServerPresentationModel pm) {
		pm.getAt(ApplicationConstants.ATT_FLIGHT_TYPE).setValue(ApplicationConstants.VAL_FT_ONE_WAY);

		pm.getAt(ApplicationConstants.ATT_FLIGHT_TYPES).setValue(String.format("%s:%s,%s:%s", ApplicationConstants.VAL_FT_ONE_WAY, "one-way-flight", ApplicationConstants.VAL_FT_RETURN, "return flight"));

		pm.getAt(ApplicationConstants.ATT_START_DATE).setValue(new DateTimeService().format(LocalDate.now()) );
		pm.getAt(ApplicationConstants.ATT_RETURN_DATE).setValue(""); // initial (invalid) value
		pm.getAt(ApplicationConstants.ATT_MESSAGE).setValue("");
	}

}
