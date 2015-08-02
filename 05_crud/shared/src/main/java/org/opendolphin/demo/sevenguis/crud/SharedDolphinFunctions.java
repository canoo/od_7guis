package org.opendolphin.demo.sevenguis.crud;

import org.opendolphin.core.Attribute;
import org.opendolphin.core.BaseAttribute;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.Tag;

import java.util.function.Function;

public class SharedDolphinFunctions {

	public static final String PROP_VALUE = "value"; // property 'value' of 'BaseAttribute'

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
		sourceAttribute.addPropertyChangeListener(PROP_VALUE, evt -> {
			T ok = mapper.apply((S) evt.getNewValue());
			okAttribute.setValue(ok);
		});

	}

	public static void showPMInfo(PresentationModel pm, String title) {
		System.out.println("---> " + title);
		System.out.println(pm.getId());
		pm.getAttributes().forEach(a -> System.out.printf("  a-id = %s, a.p=%s, a.q=%s%n", a.getId(), a.getPropertyName(), a.getQualifier()));
		System.out.println("<---");
	}

	public static void applyPMfromTo(PresentationModel sourcePM, PresentationModel targetPM) {
		targetPM.getAttributes().stream().forEach(targetAttribute -> {
			Attribute sourceAttribute = sourcePM.getAt(targetAttribute.getPropertyName(), targetAttribute.getTag());
			if (sourceAttribute != null) {
				// like 'targetAttribute.syncWith(sourceAttribute)' but without qualifier:
				targetAttribute.setBaseValue(sourceAttribute.getBaseValue());
				targetAttribute.setValue(sourceAttribute.getValue());
			}
		});
	}

	/**
	 * like 'BaseAttribute.syncWith(pm)' but with the possibility to skip qualifier (e.g. editing textfield should not change firstname/lastname in listview, only after update button got clicked):
	 * @return
	 */
	public static TargetPMBuilder populate() {
		return targetPM -> sourcePM -> skipQualifier -> () -> targetPM.getAttributes().stream().forEach(targetAttribute -> {
			Attribute sourceAttribute = sourcePM.getAt(targetAttribute.getPropertyName(), targetAttribute.getTag());
			if (sourceAttribute != null) {
				//order is important
				if (! skipQualifier) {
					((BaseAttribute)targetAttribute).setQualifier(sourceAttribute.getQualifier());
				}
				targetAttribute.setBaseValue(sourceAttribute.getBaseValue());
				targetAttribute.setValue(sourceAttribute.getValue());
			}
		});
	}

	public interface TargetPMBuilder {
		SourcePMBuilder pm(PresentationModel targetPM);
	}

	public interface SourcePMBuilder {
		QualifierPMBuilder from(PresentationModel sourcePM);
	}
	public interface QualifierPMBuilder {
		Runnable skipQualifier(boolean wq);
		default Runnable skipQualifier() {
			return skipQualifier(true);
		}
	}

}
