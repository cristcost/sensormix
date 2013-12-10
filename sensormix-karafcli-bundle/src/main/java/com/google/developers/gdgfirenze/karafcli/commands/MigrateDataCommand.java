/*
 * Copyright 2013, Cristiano Costantini, Giuseppe Gerla, Michele Ficarra, Sergio Ciampi, Stefano
 * Cigheri.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.developers.gdgfirenze.karafcli.commands;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.service.SensormixService;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class MigrateDataCommand.
 */
@Command(scope = "sensormix", name = "migratedata",
    description = "Manage migrate data state of system")
public class MigrateDataCommand extends OsgiCommandSupport {

  private static Logger logger = Logger.getLogger(MigrateDataCommand.class.getName());

  @Argument(index = 0, name = "action", description = "Action command", required = true,
      multiValued = false)
  private String action;

  @Argument(index = 1, name = "sourceBeanName", description = "Bean name of dataservice source",
      required = true, multiValued = false)
  private String sourceBeanName;

  @Argument(index = 2, name = "destinationBeanName",
      description = "Bean name of dataservice destination", required = true, multiValued = false)
  private String destinationBeanName;

  private SensormixService source;

  private SensormixService destination;

  private SensormixService getSensormixServiceById(String id) {
    SensormixService service = null;
    ServiceReference[] refs = null;
    Bundle b = FrameworkUtil.getBundle(this.getClass());
    BundleContext bc = b.getBundleContext();
    try {
      refs =
          bc.getAllServiceReferences(SensormixService.class.getName(),
              "(org.springframework.osgi.bean.name=" + id + ")");
      if (refs.length == 0) {
        logger.log(Level.SEVERE, "Unable to retrieve service reference for SensormixService");
      } else if (refs.length > 1) {
        logger.log(Level.SEVERE, "More than one service registered for SensormixService");
      } else {
        service = (SensormixService) bc.getService(refs[0]);
      }

    } catch (InvalidSyntaxException e) {
      logger.log(Level.SEVERE, "Unable to retrieve service reference for SensormixService", e);
    }
    return service;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.karaf.shell.console.AbstractAction#doExecute()
   */
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
      List<AbstractSample> samples = source.getSamples(null, null, null, null, null, null);
      destination.recordSamples(samples);
    } else {
      logger.warning("Invalid syntax");
    }

    return null;
  }

}
