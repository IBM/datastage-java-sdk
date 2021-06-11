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
 * The updateDatastageSubflows options.
 */
public class UpdateDatastageSubflowsOptions extends GenericModel {

  protected String dataIntgSubflowId;
  protected String dataIntgSubflowName;
  protected PipelineJson pipelineFlows;
  protected String catalogId;
  protected String projectId;

  /**
   * Builder.
   */
  public static class Builder {
    private String dataIntgSubflowId;
    private String dataIntgSubflowName;
    private PipelineJson pipelineFlows;
    private String catalogId;
    private String projectId;

    private Builder(UpdateDatastageSubflowsOptions updateDatastageSubflowsOptions) {
      this.dataIntgSubflowId = updateDatastageSubflowsOptions.dataIntgSubflowId;
      this.dataIntgSubflowName = updateDatastageSubflowsOptions.dataIntgSubflowName;
      this.pipelineFlows = updateDatastageSubflowsOptions.pipelineFlows;
      this.catalogId = updateDatastageSubflowsOptions.catalogId;
      this.projectId = updateDatastageSubflowsOptions.projectId;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param dataIntgSubflowId the dataIntgSubflowId
     * @param dataIntgSubflowName the dataIntgSubflowName
     */
    public Builder(String dataIntgSubflowId, String dataIntgSubflowName) {
      this.dataIntgSubflowId = dataIntgSubflowId;
      this.dataIntgSubflowName = dataIntgSubflowName;
    }

    /**
     * Builds a UpdateDatastageSubflowsOptions.
     *
     * @return the new UpdateDatastageSubflowsOptions instance
     */
    public UpdateDatastageSubflowsOptions build() {
      return new UpdateDatastageSubflowsOptions(this);
    }

    /**
     * Set the dataIntgSubflowId.
     *
     * @param dataIntgSubflowId the dataIntgSubflowId
     * @return the UpdateDatastageSubflowsOptions builder
     */
    public Builder dataIntgSubflowId(String dataIntgSubflowId) {
      this.dataIntgSubflowId = dataIntgSubflowId;
      return this;
    }

    /**
     * Set the dataIntgSubflowName.
     *
     * @param dataIntgSubflowName the dataIntgSubflowName
     * @return the UpdateDatastageSubflowsOptions builder
     */
    public Builder dataIntgSubflowName(String dataIntgSubflowName) {
      this.dataIntgSubflowName = dataIntgSubflowName;
      return this;
    }

    /**
     * Set the pipelineFlows.
     *
     * @param pipelineFlows the pipelineFlows
     * @return the UpdateDatastageSubflowsOptions builder
     */
    public Builder pipelineFlows(PipelineJson pipelineFlows) {
      this.pipelineFlows = pipelineFlows;
      return this;
    }

    /**
     * Set the catalogId.
     *
     * @param catalogId the catalogId
     * @return the UpdateDatastageSubflowsOptions builder
     */
    public Builder catalogId(String catalogId) {
      this.catalogId = catalogId;
      return this;
    }

    /**
     * Set the projectId.
     *
     * @param projectId the projectId
     * @return the UpdateDatastageSubflowsOptions builder
     */
    public Builder projectId(String projectId) {
      this.projectId = projectId;
      return this;
    }
  }

  protected UpdateDatastageSubflowsOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.dataIntgSubflowId,
      "dataIntgSubflowId cannot be empty");
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.dataIntgSubflowName,
      "dataIntgSubflowName cannot be null");
    dataIntgSubflowId = builder.dataIntgSubflowId;
    dataIntgSubflowName = builder.dataIntgSubflowName;
    pipelineFlows = builder.pipelineFlows;
    catalogId = builder.catalogId;
    projectId = builder.projectId;
  }

  /**
   * New builder.
   *
   * @return a UpdateDatastageSubflowsOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the dataIntgSubflowId.
   *
   * The DataStage subflow ID to use.
   *
   * @return the dataIntgSubflowId
   */
  public String dataIntgSubflowId() {
    return dataIntgSubflowId;
  }

  /**
   * Gets the dataIntgSubflowName.
   *
   * The DataStage subflow name.
   *
   * @return the dataIntgSubflowName
   */
  public String dataIntgSubflowName() {
    return dataIntgSubflowName;
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

