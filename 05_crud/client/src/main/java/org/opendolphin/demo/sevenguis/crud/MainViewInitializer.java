package org.opendolphin.demo.sevenguis.crud;

import org.opendolphin.core.client.ClientDolphin;

import java.util.List;
import java.util.function.Predicate;

public class MainViewInitializer {

	private final MainView mainView;
	private final ClientDolphin clientDolphin;
	private final SharedPmApi sharedPmApi;

	public MainViewInitializer(MainView mainView, ClientDolphin clientDolphin, SharedPmApi sharedPmApi) {

		this.mainView = mainView;
		this.clientDolphin = clientDolphin;
		this.sharedPmApi = sharedPmApi;
	}

	public MainViewInitializer initializeBinding() {

		SharedPmApi pmApi = new SharedPmApi(clientDolphin);
		mainView.firstname.textProperty().addListener((s,o,n) -> {
			pmApi.getCurrentItem().getAt(PersonApi.ATT_FIRST_NAME).setValue(n);
			SharedDolphinFunctions.showPMInfo(pmApi.getCurrentItem(), "***** current pm");

			mainView.listView.getItems().forEach(pm -> SharedDolphinFunctions.showPMInfo(pm, "***** listview pm"));
		});
		pmApi.getCurrentItem().getAt(PersonApi.ATT_FIRST_NAME).addPropertyChangeListener("value", evt -> {
			mainView.firstname.textProperty().setValue(SharedDolphinFunctions.stringValue(pmApi.getCurrentItem(), PersonApi.ATT_FIRST_NAME));
		});
		TextFieldBinder.bindTextfield(pmApi.getCurrentItem(), PersonApi.ATT_FIRST_NAME, mainView.firstname);
		TextFieldBinder.bindTextfield(pmApi.getCurrentItem(), PersonApi.ATT_LAST_NAME, mainView.lastname);

		mainView.createButton.setOnAction(event -> { //!! should only say 'I was clicked' and not do something like here (sending a command via dolphin)
			clientDolphin.send(ApplicationConstants.COMMAND_CREATE_PERSON);
			mainView.listView.requestFocus();
		});
		mainView.updateButton.setOnAction(event -> clientDolphin.send(ApplicationConstants.COMMAND_UPDATE_PERSON));
		mainView.deleteButton.setOnAction(event -> {
			clientDolphin.send(ApplicationConstants.COMMAND_DELETE_PERSON);
		});

		sharedPmApi.getCurrentPmIdAttribute().addPropertyChangeListener("value", evt -> {
			String n = (String) evt.getNewValue();
			int idx = indexForItem(mainView.listView.getItems(), pm -> n.equals(pm.getId()));
			mainView.listView.getSelectionModel().select(idx);
		});



		return this;
	}

	public static <T> int indexForItem(List<T> list, Predicate<T> p) { //!! concept
		for (int i = 0; i < list.size(); i++) {
			T item = list.get(i);
			if (p.test(item)) {
				return i;
			}
		}
		return -1;
	}

}
