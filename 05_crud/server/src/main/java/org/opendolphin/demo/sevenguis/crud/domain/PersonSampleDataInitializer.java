package org.opendolphin.demo.sevenguis.crud.domain;

import org.opendolphin.core.server.ServerDolphin;

public class PersonSampleDataInitializer {

	public static final String PM_1 = "PM1";
	public static final String PM_2 = "PM2";

	public void initialize(ServerDolphin serverDolphin) {

		PersonServerAPI.newPM(serverDolphin, PM_1, "Rose", "Hawkins", "25.07.1975");
		PersonServerAPI.newPM(serverDolphin, PM_2, "Susan", "Jensen", "11.3.1982");
	}


}
