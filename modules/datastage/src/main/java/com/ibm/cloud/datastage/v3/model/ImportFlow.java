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

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Import flow object.
 */
public class ImportFlow extends GenericModel {

  /**
   * type of the job or data connection in the import file.
   */
  public interface Type {
    /** px_job. */
    String PX_JOB = "px_job";
    /** server_job. */
    String SERVER_JOB = "server_job";
    /** connection. */
    String CONNECTION = "connection";
    /** table_def. */
    String TABLE_DEF = "table_def";
    /** parameter_set. */
    String PARAMETER_SET = "parameter_set";
  }

  /**
   * (deprecated) original type of the job or data flow in the import file.
   */
  public interface JobType {
    /** px_job. */
    String PX_JOB = "px_job";
    /** server_job. */
    String SERVER_JOB = "server_job";
    /** connection. */
    String CONNECTION = "connection";
    /** table_def. */
    String TABLE_DEF = "table_def";
  }

  /**
   * data import status.
   */
  public interface Status {
    /** completed. */
    String COMPLETED = "completed";
    /** in_progress. */
    String IN_PROGRESS = "in_progress";
    /** failed. */
    String FAILED = "failed";
    /** skipped. */
    String SKIPPED = "skipped";
    /** deprecated. */
    String DEPRECATED = "deprecated";
    /** unsupported. */
    String UNSUPPORTED = "unsupported";
    /** flow_conversion_failed. */
    String FLOW_CONVERSION_FAILED = "flow_conversion_failed";
    /** flow_creation_failed. */
    String FLOW_CREATION_FAILED = "flow_creation_failed";
    /** flow_compilation_failed. */
    String FLOW_COMPILATION_FAILED = "flow_compilation_failed";
    /** job_creation_failed. */
    String JOB_CREATION_FAILED = "job_creation_failed";
    /** job_run_failed. */
    String JOB_RUN_FAILED = "job_run_failed";
  }

  /**
   * conflict resolution status.
   */
  public interface ConflictResolutionStatus {
    /** flow_replacement_succeeded. */
    String FLOW_REPLACEMENT_SUCCEEDED = "flow_replacement_succeeded";
    /** flow_replacement_failed. */
    String FLOW_REPLACEMENT_FAILED = "flow_replacement_failed";
    /** import_flow_renamed. */
    String IMPORT_FLOW_RENAMED = "import_flow_renamed";
    /** import_flow_skipped. */
    String IMPORT_FLOW_SKIPPED = "import_flow_skipped";
  }

  protected String id;
  @SerializedName("ref_asset_id")
  protected String refAssetId;
  protected String name;
  @SerializedName("original_name")
  protected String originalName;
  protected String type;
  @SerializedName("job_type")
  protected String jobType;
  @SerializedName("job_id")
  protected String jobId;
  @SerializedName("job_name")
  protected String jobName;
  protected String status;
  @SerializedName("conflict_resolution_status")
  protected String conflictResolutionStatus;
  @SerializedName("end_time")
  protected Date endTime;
  protected List<DataImportError> errors;
  protected List<ImportFlowWarning> warnings;

  /**
   * Gets the id.
   *
   * Unique id of the data flow. This field is returned only if the underlying data flow has been successfully imported.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the refAssetId.
   *
   * The ID of an existing asset this object refers to. If ref_asset_id is specified, the id field will be the same as
   * ref_asset_id for backward compatibility.
   *
   * @return the refAssetId
   */
  public String getRefAssetId() {
    return refAssetId;
  }

  /**
   * Gets the name.
   *
   * Name of the imported data flow.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the originalName.
   *
   * Name of the data flow to be imported.
   *
   * @return the originalName
   */
  public String getOriginalName() {
    return originalName;
  }

  /**
   * Gets the type.
   *
   * type of the job or data connection in the import file.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Gets the jobType.
   *
   * (deprecated) original type of the job or data flow in the import file.
   *
   * @return the jobType
   */
  public String getJobType() {
    return jobType;
  }

  /**
   * Gets the jobId.
   *
   * Unique id of the job. This field is returned only if the corresponding job object has been successfully created.
   *
   * @return the jobId
   */
  public String getJobId() {
    return jobId;
  }

  /**
   * Gets the jobName.
   *
   * Job name. This field is returned only if the corresponding job object has been successfully created.
   *
   * @return the jobName
   */
  public String getJobName() {
    return jobName;
  }

  /**
   * Gets the status.
   *
   * data import status.
   *
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * Gets the conflictResolutionStatus.
   *
   * conflict resolution status.
   *
   * @return the conflictResolutionStatus
   */
  public String getConflictResolutionStatus() {
    return conflictResolutionStatus;
  }

  /**
   * Gets the endTime.
   *
   * The timestamp when the flow import is completed. In format YYYY-MM-DDTHH:mm:ssZ or YYYY-MM-DDTHH:mm:ss.sssZ,
   * matching the date-time format as specified by RFC 3339.
   *
   * @return the endTime
   */
  public Date getEndTime() {
    return endTime;
  }

  /**
   * Gets the errors.
   *
   * The errors array report all the problems preventing the data flow from being successfully imported.
   *
   * @return the errors
   */
  public List<DataImportError> getErrors() {
    return errors;
  }

  /**
   * Gets the warnings.
   *
   * The warnings array report all the warnings in the data flow import operation.
   *
   * @return the warnings
   */
  public List<ImportFlowWarning> getWarnings() {
    return warnings;
  }
}

