package org.opendolphin.demo.sevenguis.temperatureconverter;

import org.opendolphin.core.client.comm.JavaFXUiThreadHandler;
import org.opendolphin.core.comm.DefaultInMemoryConfig;
import org.opendolphin.demo.sevenguis.temperatureconverter.servlet.ApplicationDirector;

public class ApplicationInMemoryStarter {
    public static void main(String[] args) throws Exception {
        DefaultInMemoryConfig config = new DefaultInMemoryConfig();
        config.getServerDolphin().registerDefaultActions();
        config.getClientDolphin().getClientConnector().setUiThreadHandler(new JavaFXUiThreadHandler());
        registerApplicationActions(config);
        Application.clientDolphin = config.getClientDolphin();
        javafx.application.Application.launch(Application.class);
    }

    private static void registerApplicationActions(DefaultInMemoryConfig config) {
        config.getServerDolphin().register(new ApplicationDirector());
    }

}
