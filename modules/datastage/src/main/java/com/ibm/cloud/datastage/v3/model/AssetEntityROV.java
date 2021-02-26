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

import java.util.List;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The rules of visibility for an asset.
 */
public class AssetEntityROV extends GenericModel {

  protected Long mode;
  protected List<String> members;

  /**
   * Gets the mode.
   *
   * The values for mode are 0 (public, searchable and viewable by all), 8 (private, searchable by all, but not viewable
   * unless view permission given) or 16 (hidden, only searchable by users with view permissions).
   *
   * @return the mode
   */
  public Long getMode() {
    return mode;
  }

  /**
   * Gets the members.
   *
   * An array of members belonging to AssetEntityROV.
   *
   * @return the members
   */
  public List<String> getMembers() {
    return members;
  }
}

