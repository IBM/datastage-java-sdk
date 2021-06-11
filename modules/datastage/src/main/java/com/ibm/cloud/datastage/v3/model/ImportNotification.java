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
 * Import event notification.
 */
public class ImportNotification extends GenericModel {

  @SerializedName("created_at")
  protected Date createdAt;
  protected String id;
  protected String status;

  /**
   * Gets the createdAt.
   *
   * The timestamp when the import notification was created. In format YYYY-MM-DDTHH:mm:ssZ or YYYY-MM-DDTHH:mm:ss.sssZ,
   * matching the date-time format as specified by RFC 3339.
   *
   * @return the createdAt
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * Gets the id.
   *
   * Notification id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the status.
   *
   * Import status associated with the notification.
   *
   * @return the status
   */
  public String getStatus() {
    return status;
  }
}

