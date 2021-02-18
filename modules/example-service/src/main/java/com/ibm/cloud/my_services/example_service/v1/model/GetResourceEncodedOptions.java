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
 * The getResourceEncoded options.
 */
public class GetResourceEncodedOptions extends GenericModel {

  protected String urlEncodedResourceId;

  /**
   * Builder.
   */
  public static class Builder {
    private String urlEncodedResourceId;

    private Builder(GetResourceEncodedOptions getResourceEncodedOptions) {
      this.urlEncodedResourceId = getResourceEncodedOptions.urlEncodedResourceId;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param urlEncodedResourceId the urlEncodedResourceId
     */
    public Builder(String urlEncodedResourceId) {
      this.urlEncodedResourceId = urlEncodedResourceId;
    }

    /**
     * Builds a GetResourceEncodedOptions.
     *
     * @return the new GetResourceEncodedOptions instance
     */
    public GetResourceEncodedOptions build() {
      return new GetResourceEncodedOptions(this);
    }

    /**
     * Set the urlEncodedResourceId.
     *
     * @param urlEncodedResourceId the urlEncodedResourceId
     * @return the GetResourceEncodedOptions builder
     */
    public Builder urlEncodedResourceId(String urlEncodedResourceId) {
      this.urlEncodedResourceId = urlEncodedResourceId;
      return this;
    }
  }

  protected GetResourceEncodedOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.urlEncodedResourceId,
      "urlEncodedResourceId cannot be empty");
    urlEncodedResourceId = builder.urlEncodedResourceId;
  }

  /**
   * New builder.
   *
   * @return a GetResourceEncodedOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the urlEncodedResourceId.
   *
   * The id of the resource to retrieve.
   *
   * @return the urlEncodedResourceId
   */
  public String urlEncodedResourceId() {
    return urlEncodedResourceId;
  }
}

