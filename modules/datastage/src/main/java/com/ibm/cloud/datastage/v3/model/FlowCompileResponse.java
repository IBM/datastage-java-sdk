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

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Describes the compile response model.
 */
public class FlowCompileResponse extends GenericModel {

  protected Map<String, Object> message;
  protected String type;

  /**
   * Gets the message.
   *
   * Compile result for DataStage flow.
   *
   * @return the message
   */
  public Map<String, Object> getMessage() {
    return message;
  }

  /**
   * Gets the type.
   *
   * Compile response type. For example ok or error.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }
}

