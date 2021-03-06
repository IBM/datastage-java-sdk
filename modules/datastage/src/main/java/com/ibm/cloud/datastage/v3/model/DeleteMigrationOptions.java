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
 * The deleteMigration options.
 */
public class DeleteMigrationOptions extends GenericModel {

  protected String importId;
  protected String catalogId;
  protected String projectId;

  /**
   * Builder.
   */
  public static class Builder {
    private String importId;
    private String catalogId;
    private String projectId;

    private Builder(DeleteMigrationOptions deleteMigrationOptions) {
      this.importId = deleteMigrationOptions.importId;
      this.catalogId = deleteMigrationOptions.catalogId;
      this.projectId = deleteMigrationOptions.projectId;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param importId the importId
     */
    public Builder(String importId) {
      this.importId = importId;
    }

    /**
     * Builds a DeleteMigrationOptions.
     *
     * @return the new DeleteMigrationOptions instance
     */
    public DeleteMigrationOptions build() {
      return new DeleteMigrationOptions(this);
    }

    /**
     * Set the importId.
     *
     * @param importId the importId
     * @return the DeleteMigrationOptions builder
     */
    public Builder importId(String importId) {
      this.importId = importId;
      return this;
    }

    /**
     * Set the catalogId.
     *
     * @param catalogId the catalogId
     * @return the DeleteMigrationOptions builder
     */
    public Builder catalogId(String catalogId) {
      this.catalogId = catalogId;
      return this;
    }

    /**
     * Set the projectId.
     *
     * @param projectId the projectId
     * @return the DeleteMigrationOptions builder
     */
    public Builder projectId(String projectId) {
      this.projectId = projectId;
      return this;
    }
  }

  protected DeleteMigrationOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.importId,
      "importId cannot be empty");
    importId = builder.importId;
    catalogId = builder.catalogId;
    projectId = builder.projectId;
  }

  /**
   * New builder.
   *
   * @return a DeleteMigrationOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the importId.
   *
   * Unique ID of the import request.
   *
   * @return the importId
   */
  public String importId() {
    return importId;
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

