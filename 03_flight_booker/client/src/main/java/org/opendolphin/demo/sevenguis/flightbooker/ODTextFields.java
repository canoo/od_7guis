package org.opendolphin.demo.sevenguis.flightbooker;

import javafx.css.PseudoClass;
import javafx.scene.control.TextField;
import org.opendolphin.core.client.ClientAttribute;

import static org.opendolphin.demo.sevenguis.flightbooker.ApplicationConstants.PROP_VALUE;
import static org.opendolphin.demo.sevenguis.flightbooker.SharedDolphinFunctions.stringValue;

public class ODTextFields {

	public static final PseudoClass ERROR_CLASS = PseudoClass.getPseudoClass("error");

	// Red background on 'textField' when boolean 'attribute' is invalid
	public static void addRedBackgroundHandling(ClientAttribute attribute, TextField textField) {

		attribute.addPropertyChangeListener(PROP_VALUE, evt -> {
			applyRedBackground(textField, (Boolean) evt.getNewValue());
		});
	}

	private static void applyRedBackground(TextField textField, Boolean value) {
		if (value == null) return;
		textField.pseudoClassStateChanged(ERROR_CLASS, !value);
	}

	public static void addChangeBindingFromTo(TextField textField, ClientAttribute attribute) {
		textField.textProperty().addListener((s,o,n) -> attribute.setValue(n));
	}

	public static void populateFromAttribute(TextField textField, ClientAttribute attribute) {
		textField.setText(SharedDolphinFunctions.stringValue(attribute));
	}


}
