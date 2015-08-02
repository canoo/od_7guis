package org.opendolphin.demo.sevenguis.crud;

public interface PersonApi {

	String PERSON_TYPE = PersonApi.class.getName() + "PERSON_TYPE";
	String PERSON_TYPE_TECHNICAL = PersonApi.class.getName() + "PERSON_TYPE_TECHNICAL";

	String ATT_FIRST_NAME = PersonApi.class.getName() + "ATT_FIRST_NAME";
	String ATT_LAST_NAME = PersonApi.class.getName() + "ATT_LAST_NAME";
	String ATT_BIRTHDAY = PersonApi.class.getName() + "ATT_BIRTHDAY";

	String PM_MASTER_DETAIL_PERSON_ID = "MDPerson";
}
