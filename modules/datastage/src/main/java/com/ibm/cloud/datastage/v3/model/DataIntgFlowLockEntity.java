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
package com.ibm.cloud.datastage.v3.model;

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Entity information for a DataStage lock object.
 */
public class DataIntgFlowLockEntity extends GenericModel {

  @SerializedName("data_intg_flow_id")
  protected String dataIntgFlowId;
  protected String requester;

  /**
   * Gets the dataIntgFlowId.
   *
   * DataStage flow ID that is locked.
   *
   * @return the dataIntgFlowId
   */
  public String getDataIntgFlowId() {
    return dataIntgFlowId;
  }

  /**
   * Gets the requester.
   *
   * Requester of the lock.
   *
   * @return the requester
   */
  public String getRequester() {
    return requester;
  }
}

