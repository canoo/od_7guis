package org.opendolphin.demo.sevenguis.crud;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.util.Callback;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientAttribute;

import java.util.function.Predicate;

public class Context { // !! concept

	ObservableList<PresentationModel> allItems = FXCollections.observableArrayList(extractor());
	FilteredList<PresentationModel> filteredItems;

	public Context() {

		filteredItems = new FilteredList<>(allItems, null);

	}

	public static Predicate<PresentationModel> predicate(String aPrefix) {
		return pm -> {
			return aPrefix.isEmpty() || SharedDolphinFunctions.stringValue(pm, PersonApi.ATT_LAST_NAME).startsWith(aPrefix);
		};
	}

	/** see Properties Extractor: Best way to get the ListView instantly updating its elements http://www.jensd.de/wordpress/?p=1650 */
	public static Callback<PresentationModel, Observable[]> extractor() {
		return (PresentationModel pm) -> {
			return new Observable[]{
				new ClientAttributeStringWrapper((ClientAttribute) pm.getAt(PersonApi.ATT_FIRST_NAME)),
				new ClientAttributeStringWrapper((ClientAttribute) pm.getAt(PersonApi.ATT_LAST_NAME))
			};
		};
	}
}
