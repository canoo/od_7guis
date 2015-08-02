package org.opendolphin.demo.sevenguis.crud;

import org.opendolphin.core.Attribute;
import org.opendolphin.core.Dolphin;
import org.opendolphin.core.PresentationModel;

//!! concept
public class SharedPmApi {

	private final Dolphin dolphin;

	public SharedPmApi(Dolphin dolphin) {
		this.dolphin = dolphin;
	}

	public PresentationModel getCurrentItem() {
		return dolphin.getAt(ApplicationConstants.PM_CURRENT_PM);
	}

	public Attribute getCurrentPmIdAttribute() {
		return getMetaPM().getAt(ApplicationConstants.ATT_CURRENT_PM_ID);
	}
	public PresentationModel getMetaPM() {
		return dolphin.getAt(ApplicationConstants.PM_META);
	}

}
