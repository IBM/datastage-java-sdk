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

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Import statistics. total = imported (including renamed and replaced) + skipped + failed + deprecated + unsupported +
 * pending.
 */
public class ImportCount extends GenericModel {

  @SerializedName("connections_total")
  protected Long connectionsTotal;
  protected Long deprecated;
  protected Long failed;
  protected Long imported;
  @SerializedName("parameter_sets_total")
  protected Long parameterSetsTotal;
  protected Long pending;
  protected Long renamed;
  protected Long replaced;
  @SerializedName("sequence_jobs_total")
  protected Long sequenceJobsTotal;
  protected Long skipped;
  @SerializedName("subflows_total")
  protected Long subflowsTotal;
  @SerializedName("table_definitions_total")
  protected Long tableDefinitionsTotal;
  protected Long total;
  protected Long unsupported;

  /**
   * Gets the connectionsTotal.
   *
   * Total number of data connections.
   *
   * @return the connectionsTotal
   */
  public Long getConnectionsTotal() {
    return connectionsTotal;
  }

  /**
   * Gets the deprecated.
   *
   * Total number of deprecated resources in the import file.
   *
   * @return the deprecated
   */
  public Long getDeprecated() {
    return deprecated;
  }

  /**
   * Gets the failed.
   *
   * Total number of data flows that cannot be imported due to import errors.
   *
   * @return the failed
   */
  public Long getFailed() {
    return failed;
  }

  /**
   * Gets the imported.
   *
   * Total number of data flows successfully imported.
   *
   * @return the imported
   */
  public Long getImported() {
    return imported;
  }

  /**
   * Gets the parameterSetsTotal.
   *
   * Total number of parameter sets.
   *
   * @return the parameterSetsTotal
   */
  public Long getParameterSetsTotal() {
    return parameterSetsTotal;
  }

  /**
   * Gets the pending.
   *
   * Total number of data flows that have not been processed.
   *
   * @return the pending
   */
  public Long getPending() {
    return pending;
  }

  /**
   * Gets the renamed.
   *
   * Total number of data flows successfully imported and renamed due to a name conflict. The renamed count is included
   * in the imported count.
   *
   * @return the renamed
   */
  public Long getRenamed() {
    return renamed;
  }

  /**
   * Gets the replaced.
   *
   * Total number of existing data flows replaced by imported flows. The replaced count is included in the imported
   * count.
   *
   * @return the replaced
   */
  public Long getReplaced() {
    return replaced;
  }

  /**
   * Gets the sequenceJobsTotal.
   *
   * Total number of sequence jobs.
   *
   * @return the sequenceJobsTotal
   */
  public Long getSequenceJobsTotal() {
    return sequenceJobsTotal;
  }

  /**
   * Gets the skipped.
   *
   * Total number of data flows skipped due to name conflicts. The skipped count is not included in the failed count or
   * imported count.
   *
   * @return the skipped
   */
  public Long getSkipped() {
    return skipped;
  }

  /**
   * Gets the subflowsTotal.
   *
   * Total number of parallel job subflows.
   *
   * @return the subflowsTotal
   */
  public Long getSubflowsTotal() {
    return subflowsTotal;
  }

  /**
   * Gets the tableDefinitionsTotal.
   *
   * Total number of table definitions.
   *
   * @return the tableDefinitionsTotal
   */
  public Long getTableDefinitionsTotal() {
    return tableDefinitionsTotal;
  }

  /**
   * Gets the total.
   *
   * Total number of data flows to be imported.
   *
   * @return the total
   */
  public Long getTotal() {
    return total;
  }

  /**
   * Gets the unsupported.
   *
   * Total number of unsupported resources in the import file.
   *
   * @return the unsupported
   */
  public Long getUnsupported() {
    return unsupported;
  }
}

