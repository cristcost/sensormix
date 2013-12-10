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

import com.google.developers.gdgfirenze.osgi.SensormixAdminInterface;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class MaintenanceCommand.
 */
@Command(scope = "sensormix", name = "maintenance",
    description = "Set SensormixAdmin services maintenance state in on/off")
public class MaintenanceCommand extends OsgiCommandSupport {

  private static Logger logger = Logger.getLogger(MaintenanceCommand.class.getName());

  @Argument(index = 0, name = "state", description = "On\\off maintenace", required = true,
      multiValued = false)
  private String state;

  @Argument(index = 1, name = "serviceBeanName", description = "Name of bean service",
      required = true, multiValued = false)
  private String serviceBeanName;

  private SensormixAdminInterface bundleInMaintenance;

  private SensormixAdminInterface getSensormixAdminServiceById(String id) {
    SensormixAdminInterface service = null;
    ServiceReference[] refs = null;
    Bundle b = FrameworkUtil.getBundle(this.getClass());
    BundleContext bc = b.getBundleContext();
    try {
      refs =
          bc.getAllServiceReferences(SensormixAdminInterface.class.getName(),
              "(org.springframework.osgi.bean.name=" + id + ")");
      if (refs == null || refs.length == 0) {
        logger.log(Level.SEVERE,
            "Unable to retrieve service reference for SensormixAdminInterface");
      } else if (refs.length > 1) {
        logger.log(Level.SEVERE, "More than one service registered for SensormixAdminInterface");
      } else {
        service = (SensormixAdminInterface) bc.getService(refs[0]);
      }

    } catch (InvalidSyntaxException e) {
      logger.log(Level.SEVERE, "Unable to retrieve service reference for SensormixAdminInterface",
          e);
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
    if ("on".equals(state) && serviceBeanName != null) {
      logger.info("Set maintenance state of " + serviceBeanName + " to On ");
      bundleInMaintenance = getSensormixAdminServiceById(serviceBeanName);
      if (bundleInMaintenance != null)
        bundleInMaintenance.setInMaintenace(true);
    } else if ("off".equals(state) && serviceBeanName != null) {
      logger.info("Set maintenance state of " + serviceBeanName + " to Off ");
      bundleInMaintenance = getSensormixAdminServiceById(serviceBeanName);
      bundleInMaintenance.setInMaintenace(false);
    } else {
      logger.warning("Invalid syntax");
    }

    return null;
  }

}
