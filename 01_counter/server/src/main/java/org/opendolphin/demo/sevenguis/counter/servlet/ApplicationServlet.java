package org.opendolphin.demo.sevenguis.counter.servlet;

import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.demo.sevenguis.counter.ApplicationDirector;
import org.opendolphin.server.adapter.DolphinServlet;

/**
 * For real server mode, this servlet acts as entry point for all communication.
 */
public class ApplicationServlet extends DolphinServlet{
    @Override
    protected void registerApplicationActions(ServerDolphin serverDolphin) {
        serverDolphin.register(new ApplicationDirector());
    }
}
