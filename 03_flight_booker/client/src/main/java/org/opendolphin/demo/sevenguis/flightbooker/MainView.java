package org.opendolphin.demo.sevenguis.flightbooker;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class MainView {

	public final Pane root;

	public final ComboBox<Pair<String, String>> flightTypeComboBox;
	public final TextField startDateTextField;
	public final TextField returnDateTextField;
	public final Button bookButton;
	public final Label messageLabel;

	public MainView(int maxWidth) {
		root = new Pane();
		VBox vBox = new VBox(10);
		vBox.setPadding(new Insets(10));
		vBox.setSpacing(10);
		vBox.setMaxWidth(maxWidth);

		flightTypeComboBox = WidgetFactory.flightComboBox();

		startDateTextField = new TextField();
		returnDateTextField = new TextField();
		bookButton = new Button();
		messageLabel = new Label();
		messageLabel.setWrapText(true);

		root.getChildren().addAll(vBox);
		vBox.getChildren().addAll(flightTypeComboBox);
		vBox.getChildren().addAll(startDateTextField);
		vBox.getChildren().addAll(returnDateTextField);
		vBox.getChildren().addAll(bookButton);
		vBox.getChildren().addAll(messageLabel);
		bookButton.setText("Book");
	}

}
