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

/*
 * IBM OpenAPI SDK Code Generator Version: 3.27.0-c07e12f4-20210209-225127
 */

package com.ibm.cloud.datastage.v3;

import com.google.gson.JsonObject;
import com.ibm.cloud.datastage.common.SdkCommon;
import com.ibm.cloud.datastage.v3.model.CloneDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.CloneDatastageSubflowsOptions;
import com.ibm.cloud.datastage.v3.model.CompileDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.CreateDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.CreateDatastageSubflowsOptions;
import com.ibm.cloud.datastage.v3.model.CreateMigrationOptions;
import com.ibm.cloud.datastage.v3.model.DataFlowPagedCollection;
import com.ibm.cloud.datastage.v3.model.DataIntgFlow;
import com.ibm.cloud.datastage.v3.model.DataIntgFlowJson;
import com.ibm.cloud.datastage.v3.model.DeleteDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.DeleteDatastageSubflowsOptions;
import com.ibm.cloud.datastage.v3.model.DeleteMigrationOptions;
import com.ibm.cloud.datastage.v3.model.FlowCompileResponse;
import com.ibm.cloud.datastage.v3.model.GetDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.GetDatastageSubflowsOptions;
import com.ibm.cloud.datastage.v3.model.GetMigrationOptions;
import com.ibm.cloud.datastage.v3.model.ImportResponse;
import com.ibm.cloud.datastage.v3.model.ListDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.ListDatastageSubflowsOptions;
import com.ibm.cloud.datastage.v3.model.UpdateDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.UpdateDatastageSubflowsOptions;
import com.ibm.cloud.sdk.core.http.RequestBuilder;
import com.ibm.cloud.sdk.core.http.ResponseConverter;
import com.ibm.cloud.sdk.core.http.ServiceCall;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.ConfigBasedAuthenticatorFactory;
import com.ibm.cloud.sdk.core.service.BaseService;
import com.ibm.cloud.sdk.core.util.RequestUtils;
import com.ibm.cloud.sdk.core.util.ResponseConverterUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The IBM DataStage service provides APIs to manage, edit, and run data flows in supported runtimes such as PX-Engine.
 *
 * @version v3
 */
public class Datastage extends BaseService {

  public static final String DEFAULT_SERVICE_NAME = "datastage";

  public static final String DEFAULT_SERVICE_URL = "https://datastage.cloud.ibm.com/data_intg";

 /**
   * Class method which constructs an instance of the `Datastage` client.
   * The default service name is used to configure the client instance.
   *
   * @return an instance of the `Datastage` client using external configuration
   */
  public static Datastage newInstance() {
    return newInstance(DEFAULT_SERVICE_NAME);
  }

  /**
   * Class method which constructs an instance of the `Datastage` client.
   * The specified service name is used to configure the client instance.
   *
   * @param serviceName the service name to be used when configuring the client instance
   * @return an instance of the `Datastage` client using external configuration
   */
  public static Datastage newInstance(String serviceName) {
    Authenticator authenticator = ConfigBasedAuthenticatorFactory.getAuthenticator(serviceName);
    Datastage service = new Datastage(serviceName, authenticator);
    service.configureService(serviceName);
    return service;
  }

  /**
   * Constructs an instance of the `Datastage` client.
   * The specified service name and authenticator are used to configure the client instance.
   *
   * @param serviceName the service name to be used when configuring the client instance
   * @param authenticator the {@link Authenticator} instance to be configured for this client
   */
  public Datastage(String serviceName, Authenticator authenticator) {
    super(serviceName, authenticator);
    setServiceUrl(DEFAULT_SERVICE_URL);
  }

  /**
   * Delete DataStage flows.
   *
   * Deletes the specified data flows in a project or catalog (either `project_id` or `catalog_id` must be set).
   *
   * If the deletion of the data flows and their runs will take some time to finish, then a 202 response will be
   * returned and the deletion will continue asynchronously.
   *          All the data flow runs associated with the data flows will also be deleted. If a data flow is still
   * running, it will not be deleted unless the force parameter is set to true. If a data flow is still running and the
   * force parameter is set to true, the call returns immediately with a 202 response. The related data flows are
   * deleted after the data flow runs are stopped.
   *
   * @param deleteDatastageFlowsOptions the {@link DeleteDatastageFlowsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> deleteDatastageFlows(DeleteDatastageFlowsOptions deleteDatastageFlowsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(deleteDatastageFlowsOptions,
      "deleteDatastageFlowsOptions cannot be null");
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "deleteDatastageFlows");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.query("id", RequestUtils.join(deleteDatastageFlowsOptions.id(), ","));
    if (deleteDatastageFlowsOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(deleteDatastageFlowsOptions.catalogId()));
    }
    if (deleteDatastageFlowsOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(deleteDatastageFlowsOptions.projectId()));
    }
    if (deleteDatastageFlowsOptions.force() != null) {
      builder.query("force", String.valueOf(deleteDatastageFlowsOptions.force()));
    }
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get metadata and lock information for DataStage flows.
   *
   * Lists the metadata, entity and lock information for DataStage flows that are contained in the specified project.
   *
   * Use the following parameters to filter the results:
   *
   * | Field                    | Match type   | Example                                 |
   * | ------------------------ | ------------ | --------------------------------------- |
   * | `entity.name`              | Equals           | `entity.name=MyDataStageFlow`  |
   * | `entity.name`              | Starts with      | `entity.name=starts:MyData`  |
   * | `entity.description`       | Equals           | `entity.description=movement`  |
   * | `entity.description`       | Starts with      | `entity.description=starts:data`  |
   *
   * To sort the results, use one or more of the parameters  described in the following section. If no sort key is
   * specified, the results are sorted in descending order on `metadata.create_time` (i.e. returning the most  recently
   * created data flows first).
   *
   * | Field                          | Example |
   * | ------------------------- | ----------------------------------- |
   * | sort     | `sort=+entity.name` (sort by ascending name)  |
   * | sort     | `sort=-metadata.create_time` (sort by descending creation time) |
   *
   * Multiple sort keys can be specified by delimiting them with a comma. For example, to sort in descending order on
   * `create_time` and then in ascending order on name use: `sort=-metadata.create_time`,`+entity.name`.
   *
   * @param listDatastageFlowsOptions the {@link ListDatastageFlowsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataFlowPagedCollection}
   */
  public ServiceCall<DataFlowPagedCollection> listDatastageFlows(ListDatastageFlowsOptions listDatastageFlowsOptions) {
    if (listDatastageFlowsOptions == null) {
      listDatastageFlowsOptions = new ListDatastageFlowsOptions.Builder().build();
    }
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "listDatastageFlows");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (listDatastageFlowsOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(listDatastageFlowsOptions.catalogId()));
    }
    if (listDatastageFlowsOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(listDatastageFlowsOptions.projectId()));
    }
    if (listDatastageFlowsOptions.sort() != null) {
      builder.query("sort", String.valueOf(listDatastageFlowsOptions.sort()));
    }
    if (listDatastageFlowsOptions.start() != null) {
      builder.query("start", String.valueOf(listDatastageFlowsOptions.start()));
    }
    if (listDatastageFlowsOptions.limit() != null) {
      builder.query("limit", String.valueOf(listDatastageFlowsOptions.limit()));
    }
    if (listDatastageFlowsOptions.entityName() != null) {
      builder.query("entity.name", String.valueOf(listDatastageFlowsOptions.entityName()));
    }
    if (listDatastageFlowsOptions.entityDescription() != null) {
      builder.query("entity.description", String.valueOf(listDatastageFlowsOptions.entityDescription()));
    }
    ResponseConverter<DataFlowPagedCollection> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataFlowPagedCollection>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get metadata and lock information for DataStage flows.
   *
   * Lists the metadata, entity and lock information for DataStage flows that are contained in the specified project.
   *
   * Use the following parameters to filter the results:
   *
   * | Field                    | Match type   | Example                                 |
   * | ------------------------ | ------------ | --------------------------------------- |
   * | `entity.name`              | Equals           | `entity.name=MyDataStageFlow`  |
   * | `entity.name`              | Starts with      | `entity.name=starts:MyData`  |
   * | `entity.description`       | Equals           | `entity.description=movement`  |
   * | `entity.description`       | Starts with      | `entity.description=starts:data`  |
   *
   * To sort the results, use one or more of the parameters  described in the following section. If no sort key is
   * specified, the results are sorted in descending order on `metadata.create_time` (i.e. returning the most  recently
   * created data flows first).
   *
   * | Field                          | Example |
   * | ------------------------- | ----------------------------------- |
   * | sort     | `sort=+entity.name` (sort by ascending name)  |
   * | sort     | `sort=-metadata.create_time` (sort by descending creation time) |
   *
   * Multiple sort keys can be specified by delimiting them with a comma. For example, to sort in descending order on
   * `create_time` and then in ascending order on name use: `sort=-metadata.create_time`,`+entity.name`.
   *
   * @return a {@link ServiceCall} with a result of type {@link DataFlowPagedCollection}
   */
  public ServiceCall<DataFlowPagedCollection> listDatastageFlows() {
    return listDatastageFlows(null);
  }

  /**
   * Create DataStage flow.
   *
   * Creates a DataStage flow in the specified project or catalog (either `project_id` or `catalog_id` must be set). All
   * subsequent calls to use the data flow must specify the project or catalog ID the data flow was created in.
   *
   * @param createDatastageFlowsOptions the {@link CreateDatastageFlowsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataIntgFlow}
   */
  public ServiceCall<DataIntgFlow> createDatastageFlows(CreateDatastageFlowsOptions createDatastageFlowsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(createDatastageFlowsOptions,
      "createDatastageFlowsOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "createDatastageFlows");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    builder.query("data_intg_flow_name", String.valueOf(createDatastageFlowsOptions.dataIntgFlowName()));
    if (createDatastageFlowsOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(createDatastageFlowsOptions.catalogId()));
    }
    if (createDatastageFlowsOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(createDatastageFlowsOptions.projectId()));
    }
    if (createDatastageFlowsOptions.assetCategory() != null) {
      builder.query("asset_category", String.valueOf(createDatastageFlowsOptions.assetCategory()));
    }
    final JsonObject contentJson = new JsonObject();
    if (createDatastageFlowsOptions.pipelineFlows() != null) {
      contentJson.add("pipeline_flows", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(createDatastageFlowsOptions.pipelineFlows()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<DataIntgFlow> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataIntgFlow>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get DataStage flow.
   *
   * Lists the DataStage flow that is contained in the specified project. Attachments, metadata and a limited number of
   * attributes from the entity of each DataStage flow is returned.
   *
   * @param getDatastageFlowsOptions the {@link GetDatastageFlowsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataIntgFlowJson}
   */
  public ServiceCall<DataIntgFlowJson> getDatastageFlows(GetDatastageFlowsOptions getDatastageFlowsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(getDatastageFlowsOptions,
      "getDatastageFlowsOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("data_intg_flow_id", getDatastageFlowsOptions.dataIntgFlowId());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows/{data_intg_flow_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "getDatastageFlows");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (getDatastageFlowsOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(getDatastageFlowsOptions.catalogId()));
    }
    if (getDatastageFlowsOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(getDatastageFlowsOptions.projectId()));
    }
    ResponseConverter<DataIntgFlowJson> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataIntgFlowJson>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Update DataStage flow.
   *
   * Modifies a data flow in the specified project or catalog (either `project_id` or `catalog_id` must be set). All
   * subsequent calls to use the data flow must specify the project or catalog ID the data flow was created in.
   *
   * @param updateDatastageFlowsOptions the {@link UpdateDatastageFlowsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataIntgFlow}
   */
  public ServiceCall<DataIntgFlow> updateDatastageFlows(UpdateDatastageFlowsOptions updateDatastageFlowsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(updateDatastageFlowsOptions,
      "updateDatastageFlowsOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("data_intg_flow_id", updateDatastageFlowsOptions.dataIntgFlowId());
    RequestBuilder builder = RequestBuilder.put(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows/{data_intg_flow_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "updateDatastageFlows");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    builder.query("data_intg_flow_name", String.valueOf(updateDatastageFlowsOptions.dataIntgFlowName()));
    if (updateDatastageFlowsOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(updateDatastageFlowsOptions.catalogId()));
    }
    if (updateDatastageFlowsOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(updateDatastageFlowsOptions.projectId()));
    }
    final JsonObject contentJson = new JsonObject();
    if (updateDatastageFlowsOptions.pipelineFlows() != null) {
      contentJson.add("pipeline_flows", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(updateDatastageFlowsOptions.pipelineFlows()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<DataIntgFlow> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataIntgFlow>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Clone DataStage flow.
   *
   * Create a DataStage flow in the specified project or catalog based on an existing DataStage flow in the same project
   * or catalog.
   *
   * @param cloneDatastageFlowsOptions the {@link CloneDatastageFlowsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataIntgFlow}
   */
  public ServiceCall<DataIntgFlow> cloneDatastageFlows(CloneDatastageFlowsOptions cloneDatastageFlowsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(cloneDatastageFlowsOptions,
      "cloneDatastageFlowsOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("data_intg_flow_id", cloneDatastageFlowsOptions.dataIntgFlowId());
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows/{data_intg_flow_id}/clone", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "cloneDatastageFlows");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (cloneDatastageFlowsOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(cloneDatastageFlowsOptions.catalogId()));
    }
    if (cloneDatastageFlowsOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(cloneDatastageFlowsOptions.projectId()));
    }
    ResponseConverter<DataIntgFlow> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataIntgFlow>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Compile DataStage flow to generate runtime assets.
   *
   * Generate the runtime assets for a DataStage flow in the specified project or catalog for a specified runtime type.
   * Either project_id or catalog_id must be specified.
   *
   * @param compileDatastageFlowsOptions the {@link CompileDatastageFlowsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link FlowCompileResponse}
   */
  public ServiceCall<FlowCompileResponse> compileDatastageFlows(CompileDatastageFlowsOptions compileDatastageFlowsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(compileDatastageFlowsOptions,
      "compileDatastageFlowsOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("data_intg_flow_id", compileDatastageFlowsOptions.dataIntgFlowId());
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/ds_codegen/compile/{data_intg_flow_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "compileDatastageFlows");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (compileDatastageFlowsOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(compileDatastageFlowsOptions.catalogId()));
    }
    if (compileDatastageFlowsOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(compileDatastageFlowsOptions.projectId()));
    }
    if (compileDatastageFlowsOptions.runtimeType() != null) {
      builder.query("runtime_type", String.valueOf(compileDatastageFlowsOptions.runtimeType()));
    }
    ResponseConverter<FlowCompileResponse> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<FlowCompileResponse>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Delete DataStage subflows.
   *
   * Deletes the specified data subflows in a project or catalog (either `project_id` or `catalog_id` must be set).
   *
   * If the deletion of the data subflows will take some time to finish, then a 202 response will be returned and the
   * deletion will continue asynchronously.
   *
   * @param deleteDatastageSubflowsOptions the {@link DeleteDatastageSubflowsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> deleteDatastageSubflows(DeleteDatastageSubflowsOptions deleteDatastageSubflowsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(deleteDatastageSubflowsOptions,
      "deleteDatastageSubflowsOptions cannot be null");
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows/subflows"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "deleteDatastageSubflows");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.query("id", RequestUtils.join(deleteDatastageSubflowsOptions.id(), ","));
    if (deleteDatastageSubflowsOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(deleteDatastageSubflowsOptions.catalogId()));
    }
    if (deleteDatastageSubflowsOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(deleteDatastageSubflowsOptions.projectId()));
    }
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get metadata and lock information for DataStage subflows.
   *
   * Lists the metadata, entity and lock information for DataStage subflows that are contained in the specified project.
   *
   *
   * Use the following parameters to filter the results:
   *
   * | Field                    | Match type   | Example                                 |
   * | ------------------------ | ------------ | --------------------------------------- |
   * | `entity.name`              | Equals           | `entity.name=MyDataStageSubFlow`  |
   * | `entity.name`              | Starts with      | `entity.name=starts:MyData`  |
   * | `entity.description`       | Equals           | `entity.description=movement`  |
   * | `entity.description`       | Starts with      | `entity.description=starts:data`  |
   *
   * To sort the results, use one or more of the parameters  described in the following section. If no sort key is
   * specified, the results are sorted in descending order on `metadata.create_time` (i.e. returning the most  recently
   * created data flows first).
   *
   * | Field                          | Example |
   * | ------------------------- | ----------------------------------- |
   * | sort     | `sort=+entity.name` (sort by ascending name)  |
   * | sort     | `sort=-metadata.create_time` (sort by descending creation time) |
   *
   * Multiple sort keys can be specified by delimiting them with a comma. For example, to sort in descending order on
   * `create_time` and then in ascending order on name use: `sort=-metadata.create_time`,`+entity.name`.
   *
   * @param listDatastageSubflowsOptions the {@link ListDatastageSubflowsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataFlowPagedCollection}
   */
  public ServiceCall<DataFlowPagedCollection> listDatastageSubflows(ListDatastageSubflowsOptions listDatastageSubflowsOptions) {
    if (listDatastageSubflowsOptions == null) {
      listDatastageSubflowsOptions = new ListDatastageSubflowsOptions.Builder().build();
    }
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows/subflows"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "listDatastageSubflows");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (listDatastageSubflowsOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(listDatastageSubflowsOptions.catalogId()));
    }
    if (listDatastageSubflowsOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(listDatastageSubflowsOptions.projectId()));
    }
    if (listDatastageSubflowsOptions.sort() != null) {
      builder.query("sort", String.valueOf(listDatastageSubflowsOptions.sort()));
    }
    if (listDatastageSubflowsOptions.start() != null) {
      builder.query("start", String.valueOf(listDatastageSubflowsOptions.start()));
    }
    if (listDatastageSubflowsOptions.limit() != null) {
      builder.query("limit", String.valueOf(listDatastageSubflowsOptions.limit()));
    }
    if (listDatastageSubflowsOptions.entityName() != null) {
      builder.query("entity.name", String.valueOf(listDatastageSubflowsOptions.entityName()));
    }
    if (listDatastageSubflowsOptions.entityDescription() != null) {
      builder.query("entity.description", String.valueOf(listDatastageSubflowsOptions.entityDescription()));
    }
    ResponseConverter<DataFlowPagedCollection> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataFlowPagedCollection>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get metadata and lock information for DataStage subflows.
   *
   * Lists the metadata, entity and lock information for DataStage subflows that are contained in the specified project.
   *
   *
   * Use the following parameters to filter the results:
   *
   * | Field                    | Match type   | Example                                 |
   * | ------------------------ | ------------ | --------------------------------------- |
   * | `entity.name`              | Equals           | `entity.name=MyDataStageSubFlow`  |
   * | `entity.name`              | Starts with      | `entity.name=starts:MyData`  |
   * | `entity.description`       | Equals           | `entity.description=movement`  |
   * | `entity.description`       | Starts with      | `entity.description=starts:data`  |
   *
   * To sort the results, use one or more of the parameters  described in the following section. If no sort key is
   * specified, the results are sorted in descending order on `metadata.create_time` (i.e. returning the most  recently
   * created data flows first).
   *
   * | Field                          | Example |
   * | ------------------------- | ----------------------------------- |
   * | sort     | `sort=+entity.name` (sort by ascending name)  |
   * | sort     | `sort=-metadata.create_time` (sort by descending creation time) |
   *
   * Multiple sort keys can be specified by delimiting them with a comma. For example, to sort in descending order on
   * `create_time` and then in ascending order on name use: `sort=-metadata.create_time`,`+entity.name`.
   *
   * @return a {@link ServiceCall} with a result of type {@link DataFlowPagedCollection}
   */
  public ServiceCall<DataFlowPagedCollection> listDatastageSubflows() {
    return listDatastageSubflows(null);
  }

  /**
   * Create DataStage subflow.
   *
   * Creates a DataStage subflow in the specified project or catalog (either `project_id` or `catalog_id` must be set).
   * All subsequent calls to use the data flow must specify the project or catalog ID the data flow was created in.
   *
   * @param createDatastageSubflowsOptions the {@link CreateDatastageSubflowsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataIntgFlow}
   */
  public ServiceCall<DataIntgFlow> createDatastageSubflows(CreateDatastageSubflowsOptions createDatastageSubflowsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(createDatastageSubflowsOptions,
      "createDatastageSubflowsOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows/subflows"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "createDatastageSubflows");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    builder.query("data_intg_subflow_name", String.valueOf(createDatastageSubflowsOptions.dataIntgSubflowName()));
    if (createDatastageSubflowsOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(createDatastageSubflowsOptions.catalogId()));
    }
    if (createDatastageSubflowsOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(createDatastageSubflowsOptions.projectId()));
    }
    if (createDatastageSubflowsOptions.assetCategory() != null) {
      builder.query("asset_category", String.valueOf(createDatastageSubflowsOptions.assetCategory()));
    }
    final JsonObject contentJson = new JsonObject();
    if (createDatastageSubflowsOptions.pipelineFlows() != null) {
      contentJson.add("pipeline_flows", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(createDatastageSubflowsOptions.pipelineFlows()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<DataIntgFlow> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataIntgFlow>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get DataStage subflow.
   *
   * Lists the DataStage subflow that is contained in the specified project. Attachments, metadata and a limited number
   * of attributes from the entity of each DataStage flow is returned.
   *
   * @param getDatastageSubflowsOptions the {@link GetDatastageSubflowsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataIntgFlowJson}
   */
  public ServiceCall<DataIntgFlowJson> getDatastageSubflows(GetDatastageSubflowsOptions getDatastageSubflowsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(getDatastageSubflowsOptions,
      "getDatastageSubflowsOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("data_intg_subflow_id", getDatastageSubflowsOptions.dataIntgSubflowId());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows/subflows/{data_intg_subflow_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "getDatastageSubflows");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (getDatastageSubflowsOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(getDatastageSubflowsOptions.catalogId()));
    }
    if (getDatastageSubflowsOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(getDatastageSubflowsOptions.projectId()));
    }
    ResponseConverter<DataIntgFlowJson> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataIntgFlowJson>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Update DataStage subflow.
   *
   * Modifies a data subflow in the specified project or catalog (either `project_id` or `catalog_id` must be set). All
   * subsequent calls to use the data flow must specify the project or catalog ID the data flow was created in.
   *
   * @param updateDatastageSubflowsOptions the {@link UpdateDatastageSubflowsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataIntgFlow}
   */
  public ServiceCall<DataIntgFlow> updateDatastageSubflows(UpdateDatastageSubflowsOptions updateDatastageSubflowsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(updateDatastageSubflowsOptions,
      "updateDatastageSubflowsOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("data_intg_subflow_id", updateDatastageSubflowsOptions.dataIntgSubflowId());
    RequestBuilder builder = RequestBuilder.put(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows/subflows/{data_intg_subflow_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "updateDatastageSubflows");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    builder.query("data_intg_subflow_name", String.valueOf(updateDatastageSubflowsOptions.dataIntgSubflowName()));
    if (updateDatastageSubflowsOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(updateDatastageSubflowsOptions.catalogId()));
    }
    if (updateDatastageSubflowsOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(updateDatastageSubflowsOptions.projectId()));
    }
    final JsonObject contentJson = new JsonObject();
    if (updateDatastageSubflowsOptions.pipelineFlows() != null) {
      contentJson.add("pipeline_flows", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(updateDatastageSubflowsOptions.pipelineFlows()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<DataIntgFlow> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataIntgFlow>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Clone DataStage subflow.
   *
   * Create a DataStage subflow in the specified project or catalog based on an existing DataStage subflow in the same
   * project or catalog.
   *
   * @param cloneDatastageSubflowsOptions the {@link CloneDatastageSubflowsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataIntgFlow}
   */
  public ServiceCall<DataIntgFlow> cloneDatastageSubflows(CloneDatastageSubflowsOptions cloneDatastageSubflowsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(cloneDatastageSubflowsOptions,
      "cloneDatastageSubflowsOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("data_intg_subflow_id", cloneDatastageSubflowsOptions.dataIntgSubflowId());
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows/subflows/{data_intg_subflow_id}/clone", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "cloneDatastageSubflows");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (cloneDatastageSubflowsOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(cloneDatastageSubflowsOptions.catalogId()));
    }
    if (cloneDatastageSubflowsOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(cloneDatastageSubflowsOptions.projectId()));
    }
    ResponseConverter<DataIntgFlow> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataIntgFlow>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Create V3 data flows from the attached job export file.
   *
   * Creates data flows from the attached job export file. This is an asynchronous call. The API call returns almost
   * immediately which does not necessarily imply the completion of the import request. It only means that the import
   * request has been accepted. The status field of the import request is included in the import response object. The
   * status "completed" ("in_progress", "failed", resp.) indicates the import request is completed (in progress, and
   * failed, resp.) The job export file for an import request may contain one mor more data flows. Unless the on_failure
   * option is set to "stop", a completed import request may contain not only successfully imported data flows but also
   * data flows that cannot be imported.
   *
   * @param createMigrationOptions the {@link CreateMigrationOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link ImportResponse}
   */
  public ServiceCall<ImportResponse> createMigration(CreateMigrationOptions createMigrationOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(createMigrationOptions,
      "createMigrationOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/migration/isx_imports"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "createMigration");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (createMigrationOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(createMigrationOptions.catalogId()));
    }
    if (createMigrationOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(createMigrationOptions.projectId()));
    }
    if (createMigrationOptions.onFailure() != null) {
      builder.query("on_failure", String.valueOf(createMigrationOptions.onFailure()));
    }
    if (createMigrationOptions.conflictResolution() != null) {
      builder.query("conflict_resolution", String.valueOf(createMigrationOptions.conflictResolution()));
    }
    if (createMigrationOptions.attachmentType() != null) {
      builder.query("attachment_type", String.valueOf(createMigrationOptions.attachmentType()));
    }
    if (createMigrationOptions.fileName() != null) {
      builder.query("file_name", String.valueOf(createMigrationOptions.fileName()));
    }
    builder.bodyContent(createMigrationOptions.body(), "application/octet-stream");
    ResponseConverter<ImportResponse> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<ImportResponse>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Cancel a previous import request.
   *
   * Cancel a previous import request. Use GET /v3/migration/imports/{import_id} to obtain the current status of the
   * import, including whether it has been cancelled.
   *
   * @param deleteMigrationOptions the {@link DeleteMigrationOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> deleteMigration(DeleteMigrationOptions deleteMigrationOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(deleteMigrationOptions,
      "deleteMigrationOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("import_id", deleteMigrationOptions.importId());
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/migration/isx_imports/{import_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "deleteMigration");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    if (deleteMigrationOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(deleteMigrationOptions.catalogId()));
    }
    if (deleteMigrationOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(deleteMigrationOptions.projectId()));
    }
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get the status of a previous import request.
   *
   * Gets the status of an import request. The status field in the response object indicates if the given import is
   * completed, in progress, or failed. Detailed status information about each imported data flow is also contained in
   * the response object.
   *
   * @param getMigrationOptions the {@link GetMigrationOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link ImportResponse}
   */
  public ServiceCall<ImportResponse> getMigration(GetMigrationOptions getMigrationOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(getMigrationOptions,
      "getMigrationOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("import_id", getMigrationOptions.importId());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/migration/isx_imports/{import_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "getMigration");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (getMigrationOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(getMigrationOptions.catalogId()));
    }
    if (getMigrationOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(getMigrationOptions.projectId()));
    }
    ResponseConverter<ImportResponse> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<ImportResponse>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

}
