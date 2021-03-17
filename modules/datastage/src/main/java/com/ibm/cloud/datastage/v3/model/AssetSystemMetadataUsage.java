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
 * Metadata usage information about an asset.
 */
public class AssetSystemMetadataUsage extends GenericModel {

  @SerializedName("access_count")
  protected Long accessCount;
  @SerializedName("last_access_time")
  protected Date lastAccessTime;
  @SerializedName("last_accessor_id")
  protected String lastAccessorId;
  @SerializedName("last_modification_time")
  protected Date lastModificationTime;
  @SerializedName("last_modifier_id")
  protected String lastModifierId;

  /**
   * Gets the accessCount.
   *
   * Number of times this asset has been accessed.
   *
   * @return the accessCount
   */
  public Long getAccessCount() {
    return accessCount;
  }

  /**
   * Gets the lastAccessTime.
   *
   * The timestamp when the asset was last accessed (in format YYYY-MM-DDTHH:mm:ssZ or YYYY-MM-DDTHH:mm:ss.sssZ,
   * matching the date-time format as specified by RFC 3339).
   *
   * @return the lastAccessTime
   */
  public Date getLastAccessTime() {
    return lastAccessTime;
  }

  /**
   * Gets the lastAccessorId.
   *
   * The IAM ID of the user that last accessed the asset.
   *
   * @return the lastAccessorId
   */
  public String getLastAccessorId() {
    return lastAccessorId;
  }

  /**
   * Gets the lastModificationTime.
   *
   * The timestamp when the asset was last modified (in format YYYY-MM-DDTHH:mm:ssZ or YYYY-MM-DDTHH:mm:ss.sssZ,
   * matching the date-time format as specified by RFC 3339).
   *
   * @return the lastModificationTime
   */
  public Date getLastModificationTime() {
    return lastModificationTime;
  }

  /**
   * Gets the lastModifierId.
   *
   * The IAM ID of the user that last modified the asset.
   *
   * @return the lastModifierId
   */
  public String getLastModifierId() {
    return lastModifierId;
  }
}

