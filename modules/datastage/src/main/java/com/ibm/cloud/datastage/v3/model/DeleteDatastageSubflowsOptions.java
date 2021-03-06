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

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The deleteDatastageSubflows options.
 */
public class DeleteDatastageSubflowsOptions extends GenericModel {

  protected List<String> id;
  protected String catalogId;
  protected String projectId;

  /**
   * Builder.
   */
  public static class Builder {
    private List<String> id;
    private String catalogId;
    private String projectId;

    private Builder(DeleteDatastageSubflowsOptions deleteDatastageSubflowsOptions) {
      this.id = deleteDatastageSubflowsOptions.id;
      this.catalogId = deleteDatastageSubflowsOptions.catalogId;
      this.projectId = deleteDatastageSubflowsOptions.projectId;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param id the id
     */
    public Builder(List<String> id) {
      this.id = id;
    }

    /**
     * Builds a DeleteDatastageSubflowsOptions.
     *
     * @return the new DeleteDatastageSubflowsOptions instance
     */
    public DeleteDatastageSubflowsOptions build() {
      return new DeleteDatastageSubflowsOptions(this);
    }

    /**
     * Adds an id to id.
     *
     * @param id the new id
     * @return the DeleteDatastageSubflowsOptions builder
     */
    public Builder addId(String id) {
      com.ibm.cloud.sdk.core.util.Validator.notNull(id,
        "id cannot be null");
      if (this.id == null) {
        this.id = new ArrayList<String>();
      }
      this.id.add(id);
      return this;
    }

    /**
     * Set the id.
     * Existing id will be replaced.
     *
     * @param id the id
     * @return the DeleteDatastageSubflowsOptions builder
     */
    public Builder id(List<String> id) {
      this.id = id;
      return this;
    }

    /**
     * Set the catalogId.
     *
     * @param catalogId the catalogId
     * @return the DeleteDatastageSubflowsOptions builder
     */
    public Builder catalogId(String catalogId) {
      this.catalogId = catalogId;
      return this;
    }

    /**
     * Set the projectId.
     *
     * @param projectId the projectId
     * @return the DeleteDatastageSubflowsOptions builder
     */
    public Builder projectId(String projectId) {
      this.projectId = projectId;
      return this;
    }
  }

  protected DeleteDatastageSubflowsOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.id,
      "id cannot be null");
    id = builder.id;
    catalogId = builder.catalogId;
    projectId = builder.projectId;
  }

  /**
   * New builder.
   *
   * @return a DeleteDatastageSubflowsOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the id.
   *
   * The list of DataStage subflow IDs to delete.
   *
   * @return the id
   */
  public List<String> id() {
    return id;
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

