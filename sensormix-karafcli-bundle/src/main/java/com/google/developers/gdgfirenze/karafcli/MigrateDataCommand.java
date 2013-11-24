package com.google.developers.gdgfirenze.karafcli;

import java.util.List;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

import com.google.developers.gdgfirenze.service.SensormixAdminInterface;

@Command(scope = "sensormix", name = "migrate-data", description = "Manage migrate data state of system")
public class MigrateDataCommand extends OsgiCommandSupport {

	@Argument(index = 0, name = "action", description = "Start\\Stop maintenace", required = true, multiValued = false)
	String action;

	List<SensormixAdminInterface> bundlesInMaintenance;

	@Override
	protected Object doExecute() throws Exception {
		if (bundlesInMaintenance != null && bundlesInMaintenance.size() > 0) {

			if ("START".equals(action)) {
				System.out.println("Start maintenance period ");
				for (SensormixAdminInterface bun : bundlesInMaintenance) {
					bun.setMaintenace(true);
				}
			} else if ("END".equals(action)) {
				System.out.println("Stop maintenance period ");
				for (SensormixAdminInterface bun : bundlesInMaintenance) {
					bun.setMaintenace(false);
				}
			} else {
				System.err.println("Invalid syntax");
			}
		} else {
			System.err.println("No servie to do maintenance");
		}
		return null;
	}

}
