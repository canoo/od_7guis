package org.opendolphin.demo.sevenguis.flightbooker;

import org.opendolphin.core.Tag;

/**
 * Place for shared information among client and server. Typically identifiers for models, attributes and actions.
 */
public class ApplicationConstants {

	public static final String PROP_VALUE = "value"; // property 'value' of 'BaseAttribute'
	public static final String TAG_VALID = "TAG_VALID";

    public static final String PM_APP = unique("APP");
    public static final String ATT_FLIGHT_TYPE = "ATT_FLIGHT_TYPE";
    public static final String ATT_FLIGHT_TYPES = "ATT_FLIGHT_TYPES";
    public static final String VAL_FT_ONE_WAY = "o";
    public static final String VAL_FT_RETURN = "r";

	public static final String ATT_START_DATE = "ATT_START_DATE";
	public static final String ATT_RETURN_DATE = "ATT_RETURN_DATE";

	public static final String ATT_MESSAGE = "ATT_MESSAGE";

	public static final String COMMAND_CREATE_PMS = unique("COMMAND_CREATE_PMS");
	public static final String COMMAND_INIT_DATA = unique("COMMAND_INIT_DATA");
	public static final String COMMAND_BOOK = unique("CMD_BOOK");
	public static final String ATT_BOOK_COMMAND_ENABLED = "ATT_BOOK_COMMAND_ENABLED";

	public static final Tag VALID_TAG = Tag.tagFor.get(TAG_VALID);


	/**
     * Unify the identifier with the class name prefix.
     */
    private static String unique(String key) {
        return ApplicationConstants.class.getName() + "." + key;
    }

}
