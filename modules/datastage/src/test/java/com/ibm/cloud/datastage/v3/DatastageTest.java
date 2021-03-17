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
package com.ibm.cloud.datastage.v3;

import com.ibm.cloud.datastage.v3.Datastage;
import com.ibm.cloud.datastage.v3.model.AssetEntityROV;
import com.ibm.cloud.datastage.v3.model.AssetSystemMetadata;
import com.ibm.cloud.datastage.v3.model.AssetSystemMetadataUsage;
import com.ibm.cloud.datastage.v3.model.DataFlowPagedCollection;
import com.ibm.cloud.datastage.v3.model.DataImportError;
import com.ibm.cloud.datastage.v3.model.DataIntgFlow;
import com.ibm.cloud.datastage.v3.model.DataIntgFlowEntity;
import com.ibm.cloud.datastage.v3.model.DataIntgFlowJson;
import com.ibm.cloud.datastage.v3.model.DataIntgFlowLock;
import com.ibm.cloud.datastage.v3.model.DataIntgFlowLockEntity;
import com.ibm.cloud.datastage.v3.model.DataIntgFlowLockMetadata;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsCloneOptions;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsCompileOptions;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsCreateOptions;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsDeleteOptions;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsGetOptions;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsListOptions;
import com.ibm.cloud.datastage.v3.model.DatastageFlowsUpdateOptions;
import com.ibm.cloud.datastage.v3.model.FlowCompileResponse;
import com.ibm.cloud.datastage.v3.model.HrefModel;
import com.ibm.cloud.datastage.v3.model.ImportCount;
import com.ibm.cloud.datastage.v3.model.ImportFlow;
import com.ibm.cloud.datastage.v3.model.ImportFlowWarning;
import com.ibm.cloud.datastage.v3.model.ImportResponse;
import com.ibm.cloud.datastage.v3.model.ImportResponseEntity;
import com.ibm.cloud.datastage.v3.model.ImportResponseMetadata;
import com.ibm.cloud.datastage.v3.model.MigrationCreateOptions;
import com.ibm.cloud.datastage.v3.model.MigrationDeleteOptions;
import com.ibm.cloud.datastage.v3.model.MigrationGetOptions;
import com.ibm.cloud.datastage.v3.model.PipelineJson;
import com.ibm.cloud.datastage.v3.model.Pipelines;
import com.ibm.cloud.datastage.v3.utils.TestUtilities;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.NoAuthAuthenticator;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import com.ibm.cloud.sdk.core.util.DateUtils;
import com.ibm.cloud.sdk.core.util.EnvironmentUtils;
import com.ibm.cloud.sdk.core.util.RequestUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the Datastage service.
 */
@PrepareForTest({ EnvironmentUtils.class })
@PowerMockIgnore({"javax.net.ssl.*", "org.mockito.*"})
public class DatastageTest extends PowerMockTestCase {

  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  protected MockWebServer server;
  protected Datastage datastageService;

  // Creates a mock set of environment variables that are returned by EnvironmentUtils.getenv().
  private Map<String, String> getTestProcessEnvironment() {
    Map<String, String> env = new HashMap<>();
    env.put("TESTSERVICE_AUTH_TYPE", "noAuth");
    return env;
  }

  public void constructClientService() throws Throwable {
    PowerMockito.spy(EnvironmentUtils.class);
    PowerMockito.when(EnvironmentUtils.getenv()).thenReturn(getTestProcessEnvironment());
    final String serviceName = "testService";

    datastageService = Datastage.newInstance(serviceName);
    String url = server.url("/").toString();
    datastageService.setServiceUrl(url);
  }

  /**
  * Negative Test - construct the service with a null authenticator.
  */
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testConstructorWithNullAuthenticator() throws Throwable {
    final String serviceName = "testService";

    new Datastage(serviceName, null);
  }

  @Test
  public void testDatastageFlowsDeleteWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "";
    String datastageFlowsDeletePath = "/v3/data_intg_flows";

    server.enqueue(new MockResponse()
    .setResponseCode(202)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the DatastageFlowsDeleteOptions model
    DatastageFlowsDeleteOptions datastageFlowsDeleteOptionsModel = new DatastageFlowsDeleteOptions.Builder()
    .id(new java.util.ArrayList<String>(java.util.Arrays.asList("testString")))
    .catalogId("testString")
    .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
    .force(true)
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Void> response = datastageService.datastageFlowsDelete(datastageFlowsDeleteOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    // Response does not have a return type. Check that the result is null.
    assertNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("id"), RequestUtils.join(new java.util.ArrayList<String>(java.util.Arrays.asList("testString")), ","));
    assertEquals(query.get("catalog_id"), "testString");
    assertEquals(query.get("project_id"), "bd0dbbfd-810d-4f0e-b0a9-228c328a8e23");
    assertEquals(Boolean.valueOf(query.get("force")), Boolean.valueOf(true));
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, datastageFlowsDeletePath);
  }

  // Test the datastageFlowsDelete operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDatastageFlowsDeleteNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    datastageService.datastageFlowsDelete(null).execute();
  }

  @Test
  public void testDatastageFlowsListWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"data_flows\": [{\"attachments\": [\"anyValue\"], \"entity\": {\"data_intg_flow\": {\"mapKey\": \"anyValue\"}, \"description\": \"description\", \"lock\": {\"entity\": {\"data_intg_flow_id\": \"dataIntgFlowId\", \"requester\": \"requester\"}, \"metadata\": {\"alive\": false}}, \"name\": \"name\", \"rov\": {\"members\": [\"members\"], \"mode\": 4}, \"sub_type\": \"subType\"}, \"metadata\": {\"asset_id\": \"assetId\", \"asset_type\": \"assetType\", \"catalog_id\": \"catalogId\", \"create_time\": \"2019-01-01T12:00:00.000Z\", \"creator_id\": \"creatorId\", \"description\": \"description\", \"href\": \"href\", \"name\": \"name\", \"origin_country\": \"originCountry\", \"project_id\": \"projectId\", \"resource_key\": \"resourceKey\", \"size\": 4, \"source_system\": {\"mapKey\": \"anyValue\"}, \"tags\": [\"tags\"], \"usage\": {\"access_count\": 11, \"last_access_time\": \"2019-01-01T12:00:00.000Z\", \"last_accessor_id\": \"lastAccessorId\", \"last_modification_time\": \"2019-01-01T12:00:00.000Z\", \"last_modifier_id\": \"lastModifierId\"}}}], \"first\": {\"href\": \"href\"}, \"last\": {\"href\": \"href\"}, \"limit\": 5, \"next\": {\"href\": \"href\"}, \"prev\": {\"href\": \"href\"}, \"total_count\": 10}";
    String datastageFlowsListPath = "/v3/data_intg_flows";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json;charset=utf-8")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the DatastageFlowsListOptions model
    DatastageFlowsListOptions datastageFlowsListOptionsModel = new DatastageFlowsListOptions.Builder()
    .catalogId("testString")
    .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
    .sort("testString")
    .start("testString")
    .limit(Long.valueOf("100"))
    .entityName("testString")
    .entityDescription("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<DataFlowPagedCollection> response = datastageService.datastageFlowsList(datastageFlowsListOptionsModel).execute();
    assertNotNull(response);
    DataFlowPagedCollection responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("catalog_id"), "testString");
    assertEquals(query.get("project_id"), "bd0dbbfd-810d-4f0e-b0a9-228c328a8e23");
    assertEquals(query.get("sort"), "testString");
    assertEquals(query.get("start"), "testString");
    assertEquals(Long.valueOf(query.get("limit")), Long.valueOf("100"));
    assertEquals(query.get("entity.name"), "testString");
    assertEquals(query.get("entity.description"), "testString");
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, datastageFlowsListPath);
  }

  @Test
  public void testDatastageFlowsCreateWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"attachments\": [\"anyValue\"], \"entity\": {\"data_intg_flow\": {\"mapKey\": \"anyValue\"}, \"description\": \"description\", \"lock\": {\"entity\": {\"data_intg_flow_id\": \"dataIntgFlowId\", \"requester\": \"requester\"}, \"metadata\": {\"alive\": false}}, \"name\": \"name\", \"rov\": {\"members\": [\"members\"], \"mode\": 4}, \"sub_type\": \"subType\"}, \"metadata\": {\"asset_id\": \"assetId\", \"asset_type\": \"assetType\", \"catalog_id\": \"catalogId\", \"create_time\": \"2019-01-01T12:00:00.000Z\", \"creator_id\": \"creatorId\", \"description\": \"description\", \"href\": \"href\", \"name\": \"name\", \"origin_country\": \"originCountry\", \"project_id\": \"projectId\", \"resource_key\": \"resourceKey\", \"size\": 4, \"source_system\": {\"mapKey\": \"anyValue\"}, \"tags\": [\"tags\"], \"usage\": {\"access_count\": 11, \"last_access_time\": \"2019-01-01T12:00:00.000Z\", \"last_accessor_id\": \"lastAccessorId\", \"last_modification_time\": \"2019-01-01T12:00:00.000Z\", \"last_modifier_id\": \"lastModifierId\"}}}";
    String datastageFlowsCreatePath = "/v3/data_intg_flows";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json;charset=utf-8")
    .setResponseCode(201)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the Pipelines model
    Pipelines pipelinesModel = new Pipelines.Builder()
    .appData(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .description("A test DataStage flow")
    .id("fa1b859a-d592-474d-b56c-2137e4efa4bc")
    .nodes(new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")))
    .runtimeRef("pxOsh")
    .build();

    // Construct an instance of the PipelineJson model
    PipelineJson pipelineJsonModel = new PipelineJson.Builder()
    .appData(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .docType("pipeline")
    .externalParamsets(new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")))
    .id("84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff")
    .jsonSchema("http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json")
    .parameters(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .pipelines(new java.util.ArrayList<Pipelines>(java.util.Arrays.asList(pipelinesModel)))
    .primaryPipeline("fa1b859a-d592-474d-b56c-2137e4efa4bc")
    .runtimes(new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")))
    .schemas(new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")))
    .version("3.0")
    .build();

    // Construct an instance of the DatastageFlowsCreateOptions model
    DatastageFlowsCreateOptions datastageFlowsCreateOptionsModel = new DatastageFlowsCreateOptions.Builder()
    .dataIntgFlowName("testString")
    .pipelineFlows(pipelineJsonModel)
    .catalogId("testString")
    .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
    .assetCategory("system")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<DataIntgFlow> response = datastageService.datastageFlowsCreate(datastageFlowsCreateOptionsModel).execute();
    assertNotNull(response);
    DataIntgFlow responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("data_intg_flow_name"), "testString");
    assertEquals(query.get("catalog_id"), "testString");
    assertEquals(query.get("project_id"), "bd0dbbfd-810d-4f0e-b0a9-228c328a8e23");
    assertEquals(query.get("asset_category"), "system");
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, datastageFlowsCreatePath);
  }

  // Test the datastageFlowsCreate operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDatastageFlowsCreateNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    datastageService.datastageFlowsCreate(null).execute();
  }

  @Test
  public void testDatastageFlowsGetWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"attachments\": {\"app_data\": {\"mapKey\": \"anyValue\"}, \"doc_type\": \"pipeline\", \"external_paramsets\": [\"anyValue\"], \"id\": \"84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff\", \"json_schema\": \"http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json\", \"parameters\": {\"mapKey\": \"anyValue\"}, \"pipelines\": [{\"app_data\": {\"mapKey\": \"anyValue\"}, \"description\": \"A test DataStage flow\", \"id\": \"fa1b859a-d592-474d-b56c-2137e4efa4bc\", \"nodes\": [\"anyValue\"], \"runtime_ref\": \"pxOsh\"}], \"primary_pipeline\": \"fa1b859a-d592-474d-b56c-2137e4efa4bc\", \"runtimes\": [\"anyValue\"], \"schemas\": [\"anyValue\"], \"version\": \"3.0\"}, \"entity\": {\"data_intg_flow\": {\"mapKey\": \"anyValue\"}, \"description\": \"description\", \"lock\": {\"entity\": {\"data_intg_flow_id\": \"dataIntgFlowId\", \"requester\": \"requester\"}, \"metadata\": {\"alive\": false}}, \"name\": \"name\", \"rov\": {\"members\": [\"members\"], \"mode\": 4}, \"sub_type\": \"subType\"}, \"metadata\": {\"asset_id\": \"assetId\", \"asset_type\": \"assetType\", \"catalog_id\": \"catalogId\", \"create_time\": \"2019-01-01T12:00:00.000Z\", \"creator_id\": \"creatorId\", \"description\": \"description\", \"href\": \"href\", \"name\": \"name\", \"origin_country\": \"originCountry\", \"project_id\": \"projectId\", \"resource_key\": \"resourceKey\", \"size\": 4, \"source_system\": {\"mapKey\": \"anyValue\"}, \"tags\": [\"tags\"], \"usage\": {\"access_count\": 11, \"last_access_time\": \"2019-01-01T12:00:00.000Z\", \"last_accessor_id\": \"lastAccessorId\", \"last_modification_time\": \"2019-01-01T12:00:00.000Z\", \"last_modifier_id\": \"lastModifierId\"}}}";
    String datastageFlowsGetPath = "/v3/data_intg_flows/testString";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json;charset=utf-8")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the DatastageFlowsGetOptions model
    DatastageFlowsGetOptions datastageFlowsGetOptionsModel = new DatastageFlowsGetOptions.Builder()
    .dataIntgFlowId("testString")
    .catalogId("testString")
    .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<DataIntgFlowJson> response = datastageService.datastageFlowsGet(datastageFlowsGetOptionsModel).execute();
    assertNotNull(response);
    DataIntgFlowJson responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("catalog_id"), "testString");
    assertEquals(query.get("project_id"), "bd0dbbfd-810d-4f0e-b0a9-228c328a8e23");
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, datastageFlowsGetPath);
  }

  // Test the datastageFlowsGet operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDatastageFlowsGetNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    datastageService.datastageFlowsGet(null).execute();
  }

  @Test
  public void testDatastageFlowsUpdateWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"attachments\": [\"anyValue\"], \"entity\": {\"data_intg_flow\": {\"mapKey\": \"anyValue\"}, \"description\": \"description\", \"lock\": {\"entity\": {\"data_intg_flow_id\": \"dataIntgFlowId\", \"requester\": \"requester\"}, \"metadata\": {\"alive\": false}}, \"name\": \"name\", \"rov\": {\"members\": [\"members\"], \"mode\": 4}, \"sub_type\": \"subType\"}, \"metadata\": {\"asset_id\": \"assetId\", \"asset_type\": \"assetType\", \"catalog_id\": \"catalogId\", \"create_time\": \"2019-01-01T12:00:00.000Z\", \"creator_id\": \"creatorId\", \"description\": \"description\", \"href\": \"href\", \"name\": \"name\", \"origin_country\": \"originCountry\", \"project_id\": \"projectId\", \"resource_key\": \"resourceKey\", \"size\": 4, \"source_system\": {\"mapKey\": \"anyValue\"}, \"tags\": [\"tags\"], \"usage\": {\"access_count\": 11, \"last_access_time\": \"2019-01-01T12:00:00.000Z\", \"last_accessor_id\": \"lastAccessorId\", \"last_modification_time\": \"2019-01-01T12:00:00.000Z\", \"last_modifier_id\": \"lastModifierId\"}}}";
    String datastageFlowsUpdatePath = "/v3/data_intg_flows/testString";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json;charset=utf-8")
    .setResponseCode(201)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the Pipelines model
    Pipelines pipelinesModel = new Pipelines.Builder()
    .appData(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .description("A test DataStage flow")
    .id("fa1b859a-d592-474d-b56c-2137e4efa4bc")
    .nodes(new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")))
    .runtimeRef("pxOsh")
    .build();

    // Construct an instance of the PipelineJson model
    PipelineJson pipelineJsonModel = new PipelineJson.Builder()
    .appData(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .docType("pipeline")
    .externalParamsets(new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")))
    .id("84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff")
    .jsonSchema("http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json")
    .parameters(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .pipelines(new java.util.ArrayList<Pipelines>(java.util.Arrays.asList(pipelinesModel)))
    .primaryPipeline("fa1b859a-d592-474d-b56c-2137e4efa4bc")
    .runtimes(new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")))
    .schemas(new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")))
    .version("3.0")
    .build();

    // Construct an instance of the DatastageFlowsUpdateOptions model
    DatastageFlowsUpdateOptions datastageFlowsUpdateOptionsModel = new DatastageFlowsUpdateOptions.Builder()
    .dataIntgFlowId("testString")
    .dataIntgFlowName("testString")
    .pipelineFlows(pipelineJsonModel)
    .catalogId("testString")
    .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<DataIntgFlow> response = datastageService.datastageFlowsUpdate(datastageFlowsUpdateOptionsModel).execute();
    assertNotNull(response);
    DataIntgFlow responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PUT");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("data_intg_flow_name"), "testString");
    assertEquals(query.get("catalog_id"), "testString");
    assertEquals(query.get("project_id"), "bd0dbbfd-810d-4f0e-b0a9-228c328a8e23");
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, datastageFlowsUpdatePath);
  }

  // Test the datastageFlowsUpdate operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDatastageFlowsUpdateNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    datastageService.datastageFlowsUpdate(null).execute();
  }

  @Test
  public void testDatastageFlowsCloneWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"attachments\": [\"anyValue\"], \"entity\": {\"data_intg_flow\": {\"mapKey\": \"anyValue\"}, \"description\": \"description\", \"lock\": {\"entity\": {\"data_intg_flow_id\": \"dataIntgFlowId\", \"requester\": \"requester\"}, \"metadata\": {\"alive\": false}}, \"name\": \"name\", \"rov\": {\"members\": [\"members\"], \"mode\": 4}, \"sub_type\": \"subType\"}, \"metadata\": {\"asset_id\": \"assetId\", \"asset_type\": \"assetType\", \"catalog_id\": \"catalogId\", \"create_time\": \"2019-01-01T12:00:00.000Z\", \"creator_id\": \"creatorId\", \"description\": \"description\", \"href\": \"href\", \"name\": \"name\", \"origin_country\": \"originCountry\", \"project_id\": \"projectId\", \"resource_key\": \"resourceKey\", \"size\": 4, \"source_system\": {\"mapKey\": \"anyValue\"}, \"tags\": [\"tags\"], \"usage\": {\"access_count\": 11, \"last_access_time\": \"2019-01-01T12:00:00.000Z\", \"last_accessor_id\": \"lastAccessorId\", \"last_modification_time\": \"2019-01-01T12:00:00.000Z\", \"last_modifier_id\": \"lastModifierId\"}}}";
    String datastageFlowsClonePath = "/v3/data_intg_flows/testString/clone";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json;charset=utf-8")
    .setResponseCode(201)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the DatastageFlowsCloneOptions model
    DatastageFlowsCloneOptions datastageFlowsCloneOptionsModel = new DatastageFlowsCloneOptions.Builder()
    .dataIntgFlowId("testString")
    .catalogId("testString")
    .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<DataIntgFlow> response = datastageService.datastageFlowsClone(datastageFlowsCloneOptionsModel).execute();
    assertNotNull(response);
    DataIntgFlow responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("catalog_id"), "testString");
    assertEquals(query.get("project_id"), "bd0dbbfd-810d-4f0e-b0a9-228c328a8e23");
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, datastageFlowsClonePath);
  }

  // Test the datastageFlowsClone operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDatastageFlowsCloneNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    datastageService.datastageFlowsClone(null).execute();
  }

  @Test
  public void testDatastageFlowsCompileWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"message\": {\"mapKey\": \"anyValue\"}, \"type\": \"type\"}";
    String datastageFlowsCompilePath = "/v3/ds_codegen/compile/testString";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json;charset=utf-8")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the DatastageFlowsCompileOptions model
    DatastageFlowsCompileOptions datastageFlowsCompileOptionsModel = new DatastageFlowsCompileOptions.Builder()
    .dataIntgFlowId("testString")
    .catalogId("testString")
    .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
    .runtimeType("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<FlowCompileResponse> response = datastageService.datastageFlowsCompile(datastageFlowsCompileOptionsModel).execute();
    assertNotNull(response);
    FlowCompileResponse responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("catalog_id"), "testString");
    assertEquals(query.get("project_id"), "bd0dbbfd-810d-4f0e-b0a9-228c328a8e23");
    assertEquals(query.get("runtime_type"), "testString");
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, datastageFlowsCompilePath);
  }

  // Test the datastageFlowsCompile operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDatastageFlowsCompileNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    datastageService.datastageFlowsCompile(null).execute();
  }

  @Test
  public void testMigrationCreateWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"entity\": {\"cancelled_by\": \"user1@company1.com\", \"conflict_resolution\": \"conflictResolution\", \"end_time\": \"2019-01-01T12:00:00.000Z\", \"import_data_flows\": [{\"conflict_resolution_status\": \"import_flow_renamed\", \"end_time\": \"2019-01-01T12:00:00.000Z\", \"errors\": [{\"description\": \"description\", \"name\": \"name\", \"type\": \"unsupported_stage_type\"}], \"id\": \"ccfdbbfd-810d-4f0e-b0a9-228c328a0136\", \"job_id\": \"ccfaaafd-810d-4f0e-b0a9-228c328a0136\", \"job_name\": \"Aggregator12_DataStage_1\", \"job_type\": \"px_job\", \"name\": \"cancel-reservation-job\", \"original_name\": \"cancel-reservation-job\", \"ref_asset_id\": \"ccfdbbfd-810d-4f0e-b0a9-228c328a0136\", \"status\": \"completed\", \"type\": \"px_job\", \"warnings\": [{\"description\": \"description\", \"name\": \"name\", \"type\": \"unreleased_stage_type\"}]}], \"name\": \"seat-reservation-jobs\", \"on_failure\": \"onFailure\", \"remaining_time\": 13, \"start_time\": \"2019-01-01T12:00:00.000Z\", \"status\": \"in_progress\", \"tally\": {\"connections_total\": 16, \"deprecated\": 10, \"failed\": 6, \"imported\": 8, \"parameter_sets_total\": 18, \"pending\": 7, \"px_containers_total\": 17, \"renamed\": 7, \"replaced\": 8, \"sequence_jobs_total\": 17, \"skipped\": 7, \"table_definitions_total\": 21, \"total\": 5, \"unsupported\": 11}}, \"metadata\": {\"catalog_id\": \"catalogId\", \"created_at\": \"2019-01-01T12:00:00.000Z\", \"created_by\": \"user1@company1.com\", \"id\": \"id\", \"modified_at\": \"2019-01-01T12:00:00.000Z\", \"name\": \"name\", \"project_id\": \"projectId\", \"project_name\": \"projectName\", \"url\": \"url\"}}";
    String migrationCreatePath = "/v3/migration/isx_imports";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json;charset=utf-8")
    .setResponseCode(202)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the MigrationCreateOptions model
    MigrationCreateOptions migrationCreateOptionsModel = new MigrationCreateOptions.Builder()
    .body(TestUtilities.createMockStream("This is a mock file."))
    .catalogId("testString")
    .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
    .onFailure("continue")
    .conflictResolution("rename")
    .attachmentType("isx")
    .fileName("myFlows.isx")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<ImportResponse> response = datastageService.migrationCreate(migrationCreateOptionsModel).execute();
    assertNotNull(response);
    ImportResponse responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("catalog_id"), "testString");
    assertEquals(query.get("project_id"), "bd0dbbfd-810d-4f0e-b0a9-228c328a8e23");
    assertEquals(query.get("on_failure"), "continue");
    assertEquals(query.get("conflict_resolution"), "rename");
    assertEquals(query.get("attachment_type"), "isx");
    assertEquals(query.get("file_name"), "myFlows.isx");
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, migrationCreatePath);
  }

  // Test the migrationCreate operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testMigrationCreateNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    datastageService.migrationCreate(null).execute();
  }

  @Test
  public void testMigrationDeleteWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "";
    String migrationDeletePath = "/v3/migration/isx_imports/cc6dbbfd-810d-4f0e-b0a9-228c328aff29";

    server.enqueue(new MockResponse()
    .setResponseCode(204)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the MigrationDeleteOptions model
    MigrationDeleteOptions migrationDeleteOptionsModel = new MigrationDeleteOptions.Builder()
    .importId("cc6dbbfd-810d-4f0e-b0a9-228c328aff29")
    .catalogId("testString")
    .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Void> response = datastageService.migrationDelete(migrationDeleteOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    // Response does not have a return type. Check that the result is null.
    assertNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("catalog_id"), "testString");
    assertEquals(query.get("project_id"), "bd0dbbfd-810d-4f0e-b0a9-228c328a8e23");
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, migrationDeletePath);
  }

  // Test the migrationDelete operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testMigrationDeleteNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    datastageService.migrationDelete(null).execute();
  }

  @Test
  public void testMigrationGetWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"entity\": {\"cancelled_by\": \"user1@company1.com\", \"conflict_resolution\": \"conflictResolution\", \"end_time\": \"2019-01-01T12:00:00.000Z\", \"import_data_flows\": [{\"conflict_resolution_status\": \"import_flow_renamed\", \"end_time\": \"2019-01-01T12:00:00.000Z\", \"errors\": [{\"description\": \"description\", \"name\": \"name\", \"type\": \"unsupported_stage_type\"}], \"id\": \"ccfdbbfd-810d-4f0e-b0a9-228c328a0136\", \"job_id\": \"ccfaaafd-810d-4f0e-b0a9-228c328a0136\", \"job_name\": \"Aggregator12_DataStage_1\", \"job_type\": \"px_job\", \"name\": \"cancel-reservation-job\", \"original_name\": \"cancel-reservation-job\", \"ref_asset_id\": \"ccfdbbfd-810d-4f0e-b0a9-228c328a0136\", \"status\": \"completed\", \"type\": \"px_job\", \"warnings\": [{\"description\": \"description\", \"name\": \"name\", \"type\": \"unreleased_stage_type\"}]}], \"name\": \"seat-reservation-jobs\", \"on_failure\": \"onFailure\", \"remaining_time\": 13, \"start_time\": \"2019-01-01T12:00:00.000Z\", \"status\": \"in_progress\", \"tally\": {\"connections_total\": 16, \"deprecated\": 10, \"failed\": 6, \"imported\": 8, \"parameter_sets_total\": 18, \"pending\": 7, \"px_containers_total\": 17, \"renamed\": 7, \"replaced\": 8, \"sequence_jobs_total\": 17, \"skipped\": 7, \"table_definitions_total\": 21, \"total\": 5, \"unsupported\": 11}}, \"metadata\": {\"catalog_id\": \"catalogId\", \"created_at\": \"2019-01-01T12:00:00.000Z\", \"created_by\": \"user1@company1.com\", \"id\": \"id\", \"modified_at\": \"2019-01-01T12:00:00.000Z\", \"name\": \"name\", \"project_id\": \"projectId\", \"project_name\": \"projectName\", \"url\": \"url\"}}";
    String migrationGetPath = "/v3/migration/isx_imports/testString";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json;charset=utf-8")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the MigrationGetOptions model
    MigrationGetOptions migrationGetOptionsModel = new MigrationGetOptions.Builder()
    .importId("testString")
    .catalogId("testString")
    .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<ImportResponse> response = datastageService.migrationGet(migrationGetOptionsModel).execute();
    assertNotNull(response);
    ImportResponse responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("catalog_id"), "testString");
    assertEquals(query.get("project_id"), "bd0dbbfd-810d-4f0e-b0a9-228c328a8e23");
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, migrationGetPath);
  }

  // Test the migrationGet operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testMigrationGetNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    datastageService.migrationGet(null).execute();
  }

  /** Initialize the server */
  @BeforeMethod
  public void setUpMockServer() {
    try {
        server = new MockWebServer();
        // register handler
        server.start();
        }
    catch (IOException err) {
        fail("Failed to instantiate mock web server");
    }
  }

  @AfterMethod
  public void tearDownMockServer() throws IOException {
    server.shutdown();
    datastageService = null;
  }
}