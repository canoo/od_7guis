package org.opendolphin.demo.sevenguis.flightbooker;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.util.Pair;
import org.opendolphin.core.Tag;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientPresentationModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainViewInitializer {

	private final MainView mainView;
	private final ClientDolphin clientDolphin;

	public MainViewInitializer(MainView mainView, ClientDolphin clientDolphin) {

		this.mainView = mainView;
		this.clientDolphin = clientDolphin;
	}

	public MainViewInitializer initializeBinding() {

		ClientPresentationModel pm = clientDolphin.getAt(ApplicationConstants.PM_APP);

		// Flight Type:
		ODComboBoxes.bindSelectionTo(mainView.flightTypeComboBox, pm.getAt(ApplicationConstants.ATT_FLIGHT_TYPE));

		// Start Date:
		ODTextFields.addChangeBindingFromTo(mainView.startDateTextField, pm.getAt(ApplicationConstants.ATT_START_DATE));
		ODTextFields.addRedBackgroundHandling(pm.getAt(ApplicationConstants.ATT_START_DATE, ApplicationConstants.VALID_TAG), mainView.startDateTextField);

		// Return Date:
		ODTextFields.addChangeBindingFromTo(mainView.returnDateTextField, pm.getAt(ApplicationConstants.ATT_RETURN_DATE));
		ODTextFields.addRedBackgroundHandling(pm.getAt(ApplicationConstants.ATT_RETURN_DATE, ApplicationConstants.VALID_TAG), mainView.returnDateTextField);
		ODNodes.addDisablingBinding(pm.getAt(ApplicationConstants.ATT_RETURN_DATE, Tag.ENABLED), mainView.returnDateTextField);

		// Book Button:
		ODNodes.addDisablingBinding(pm.getAt(ApplicationConstants.ATT_BOOK_COMMAND_ENABLED), mainView.bookButton);
		mainView.bookButton.setOnAction(event -> clientDolphin.send(ApplicationConstants.COMMAND_BOOK));

		// Message:
		pm.getAt(ApplicationConstants.ATT_MESSAGE).addPropertyChangeListener(ApplicationConstants.PROP_VALUE, evt -> {
			mainView.messageLabel.setText((String) evt.getNewValue());
		});

		return this;
	}

	/**
	 * todo: note: two-way binding for widgets would be overkill. That's why the widget's values are initialized here
	 */
	public void handleDataInitializedEvent() {

		ClientPresentationModel pm = clientDolphin.getAt(ApplicationConstants.PM_APP);

		ComboBox<Pair<String, String>> cb = mainView.flightTypeComboBox;

		cb.getItems().addAll(FXCollections.observableArrayList(pairlistFromString(SharedDolphinFunctions.stringValue(pm.getAt(ApplicationConstants.ATT_FLIGHT_TYPES)))));
		ODComboBoxes.populateFromAttribute(cb, pm.getAt(ApplicationConstants.ATT_FLIGHT_TYPE));

		ODTextFields.populateFromAttribute(mainView.startDateTextField, pm.getAt(ApplicationConstants.ATT_START_DATE));
		ODTextFields.populateFromAttribute(mainView.returnDateTextField, pm.getAt(ApplicationConstants.ATT_RETURN_DATE));
	}

	private List<Pair<String, String>> pairlistFromString(String mapString) { // mapString format: key1:value1,key2,value2...
		return Arrays.asList(mapString.split(",")).stream().map(s -> new Pair<>(s.split(":")[0], s.split(":")[1])).collect(Collectors.toList());
	}
}
