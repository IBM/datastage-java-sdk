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
 * An import warning object describe a warning message specific to a particular data flow.
 */
public class ImportFlowWarning extends GenericModel {

  /**
   * warning type.
   */
  public interface Type {
    /** unreleased_stage_type. */
    String UNRELEASED_STAGE_TYPE = "unreleased_stage_type";
    /** unreleased_feature. */
    String UNRELEASED_FEATURE = "unreleased_feature";
    /** credentials_file_warning. */
    String CREDENTIALS_FILE_WARNING = "credentials_file_warning";
    /** transformer_trigger_unsupported. */
    String TRANSFORMER_TRIGGER_UNSUPPORTED = "transformer_trigger_unsupported";
    /** transformer_buildtab_unsupported. */
    String TRANSFORMER_BUILDTAB_UNSUPPORTED = "transformer_buildtab_unsupported";
    /** unsupported_secure_gateway. */
    String UNSUPPORTED_SECURE_GATEWAY = "unsupported_secure_gateway";
    /** placeholder_connection_parameters. */
    String PLACEHOLDER_CONNECTION_PARAMETERS = "placeholder_connection_parameters";
    /** description_truncated. */
    String DESCRIPTION_TRUNCATED = "description_truncated";
    /** empty_stage_list. */
    String EMPTY_STAGE_LIST = "empty_stage_list";
    /** missing_parameter_set. */
    String MISSING_PARAMETER_SET = "missing_parameter_set";
  }

  protected String description;
  protected String name;
  protected String type;

  /**
   * Gets the description.
   *
   * additional warning text.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the name.
   *
   * warning object name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the type.
   *
   * warning type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }
}

