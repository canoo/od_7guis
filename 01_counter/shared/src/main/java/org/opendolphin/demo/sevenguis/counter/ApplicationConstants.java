package org.opendolphin.demo.sevenguis.counter;

/**
 * Place for shared information among client and server. Typically identifiers for models, attributes and actions.
 */
public class ApplicationConstants {

    public static final String PM_APP = unique("APP");
	public static final String ATT_COUNTER = "ATT_COUNTER";

	public static final String COMMAND_INIT = unique("CMD_INIT");
	public static final String COMMAND_INC_COUNTER = unique("COMMAND_INC_COUNTER");


    /**
     * Unify the identifier with the class name prefix.
     */
    private static String unique(String key) {
        return ApplicationConstants.class.getName() + "." + key;
    }

}
