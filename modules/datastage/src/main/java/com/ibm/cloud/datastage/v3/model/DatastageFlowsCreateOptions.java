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
 * The datastageFlowsCreate options.
 */
public class DatastageFlowsCreateOptions extends GenericModel {

  /**
   * The category of the asset. Must be either SYSTEM or USER. Only a registered service can use this parameter.
   */
  public interface AssetCategory {
    /** system. */
    String SYSTEM = "system";
    /** user. */
    String USER = "user";
  }

  protected String dataIntgFlowName;
  protected PipelineJson pipelineFlows;
  protected String catalogId;
  protected String projectId;
  protected String assetCategory;

  /**
   * Builder.
   */
  public static class Builder {
    private String dataIntgFlowName;
    private PipelineJson pipelineFlows;
    private String catalogId;
    private String projectId;
    private String assetCategory;

    private Builder(DatastageFlowsCreateOptions datastageFlowsCreateOptions) {
      this.dataIntgFlowName = datastageFlowsCreateOptions.dataIntgFlowName;
      this.pipelineFlows = datastageFlowsCreateOptions.pipelineFlows;
      this.catalogId = datastageFlowsCreateOptions.catalogId;
      this.projectId = datastageFlowsCreateOptions.projectId;
      this.assetCategory = datastageFlowsCreateOptions.assetCategory;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param dataIntgFlowName the dataIntgFlowName
     */
    public Builder(String dataIntgFlowName) {
      this.dataIntgFlowName = dataIntgFlowName;
    }

    /**
     * Builds a DatastageFlowsCreateOptions.
     *
     * @return the new DatastageFlowsCreateOptions instance
     */
    public DatastageFlowsCreateOptions build() {
      return new DatastageFlowsCreateOptions(this);
    }

    /**
     * Set the dataIntgFlowName.
     *
     * @param dataIntgFlowName the dataIntgFlowName
     * @return the DatastageFlowsCreateOptions builder
     */
    public Builder dataIntgFlowName(String dataIntgFlowName) {
      this.dataIntgFlowName = dataIntgFlowName;
      return this;
    }

    /**
     * Set the pipelineFlows.
     *
     * @param pipelineFlows the pipelineFlows
     * @return the DatastageFlowsCreateOptions builder
     */
    public Builder pipelineFlows(PipelineJson pipelineFlows) {
      this.pipelineFlows = pipelineFlows;
      return this;
    }

    /**
     * Set the catalogId.
     *
     * @param catalogId the catalogId
     * @return the DatastageFlowsCreateOptions builder
     */
    public Builder catalogId(String catalogId) {
      this.catalogId = catalogId;
      return this;
    }

    /**
     * Set the projectId.
     *
     * @param projectId the projectId
     * @return the DatastageFlowsCreateOptions builder
     */
    public Builder projectId(String projectId) {
      this.projectId = projectId;
      return this;
    }

    /**
     * Set the assetCategory.
     *
     * @param assetCategory the assetCategory
     * @return the DatastageFlowsCreateOptions builder
     */
    public Builder assetCategory(String assetCategory) {
      this.assetCategory = assetCategory;
      return this;
    }
  }

  protected DatastageFlowsCreateOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.dataIntgFlowName,
      "dataIntgFlowName cannot be null");
    dataIntgFlowName = builder.dataIntgFlowName;
    pipelineFlows = builder.pipelineFlows;
    catalogId = builder.catalogId;
    projectId = builder.projectId;
    assetCategory = builder.assetCategory;
  }

  /**
   * New builder.
   *
   * @return a DatastageFlowsCreateOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
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

