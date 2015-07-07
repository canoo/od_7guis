package org.opendolphin.demo.sevenguis.flightbooker;

import org.opendolphin.core.BaseAttribute;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.Tag;

import java.util.function.Function;

public class SharedDolphinFunctions {

	public static Tag extractTag(Tag[] tags) {
		return tags == null ? Tag.VALUE : tags.length == 0 ? Tag.VALUE : tags[0];
	}

	public static String stringValue(PresentationModel pm, String propertyName) {
		return (String) pm.getAt(propertyName).getValue();
	}

	public static String stringValue(BaseAttribute attribute) {
		return (String) attribute.getValue();
	}
	public static Boolean booleanValue(BaseAttribute attribute) {
		return attribute.getValue() == null ? Boolean.FALSE : (Boolean) attribute.getValue();
	}

	public static <S, T> void bindAttributeTo(BaseAttribute sourceAttribute, Function<S, T> mapper, BaseAttribute okAttribute) {
		sourceAttribute.addPropertyChangeListener(ApplicationConstants.PROP_VALUE, evt -> {
			T ok = mapper.apply((S) evt.getNewValue());
			okAttribute.setValue(ok);
		});

	}
}
