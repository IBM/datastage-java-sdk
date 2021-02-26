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
 * The datastageFlowsList options.
 */
public class DatastageFlowsListOptions extends GenericModel {

  protected String catalogId;
  protected String projectId;
  protected String sort;
  protected String start;
  protected Long limit;
  protected String entityName;
  protected String entityDescription;

  /**
   * Builder.
   */
  public static class Builder {
    private String catalogId;
    private String projectId;
    private String sort;
    private String start;
    private Long limit;
    private String entityName;
    private String entityDescription;

    private Builder(DatastageFlowsListOptions datastageFlowsListOptions) {
      this.catalogId = datastageFlowsListOptions.catalogId;
      this.projectId = datastageFlowsListOptions.projectId;
      this.sort = datastageFlowsListOptions.sort;
      this.start = datastageFlowsListOptions.start;
      this.limit = datastageFlowsListOptions.limit;
      this.entityName = datastageFlowsListOptions.entityName;
      this.entityDescription = datastageFlowsListOptions.entityDescription;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a DatastageFlowsListOptions.
     *
     * @return the new DatastageFlowsListOptions instance
     */
    public DatastageFlowsListOptions build() {
      return new DatastageFlowsListOptions(this);
    }

    /**
     * Set the catalogId.
     *
     * @param catalogId the catalogId
     * @return the DatastageFlowsListOptions builder
     */
    public Builder catalogId(String catalogId) {
      this.catalogId = catalogId;
      return this;
    }

    /**
     * Set the projectId.
     *
     * @param projectId the projectId
     * @return the DatastageFlowsListOptions builder
     */
    public Builder projectId(String projectId) {
      this.projectId = projectId;
      return this;
    }

    /**
     * Set the sort.
     *
     * @param sort the sort
     * @return the DatastageFlowsListOptions builder
     */
    public Builder sort(String sort) {
      this.sort = sort;
      return this;
    }

    /**
     * Set the start.
     *
     * @param start the start
     * @return the DatastageFlowsListOptions builder
     */
    public Builder start(String start) {
      this.start = start;
      return this;
    }

    /**
     * Set the limit.
     *
     * @param limit the limit
     * @return the DatastageFlowsListOptions builder
     */
    public Builder limit(long limit) {
      this.limit = limit;
      return this;
    }

    /**
     * Set the entityName.
     *
     * @param entityName the entityName
     * @return the DatastageFlowsListOptions builder
     */
    public Builder entityName(String entityName) {
      this.entityName = entityName;
      return this;
    }

    /**
     * Set the entityDescription.
     *
     * @param entityDescription the entityDescription
     * @return the DatastageFlowsListOptions builder
     */
    public Builder entityDescription(String entityDescription) {
      this.entityDescription = entityDescription;
      return this;
    }
  }

  protected DatastageFlowsListOptions(Builder builder) {
    catalogId = builder.catalogId;
    projectId = builder.projectId;
    sort = builder.sort;
    start = builder.start;
    limit = builder.limit;
    entityName = builder.entityName;
    entityDescription = builder.entityDescription;
  }

  /**
   * New builder.
   *
   * @return a DatastageFlowsListOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
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
   * Gets the sort.
   *
   * The field to sort the results on, including whether to sort ascending (+) or descending (-), for example,
   * sort=-metadata.create_time.
   *
   * @return the sort
   */
  public String sort() {
    return sort;
  }

  /**
   * Gets the start.
   *
   * The page token indicating where to start paging from.
   *
   * @return the start
   */
  public String start() {
    return start;
  }

  /**
   * Gets the limit.
   *
   * The limit of the number of items to return, for example limit=50. If not specified a default of 100 will be  used.
   *
   * @return the limit
   */
  public Long limit() {
    return limit;
  }

  /**
   * Gets the entityName.
   *
   * Filter results based on the specified name.
   *
   * @return the entityName
   */
  public String entityName() {
    return entityName;
  }

  /**
   * Gets the entityDescription.
   *
   * Filter results based on the specified description.
   *
   * @return the entityDescription
   */
  public String entityDescription() {
    return entityDescription;
  }
}

