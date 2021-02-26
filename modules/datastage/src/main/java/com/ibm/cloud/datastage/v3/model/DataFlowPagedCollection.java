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

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * A page from a collection of DataStage flows.
 */
public class DataFlowPagedCollection extends GenericModel {

  @SerializedName("data_flows")
  protected List<DataIntgFlow> dataFlows;
  protected HrefModel first;
  protected HrefModel prev;
  protected HrefModel next;
  protected HrefModel last;
  protected Long limit;
  @SerializedName("total_count")
  protected Long totalCount;

  /**
   * Gets the dataFlows.
   *
   * A page from a collection of DataStage flows.
   *
   * @return the dataFlows
   */
  public List<DataIntgFlow> getDataFlows() {
    return dataFlows;
  }

  /**
   * Gets the first.
   *
   * URI of a resource.
   *
   * @return the first
   */
  public HrefModel getFirst() {
    return first;
  }

  /**
   * Gets the prev.
   *
   * URI of a resource.
   *
   * @return the prev
   */
  public HrefModel getPrev() {
    return prev;
  }

  /**
   * Gets the next.
   *
   * URI of a resource.
   *
   * @return the next
   */
  public HrefModel getNext() {
    return next;
  }

  /**
   * Gets the last.
   *
   * URI of a resource.
   *
   * @return the last
   */
  public HrefModel getLast() {
    return last;
  }

  /**
   * Gets the limit.
   *
   * The number of data flows requested to be returned.
   *
   * @return the limit
   */
  public Long getLimit() {
    return limit;
  }

  /**
   * Gets the totalCount.
   *
   * The total number of DataStage flows available.
   *
   * @return the totalCount
   */
  public Long getTotalCount() {
    return totalCount;
  }
}

