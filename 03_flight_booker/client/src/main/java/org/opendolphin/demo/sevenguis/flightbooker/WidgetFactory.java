package org.opendolphin.demo.sevenguis.flightbooker;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.util.Pair;
import javafx.util.StringConverter;

public class WidgetFactory {
	static ComboBox<Pair<String, String>> flightComboBox() {
		// See http://code.makery.ch/blog/javafx-8-event-handling-examples/

		ComboBox<Pair<String, String>> result = new ComboBox<>();
		result.setCellFactory((cb) -> new ListCell<Pair<String, String>>() {
			@Override
			protected void updateItem(Pair<String, String> item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty) {
					setText(null);
				} else {
					setText(item.getValue());
				}
			}
		});
		result.setConverter(new StringConverter<Pair<String, String>>() {
			@Override
			public String toString(Pair<String, String> item) {
				if (item == null) {
					return null;
				}
				else {
					return item.getValue();
				}
			}

			@Override
			public Pair<String, String> fromString(String string) {
				return null; // not needed
			}
		});
		return result;
	}
}
