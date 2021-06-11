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
 * The createDatastageSubflows options.
 */
public class CreateDatastageSubflowsOptions extends GenericModel {

  /**
   * The category of the asset. Must be either SYSTEM or USER. Only a registered service can use this parameter.
   */
  public interface AssetCategory {
    /** system. */
    String SYSTEM = "system";
    /** user. */
    String USER = "user";
  }

  protected String dataIntgSubflowName;
  protected PipelineJson pipelineFlows;
  protected String catalogId;
  protected String projectId;
  protected String assetCategory;

  /**
   * Builder.
   */
  public static class Builder {
    private String dataIntgSubflowName;
    private PipelineJson pipelineFlows;
    private String catalogId;
    private String projectId;
    private String assetCategory;

    private Builder(CreateDatastageSubflowsOptions createDatastageSubflowsOptions) {
      this.dataIntgSubflowName = createDatastageSubflowsOptions.dataIntgSubflowName;
      this.pipelineFlows = createDatastageSubflowsOptions.pipelineFlows;
      this.catalogId = createDatastageSubflowsOptions.catalogId;
      this.projectId = createDatastageSubflowsOptions.projectId;
      this.assetCategory = createDatastageSubflowsOptions.assetCategory;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param dataIntgSubflowName the dataIntgSubflowName
     */
    public Builder(String dataIntgSubflowName) {
      this.dataIntgSubflowName = dataIntgSubflowName;
    }

    /**
     * Builds a CreateDatastageSubflowsOptions.
     *
     * @return the new CreateDatastageSubflowsOptions instance
     */
    public CreateDatastageSubflowsOptions build() {
      return new CreateDatastageSubflowsOptions(this);
    }

    /**
     * Set the dataIntgSubflowName.
     *
     * @param dataIntgSubflowName the dataIntgSubflowName
     * @return the CreateDatastageSubflowsOptions builder
     */
    public Builder dataIntgSubflowName(String dataIntgSubflowName) {
      this.dataIntgSubflowName = dataIntgSubflowName;
      return this;
    }

    /**
     * Set the pipelineFlows.
     *
     * @param pipelineFlows the pipelineFlows
     * @return the CreateDatastageSubflowsOptions builder
     */
    public Builder pipelineFlows(PipelineJson pipelineFlows) {
      this.pipelineFlows = pipelineFlows;
      return this;
    }

    /**
     * Set the catalogId.
     *
     * @param catalogId the catalogId
     * @return the CreateDatastageSubflowsOptions builder
     */
    public Builder catalogId(String catalogId) {
      this.catalogId = catalogId;
      return this;
    }

    /**
     * Set the projectId.
     *
     * @param projectId the projectId
     * @return the CreateDatastageSubflowsOptions builder
     */
    public Builder projectId(String projectId) {
      this.projectId = projectId;
      return this;
    }

    /**
     * Set the assetCategory.
     *
     * @param assetCategory the assetCategory
     * @return the CreateDatastageSubflowsOptions builder
     */
    public Builder assetCategory(String assetCategory) {
      this.assetCategory = assetCategory;
      return this;
    }
  }

  protected CreateDatastageSubflowsOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.dataIntgSubflowName,
      "dataIntgSubflowName cannot be null");
    dataIntgSubflowName = builder.dataIntgSubflowName;
    pipelineFlows = builder.pipelineFlows;
    catalogId = builder.catalogId;
    projectId = builder.projectId;
    assetCategory = builder.assetCategory;
  }

  /**
   * New builder.
   *
   * @return a CreateDatastageSubflowsOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
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

  /**
   * Gets the assetCategory.
   *
   * The category of the asset. Must be either SYSTEM or USER. Only a registered service can use this parameter.
   *
   * @return the assetCategory
   */
  public String assetCategory() {
    return assetCategory;
  }
}

