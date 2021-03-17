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
 * A DataStage flow model that defines physical source(s), physical target(s) and an optional pipeline containing
 * operations to apply to source(s).
 */
public class DataIntgFlow extends GenericModel {

  protected List<Object> attachments;
  protected DataIntgFlowEntity entity;
  protected AssetSystemMetadata metadata;

  /**
   * Gets the attachments.
   *
   * Metadata information for datastage flow.
   *
   * @return the attachments
   */
  public List<Object> getAttachments() {
    return attachments;
  }

  /**
   * Gets the entity.
   *
   * The underlying DataStage flow definition.
   *
   * @return the entity
   */
  public DataIntgFlowEntity getEntity() {
    return entity;
  }

  /**
   * Gets the metadata.
   *
   * System metadata about an asset.
   *
   * @return the metadata
   */
  public AssetSystemMetadata getMetadata() {
    return metadata;
  }
}

