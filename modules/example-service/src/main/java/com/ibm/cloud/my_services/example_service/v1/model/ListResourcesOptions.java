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
 * The listResources options.
 */
public class ListResourcesOptions extends GenericModel {

  protected Long limit;

  /**
   * Builder.
   */
  public static class Builder {
    private Long limit;

    private Builder(ListResourcesOptions listResourcesOptions) {
      this.limit = listResourcesOptions.limit;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a ListResourcesOptions.
     *
     * @return the new ListResourcesOptions instance
     */
    public ListResourcesOptions build() {
      return new ListResourcesOptions(this);
    }

    /**
     * Set the limit.
     *
     * @param limit the limit
     * @return the ListResourcesOptions builder
     */
    public Builder limit(long limit) {
      this.limit = limit;
      return this;
    }
  }

  protected ListResourcesOptions(Builder builder) {
    limit = builder.limit;
  }

  /**
   * New builder.
   *
   * @return a ListResourcesOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the limit.
   *
   * How many items to return at one time (max 100).
   *
   * @return the limit
   */
  public Long limit() {
    return limit;
  }
}

