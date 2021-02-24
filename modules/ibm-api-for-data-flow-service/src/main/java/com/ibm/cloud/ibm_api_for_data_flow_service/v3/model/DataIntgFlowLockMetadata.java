/*
 * (C) Copyright IBM Corp. 2021.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.ibm.cloud.ibm_api_for_data_flow_service.v3.model;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Metadata information for a DataStage lock object.
 */
public class DataIntgFlowLockMetadata extends GenericModel {

  protected Boolean alive;

  /**
   * Gets the alive.
   *
   * Lock status.
   *
   * @return the alive
   */
  public Boolean isAlive() {
    return alive;
  }
}

