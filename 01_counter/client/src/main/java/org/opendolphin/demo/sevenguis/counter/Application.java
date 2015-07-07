package org.opendolphin.demo.sevenguis.counter;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.opendolphin.binding.JFXBinder;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientPresentationModel;
import org.opendolphin.core.client.comm.OnFinishedHandlerAdapter;

import java.util.List;

import static org.opendolphin.demo.sevenguis.counter.ApplicationConstants.*;

public class Application extends javafx.application.Application {
    static ClientDolphin clientDolphin;

    private Button button;
    private Label counterLabel;

    @Override
    public void start(Stage stage) throws Exception {

        Pane root = setupStage();

        initializePMs();

        Scene scene = new Scene(root, 300, 300);
        scene.setFill(Color.GREEN);
        stage.setScene(scene);
        stage.setTitle("7 GUIs: Counter");
        stage.show();
    }

    private Pane setupStage() {
        Pane pane = new Pane();
        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        button = new Button();
        counterLabel = new Label("");
        counterLabel.setFont(Font.font("Verdana", 20));

        pane.getChildren().addAll(hBox);
		hBox.getChildren().addAll(counterLabel);
		hBox.getChildren().addAll(button);
        button.setText("Count");
        return pane;
    }

    private void initializePMs() {
		clientDolphin.send(COMMAND_INIT, new OnFinishedHandlerAdapter() {
    		@Override
    		public void onFinished(List<ClientPresentationModel> presentationModels) {
    			setupBinding();
    		}
    	});
    }

    private void setupBinding() {

        button.setOnAction(actionEvent -> clientDolphin.send(COMMAND_INC_COUNTER));
        PresentationModel pm = clientDolphin.getAt(PM_APP);
        JFXBinder.bind(ATT_COUNTER).of(pm).to("text").of(counterLabel);
    }

}
