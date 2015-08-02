package org.opendolphin.demo.sevenguis.crud;

import javafx.collections.ObservableList;
import org.opendolphin.core.Attribute;
import org.opendolphin.core.ModelStoreEvent;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientDolphin;

public class ModelStoreBinder {

	private final ClientDolphin clientDolphin;

	public ModelStoreBinder(ClientDolphin clientDolphin) {
		this.clientDolphin = clientDolphin;
	}

	/**
	 * Bind a 'pms' to all PMs of type 'pmType' in the modelstore. When a PM of type 'pmType' is added/removed
	 * to/from the model store 'pms' is updated accordingly.
	 */
	public void bind(ObservableList<PresentationModel> pms, String pmType, MainView view) {

		clientDolphin.getModelStore().addModelStoreListener(pmType, event -> {
			if (ModelStoreEvent.Type.ADDED.equals(event.getType())) {
				pms.add(event.getPresentationModel());
			} else if (ModelStoreEvent.Type.REMOVED.equals(event.getType())) {
				int idx = MainViewInitializer.indexForItem(pms, pm -> pm.getId().equals(event.getPresentationModel().getId()));
				pms.remove(idx);
				if (pms.size() > 0) {
					SharedPmApi sharedPmApi = new SharedPmApi(clientDolphin);
					Attribute at = sharedPmApi.getMetaPM().getAt(ApplicationConstants.ATT_CURRENT_PM_ID);
					int newIdx = idx > 0 ? idx - 1 : 0;
					at.setValue(pms.get(newIdx).getId());
					view.listView.requestFocus();
				}
			}
		});

	}
}

