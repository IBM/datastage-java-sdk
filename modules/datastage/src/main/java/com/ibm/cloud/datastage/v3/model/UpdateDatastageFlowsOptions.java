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
 * The updateDatastageFlows options.
 */
public class UpdateDatastageFlowsOptions extends GenericModel {

  protected String dataIntgFlowId;
  protected String dataIntgFlowName;
  protected PipelineJson pipelineFlows;
  protected String catalogId;
  protected String projectId;

  /**
   * Builder.
   */
  public static class Builder {
    private String dataIntgFlowId;
    private String dataIntgFlowName;
    private PipelineJson pipelineFlows;
    private String catalogId;
    private String projectId;

    private Builder(UpdateDatastageFlowsOptions updateDatastageFlowsOptions) {
      this.dataIntgFlowId = updateDatastageFlowsOptions.dataIntgFlowId;
      this.dataIntgFlowName = updateDatastageFlowsOptions.dataIntgFlowName;
      this.pipelineFlows = updateDatastageFlowsOptions.pipelineFlows;
      this.catalogId = updateDatastageFlowsOptions.catalogId;
      this.projectId = updateDatastageFlowsOptions.projectId;
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
     * @param dataIntgFlowName the dataIntgFlowName
     */
    public Builder(String dataIntgFlowId, String dataIntgFlowName) {
      this.dataIntgFlowId = dataIntgFlowId;
      this.dataIntgFlowName = dataIntgFlowName;
    }

    /**
     * Builds a UpdateDatastageFlowsOptions.
     *
     * @return the new UpdateDatastageFlowsOptions instance
     */
    public UpdateDatastageFlowsOptions build() {
      return new UpdateDatastageFlowsOptions(this);
    }

    /**
     * Set the dataIntgFlowId.
     *
     * @param dataIntgFlowId the dataIntgFlowId
     * @return the UpdateDatastageFlowsOptions builder
     */
    public Builder dataIntgFlowId(String dataIntgFlowId) {
      this.dataIntgFlowId = dataIntgFlowId;
      return this;
    }

    /**
     * Set the dataIntgFlowName.
     *
     * @param dataIntgFlowName the dataIntgFlowName
     * @return the UpdateDatastageFlowsOptions builder
     */
    public Builder dataIntgFlowName(String dataIntgFlowName) {
      this.dataIntgFlowName = dataIntgFlowName;
      return this;
    }

    /**
     * Set the pipelineFlows.
     *
     * @param pipelineFlows the pipelineFlows
     * @return the UpdateDatastageFlowsOptions builder
     */
    public Builder pipelineFlows(PipelineJson pipelineFlows) {
      this.pipelineFlows = pipelineFlows;
      return this;
    }

    /**
     * Set the catalogId.
     *
     * @param catalogId the catalogId
     * @return the UpdateDatastageFlowsOptions builder
     */
    public Builder catalogId(String catalogId) {
      this.catalogId = catalogId;
      return this;
    }

    /**
     * Set the projectId.
     *
     * @param projectId the projectId
     * @return the UpdateDatastageFlowsOptions builder
     */
    public Builder projectId(String projectId) {
      this.projectId = projectId;
      return this;
    }
  }

  protected UpdateDatastageFlowsOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.dataIntgFlowId,
      "dataIntgFlowId cannot be empty");
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.dataIntgFlowName,
      "dataIntgFlowName cannot be null");
    dataIntgFlowId = builder.dataIntgFlowId;
    dataIntgFlowName = builder.dataIntgFlowName;
    pipelineFlows = builder.pipelineFlows;
    catalogId = builder.catalogId;
    projectId = builder.projectId;
  }

  /**
   * New builder.
   *
   * @return a UpdateDatastageFlowsOptions builder
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
   * Gets the dataIntgFlowName.
   *
   * The data flow name.
   *
   * @return the dataIntgFlowName
   */
  public String dataIntgFlowName() {
    return dataIntgFlowName;
  }

  /**
   * Gets the pipelineFlows.
   *
   * Pipeline flow to be stored.
   *
   * @return the pipelineFlows
   */
  public PipelineJson pipelineFlows() {
    return pipelineFlows;
  }

  /**
   * Gets the catalogId.
   *
   * The ID of the catalog to use. `catalog_id` or `project_id` is required.
   *
   * @return the catalogId
   */
  public String catalogId() {
    return catalogId;
  }

  /**
   * Gets the projectId.
   *
   * The ID of the project to use. `catalog_id` or `project_id` is required.
   *
   * @return the projectId
   */
  public String projectId() {
    return projectId;
  }
}

