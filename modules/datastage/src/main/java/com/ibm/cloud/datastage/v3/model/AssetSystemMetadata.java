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
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * System metadata about an asset.
 */
public class AssetSystemMetadata extends GenericModel {

  @SerializedName("asset_id")
  protected String assetId;
  @SerializedName("asset_type")
  protected String assetType;
  @SerializedName("catalog_id")
  protected String catalogId;
  @SerializedName("create_time")
  protected Date createTime;
  @SerializedName("creator_id")
  protected String creatorId;
  protected String href;
  protected String name;
  @SerializedName("origin_country")
  protected String originCountry;
  protected Long size;
  @SerializedName("project_id")
  protected String projectId;
  @SerializedName("resource_key")
  protected String resourceKey;
  protected String description;
  protected List<String> tags;
  @SerializedName("source_system")
  protected Map<String, Object> sourceSystem;
  protected AssetSystemMetadataUsage usage;

  /**
   * Gets the assetId.
   *
   * The ID of the asset.
   *
   * @return the assetId
   */
  public String getAssetId() {
    return assetId;
  }

  /**
   * Gets the assetType.
   *
   * The type of the asset.
   *
   * @return the assetType
   */
  public String getAssetType() {
    return assetType;
  }

  /**
   * Gets the catalogId.
   *
   * The ID of the catalog which contains the asset. catalog_id or project_id is required.
   *
   * @return the catalogId
   */
  public String getCatalogId() {
    return catalogId;
  }

  /**
   * Gets the createTime.
   *
   * The timestamp when the asset was created (in format YYYY-MM-DDTHH:mm:ssZ or YYYY-MM-DDTHH:mm:ss.sssZ, matching the
   * date-time format as specified by RFC 3339).
   *
   * @return the createTime
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * Gets the creatorId.
   *
   * The IAM ID of the user that created the asset.
   *
   * @return the creatorId
   */
  public String getCreatorId() {
    return creatorId;
  }

  /**
   * Gets the href.
   *
   * URL that can be used to get the asset.
   *
   * @return the href
   */
  public String getHref() {
    return href;
  }

  /**
   * Gets the name.
   *
   * name of the asset.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the originCountry.
   *
   * origin of the asset.
   *
   * @return the originCountry
   */
  public String getOriginCountry() {
    return originCountry;
  }

  /**
   * Gets the size.
   *
   * size of the asset.
   *
   * @return the size
   */
  public Long getSize() {
    return size;
  }

  /**
   * Gets the projectId.
   *
   * The ID of the project which contains the asset. catalog_id or project_id is required.
   *
   * @return the projectId
   */
  public String getProjectId() {
    return projectId;
  }

  /**
   * Gets the resourceKey.
   *
   * This is a unique string that uniquely identifies an asset.
   *
   * @return the resourceKey
   */
  public String getResourceKey() {
    return resourceKey;
  }

  /**
   * Gets the description.
   *
   * The description of the asset.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the tags.
   *
   * A list of tags that can be used to identify different types of data flow.
   *
   * @return the tags
   */
  public List<String> getTags() {
    return tags;
  }

  /**
   * Gets the sourceSystem.
   *
   * Custom data to be associated with a given object.
   *
   * @return the sourceSystem
   */
  public Map<String, Object> getSourceSystem() {
    return sourceSystem;
  }

  /**
   * Gets the usage.
   *
   * Metadata usage information about an asset.
   *
   * @return the usage
   */
  public AssetSystemMetadataUsage getUsage() {
    return usage;
  }
}

