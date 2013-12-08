package com.google.developers.gdgfirenze.karafcli.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.google.developers.gdgfirenze.osgi.SensormixAdminInterface;

@Command(scope = "sensormix", name = "maintenance", description = "Set SensormixAdmin services maintenance state in on/off")
public class MaintenanceCommand extends OsgiCommandSupport {

	private static Logger logger = Logger.getLogger(MaintenanceCommand.class
			.getName());

	@Argument(index = 0, name = "state", description = "On\\off maintenace", required = true, multiValued = false)
	private String state;

	@Argument(index = 1, name = "serviceBeanName", description = "Name of bean service", required = true, multiValued = false)
	private String serviceBeanName;

	private SensormixAdminInterface bundleInMaintenance;

	private SensormixAdminInterface getSensormixAdminServiceById(String id) {
		SensormixAdminInterface service = null;
		ServiceReference[] refs = null;
		Bundle b = FrameworkUtil.getBundle(this.getClass());
		BundleContext bc = b.getBundleContext();
		try {
			refs = bc.getAllServiceReferences(
					SensormixAdminInterface.class.getName(),
					"(org.springframework.osgi.bean.name=" + id + ")");
			if (refs == null || refs.length == 0) {
				logger.log(Level.SEVERE,
						"Unable to retrieve service reference for SensormixAdminInterface");
			} else if (refs.length > 1) {
				logger.log(Level.SEVERE,
						"More than one service registered for SensormixAdminInterface");
			} else {
				service = (SensormixAdminInterface) bc.getService(refs[0]);
			}

		} catch (InvalidSyntaxException e) {
			logger.log(
					Level.SEVERE,
					"Unable to retrieve service reference for SensormixAdminInterface",
					e);
		}
		return service;
	}

	@Override
	protected Object doExecute() throws Exception {
		if ("on".equals(state) && serviceBeanName != null) {
			logger.info("Set maintenance state of " + serviceBeanName
					+ " to On ");
			bundleInMaintenance = getSensormixAdminServiceById(serviceBeanName);
			if (bundleInMaintenance != null)
				bundleInMaintenance.setInMaintenace(true);
		} else if ("off".equals(state) && serviceBeanName != null) {
			logger.info("Set maintenance state of " + serviceBeanName
					+ " to Off ");
			bundleInMaintenance = getSensormixAdminServiceById(serviceBeanName);
			bundleInMaintenance.setInMaintenace(false);
		} else {
			logger.warning("Invalid syntax");
		}

		return null;
	}

}
