package org.opendolphin.demo.sevenguis.crud;

import javafx.scene.control.TextField;
import org.opendolphin.binding.JFXBinder;
import org.opendolphin.core.PresentationModel;

public class TextFieldBinder {

	public static void bindTextfield(PresentationModel pm, String attribute, TextField textField) {
		JFXBinder.bind(attribute).of(pm).to("text").of(textField);
		JFXBinder.bind("text").of(textField).to(attribute).of(pm);
	}

}