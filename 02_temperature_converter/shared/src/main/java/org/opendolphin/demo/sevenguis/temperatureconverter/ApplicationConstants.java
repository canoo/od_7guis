package org.opendolphin.demo.sevenguis.temperatureconverter;

/**
 * Place for shared information among client and server. Typically identifiers for models, attributes and actions.
 */
public class ApplicationConstants {

    public static final String PM_APP = unique("APP");
	public static final String ATT_CELSIUS = "ATT_CELSIUS";
	public static final String ATT_FAHRENHEIT = "ATT_FAHRENHEIT";
	public static final String ATT_DIRECTION = "ATT_DIRECTION";
	public static final String VAL_DIRECTION_CELSIUS_TO_FAHRENHEIT = "C->F";
	public static final String VAL_DIRECTION_FAHRENHEIT_TO_CELSIUS = "F->C";

	public static final String COMMAND_INIT = unique("CMD_INIT");


    /**
     * Unify the identifier with the class name prefix.
     */
    private static String unique(String key) {
        return ApplicationConstants.class.getName() + "." + key;
    }

}
