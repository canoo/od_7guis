package org.opendolphin.demo.sevenguis.crud;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.opendolphin.core.PresentationModel;

public class MainView {

	@FXML
	TextField prefix;

	@FXML
	ListView<PresentationModel> listView;

	@FXML
	TextField firstname;

	@FXML
	TextField lastname;

	@FXML
	Button createButton;

	@FXML
	Button updateButton;

	@FXML
	Button deleteButton;

}
