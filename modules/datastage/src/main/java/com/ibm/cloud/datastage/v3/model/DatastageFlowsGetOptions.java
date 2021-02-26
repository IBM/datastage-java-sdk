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

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The datastageFlowsGet options.
 */
public class DatastageFlowsGetOptions extends GenericModel {

  protected String dataIntgFlowId;
  protected String catalogId;
  protected String projectId;

  /**
   * Builder.
   */
  public static class Builder {
    private String dataIntgFlowId;
    private String catalogId;
    private String projectId;

    private Builder(DatastageFlowsGetOptions datastageFlowsGetOptions) {
      this.dataIntgFlowId = datastageFlowsGetOptions.dataIntgFlowId;
      this.catalogId = datastageFlowsGetOptions.catalogId;
      this.projectId = datastageFlowsGetOptions.projectId;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param dataIntgFlowId the dataIntgFlowId
     */
    public Builder(String dataIntgFlowId) {
      this.dataIntgFlowId = dataIntgFlowId;
    }

    /**
     * Builds a DatastageFlowsGetOptions.
     *
     * @return the new DatastageFlowsGetOptions instance
     */
    public DatastageFlowsGetOptions build() {
      return new DatastageFlowsGetOptions(this);
    }

    /**
     * Set the dataIntgFlowId.
     *
     * @param dataIntgFlowId the dataIntgFlowId
     * @return the DatastageFlowsGetOptions builder
     */
    public Builder dataIntgFlowId(String dataIntgFlowId) {
      this.dataIntgFlowId = dataIntgFlowId;
      return this;
    }

    /**
     * Set the catalogId.
     *
     * @param catalogId the catalogId
     * @return the DatastageFlowsGetOptions builder
     */
    public Builder catalogId(String catalogId) {
      this.catalogId = catalogId;
      return this;
    }

    /**
     * Set the projectId.
     *
     * @param projectId the projectId
     * @return the DatastageFlowsGetOptions builder
     */
    public Builder projectId(String projectId) {
      this.projectId = projectId;
      return this;
    }
  }

  protected DatastageFlowsGetOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.dataIntgFlowId,
      "dataIntgFlowId cannot be empty");
    dataIntgFlowId = builder.dataIntgFlowId;
    catalogId = builder.catalogId;
    projectId = builder.projectId;
  }

  /**
   * New builder.
   *
   * @return a DatastageFlowsGetOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the dataIntgFlowId.
   *
   * The DataStage flow ID to use.
   *
   * @return the dataIntgFlowId
   */
  public String dataIntgFlowId() {
    return dataIntgFlowId;
  }

  /**
   * Gets the catalogId.
   *
   * The ID of the catalog to use. catalog_id or project_id is required.
   *
   * @return the catalogId
   */
  public String catalogId() {
    return catalogId;
  }

  /**
   * Gets the projectId.
   *
   * The ID of the project to use. catalog_id or project_id is required.
   *
   * @return the projectId
   */
  public String projectId() {
    return projectId;
  }
}

