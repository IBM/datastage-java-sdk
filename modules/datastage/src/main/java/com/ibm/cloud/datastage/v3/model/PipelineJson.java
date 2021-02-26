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

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Pipeline flow to be stored.
 */
public class PipelineJson extends GenericModel {

  @SerializedName("doc_type")
  protected String docType;
  protected String version;
  @SerializedName("json_schema")
  protected String jsonSchema;
  protected String id;
  @SerializedName("primary_pipeline")
  protected String primaryPipeline;
  protected List<Pipelines> pipelines;
  protected Object schemas;
  protected Object runtimes;
  @SerializedName("app_data")
  protected Object appData;

  /**
   * Builder.
   */
  public static class Builder {
    private String docType;
    private String version;
    private String jsonSchema;
    private String id;
    private String primaryPipeline;
    private List<Pipelines> pipelines;
    private Object schemas;
    private Object runtimes;
    private Object appData;

    private Builder(PipelineJson pipelineJson) {
      this.docType = pipelineJson.docType;
      this.version = pipelineJson.version;
      this.jsonSchema = pipelineJson.jsonSchema;
      this.id = pipelineJson.id;
      this.primaryPipeline = pipelineJson.primaryPipeline;
      this.pipelines = pipelineJson.pipelines;
      this.schemas = pipelineJson.schemas;
      this.runtimes = pipelineJson.runtimes;
      this.appData = pipelineJson.appData;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a PipelineJson.
     *
     * @return the new PipelineJson instance
     */
    public PipelineJson build() {
      return new PipelineJson(this);
    }

    /**
     * Adds an pipelines to pipelines.
     *
     * @param pipelines the new pipelines
     * @return the PipelineJson builder
     */
    public Builder addPipelines(Pipelines pipelines) {
      com.ibm.cloud.sdk.core.util.Validator.notNull(pipelines,
        "pipelines cannot be null");
      if (this.pipelines == null) {
        this.pipelines = new ArrayList<Pipelines>();
      }
      this.pipelines.add(pipelines);
      return this;
    }

    /**
     * Set the docType.
     *
     * @param docType the docType
     * @return the PipelineJson builder
     */
    public Builder docType(String docType) {
      this.docType = docType;
      return this;
    }

    /**
     * Set the version.
     *
     * @param version the version
     * @return the PipelineJson builder
     */
    public Builder version(String version) {
      this.version = version;
      return this;
    }

    /**
     * Set the jsonSchema.
     *
     * @param jsonSchema the jsonSchema
     * @return the PipelineJson builder
     */
    public Builder jsonSchema(String jsonSchema) {
      this.jsonSchema = jsonSchema;
      return this;
    }

    /**
     * Set the id.
     *
     * @param id the id
     * @return the PipelineJson builder
     */
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the primaryPipeline.
     *
     * @param primaryPipeline the primaryPipeline
     * @return the PipelineJson builder
     */
    public Builder primaryPipeline(String primaryPipeline) {
      this.primaryPipeline = primaryPipeline;
      return this;
    }

    /**
     * Set the pipelines.
     * Existing pipelines will be replaced.
     *
     * @param pipelines the pipelines
     * @return the PipelineJson builder
     */
    public Builder pipelines(List<Pipelines> pipelines) {
      this.pipelines = pipelines;
      return this;
    }

    /**
     * Set the schemas.
     *
     * @param schemas the schemas
     * @return the PipelineJson builder
     */
    public Builder schemas(Object schemas) {
      this.schemas = schemas;
      return this;
    }

    /**
     * Set the runtimes.
     *
     * @param runtimes the runtimes
     * @return the PipelineJson builder
     */
    public Builder runtimes(Object runtimes) {
      this.runtimes = runtimes;
      return this;
    }

    /**
     * Set the appData.
     *
     * @param appData the appData
     * @return the PipelineJson builder
     */
    public Builder appData(Object appData) {
      this.appData = appData;
      return this;
    }
  }

  protected PipelineJson(Builder builder) {
    docType = builder.docType;
    version = builder.version;
    jsonSchema = builder.jsonSchema;
    id = builder.id;
    primaryPipeline = builder.primaryPipeline;
    pipelines = builder.pipelines;
    schemas = builder.schemas;
    runtimes = builder.runtimes;
    appData = builder.appData;
  }

  /**
   * New builder.
   *
   * @return a PipelineJson builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the docType.
   *
   * The document type.
   *
   * @return the docType
   */
  public String docType() {
    return docType;
  }

  /**
   * Gets the version.
   *
   * Pipeline flow version.
   *
   * @return the version
   */
  public String version() {
    return version;
  }

  /**
   * Gets the jsonSchema.
   *
   * Pipeline flow schema used.
   *
   * @return the jsonSchema
   */
  public String jsonSchema() {
    return jsonSchema;
  }

  /**
   * Gets the id.
   *
   * Pipeline flow ID.
   *
   * @return the id
   */
  public String id() {
    return id;
  }

  /**
   * Gets the primaryPipeline.
   *
   * Primary pipeline ID.
   *
   * @return the primaryPipeline
   */
  public String primaryPipeline() {
    return primaryPipeline;
  }

  /**
   * Gets the pipelines.
   *
   * @return the pipelines
   */
  public List<Pipelines> pipelines() {
    return pipelines;
  }

  /**
   * Gets the schemas.
   *
   * Array of data record schemas used in the pipeline.
   *
   * @return the schemas
   */
  public Object schemas() {
    return schemas;
  }

  /**
   * Gets the runtimes.
   *
   * Runtime information for pipeline flow.
   *
   * @return the runtimes
   */
  public Object runtimes() {
    return runtimes;
  }

  /**
   * Gets the appData.
   *
   * Additional parameters for pipeline flow.
   *
   * @return the appData
   */
  public Object appData() {
    return appData;
  }
}

