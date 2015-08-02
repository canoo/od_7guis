package org.opendolphin.demo.sevenguis.crud;

import org.opendolphin.demo.sevenguis.crud.domain.PersonServerAPI;
import org.opendolphin.core.ModelStore;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.ServerPresentationModel;
import org.opendolphin.core.server.Slot;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.demo.sevenguis.crud.domain.PersonSampleDataInitializer;

import java.util.Date;

public class ApplicationAction extends DolphinServerAction{

    public void registerIn(ActionRegistry actionRegistry) {

        SharedPmApi sharedPmApi = new SharedPmApi(getServerDolphin());

        actionRegistry.register(ApplicationConstants.COMMAND_INIT_PMS, (command, response) -> {
			new PersonSampleDataInitializer().initialize(getServerDolphin());
            createCurrentPM();
            createMetaPM();
            addCurrentPMIdHandler();
		});

        actionRegistry.register(ApplicationConstants.COMMAND_CREATE_PERSON, (command, response) -> {
            System.out.println("COMMAND_CREATE_PERSON");
            String pmId = "PM_" + new Date().getTime();
            ModelStore modelStore = getServerDolphin().getModelStore();
            PersonServerAPI.newPM(getServerDolphin(), pmId, "FIRSTNAME", "LASTNAME", "11.11.2015");
            sharedPmApi.getCurrentPmIdAttribute().setValue(pmId);
        });

        actionRegistry.register(ApplicationConstants.COMMAND_UPDATE_PERSON, (command, response) -> {
            System.out.println("COMMAND_UPDATE_PERSON");
            ServerPresentationModel targetPM = getServerDolphin().getAt(SharedDolphinFunctions.stringValue(sharedPmApi.getMetaPM(), ApplicationConstants.ATT_CURRENT_PM_ID));
            SharedDolphinFunctions.applyPMfromTo(sharedPmApi.getCurrentItem(), targetPM);
		});

        actionRegistry.register(ApplicationConstants.COMMAND_DELETE_PERSON, (command, response) -> {
            ServerPresentationModel targetPM = getServerDolphin().getAt(SharedDolphinFunctions.stringValue(sharedPmApi.getMetaPM(), ApplicationConstants.ATT_CURRENT_PM_ID));
            getServerDolphin().remove(targetPM);
            System.out.println("COMMAND_DELETE_PERSON");
		});

    }

    private void createCurrentPM() {
        getServerDolphin().presentationModel(ApplicationConstants.PM_CURRENT_PM, PersonApi.PERSON_TYPE_TECHNICAL, PersonServerAPI.newDTO(ApplicationConstants.PM_CURRENT_PM, "", "", ""));//!! PERSON_TYPE_TECHNICAL so that it does not appear in ListView

    }

    private void createMetaPM() {
        getServerDolphin().presentationModel(ApplicationConstants.PM_META, ApplicationConstants.TYPE_META, new DTO(new Slot(ApplicationConstants.ATT_CURRENT_PM_ID, null)));
    }

    private void addCurrentPMIdHandler() {
        PresentationModel metaPM = getServerDolphin().getAt(ApplicationConstants.PM_META);
        metaPM.getAt(ApplicationConstants.ATT_CURRENT_PM_ID).addPropertyChangeListener("value", evt -> {
            String pmId = (String) evt.getNewValue();
            ServerPresentationModel pm = getServerDolphin().findPresentationModelById(pmId);
            if (pm != null) {
                SharedPmApi pmApi = new SharedPmApi(getServerDolphin());
                PresentationModel currentPM = pmApi.getCurrentItem();
                // like 'currentPM.syncWith(pm)' but without qualifier (bc. editing the textfield should not change firstname/lastname in listview, only after update button got clicked):
                SharedDolphinFunctions.populate().pm(currentPM).from(pm).skipQualifier().run();

            }
        });

    }


}
