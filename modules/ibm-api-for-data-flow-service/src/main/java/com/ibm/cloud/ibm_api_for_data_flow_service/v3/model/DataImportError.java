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

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * An import error object describe an import problem specific to a particular data flow.
 */
public class DataImportError extends GenericModel {

  /**
   * error type.
   */
  public interface Type {
    /** unsupported_stage_type. */
    String UNSUPPORTED_STAGE_TYPE = "unsupported_stage_type";
    /** unsupported_feature. */
    String UNSUPPORTED_FEATURE = "unsupported_feature";
    /** empty_json. */
    String EMPTY_JSON = "empty_json";
    /** isx_conversion_error. */
    String ISX_CONVERSION_ERROR = "isx_conversion_error";
    /** model_conversion_error. */
    String MODEL_CONVERSION_ERROR = "model_conversion_error";
    /** invalid_input_type. */
    String INVALID_INPUT_TYPE = "invalid_input_type";
    /** invalid_json_format. */
    String INVALID_JSON_FORMAT = "invalid_json_format";
    /** json_conversion_error. */
    String JSON_CONVERSION_ERROR = "json_conversion_error";
    /** flow_deletion_error. */
    String FLOW_DELETION_ERROR = "flow_deletion_error";
    /** flow_creation_error. */
    String FLOW_CREATION_ERROR = "flow_creation_error";
    /** flow_response_parsing_error. */
    String FLOW_RESPONSE_PARSING_ERROR = "flow_response_parsing_error";
    /** auth_token_error. */
    String AUTH_TOKEN_ERROR = "auth_token_error";
    /** flow_compilation_error. */
    String FLOW_COMPILATION_ERROR = "flow_compilation_error";
    /** empty_stage_list. */
    String EMPTY_STAGE_LIST = "empty_stage_list";
    /** empty_stage_node. */
    String EMPTY_STAGE_NODE = "empty_stage_node";
    /** missing_stage_type_class_name. */
    String MISSING_STAGE_TYPE_CLASS_NAME = "missing_stage_type_class_name";
    /** dummy_stage. */
    String DUMMY_STAGE = "dummy_stage";
    /** missing_stage_type. */
    String MISSING_STAGE_TYPE = "missing_stage_type";
    /** missing_repos_id. */
    String MISSING_REPOS_ID = "missing_repos_id";
    /** stage_conversion_error. */
    String STAGE_CONVERSION_ERROR = "stage_conversion_error";
    /** unimplemented_stage_type. */
    String UNIMPLEMENTED_STAGE_TYPE = "unimplemented_stage_type";
    /** job_creation_error. */
    String JOB_CREATION_ERROR = "job_creation_error";
    /** job_run_error. */
    String JOB_RUN_ERROR = "job_run_error";
    /** flow_search_error. */
    String FLOW_SEARCH_ERROR = "flow_search_error";
    /** unsupported_job_type. */
    String UNSUPPORTED_JOB_TYPE = "unsupported_job_type";
    /** internal_error. */
    String INTERNAL_ERROR = "internal_error";
    /** connection_creation_error. */
    String CONNECTION_CREATION_ERROR = "connection_creation_error";
    /** flow_rename_error. */
    String FLOW_RENAME_ERROR = "flow_rename_error";
    /** duplicate_job_error. */
    String DUPLICATE_JOB_ERROR = "duplicate_job_error";
  }

  protected String type;
  protected String name;
  protected String description;

  /**
   * Gets the type.
   *
   * error type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Gets the name.
   *
   * error object name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the description.
   *
   * additional error text.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }
}

