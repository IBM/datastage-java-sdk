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

  @SerializedName("app_data")
  protected Map<String, Object> appData;
  protected String description;
  protected String id;
  protected String name;
  protected List<Object> nodes;
  @SerializedName("runtime_ref")
  protected String runtimeRef;

  /**
   * Builder.
   */
  public static class Builder {
    private Map<String, Object> appData;
    private String description;
    private String id;
    private String name;
    private List<Object> nodes;
    private String runtimeRef;

    private Builder(Pipelines pipelines) {
      this.appData = pipelines.appData;
      this.description = pipelines.description;
      this.id = pipelines.id;
      this.name = pipelines.name;
      this.nodes = pipelines.nodes;
      this.runtimeRef = pipelines.runtimeRef;
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
     * Set the appData.
     *
     * @param appData the appData
     * @return the Pipelines builder
     */
    public Builder appData(Map<String, Object> appData) {
      this.appData = appData;
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
     * Set the name.
     *
     * @param name the name
     * @return the Pipelines builder
     */
    public Builder name(String name) {
      this.name = name;
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
     * Set the runtimeRef.
     *
     * @param runtimeRef the runtimeRef
     * @return the Pipelines builder
     */
    public Builder runtimeRef(String runtimeRef) {
      this.runtimeRef = runtimeRef;
      return this;
    }
  }

  protected Pipelines(Builder builder) {
    appData = builder.appData;
    description = builder.description;
    id = builder.id;
    name = builder.name;
    nodes = builder.nodes;
    runtimeRef = builder.runtimeRef;
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
   * Gets the appData.
   *
   * Object containing app-specific data.
   *
   * @return the appData
   */
  public Map<String, Object> appData() {
    return appData;
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
   * Gets the name.
   *
   * Name of the pipeline.
   *
   * @return the name
   */
  public String name() {
    return name;
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
   * Gets the runtimeRef.
   *
   * Reference to the runtime type.
   *
   * @return the runtimeRef
   */
  public String runtimeRef() {
    return runtimeRef;
  }
}

