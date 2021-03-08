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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Pipelines.
 */
public class Pipelines extends GenericModel {

  protected String id;
  protected String description;
  @SerializedName("runtime_ref")
  protected String runtimeRef;
  protected List<Object> nodes;
  @SerializedName("app_data")
  protected Map<String, Object> appData;

  /**
   * Builder.
   */
  public static class Builder {
    private String id;
    private String description;
    private String runtimeRef;
    private List<Object> nodes;
    private Map<String, Object> appData;

    private Builder(Pipelines pipelines) {
      this.id = pipelines.id;
      this.description = pipelines.description;
      this.runtimeRef = pipelines.runtimeRef;
      this.nodes = pipelines.nodes;
      this.appData = pipelines.appData;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a Pipelines.
     *
     * @return the new Pipelines instance
     */
    public Pipelines build() {
      return new Pipelines(this);
    }

    /**
     * Adds an nodes to nodes.
     *
     * @param nodes the new nodes
     * @return the Pipelines builder
     */
    public Builder addNodes(Object nodes) {
      com.ibm.cloud.sdk.core.util.Validator.notNull(nodes,
        "nodes cannot be null");
      if (this.nodes == null) {
        this.nodes = new ArrayList<Object>();
      }
      this.nodes.add(nodes);
      return this;
    }

    /**
     * Set the id.
     *
     * @param id the id
     * @return the Pipelines builder
     */
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the description.
     *
     * @param description the description
     * @return the Pipelines builder
     */
    public Builder description(String description) {
      this.description = description;
      return this;
    }

    /**
     * Set the runtimeRef.
     *
     * @param runtimeRef the runtimeRef
     * @return the Pipelines builder
     */
    public Builder runtimeRef(String runtimeRef) {
      this.runtimeRef = runtimeRef;
      return this;
    }

    /**
     * Set the nodes.
     * Existing nodes will be replaced.
     *
     * @param nodes the nodes
     * @return the Pipelines builder
     */
    public Builder nodes(List<Object> nodes) {
      this.nodes = nodes;
      return this;
    }

    /**
     * Set the appData.
     *
     * @param appData the appData
     * @return the Pipelines builder
     */
    public Builder appData(Map<String, Object> appData) {
      this.appData = appData;
      return this;
    }
  }

  protected Pipelines(Builder builder) {
    id = builder.id;
    description = builder.description;
    runtimeRef = builder.runtimeRef;
    nodes = builder.nodes;
    appData = builder.appData;
  }

  /**
   * New builder.
   *
   * @return a Pipelines builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the id.
   *
   * Unique identifier.
   *
   * @return the id
   */
  public String id() {
    return id;
  }

  /**
   * Gets the description.
   *
   * A brief description of the DataStage flow.
   *
   * @return the description
   */
  public String description() {
    return description;
  }

  /**
   * Gets the runtimeRef.
   *
   * Reference to the runtime type.
   *
   * @return the runtimeRef
   */
  public String runtimeRef() {
    return runtimeRef;
  }

  /**
   * Gets the nodes.
   *
   * Array of pipeline nodes.
   *
   * @return the nodes
   */
  public List<Object> nodes() {
    return nodes;
  }

  /**
   * Gets the appData.
   *
   * Object containing app-specific data.
   *
   * @return the appData
   */
  public Map<String, Object> appData() {
    return appData;
  }
}

