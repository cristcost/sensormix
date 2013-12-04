package com.google.developers.gdgfirenze.karafcli.commands;

import java.util.List;
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

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.service.SensormixService;

@Command(scope = "sensormix", name = "migratedata", description = "Manage migrate data state of system")
public class MigrateDataCommand extends OsgiCommandSupport {

	private static Logger logger = Logger.getLogger(MigrateDataCommand.class
			.getName());

	@Argument(index = 0, name = "action", description = "Action command", required = true, multiValued = false)
	private String action;
	
	@Argument(index = 1, name = "sourceBeanName", description = "Bean name of dataservice source", required = true, multiValued = false)
	private String sourceBeanName;

	@Argument(index = 2, name = "destinationBeanName", description = "Bean name of dataservice destination", required = true, multiValued = false)
	private String destinationBeanName;

	private SensormixService source;

	private SensormixService destination;

	private SensormixService getSensormixServiceById(String id) {
		SensormixService service = null;
		ServiceReference[] refs = null;
		Bundle b = FrameworkUtil.getBundle(this.getClass());
		BundleContext bc = b.getBundleContext();
		try {
			refs = bc.getAllServiceReferences(SensormixService.class.getName(),
					"(org.springframework.osgi.bean.name=" + id + ")");
			if (refs.length == 0) {
				logger.log(Level.SEVERE,
						"Unable to retrieve service reference for SensormixService");
			} else if (refs.length > 1) {
				logger.log(Level.SEVERE,
						"More than one service registered for SensormixService");
			} else {
				service = (SensormixService) bc.getService(refs[0]);
			}

		} catch (InvalidSyntaxException e) {
			logger.log(
					Level.SEVERE,
					"Unable to retrieve service reference for SensormixService",
					e);
		}
		return service;
	}

	@Override
	protected Object doExecute() throws Exception {
		if ("migrate".equals(action) && sourceBeanName != null && destinationBeanName != null) {
			logger.info("Find source service with bean name " + sourceBeanName);
			source = getSensormixServiceById(sourceBeanName);
		
			logger.info("Find destination service with bean name " + destinationBeanName);
			destination = getSensormixServiceById(destinationBeanName);
		
			logger.info("Moving sensors data ");
			List<Sensor> sensors = source.getSensors(null, null, null);
			for (Sensor s : sensors) {
				destination.registerSensor(s);
			}
			logger.info("Moving samples data ");
			List<AbstractSample> samples = source.getSamples(null, null, null,
					null, null, null);
			destination.recordSamples(samples);
		} else {
			logger.warning("Invalid syntax");
		}

		return null;
	}

}
