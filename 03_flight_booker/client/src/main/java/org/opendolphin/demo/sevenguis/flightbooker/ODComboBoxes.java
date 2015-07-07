package org.opendolphin.demo.sevenguis.flightbooker;

import javafx.scene.control.ComboBox;
import javafx.util.Pair;
import org.opendolphin.core.client.ClientAttribute;

import static org.opendolphin.demo.sevenguis.flightbooker.SharedDolphinFunctions.stringValue;

public class ODComboBoxes {

	public static void populateFromAttribute(ComboBox comboBox, ClientAttribute attribute) {
		comboBox.getSelectionModel().select(ComboBoxes.comboBoxItemForKey(comboBox, SharedDolphinFunctions.stringValue(attribute)));
	}

	public static void bindSelectionTo(ComboBox<Pair<String, String>> comboBox, ClientAttribute attribute) {
		comboBox.setOnAction(e -> {
			Object k = comboBox.getSelectionModel().getSelectedItem().getKey();
			attribute.setValue(k);
		});
	}
}
