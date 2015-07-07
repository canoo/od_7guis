package org.opendolphin.demo.sevenguis.flightbooker;

import org.opendolphin.core.Tag;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.ServerPresentationModel;

import java.util.Arrays;

public class PMBinder {

	public static void initializePMBinding(ServerDolphin serverDolphin, DomainLogic domainLogic) {
		ServerPresentationModel pm = serverDolphin.getAt(ApplicationConstants.PM_APP);

		SharedDolphinFunctions.bindAttributeTo(pm.getAt(ApplicationConstants.ATT_START_DATE), domainLogic::isDateStringValid, pm.getAt(ApplicationConstants.ATT_START_DATE, ApplicationConstants.VALID_TAG));

		SharedDolphinFunctions.bindAttributeTo(pm.getAt(ApplicationConstants.ATT_RETURN_DATE), domainLogic::isDateStringValid, pm.getAt(ApplicationConstants.ATT_RETURN_DATE, ApplicationConstants.VALID_TAG));

		// Handle change: flightType -> isReturnFlight:
		pm.getAt(ApplicationConstants.ATT_FLIGHT_TYPE).addPropertyChangeListener(ApplicationConstants.PROP_VALUE, evt -> {
			pm.getAt(ApplicationConstants.ATT_RETURN_DATE, Tag.ENABLED).setValue(domainLogic.isReturnFlight(SharedDolphinFunctions.stringValue(pm.getAt(ApplicationConstants.ATT_FLIGHT_TYPE))));
		});

		// Handle change: flightType | startDate | returnDate => BookCommandEnabled
		Arrays.asList(
			pm.getAt(ApplicationConstants.ATT_FLIGHT_TYPE), pm.getAt(ApplicationConstants.ATT_START_DATE), pm.getAt(ApplicationConstants.ATT_RETURN_DATE)
		).forEach(attr -> attr.addPropertyChangeListener(ApplicationConstants.PROP_VALUE, evt -> evaluateBookCommandEnabled(pm, domainLogic)));

	}

	private static void evaluateBookCommandEnabled(ServerPresentationModel pm, DomainLogic domainLogic) {
		final boolean enabled;
		if (domainLogic.isReturnFlight(SharedDolphinFunctions.stringValue(pm.getAt(ApplicationConstants.ATT_FLIGHT_TYPE)))) {
			enabled = SharedDolphinFunctions.booleanValue(pm.getAt(ApplicationConstants.ATT_START_DATE, ApplicationConstants.VALID_TAG)) && SharedDolphinFunctions.booleanValue(pm.getAt(ApplicationConstants.ATT_RETURN_DATE, ApplicationConstants.VALID_TAG))
				&& domainLogic.isValidDateSequence(SharedDolphinFunctions.stringValue(pm.getAt(ApplicationConstants.ATT_START_DATE)), SharedDolphinFunctions.stringValue(pm.getAt(ApplicationConstants.ATT_RETURN_DATE)));
		}
		else {
			enabled = SharedDolphinFunctions.booleanValue(pm.getAt(ApplicationConstants.ATT_START_DATE, ApplicationConstants.VALID_TAG));
		}

		pm.getAt(ApplicationConstants.ATT_BOOK_COMMAND_ENABLED).setValue(enabled);
	}

}
