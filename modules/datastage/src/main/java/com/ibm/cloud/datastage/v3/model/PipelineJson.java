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
import java.util.Map;

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
  protected List<Object> schemas;
  protected List<Object> runtimes;
  @SerializedName("app_data")
  protected Map<String, Object> appData;
  protected Map<String, Object> parameters;
  @SerializedName("external_paramsets")
  protected List<Object> externalParamsets;

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
    private List<Object> schemas;
    private List<Object> runtimes;
    private Map<String, Object> appData;
    private Map<String, Object> parameters;
    private List<Object> externalParamsets;

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
      this.parameters = pipelineJson.parameters;
      this.externalParamsets = pipelineJson.externalParamsets;
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
     * Adds an schemas to schemas.
     *
     * @param schemas the new schemas
     * @return the PipelineJson builder
     */
    public Builder addSchemas(Object schemas) {
      com.ibm.cloud.sdk.core.util.Validator.notNull(schemas,
        "schemas cannot be null");
      if (this.schemas == null) {
        this.schemas = new ArrayList<Object>();
      }
      this.schemas.add(schemas);
      return this;
    }

    /**
     * Adds an runtimes to runtimes.
     *
     * @param runtimes the new runtimes
     * @return the PipelineJson builder
     */
    public Builder addRuntimes(Object runtimes) {
      com.ibm.cloud.sdk.core.util.Validator.notNull(runtimes,
        "runtimes cannot be null");
      if (this.runtimes == null) {
        this.runtimes = new ArrayList<Object>();
      }
      this.runtimes.add(runtimes);
      return this;
    }

    /**
     * Adds an externalParamsets to externalParamsets.
     *
     * @param externalParamsets the new externalParamsets
     * @return the PipelineJson builder
     */
    public Builder addExternalParamsets(Object externalParamsets) {
      com.ibm.cloud.sdk.core.util.Validator.notNull(externalParamsets,
        "externalParamsets cannot be null");
      if (this.externalParamsets == null) {
        this.externalParamsets = new ArrayList<Object>();
      }
      this.externalParamsets.add(externalParamsets);
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
     * Existing schemas will be replaced.
     *
     * @param schemas the schemas
     * @return the PipelineJson builder
     */
    public Builder schemas(List<Object> schemas) {
      this.schemas = schemas;
      return this;
    }

    /**
     * Set the runtimes.
     * Existing runtimes will be replaced.
     *
     * @param runtimes the runtimes
     * @return the PipelineJson builder
     */
    public Builder runtimes(List<Object> runtimes) {
      this.runtimes = runtimes;
      return this;
    }

    /**
     * Set the appData.
     *
     * @param appData the appData
     * @return the PipelineJson builder
     */
    public Builder appData(Map<String, Object> appData) {
      this.appData = appData;
      return this;
    }

    /**
     * Set the parameters.
     *
     * @param parameters the parameters
     * @return the PipelineJson builder
     */
    public Builder parameters(Map<String, Object> parameters) {
      this.parameters = parameters;
      return this;
    }

    /**
     * Set the externalParamsets.
     * Existing externalParamsets will be replaced.
     *
     * @param externalParamsets the externalParamsets
     * @return the PipelineJson builder
     */
    public Builder externalParamsets(List<Object> externalParamsets) {
      this.externalParamsets = externalParamsets;
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
    parameters = builder.parameters;
    externalParamsets = builder.externalParamsets;
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
   * Refers to the JSON schema used to validate documents of this type.
   *
   * @return the jsonSchema
   */
  public String jsonSchema() {
    return jsonSchema;
  }

  /**
   * Gets the id.
   *
   * Document identifier, GUID recommended.
   *
   * @return the id
   */
  public String id() {
    return id;
  }

  /**
   * Gets the primaryPipeline.
   *
   * Reference to the primary (main) pipeline flow within the document.
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
  public List<Object> schemas() {
    return schemas;
  }

  /**
   * Gets the runtimes.
   *
   * Runtime information for pipeline flow.
   *
   * @return the runtimes
   */
  public List<Object> runtimes() {
    return runtimes;
  }

  /**
   * Gets the appData.
   *
   * Object containing app-specific data.
   *
   * @return the appData
   */
  public Map<String, Object> appData() {
    return appData;
  }

  /**
   * Gets the parameters.
   *
   * Parameters for the flow document.
   *
   * @return the parameters
   */
  public Map<String, Object> parameters() {
    return parameters;
  }

  /**
   * Gets the externalParamsets.
   *
   * Array of parameter set references.
   *
   * @return the externalParamsets
   */
  public List<Object> externalParamsets() {
    return externalParamsets;
  }
}

