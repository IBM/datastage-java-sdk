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
 * The cloneDatastageSubflows options.
 */
public class CloneDatastageSubflowsOptions extends GenericModel {

  protected String dataIntgSubflowId;
  protected String catalogId;
  protected String projectId;

  /**
   * Builder.
   */
  public static class Builder {
    private String dataIntgSubflowId;
    private String catalogId;
    private String projectId;

    private Builder(CloneDatastageSubflowsOptions cloneDatastageSubflowsOptions) {
      this.dataIntgSubflowId = cloneDatastageSubflowsOptions.dataIntgSubflowId;
      this.catalogId = cloneDatastageSubflowsOptions.catalogId;
      this.projectId = cloneDatastageSubflowsOptions.projectId;
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
     */
    public Builder(String dataIntgSubflowId) {
      this.dataIntgSubflowId = dataIntgSubflowId;
    }

    /**
     * Builds a CloneDatastageSubflowsOptions.
     *
     * @return the new CloneDatastageSubflowsOptions instance
     */
    public CloneDatastageSubflowsOptions build() {
      return new CloneDatastageSubflowsOptions(this);
    }

    /**
     * Set the dataIntgSubflowId.
     *
     * @param dataIntgSubflowId the dataIntgSubflowId
     * @return the CloneDatastageSubflowsOptions builder
     */
    public Builder dataIntgSubflowId(String dataIntgSubflowId) {
      this.dataIntgSubflowId = dataIntgSubflowId;
      return this;
    }

    /**
     * Set the catalogId.
     *
     * @param catalogId the catalogId
     * @return the CloneDatastageSubflowsOptions builder
     */
    public Builder catalogId(String catalogId) {
      this.catalogId = catalogId;
      return this;
    }

    /**
     * Set the projectId.
     *
     * @param projectId the projectId
     * @return the CloneDatastageSubflowsOptions builder
     */
    public Builder projectId(String projectId) {
      this.projectId = projectId;
      return this;
    }
  }

  protected CloneDatastageSubflowsOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.dataIntgSubflowId,
      "dataIntgSubflowId cannot be empty");
    dataIntgSubflowId = builder.dataIntgSubflowId;
    catalogId = builder.catalogId;
    projectId = builder.projectId;
  }

  /**
   * New builder.
   *
   * @return a CloneDatastageSubflowsOptions builder
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

