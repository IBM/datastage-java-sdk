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

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The createResource options.
 */
public class CreateResourceOptions extends GenericModel {

  protected String resourceId;
  protected String name;
  protected String tag;

  /**
   * Builder.
   */
  public static class Builder {
    private String resourceId;
    private String name;
    private String tag;

    private Builder(CreateResourceOptions createResourceOptions) {
      this.resourceId = createResourceOptions.resourceId;
      this.name = createResourceOptions.name;
      this.tag = createResourceOptions.tag;
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
     * Builds a CreateResourceOptions.
     *
     * @return the new CreateResourceOptions instance
     */
    public CreateResourceOptions build() {
      return new CreateResourceOptions(this);
    }

    /**
     * Set the resourceId.
     *
     * @param resourceId the resourceId
     * @return the CreateResourceOptions builder
     */
    public Builder resourceId(String resourceId) {
      this.resourceId = resourceId;
      return this;
    }

    /**
     * Set the name.
     *
     * @param name the name
     * @return the CreateResourceOptions builder
     */
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the tag.
     *
     * @param tag the tag
     * @return the CreateResourceOptions builder
     */
    public Builder tag(String tag) {
      this.tag = tag;
      return this;
    }

    /**
     * Set the resource.
     *
     * @param resource the resource
     * @return the CreateResourceOptions builder
     */
    public Builder resource(Resource resource) {
      this.resourceId = resource.resourceId();
      this.name = resource.name();
      this.tag = resource.tag();
      return this;
    }
  }

  protected CreateResourceOptions(Builder builder) {
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
   * @return a CreateResourceOptions builder
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
}

