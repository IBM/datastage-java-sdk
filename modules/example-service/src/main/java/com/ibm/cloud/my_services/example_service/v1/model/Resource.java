/*
 * (C) Copyright IBM Corp. 2020.
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
package com.ibm.cloud.my_services.example_service.v1.model;

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * A resource.
 */
public class Resource extends GenericModel {

  @SerializedName("resource_id")
  protected String resourceId;
  protected String name;
  protected String tag;
  @SerializedName("read_only")
  protected String readOnly;

  /**
   * Builder.
   */
  public static class Builder {
    private String resourceId;
    private String name;
    private String tag;

    private Builder(Resource resource) {
      this.resourceId = resource.resourceId;
      this.name = resource.name;
      this.tag = resource.tag;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param resourceId the resourceId
     * @param name the name
     */
    public Builder(String resourceId, String name) {
      this.resourceId = resourceId;
      this.name = name;
    }

    /**
     * Builds a Resource.
     *
     * @return the new Resource instance
     */
    public Resource build() {
      return new Resource(this);
    }

    /**
     * Set the resourceId.
     *
     * @param resourceId the resourceId
     * @return the Resource builder
     */
    public Builder resourceId(String resourceId) {
      this.resourceId = resourceId;
      return this;
    }

    /**
     * Set the name.
     *
     * @param name the name
     * @return the Resource builder
     */
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the tag.
     *
     * @param tag the tag
     * @return the Resource builder
     */
    public Builder tag(String tag) {
      this.tag = tag;
      return this;
    }
  }

  protected Resource(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.resourceId,
      "resourceId cannot be null");
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.name,
      "name cannot be null");
    resourceId = builder.resourceId;
    name = builder.name;
    tag = builder.tag;
  }

  /**
   * New builder.
   *
   * @return a Resource builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the resourceId.
   *
   * The id of the resource.
   *
   * @return the resourceId
   */
  public String resourceId() {
    return resourceId;
  }

  /**
   * Gets the name.
   *
   * The name of the resource.
   *
   * @return the name
   */
  public String name() {
    return name;
  }

  /**
   * Gets the tag.
   *
   * A tag value for the resource.
   *
   * @return the tag
   */
  public String tag() {
    return tag;
  }

  /**
   * Gets the readOnly.
   *
   * This is a read only string.
   *
   * @return the readOnly
   */
  public String readOnly() {
    return readOnly;
  }
}

