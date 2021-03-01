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
import com.ibm.cloud.datastage.v3.model.DataFlowPagedCollection;
import com.ibm.cloud.datastage.v3.model.DataIntgFlow;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsCloneOptions;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsCompileOptions;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsCreateOptions;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsDeleteOptions;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsGetOptions;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsListOptions;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsUpdateOptions;
import com.ibm.cloud.datastage.v3.model.FlowCompileResponse;
import com.ibm.cloud.datastage.v3.model.ImportResponse;
import com.ibm.cloud.datastage.v3.model.MigrationCreateOptions;
import com.ibm.cloud.datastage.v3.model.MigrationDeleteOptions;
import com.ibm.cloud.datastage.v3.model.MigrationGetOptions;
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
 * The IBM  Data API Data Flow service provides APIs to manage, edit, and run data flows in supported runtimes such as
 * PX-Engine.
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
   * Get metadata and lock information for DataStage flows.
   *
   * Lists the metadata, entity and lock information for DataStage flows that are contained in the specified project.
   *
   * Use the following parameters to filter the results:
   *
   * | Field                    | Match type   | Example                                 |
   * | ------------------------ | ------------ | --------------------------------------- |
   * | entity.name              | Equals           | entity.name=MyDataStageFlow  |
   * | entity.name              | Starts with      | entity.name=starts:MyData  |
   * | entity.description       | Equals           | entity.description=movement  |
   * | entity.description       | Starts with      | entity.description=starts:data  |
   *
   * To sort the results, use one or more of the parameters  described in the following section. If no sort key is
   * specified, the results are sorted in descending order on metadata.create_time (i.e. returning the most  recently
   * created data flows first).
   *
   * | Field                          | Example |
   * | ------------------------- | ----------------------------------- |
   * | sort     | sort=+entity.name (sort by ascending name)  |
   * | sort     | sort=-metadata.create_time (sort by descending creation time) |
   *
   * Multiple sort keys can be specified by delimiting them with a comma. For example, to sort in descending order on
   * create_time and then in ascending order on name use: sort=-metadata.create_time,+entity.name.
   *
   * @param datastageFlowsListOptions the {@link DatastageFlowsListOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataFlowPagedCollection}
   */
  public ServiceCall<DataFlowPagedCollection> datastageFlowsList(DatastageFlowsListOptions datastageFlowsListOptions) {
    if (datastageFlowsListOptions == null) {
      datastageFlowsListOptions = new DatastageFlowsListOptions.Builder().build();
    }
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "datastageFlowsList");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (datastageFlowsListOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(datastageFlowsListOptions.catalogId()));
    }
    if (datastageFlowsListOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(datastageFlowsListOptions.projectId()));
    }
    if (datastageFlowsListOptions.sort() != null) {
      builder.query("sort", String.valueOf(datastageFlowsListOptions.sort()));
    }
    if (datastageFlowsListOptions.start() != null) {
      builder.query("start", String.valueOf(datastageFlowsListOptions.start()));
    }
    if (datastageFlowsListOptions.limit() != null) {
      builder.query("limit", String.valueOf(datastageFlowsListOptions.limit()));
    }
    if (datastageFlowsListOptions.entityName() != null) {
      builder.query("entity.name", String.valueOf(datastageFlowsListOptions.entityName()));
    }
    if (datastageFlowsListOptions.entityDescription() != null) {
      builder.query("entity.description", String.valueOf(datastageFlowsListOptions.entityDescription()));
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
   * | entity.name              | Equals           | entity.name=MyDataStageFlow  |
   * | entity.name              | Starts with      | entity.name=starts:MyData  |
   * | entity.description       | Equals           | entity.description=movement  |
   * | entity.description       | Starts with      | entity.description=starts:data  |
   *
   * To sort the results, use one or more of the parameters  described in the following section. If no sort key is
   * specified, the results are sorted in descending order on metadata.create_time (i.e. returning the most  recently
   * created data flows first).
   *
   * | Field                          | Example |
   * | ------------------------- | ----------------------------------- |
   * | sort     | sort=+entity.name (sort by ascending name)  |
   * | sort     | sort=-metadata.create_time (sort by descending creation time) |
   *
   * Multiple sort keys can be specified by delimiting them with a comma. For example, to sort in descending order on
   * create_time and then in ascending order on name use: sort=-metadata.create_time,+entity.name.
   *
   * @return a {@link ServiceCall} with a result of type {@link DataFlowPagedCollection}
   */
  public ServiceCall<DataFlowPagedCollection> datastageFlowsList() {
    return datastageFlowsList(null);
  }

  /**
   * Create DataStage flow.
   *
   * Creates a DataStage flow in the specified project or catalog (either project_id or catalog_id must be set). All
   * subsequent calls to use the data flow must specify the project or catalog ID the data flow was created in.
   *
   * @param datastageFlowsCreateOptions the {@link DatastageFlowsCreateOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataIntgFlow}
   */
  public ServiceCall<DataIntgFlow> datastageFlowsCreate(DatastageFlowsCreateOptions datastageFlowsCreateOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(datastageFlowsCreateOptions,
      "datastageFlowsCreateOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "datastageFlowsCreate");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    builder.query("data_intg_flow_name", String.valueOf(datastageFlowsCreateOptions.dataIntgFlowName()));
    if (datastageFlowsCreateOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(datastageFlowsCreateOptions.catalogId()));
    }
    if (datastageFlowsCreateOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(datastageFlowsCreateOptions.projectId()));
    }
    if (datastageFlowsCreateOptions.assetCategory() != null) {
      builder.query("asset_category", String.valueOf(datastageFlowsCreateOptions.assetCategory()));
    }
    final JsonObject contentJson = new JsonObject();
    if (datastageFlowsCreateOptions.pipelineFlows() != null) {
      contentJson.add("pipeline_flows", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(datastageFlowsCreateOptions.pipelineFlows()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<DataIntgFlow> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataIntgFlow>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Delete DataStage flows.
   *
   * Deletes the specified data flows in a project or catalog (either project_id or catalog_id must be set).
   *
   * If the deletion of the data flows and their runs will take some time to finish, then a 202 response will be
   * returned and the deletion will continue asynchronously.
   *          All the data flow runs associated with the data flows will also be deleted. If a data flow is still
   * running, it will not be deleted unless the force parameter is set to true. If a data flow is still running and the
   * force parameter is set to true, the call returns immediately with a 202 response. The related data flows are
   * deleted after the data flow runs are stopped.
   *
   * @param datastageFlowsDeleteOptions the {@link DatastageFlowsDeleteOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> datastageFlowsDelete(DatastageFlowsDeleteOptions datastageFlowsDeleteOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(datastageFlowsDeleteOptions,
      "datastageFlowsDeleteOptions cannot be null");
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "datastageFlowsDelete");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.query("id", RequestUtils.join(datastageFlowsDeleteOptions.id(), ","));
    if (datastageFlowsDeleteOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(datastageFlowsDeleteOptions.catalogId()));
    }
    if (datastageFlowsDeleteOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(datastageFlowsDeleteOptions.projectId()));
    }
    if (datastageFlowsDeleteOptions.force() != null) {
      builder.query("force", String.valueOf(datastageFlowsDeleteOptions.force()));
    }
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get DataStage flow.
   *
   * Lists the DataStage flow that is contained in the specified project. Attachments, metadata and a limited number of
   * attributes from the entity of each DataStage flow is returned.
   *
   * @param datastageFlowsGetOptions the {@link DatastageFlowsGetOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataIntgFlow}
   */
  public ServiceCall<DataIntgFlow> datastageFlowsGet(DatastageFlowsGetOptions datastageFlowsGetOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(datastageFlowsGetOptions,
      "datastageFlowsGetOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("data_intg_flow_id", datastageFlowsGetOptions.dataIntgFlowId());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows/{data_intg_flow_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "datastageFlowsGet");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (datastageFlowsGetOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(datastageFlowsGetOptions.catalogId()));
    }
    if (datastageFlowsGetOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(datastageFlowsGetOptions.projectId()));
    }
    ResponseConverter<DataIntgFlow> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataIntgFlow>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Update DataStage flow.
   *
   * Modifies a data flow in the specified project or catalog (either project_id or catalog_id must be set). All
   * subsequent calls to use the data flow must specify the project or catalog ID the data flow was created in.
   *
   * @param datastageFlowsUpdateOptions the {@link DatastageFlowsUpdateOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataIntgFlow}
   */
  public ServiceCall<DataIntgFlow> datastageFlowsUpdate(DatastageFlowsUpdateOptions datastageFlowsUpdateOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(datastageFlowsUpdateOptions,
      "datastageFlowsUpdateOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("data_intg_flow_id", datastageFlowsUpdateOptions.dataIntgFlowId());
    RequestBuilder builder = RequestBuilder.put(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows/{data_intg_flow_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "datastageFlowsUpdate");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    builder.query("data_intg_flow_name", String.valueOf(datastageFlowsUpdateOptions.dataIntgFlowName()));
    if (datastageFlowsUpdateOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(datastageFlowsUpdateOptions.catalogId()));
    }
    if (datastageFlowsUpdateOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(datastageFlowsUpdateOptions.projectId()));
    }
    final JsonObject contentJson = new JsonObject();
    if (datastageFlowsUpdateOptions.pipelineFlows() != null) {
      contentJson.add("pipeline_flows", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(datastageFlowsUpdateOptions.pipelineFlows()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<DataIntgFlow> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataIntgFlow>() { }.getType());
    System.out.println("update " + contentJson.toString());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Clone DataStage flow.
   *
   * Create a DataStage flow in the specified project or catalog based on an existing DataStage flow in the same project
   * or catalog.
   *
   * @param datastageFlowsCloneOptions the {@link DatastageFlowsCloneOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link DataIntgFlow}
   */
  public ServiceCall<DataIntgFlow> datastageFlowsClone(DatastageFlowsCloneOptions datastageFlowsCloneOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(datastageFlowsCloneOptions,
      "datastageFlowsCloneOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("data_intg_flow_id", datastageFlowsCloneOptions.dataIntgFlowId());
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/data_intg_flows/{data_intg_flow_id}/clone", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "datastageFlowsClone");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (datastageFlowsCloneOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(datastageFlowsCloneOptions.catalogId()));
    }
    if (datastageFlowsCloneOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(datastageFlowsCloneOptions.projectId()));
    }
    ResponseConverter<DataIntgFlow> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<DataIntgFlow>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Compile DataStage Flow to generate runtime assets.
   *
   * Generate the runtime assets for a DataStage flow in the specified project or catalog (either project_id or
   * catalog_id must be set) for specified runtime type.
   *
   * @param datastageFlowsCompileOptions the {@link DatastageFlowsCompileOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link FlowCompileResponse}
   */
  public ServiceCall<FlowCompileResponse> datastageFlowsCompile(DatastageFlowsCompileOptions datastageFlowsCompileOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(datastageFlowsCompileOptions,
      "datastageFlowsCompileOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("data_intg_flow_id", datastageFlowsCompileOptions.dataIntgFlowId());
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/ds_codegen/compile/{data_intg_flow_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "datastageFlowsCompile");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (datastageFlowsCompileOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(datastageFlowsCompileOptions.catalogId()));
    }
    if (datastageFlowsCompileOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(datastageFlowsCompileOptions.projectId()));
    }
    if (datastageFlowsCompileOptions.runtimeType() != null) {
      builder.query("runtime_type", String.valueOf(datastageFlowsCompileOptions.runtimeType()));
    }
    ResponseConverter<FlowCompileResponse> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<FlowCompileResponse>() { }.getType());
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
   * @param migrationCreateOptions the {@link MigrationCreateOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link ImportResponse}
   */
  public ServiceCall<ImportResponse> migrationCreate(MigrationCreateOptions migrationCreateOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(migrationCreateOptions,
      "migrationCreateOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/migration/isx_imports"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "migrationCreate");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (migrationCreateOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(migrationCreateOptions.catalogId()));
    }
    if (migrationCreateOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(migrationCreateOptions.projectId()));
    }
    if (migrationCreateOptions.onFailure() != null) {
      builder.query("on_failure", String.valueOf(migrationCreateOptions.onFailure()));
    }
    if (migrationCreateOptions.conflictResolution() != null) {
      builder.query("conflict_resolution", String.valueOf(migrationCreateOptions.conflictResolution()));
    }
    if (migrationCreateOptions.attachmentType() != null) {
      builder.query("attachment_type", String.valueOf(migrationCreateOptions.attachmentType()));
    }
    if (migrationCreateOptions.fileName() != null) {
      builder.query("file_name", String.valueOf(migrationCreateOptions.fileName()));
    }
    builder.bodyContent(migrationCreateOptions.body(), "application/octet-stream");
    ResponseConverter<ImportResponse> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<ImportResponse>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get the status of a previous import request.
   *
   * Gets the status of an import request. The status field in the response object indicates if the given import is
   * completed, in progress, or failed. Detailed status information about each imported data flow is also contained in
   * the response object.
   *
   * @param migrationGetOptions the {@link MigrationGetOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link ImportResponse}
   */
  public ServiceCall<ImportResponse> migrationGet(MigrationGetOptions migrationGetOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(migrationGetOptions,
      "migrationGetOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("import_id", migrationGetOptions.importId());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/migration/isx_imports/{import_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "migrationGet");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json;charset=utf-8");
    if (migrationGetOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(migrationGetOptions.catalogId()));
    }
    if (migrationGetOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(migrationGetOptions.projectId()));
    }
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
   * @param migrationDeleteOptions the {@link MigrationDeleteOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> migrationDelete(MigrationDeleteOptions migrationDeleteOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(migrationDeleteOptions,
      "migrationDeleteOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("import_id", migrationDeleteOptions.importId());
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/migration/isx_imports/{import_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("datastage", "v3", "migrationDelete");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    if (migrationDeleteOptions.catalogId() != null) {
      builder.query("catalog_id", String.valueOf(migrationDeleteOptions.catalogId()));
    }
    if (migrationDeleteOptions.projectId() != null) {
      builder.query("project_id", String.valueOf(migrationDeleteOptions.projectId()));
    }
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

}
