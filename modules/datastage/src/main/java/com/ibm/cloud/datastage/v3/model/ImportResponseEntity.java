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
 * import response entity.
 */
public class ImportResponseEntity extends GenericModel {

  /**
   * import status.
   */
  public interface Status {
    /** in_progress. */
    String IN_PROGRESS = "in_progress";
    /** cancelled. */
    String CANCELLED = "cancelled";
    /** queued. */
    String QUEUED = "queued";
    /** started. */
    String STARTED = "started";
    /** completed. */
    String COMPLETED = "completed";
  }

  protected String name;
  protected String status;
  @SerializedName("start_time")
  protected Date startTime;
  @SerializedName("end_time")
  protected Date endTime;
  @SerializedName("remaining_time")
  protected Long remainingTime;
  @SerializedName("cancelled_by")
  protected String cancelledBy;
  @SerializedName("on_failure")
  protected String onFailure;
  @SerializedName("conflict_resolution")
  protected String conflictResolution;
  @SerializedName("import_data_flows")
  protected List<ImportFlow> importDataFlows;
  protected ImportCount tally;

  /**
   * Gets the name.
   *
   * Name of the import request.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the status.
   *
   * import status.
   *
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * Gets the startTime.
   *
   * The timestamp when the import opearton started. In format YYYY-MM-DDTHH:mm:ssZ or YYYY-MM-DDTHH:mm:ss.sssZ,
   * matching the date-time format as specified by RFC 3339.
   *
   * @return the startTime
   */
  public Date getStartTime() {
    return startTime;
  }

  /**
   * Gets the endTime.
   *
   * The timestamp when the import opearton completed. In format YYYY-MM-DDTHH:mm:ssZ or YYYY-MM-DDTHH:mm:ss.sssZ,
   * matching the date-time format as specified by RFC 3339.
   *
   * @return the endTime
   */
  public Date getEndTime() {
    return endTime;
  }

  /**
   * Gets the remainingTime.
   *
   * Estimate of remaining time in seconds.
   *
   * @return the remainingTime
   */
  public Long getRemainingTime() {
    return remainingTime;
  }

  /**
   * Gets the cancelledBy.
   *
   * Account ID of the user who cancelled the import request. This field is required only when the status  field is
   * "cancelled".
   *
   * @return the cancelledBy
   */
  public String getCancelledBy() {
    return cancelledBy;
  }

  /**
   * Gets the onFailure.
   *
   * The on_failure option used for the import.
   *
   * @return the onFailure
   */
  public String getOnFailure() {
    return onFailure;
  }

  /**
   * Gets the conflictResolution.
   *
   * The conflict_resolution option used for the import.
   *
   * @return the conflictResolution
   */
  public String getConflictResolution() {
    return conflictResolution;
  }

  /**
   * Gets the importDataFlows.
   *
   * All data flows imported or to be imported. Each ImportFlow object contains status for the individual data flow
   * import operation.
   *
   * @return the importDataFlows
   */
  public List<ImportFlow> getImportDataFlows() {
    return importDataFlows;
  }

  /**
   * Gets the tally.
   *
   * Import statistics. total = imported (including renamed and replaced) + skipped + failed + deprecated + unsupported
   * + pending.
   *
   * @return the tally
   */
  public ImportCount getTally() {
    return tally;
  }
}

