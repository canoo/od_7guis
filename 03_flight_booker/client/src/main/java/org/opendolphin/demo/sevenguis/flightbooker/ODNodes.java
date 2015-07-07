package org.opendolphin.demo.sevenguis.flightbooker;

import javafx.scene.Node;
import org.opendolphin.core.client.ClientAttribute;

import java.beans.PropertyChangeListener;

public class ODNodes {

	public static void addDisablingBinding(ClientAttribute attribute, Node node) {
		attribute.addPropertyChangeListener(ApplicationConstants.PROP_VALUE, newDisableListener(node));
	}

	private static PropertyChangeListener newDisableListener(Node node) {
		return evt -> {
			Boolean b = (Boolean) evt.getNewValue();
			if (b != null) {
				node.disableProperty().setValue(Boolean.valueOf(!b));
			}
		};
	}
}
