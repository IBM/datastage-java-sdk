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
 * The getResource options.
 */
public class GetResourceOptions extends GenericModel {

  protected String resourceId;

  /**
   * Builder.
   */
  public static class Builder {
    private String resourceId;

    private Builder(GetResourceOptions getResourceOptions) {
      this.resourceId = getResourceOptions.resourceId;
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
     */
    public Builder(String resourceId) {
      this.resourceId = resourceId;
    }

    /**
     * Builds a GetResourceOptions.
     *
     * @return the new GetResourceOptions instance
     */
    public GetResourceOptions build() {
      return new GetResourceOptions(this);
    }

    /**
     * Set the resourceId.
     *
     * @param resourceId the resourceId
     * @return the GetResourceOptions builder
     */
    public Builder resourceId(String resourceId) {
      this.resourceId = resourceId;
      return this;
    }
  }

  protected GetResourceOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.resourceId,
      "resourceId cannot be empty");
    resourceId = builder.resourceId;
  }

  /**
   * New builder.
   *
   * @return a GetResourceOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the resourceId.
   *
   * The id of the resource to retrieve.
   *
   * @return the resourceId
   */
  public String resourceId() {
    return resourceId;
  }
}

