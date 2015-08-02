package org.opendolphin.demo.sevenguis.crud;

import javafx.beans.property.SimpleStringProperty;
import org.opendolphin.core.client.ClientAttribute;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.WeakReference;

/**
 * Like ClientAttributeWrapper but extending SimpleStringProperty so that a TableView's columns can be of type String
 */
public class ClientAttributeStringWrapper extends SimpleStringProperty {
	private final WeakReference<ClientAttribute> attributeRef;
	private final String name;

	public ClientAttributeStringWrapper(ClientAttribute attribute) {
		this.attributeRef = new WeakReference<ClientAttribute>(attribute);
		// we cache the attribute's propertyName as the property's name
		// because the value does not change and we want to avoid
		// dealing with null values from WR
		this.name = attribute.getPropertyName();
		attribute.addPropertyChangeListener("value", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				fireValueChangedEvent();
			}
		});
		// the dirtyness may also change and shall call a re-render as the consumer may rely on that
		attribute.addPropertyChangeListener("dirty", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				fireValueChangedEvent();
			}
		});
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void set(String value) {
		ClientAttribute attribute = attributeRef.get();
		if (attribute != null) attribute.setValue(value);
	}

	@Override
	public String get() {
		ClientAttribute attribute = attributeRef.get();
		return attribute != null ? (String) attribute.getValue() : null;
	}
}
