package org.opendolphin.demo.sevenguis.flightbooker;

import org.opendolphin.core.Tag;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.Slot;

public class PMCreator {

	public static void createPMs(ServerDolphin serverDolphin) {
		// Create PM:
		DTO dto = new DTO(
			new Slot(ApplicationConstants.ATT_FLIGHT_TYPE, null),
			new Slot(ApplicationConstants.ATT_FLIGHT_TYPES, null),
			new Slot(ApplicationConstants.ATT_START_DATE, null ),
			new Slot(ApplicationConstants.ATT_START_DATE, null, null, ApplicationConstants.VALID_TAG),
			new Slot(ApplicationConstants.ATT_RETURN_DATE, null),
			new Slot(ApplicationConstants.ATT_RETURN_DATE, null, null, ApplicationConstants.VALID_TAG),
			new Slot(ApplicationConstants.ATT_RETURN_DATE, null, null, Tag.ENABLED),
			new Slot(ApplicationConstants.ATT_MESSAGE, null),
			new Slot(ApplicationConstants.ATT_BOOK_COMMAND_ENABLED, null)
		);
		serverDolphin.presentationModel(ApplicationConstants.PM_APP, null, dto);
	}
}
