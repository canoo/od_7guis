package org.opendolphin.demo.sevenguis.crud;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientPresentationModel;
import org.opendolphin.core.client.comm.OnFinishedHandlerAdapter;
import org.svenehrke.javafxextensions.fxml.FXMLLoader2;
import org.svenehrke.javafxextensions.fxml.ViewAndRoot;

import java.util.List;
import java.util.function.Function;

import static org.opendolphin.demo.sevenguis.crud.SharedDolphinFunctions.stringValue;

public class Application extends javafx.application.Application {
    static ClientDolphin clientDolphin;

    private ViewAndRoot<MainView, BorderPane> vr;
    private MainView view;
    private SharedPmApi sharedPmApi;
    private Context context;

    @Override
    public void start(Stage stage) throws Exception {

        initClientContext();
        vr = createGUI(stage);
        view = vr.getView();
        prepareGUIforPMs();

        clientDolphin.send(ApplicationConstants.COMMAND_INIT_PMS, new OnFinishedHandlerAdapter() {
            @Override
            public void onFinished(List<ClientPresentationModel> presentationModels) {
                new MainViewInitializer(vr.getView(), clientDolphin, sharedPmApi).initializeBinding();
                stage.show();
            }
        });
    }

    private void initClientContext() {
        sharedPmApi = new SharedPmApi(clientDolphin);
        context = new Context();
    }

    private ViewAndRoot<MainView, BorderPane> createGUI(final Stage stage) {

        ViewAndRoot<MainView, BorderPane> result = viewAndRoot();

        Scene scene = new Scene(result.getRoot());
        scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("7 GUIs: CRUD");

        result.getView().listView.setItems(context.filteredItems);
        result.getView().prefix.textProperty().addListener(observable -> {
            context.filteredItems.setPredicate(Context.predicate(result.getView().prefix.getText()));
        });
        return result;
	}

    private void prepareGUIforPMs() {
        view.listView.setCellFactory(param -> new SimplePMListCell((pm, empty) -> listEntryFormatter().apply(pm)));
        new ModelStoreBinder(clientDolphin).bind(context.allItems, PersonApi.PERSON_TYPE, view); //!! concept
        new ODListViews().bindSelectedPmId(view.listView, sharedPmApi::getCurrentPmIdAttribute); //!! concept
    }

    private ViewAndRoot<MainView, BorderPane> viewAndRoot() {
        final ViewAndRoot<MainView, BorderPane> vr = FXMLLoader2.loadFXML("/Main.fxml");
        return vr;
    }

    private Function<PresentationModel, String> listEntryFormatter() {
        return (PresentationModel pm) -> String.format("%s, %s", stringValue(pm, PersonApi.ATT_LAST_NAME), stringValue(pm, PersonApi.ATT_FIRST_NAME));
    }


}
