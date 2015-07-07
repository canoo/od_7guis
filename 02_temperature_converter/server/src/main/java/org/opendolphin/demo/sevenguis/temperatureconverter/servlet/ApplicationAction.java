package org.opendolphin.demo.sevenguis.temperatureconverter.servlet;

import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.ServerPresentationModel;
import org.opendolphin.core.server.Slot;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.demo.sevenguis.temperatureconverter.ApplicationConstants;

import static org.opendolphin.demo.sevenguis.temperatureconverter.ApplicationConstants.*;

public class ApplicationAction extends DolphinServerAction{

    public void registerIn(ActionRegistry actionRegistry) {

        actionRegistry.register(COMMAND_INIT, (command, response) -> {
			// Create PM:
			DTO dto = new DTO(new Slot(ATT_CELSIUS, null), new Slot(ATT_FAHRENHEIT, null), new Slot(ATT_DIRECTION, VAL_DIRECTION_CELSIUS_TO_FAHRENHEIT));
			getServerDolphin().presentationModel(PM_APP, null, dto);

			ServerPresentationModel pm = getServerDolphin().getAt(PM_APP);
			// Not supported: Binder.bind(ATT_CELSIUS).of(pm).using(value -> "xx").to(ATT_FAHRENHEIT).of(pm);
			// PropertyChangeListener as workaround:
			pm.getAt(ATT_CELSIUS).addPropertyChangeListener("value", evt -> {
				if (VAL_DIRECTION_FAHRENHEIT_TO_CELSIUS.equals(pm.getAt(ATT_DIRECTION).getValue())) {
					return;
				}
				String newValue = (String) evt.getNewValue();
				Double c = null;
				try {
					c = Double.valueOf(newValue);
					Double f = TemperatureConverter.fahrenheitFromCelsius(c);
					String sf = String.valueOf(f);
					pm.getAt(ATT_FAHRENHEIT).setValue(sf);
				} catch (NumberFormatException e) {
					// invalid celsius string
				}
			});
			pm.getAt(ATT_FAHRENHEIT).addPropertyChangeListener("value", evt -> {
				if (VAL_DIRECTION_CELSIUS_TO_FAHRENHEIT.equals(pm.getAt(ATT_DIRECTION).getValue())) {
					return;
				}
				String newValue = (String) evt.getNewValue();
				Double f = null;
				try {
					f = Double.valueOf(newValue);
					Double c = TemperatureConverter.celsiusFromFahrenheit(f);
					String sc = String.valueOf(c);
					pm.getAt(ATT_CELSIUS).setValue(sc);
				} catch (NumberFormatException e) {
					// invalid fahrenheit string
				}
			});
		});


    }
}
