package org.opendolphin.demo.sevenguis.flightbooker;

import javafx.scene.control.ComboBox;
import javafx.util.Pair;

public class ComboBoxes {

	public static Pair<String, String> comboBoxItemForKey(ComboBox<Pair<String, String>> cb, String key) {
		return cb.getItems().stream().filter(p -> p.getKey().equals(key)).limit(1).findFirst().get();
	}
}
