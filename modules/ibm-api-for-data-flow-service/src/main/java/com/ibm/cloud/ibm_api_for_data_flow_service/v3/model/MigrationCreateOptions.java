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
package com.ibm.cloud.ibm_api_for_data_flow_service.v3.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The migrationCreate options.
 */
public class MigrationCreateOptions extends GenericModel {

  /**
   * Action when the first import failure occurs. The default action is "continue" which will continue importing the
   * remaining data flows. The "stop" action will stop the import operation upon the first error.
   */
  public interface OnFailure {
    /** continue. */
    String X_CONTINUE = "continue";
    /** stop. */
    String STOP = "stop";
  }

  /**
   * Resolution when data flow to be imported has a name conflict with an existing data flow in the project or catalog.
   * The default conflict resolution is "skip" will skip  the data flow so that it will not be imported. The "rename"
   * resolution will append "_Import_NNNN" suffix to the original name and use the new name for the imported data flow,
   * while the "replace" resolution will first remove the existing data flow with the same name and  import the new data
   * flow. For the "rename_replace" option, when the flow name is already used, a new flow name with the suffix
   * "_DATASTAGE_ISX_IMPORT" will be used. If the name is not currently used, the imported flow will be created with
   * this name. In case the new name is already used, the existing flow will be removed  first before the imported flow
   * is created. With the rename_replace option, job creation will be determined  as follows. If the job name is already
   * used, a new job name with the suffix ".DataStage job" will be used. If the new job name is not currently used, the
   * job will be created with this name. In case the new job name is already used, the job creation will not happen and
   * an error will be raised.
   */
  public interface ConflictResolution {
    /** skip. */
    String SKIP = "skip";
    /** rename. */
    String RENAME = "rename";
    /** replace. */
    String REPLACE = "replace";
    /** rename_replace. */
    String RENAME_REPLACE = "rename_replace";
  }

  /**
   * Type of attachment. The default attachment type is "isx".
   */
  public interface AttachmentType {
    /** isx. */
    String ISX = "isx";
  }

  protected InputStream body;
  protected String catalogId;
  protected String projectId;
  protected String onFailure;
  protected String conflictResolution;
  protected String attachmentType;
  protected String fileName;

  /**
   * Builder.
   */
  public static class Builder {
    private InputStream body;
    private String catalogId;
    private String projectId;
    private String onFailure;
    private String conflictResolution;
    private String attachmentType;
    private String fileName;

    private Builder(MigrationCreateOptions migrationCreateOptions) {
      this.body = migrationCreateOptions.body;
      this.catalogId = migrationCreateOptions.catalogId;
      this.projectId = migrationCreateOptions.projectId;
      this.onFailure = migrationCreateOptions.onFailure;
      this.conflictResolution = migrationCreateOptions.conflictResolution;
      this.attachmentType = migrationCreateOptions.attachmentType;
      this.fileName = migrationCreateOptions.fileName;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param body the body
     */
    public Builder(InputStream body) {
      this.body = body;
    }

    /**
     * Builds a MigrationCreateOptions.
     *
     * @return the new MigrationCreateOptions instance
     */
    public MigrationCreateOptions build() {
      return new MigrationCreateOptions(this);
    }

    /**
     * Set the body.
     *
     * @param body the body
     * @return the MigrationCreateOptions builder
     */
    public Builder body(InputStream body) {
      this.body = body;
      return this;
    }

    /**
     * Set the catalogId.
     *
     * @param catalogId the catalogId
     * @return the MigrationCreateOptions builder
     */
    public Builder catalogId(String catalogId) {
      this.catalogId = catalogId;
      return this;
    }

    /**
     * Set the projectId.
     *
     * @param projectId the projectId
     * @return the MigrationCreateOptions builder
     */
    public Builder projectId(String projectId) {
      this.projectId = projectId;
      return this;
    }

    /**
     * Set the onFailure.
     *
     * @param onFailure the onFailure
     * @return the MigrationCreateOptions builder
     */
    public Builder onFailure(String onFailure) {
      this.onFailure = onFailure;
      return this;
    }

    /**
     * Set the conflictResolution.
     *
     * @param conflictResolution the conflictResolution
     * @return the MigrationCreateOptions builder
     */
    public Builder conflictResolution(String conflictResolution) {
      this.conflictResolution = conflictResolution;
      return this;
    }

    /**
     * Set the attachmentType.
     *
     * @param attachmentType the attachmentType
     * @return the MigrationCreateOptions builder
     */
    public Builder attachmentType(String attachmentType) {
      this.attachmentType = attachmentType;
      return this;
    }

    /**
     * Set the fileName.
     *
     * @param fileName the fileName
     * @return the MigrationCreateOptions builder
     */
    public Builder fileName(String fileName) {
      this.fileName = fileName;
      return this;
    }

    /**
     * Set the body.
     *
     * @param body the body
     * @return the MigrationCreateOptions builder
     *
     * @throws FileNotFoundException if the file could not be found
     */
    public Builder body(File body) throws FileNotFoundException {
      this.body = new FileInputStream(body);
      return this;
    }
  }

  protected MigrationCreateOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.body,
      "body cannot be null");
    body = builder.body;
    catalogId = builder.catalogId;
    projectId = builder.projectId;
    onFailure = builder.onFailure;
    conflictResolution = builder.conflictResolution;
    attachmentType = builder.attachmentType;
    fileName = builder.fileName;
  }

  /**
   * New builder.
   *
   * @return a MigrationCreateOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the body.
   *
   * @return the body
   */
  public InputStream body() {
    return body;
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
   * Gets the onFailure.
   *
   * Action when the first import failure occurs. The default action is "continue" which will continue importing the
   * remaining data flows. The "stop" action will stop the import operation upon the first error.
   *
   * @return the onFailure
   */
  public String onFailure() {
    return onFailure;
  }

  /**
   * Gets the conflictResolution.
   *
   * Resolution when data flow to be imported has a name conflict with an existing data flow in the project or catalog.
   * The default conflict resolution is "skip" will skip  the data flow so that it will not be imported. The "rename"
   * resolution will append "_Import_NNNN" suffix to the original name and use the new name for the imported data flow,
   * while the "replace" resolution will first remove the existing data flow with the same name and  import the new data
   * flow. For the "rename_replace" option, when the flow name is already used, a new flow name with the suffix
   * "_DATASTAGE_ISX_IMPORT" will be used. If the name is not currently used, the imported flow will be created with
   * this name. In case the new name is already used, the existing flow will be removed  first before the imported flow
   * is created. With the rename_replace option, job creation will be determined  as follows. If the job name is already
   * used, a new job name with the suffix ".DataStage job" will be used. If the new job name is not currently used, the
   * job will be created with this name. In case the new job name is already used, the job creation will not happen and
   * an error will be raised.
   *
   * @return the conflictResolution
   */
  public String conflictResolution() {
    return conflictResolution;
  }

  /**
   * Gets the attachmentType.
   *
   * Type of attachment. The default attachment type is "isx".
   *
   * @return the attachmentType
   */
  public String attachmentType() {
    return attachmentType;
  }

  /**
   * Gets the fileName.
   *
   * Name of the input file (if exists).
   *
   * @return the fileName
   */
  public String fileName() {
    return fileName;
  }
}

