package org.opendolphin.demo.sevenguis.crud;

import javafx.collections.ListChangeListener;
import javafx.scene.control.ListView;
import org.opendolphin.core.Attribute;
import org.opendolphin.core.PresentationModel;

import java.util.function.Supplier;

public class ODListViews {

	public ODListViews() {
	}

	void bindSelectedPmId(ListView<PresentationModel> listView, Supplier<Attribute> attributeSupplier) {
		listView.getSelectionModel().selectedItemProperty().addListener((s, o, n) -> {
			if (n != null) {
				attributeSupplier.get().setValue(n.getId());
			}
		});

		//!! todo: create small JavaFX project demonstrating listview/observable changes:
		// maintain PMid to idx map:
		if (1 > 2) {
			listView.getItems().addListener(new ListChangeListener<PresentationModel>() {
				@Override
				public void onChanged(Change change) {
					System.out.println("Detected a change! " + change);
					while (change.next()) {
						if (change.wasAdded()) {
							System.out.println("added");
						}
						else if (change.wasRemoved()) {
							System.out.println("removed");
						}
						else if (change.wasReplaced()) {
							System.out.println("replaced");
						}
						else if (change.wasPermutated()) {
							System.out.println("permutated");
						}
					}
				}
			});
		}
	}


}
