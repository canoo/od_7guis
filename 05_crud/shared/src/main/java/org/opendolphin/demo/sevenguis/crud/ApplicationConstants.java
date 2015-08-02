package org.opendolphin.demo.sevenguis.crud;

/**
 * Place for shared information among client and server. Typically identifiers for models, attributes and actions.
 */
public class ApplicationConstants {

    public static final String TYPE_META = unique("TYPE_META");

    public static final String PM_APP = unique("APP");
    public static final String ATT_NAME = "ATT_NAME";
    public static final String ATT_GREETING = "ATT_GREETING";

	public static final String COMMAND_INIT_PMS = unique("CMD_INIT_PMS");
	public static final String COMMAND_INIT_DATA = unique("CMD_INIT_DATA");
	public static final String COMMAND_CREATE_PERSON = unique("CMD_CREATE_PERSON");
	public static final String COMMAND_UPDATE_PERSON = unique("CMD_UPDATE_PERSON");
	public static final String COMMAND_DELETE_PERSON = unique("CMD_DELETE_PERSON");

    public static final String PM_CURRENT_PM = unique("PM_CURRENT_PM");

    public static final String PM_META = unique("META");
    public static final String ATT_CURRENT_PM_ID = unique("ATT_CURRENT_PM_ID");

    /**
     * Unify the identifier with the class name prefix.
     */
    private static String unique(String key) {
        return ApplicationConstants.class.getName() + "." + key;
    }

}
