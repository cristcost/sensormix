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
package com.google.developers.gdgfirenze.osgi;

/**
 * The Interface SensormixAdminInterface.
 * 
 * Interface that exposes method for technical maintenance of the services.
 */
public interface SensormixAdminInterface {
  /**
   * Set maintenance state.
   *
   * @param value true for maintenance state
   */
  void setInMaintenace(boolean value);

  /**
   * Get maintenance state.
   *
   * @return true if the service is in maintenance
   */
  boolean isInMaintenance();
}
