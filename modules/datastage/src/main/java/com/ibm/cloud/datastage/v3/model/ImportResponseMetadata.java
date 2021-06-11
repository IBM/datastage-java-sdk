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

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Import the response metadata.
 */
public class ImportResponseMetadata extends GenericModel {

  @SerializedName("catalog_id")
  protected String catalogId;
  @SerializedName("created_at")
  protected Date createdAt;
  @SerializedName("created_by")
  protected String createdBy;
  protected String id;
  @SerializedName("modified_at")
  protected Date modifiedAt;
  protected String name;
  @SerializedName("project_id")
  protected String projectId;
  @SerializedName("project_name")
  protected String projectName;
  protected String url;

  /**
   * Gets the catalogId.
   *
   * Catalog id.
   *
   * @return the catalogId
   */
  public String getCatalogId() {
    return catalogId;
  }

  /**
   * Gets the createdAt.
   *
   * The timestamp when the import API was submitted. In format YYYY-MM-DDTHH:mm:ssZ or YYYY-MM-DDTHH:mm:ss.sssZ,
   * matching the date-time format as specified by RFC 3339.
   *
   * @return the createdAt
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * Gets the createdBy.
   *
   * Account ID of the user who submitted the import request.
   *
   * @return the createdBy
   */
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * Gets the id.
   *
   * The unique import id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the modifiedAt.
   *
   * The timestamp when the import status was last updated. In format YYYY-MM-DDTHH:mm:ssZ or YYYY-MM-DDTHH:mm:ss.sssZ,
   * matching the date-time format as specified by RFC 3339.
   *
   * @return the modifiedAt
   */
  public Date getModifiedAt() {
    return modifiedAt;
  }

  /**
   * Gets the name.
   *
   * import file name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the projectId.
   *
   * Project id.
   *
   * @return the projectId
   */
  public String getProjectId() {
    return projectId;
  }

  /**
   * Gets the projectName.
   *
   * Project name.
   *
   * @return the projectName
   */
  public String getProjectName() {
    return projectName;
  }

  /**
   * Gets the url.
   *
   * The URL which can be used to get the status of the import request right after it is submitted.
   *
   * @return the url
   */
  public String getUrl() {
    return url;
  }
}

