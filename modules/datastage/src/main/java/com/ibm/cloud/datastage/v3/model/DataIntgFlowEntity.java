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

import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The underlying DataStage flow definition.
 */
public class DataIntgFlowEntity extends GenericModel {

  @SerializedName("data_intg_flow")
  protected Map<String, Object> dataIntgFlow;
  protected DataIntgFlowLock lock;
  protected String description;
  protected String name;
  protected AssetEntityROV rov;
  @SerializedName("sub_type")
  protected String subType;

  /**
   * Gets the dataIntgFlow.
   *
   * Asset type object.
   *
   * @return the dataIntgFlow
   */
  public Map<String, Object> getDataIntgFlow() {
    return dataIntgFlow;
  }

  /**
   * Gets the lock.
   *
   * Lock information for a DataStage flow asset.
   *
   * @return the lock
   */
  public DataIntgFlowLock getLock() {
    return lock;
  }

  /**
   * Gets the description.
   *
   * The description of the DataStage flow.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the name.
   *
   * The name of the DataStage flow.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the rov.
   *
   * The rules of visibility for an asset.
   *
   * @return the rov
   */
  public AssetEntityROV getRov() {
    return rov;
  }

  /**
   * Gets the subType.
   *
   * A read-only field that can be used to distinguish between different types of data flow based on the service that
   * created it.
   *
   * @return the subType
   */
  public String getSubType() {
    return subType;
  }
}

