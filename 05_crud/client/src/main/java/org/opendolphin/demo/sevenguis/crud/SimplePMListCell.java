package org.opendolphin.demo.sevenguis.crud;

import javafx.scene.control.ListCell;
import org.opendolphin.core.PresentationModel;

import java.util.function.BiFunction;

public class SimplePMListCell extends ListCell<PresentationModel> {

	private final BiFunction<PresentationModel, Boolean, String> formatter;

	public SimplePMListCell(BiFunction<PresentationModel, Boolean, String> formatter) {
		this.formatter = formatter;
	}

	@Override
	protected void updateItem(PresentationModel pm, boolean empty) {
		super.updateItem(pm, empty);
		setText(pm == null ? "" : formatter.apply(pm, empty));
	}
}
