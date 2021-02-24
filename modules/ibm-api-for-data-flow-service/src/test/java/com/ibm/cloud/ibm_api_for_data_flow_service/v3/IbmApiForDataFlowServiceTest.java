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
package com.ibm.cloud.ibm_api_for_data_flow_service.v3;

import com.ibm.cloud.ibm_api_for_data_flow_service.v3.IbmApiForDataFlowService;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.AssetEntityROV;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.AssetSystemMetadata;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.AssetSystemMetadataUsage;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DataFlowPagedCollection;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DataImportError;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DataIntgFlow;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DataIntgFlowEntity;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DataIntgFlowLock;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DataIntgFlowLockEntity;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DataIntgFlowLockMetadata;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DatastageFlowsCloneOptions;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DatastageFlowsCompileOptions;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DatastageFlowsCreateOptions;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DatastageFlowsDeleteOptions;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DatastageFlowsGetOptions;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DatastageFlowsListOptions;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.DatastageFlowsUpdateOptions;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.FlowCompileResponse;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.HrefModel;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.ImportCount;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.ImportFlow;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.ImportFlowWarning;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.ImportResponse;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.ImportResponseEntity;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.ImportResponseMetadata;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.MigrationCreateOptions;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.MigrationDeleteOptions;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.MigrationGetOptions;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.PipelineJson;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.Pipelines;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.utils.TestUtilities;
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
 * Unit test class for the IbmApiForDataFlowService service.
 */
@PrepareForTest({ EnvironmentUtils.class })
@PowerMockIgnore({"javax.net.ssl.*", "org.mockito.*"})
public class IbmApiForDataFlowServiceTest extends PowerMockTestCase {

  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  protected MockWebServer server;
  protected IbmApiForDataFlowService ibmApiForDataFlowServiceService;

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

    ibmApiForDataFlowServiceService = IbmApiForDataFlowService.newInstance(serviceName);
    String url = server.url("/").toString();
    ibmApiForDataFlowServiceService.setServiceUrl(url);
  }

  /**
  * Negative Test - construct the service with a null authenticator.
  */
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testConstructorWithNullAuthenticator() throws Throwable {
    final String serviceName = "testService";

    new IbmApiForDataFlowService(serviceName, null);
  }

  @Test
  public void testDatastageFlowsListWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"data_flows\": [{\"metadata\": {\"asset_id\": \"assetId\", \"asset_type\": \"assetType\", \"catalog_id\": \"catalogId\", \"create_time\": \"2019-01-01T12:00:00.000Z\", \"creator_id\": \"creatorId\", \"href\": \"href\", \"name\": \"name\", \"origin_country\": \"originCountry\", \"size\": 4, \"project_id\": \"projectId\", \"resource_key\": \"resourceKey\", \"description\": \"description\", \"tags\": [\"tags\"], \"source_system\": {\"mapKey\": \"anyValue\"}, \"usage\": {\"last_modification_time\": \"2019-01-01T12:00:00.000Z\", \"last_modifier_id\": \"lastModifierId\", \"last_access_time\": \"2019-01-01T12:00:00.000Z\", \"last_accessor_id\": \"lastAccessorId\", \"access_count\": 11}}, \"entity\": {\"data_intg_flow\": {\"mapKey\": \"anyValue\"}, \"lock\": {\"metadata\": {\"alive\": false}, \"entity\": {\"data_intg_flow_id\": \"dataIntgFlowId\", \"requester\": \"requester\"}}, \"description\": \"description\", \"name\": \"name\", \"rov\": {\"mode\": 4, \"members\": [\"members\"]}, \"sub_type\": \"subType\"}, \"attachments\": {\"mapKey\": \"anyValue\"}}], \"first\": {\"href\": \"href\"}, \"prev\": {\"href\": \"href\"}, \"next\": {\"href\": \"href\"}, \"last\": {\"href\": \"href\"}, \"limit\": 5, \"total_count\": 10}";
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
    Response<DataFlowPagedCollection> response = ibmApiForDataFlowServiceService.datastageFlowsList(datastageFlowsListOptionsModel).execute();
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
    String mockResponseBody = "{\"metadata\": {\"asset_id\": \"assetId\", \"asset_type\": \"assetType\", \"catalog_id\": \"catalogId\", \"create_time\": \"2019-01-01T12:00:00.000Z\", \"creator_id\": \"creatorId\", \"href\": \"href\", \"name\": \"name\", \"origin_country\": \"originCountry\", \"size\": 4, \"project_id\": \"projectId\", \"resource_key\": \"resourceKey\", \"description\": \"description\", \"tags\": [\"tags\"], \"source_system\": {\"mapKey\": \"anyValue\"}, \"usage\": {\"last_modification_time\": \"2019-01-01T12:00:00.000Z\", \"last_modifier_id\": \"lastModifierId\", \"last_access_time\": \"2019-01-01T12:00:00.000Z\", \"last_accessor_id\": \"lastAccessorId\", \"access_count\": 11}}, \"entity\": {\"data_intg_flow\": {\"mapKey\": \"anyValue\"}, \"lock\": {\"metadata\": {\"alive\": false}, \"entity\": {\"data_intg_flow_id\": \"dataIntgFlowId\", \"requester\": \"requester\"}}, \"description\": \"description\", \"name\": \"name\", \"rov\": {\"mode\": 4, \"members\": [\"members\"]}, \"sub_type\": \"subType\"}, \"attachments\": {\"mapKey\": \"anyValue\"}}";
    String datastageFlowsCreatePath = "/v3/data_intg_flows";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json;charset=utf-8")
    .setResponseCode(201)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the Pipelines model
    Pipelines pipelinesModel = new Pipelines.Builder()
    .id("fa1b859a-d592-474d-b56c-2137e4efa4bc")
    .description("A test DataStage flow")
    .runtimeRef("pxOsh")
    .nodes(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .appData(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .build();

    // Construct an instance of the PipelineJson model
    PipelineJson pipelineJsonModel = new PipelineJson.Builder()
    .docType("pipeline")
    .version("3.0")
    .jsonSchema("http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json")
    .id("84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff")
    .primaryPipeline("fa1b859a-d592-474d-b56c-2137e4efa4bc")
    .pipelines(new java.util.ArrayList<Pipelines>(java.util.Arrays.asList(pipelinesModel)))
    .schemas(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .runtimes(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .appData(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
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
    Response<DataIntgFlow> response = ibmApiForDataFlowServiceService.datastageFlowsCreate(datastageFlowsCreateOptionsModel).execute();
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
    ibmApiForDataFlowServiceService.datastageFlowsCreate(null).execute();
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
    Response<Void> response = ibmApiForDataFlowServiceService.datastageFlowsDelete(datastageFlowsDeleteOptionsModel).execute();
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
    ibmApiForDataFlowServiceService.datastageFlowsDelete(null).execute();
  }

  @Test
  public void testDatastageFlowsGetWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"metadata\": {\"asset_id\": \"assetId\", \"asset_type\": \"assetType\", \"catalog_id\": \"catalogId\", \"create_time\": \"2019-01-01T12:00:00.000Z\", \"creator_id\": \"creatorId\", \"href\": \"href\", \"name\": \"name\", \"origin_country\": \"originCountry\", \"size\": 4, \"project_id\": \"projectId\", \"resource_key\": \"resourceKey\", \"description\": \"description\", \"tags\": [\"tags\"], \"source_system\": {\"mapKey\": \"anyValue\"}, \"usage\": {\"last_modification_time\": \"2019-01-01T12:00:00.000Z\", \"last_modifier_id\": \"lastModifierId\", \"last_access_time\": \"2019-01-01T12:00:00.000Z\", \"last_accessor_id\": \"lastAccessorId\", \"access_count\": 11}}, \"entity\": {\"data_intg_flow\": {\"mapKey\": \"anyValue\"}, \"lock\": {\"metadata\": {\"alive\": false}, \"entity\": {\"data_intg_flow_id\": \"dataIntgFlowId\", \"requester\": \"requester\"}}, \"description\": \"description\", \"name\": \"name\", \"rov\": {\"mode\": 4, \"members\": [\"members\"]}, \"sub_type\": \"subType\"}, \"attachments\": {\"mapKey\": \"anyValue\"}}";
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
    Response<DataIntgFlow> response = ibmApiForDataFlowServiceService.datastageFlowsGet(datastageFlowsGetOptionsModel).execute();
    assertNotNull(response);
    DataIntgFlow responseObj = response.getResult();
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
    ibmApiForDataFlowServiceService.datastageFlowsGet(null).execute();
  }

  @Test
  public void testDatastageFlowsUpdateWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"metadata\": {\"asset_id\": \"assetId\", \"asset_type\": \"assetType\", \"catalog_id\": \"catalogId\", \"create_time\": \"2019-01-01T12:00:00.000Z\", \"creator_id\": \"creatorId\", \"href\": \"href\", \"name\": \"name\", \"origin_country\": \"originCountry\", \"size\": 4, \"project_id\": \"projectId\", \"resource_key\": \"resourceKey\", \"description\": \"description\", \"tags\": [\"tags\"], \"source_system\": {\"mapKey\": \"anyValue\"}, \"usage\": {\"last_modification_time\": \"2019-01-01T12:00:00.000Z\", \"last_modifier_id\": \"lastModifierId\", \"last_access_time\": \"2019-01-01T12:00:00.000Z\", \"last_accessor_id\": \"lastAccessorId\", \"access_count\": 11}}, \"entity\": {\"data_intg_flow\": {\"mapKey\": \"anyValue\"}, \"lock\": {\"metadata\": {\"alive\": false}, \"entity\": {\"data_intg_flow_id\": \"dataIntgFlowId\", \"requester\": \"requester\"}}, \"description\": \"description\", \"name\": \"name\", \"rov\": {\"mode\": 4, \"members\": [\"members\"]}, \"sub_type\": \"subType\"}, \"attachments\": {\"mapKey\": \"anyValue\"}}";
    String datastageFlowsUpdatePath = "/v3/data_intg_flows/testString";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json;charset=utf-8")
    .setResponseCode(201)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the Pipelines model
    Pipelines pipelinesModel = new Pipelines.Builder()
    .id("fa1b859a-d592-474d-b56c-2137e4efa4bc")
    .description("A test DataStage flow")
    .runtimeRef("pxOsh")
    .nodes(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .appData(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .build();

    // Construct an instance of the PipelineJson model
    PipelineJson pipelineJsonModel = new PipelineJson.Builder()
    .docType("pipeline")
    .version("3.0")
    .jsonSchema("http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json")
    .id("84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff")
    .primaryPipeline("fa1b859a-d592-474d-b56c-2137e4efa4bc")
    .pipelines(new java.util.ArrayList<Pipelines>(java.util.Arrays.asList(pipelinesModel)))
    .schemas(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .runtimes(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
    .appData(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
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
    Response<DataIntgFlow> response = ibmApiForDataFlowServiceService.datastageFlowsUpdate(datastageFlowsUpdateOptionsModel).execute();
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
    ibmApiForDataFlowServiceService.datastageFlowsUpdate(null).execute();
  }

  @Test
  public void testDatastageFlowsCloneWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"metadata\": {\"asset_id\": \"assetId\", \"asset_type\": \"assetType\", \"catalog_id\": \"catalogId\", \"create_time\": \"2019-01-01T12:00:00.000Z\", \"creator_id\": \"creatorId\", \"href\": \"href\", \"name\": \"name\", \"origin_country\": \"originCountry\", \"size\": 4, \"project_id\": \"projectId\", \"resource_key\": \"resourceKey\", \"description\": \"description\", \"tags\": [\"tags\"], \"source_system\": {\"mapKey\": \"anyValue\"}, \"usage\": {\"last_modification_time\": \"2019-01-01T12:00:00.000Z\", \"last_modifier_id\": \"lastModifierId\", \"last_access_time\": \"2019-01-01T12:00:00.000Z\", \"last_accessor_id\": \"lastAccessorId\", \"access_count\": 11}}, \"entity\": {\"data_intg_flow\": {\"mapKey\": \"anyValue\"}, \"lock\": {\"metadata\": {\"alive\": false}, \"entity\": {\"data_intg_flow_id\": \"dataIntgFlowId\", \"requester\": \"requester\"}}, \"description\": \"description\", \"name\": \"name\", \"rov\": {\"mode\": 4, \"members\": [\"members\"]}, \"sub_type\": \"subType\"}, \"attachments\": {\"mapKey\": \"anyValue\"}}";
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
    Response<DataIntgFlow> response = ibmApiForDataFlowServiceService.datastageFlowsClone(datastageFlowsCloneOptionsModel).execute();
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
    ibmApiForDataFlowServiceService.datastageFlowsClone(null).execute();
  }

  @Test
  public void testDatastageFlowsCompileWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"type\": \"type\", \"message\": {\"mapKey\": \"anyValue\"}}";
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
    Response<FlowCompileResponse> response = ibmApiForDataFlowServiceService.datastageFlowsCompile(datastageFlowsCompileOptionsModel).execute();
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
    ibmApiForDataFlowServiceService.datastageFlowsCompile(null).execute();
  }

  @Test
  public void testMigrationCreateWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"metadata\": {\"id\": \"id\", \"name\": \"name\", \"url\": \"url\", \"project_id\": \"projectId\", \"project_name\": \"projectName\", \"catalog_id\": \"catalogId\", \"created_at\": \"2019-01-01T12:00:00.000Z\", \"modified_at\": \"2019-01-01T12:00:00.000Z\", \"created_by\": \"user1@company1.com\"}, \"entity\": {\"name\": \"seat-reservation-jobs\", \"status\": \"in_progress\", \"start_time\": \"2019-01-01T12:00:00.000Z\", \"end_time\": \"2019-01-01T12:00:00.000Z\", \"remaining_time\": 13, \"cancelled_by\": \"user1@company1.com\", \"on_failure\": \"onFailure\", \"conflict_resolution\": \"conflictResolution\", \"import_data_flows\": [{\"id\": \"ccfdbbfd-810d-4f0e-b0a9-228c328a0136\", \"ref_asset_id\": \"ccfdbbfd-810d-4f0e-b0a9-228c328a0136\", \"name\": \"cancel-reservation-job\", \"original_name\": \"cancel-reservation-job\", \"type\": \"px_job\", \"job_type\": \"px_job\", \"job_id\": \"ccfaaafd-810d-4f0e-b0a9-228c328a0136\", \"job_name\": \"Aggregator12_DataStage_1\", \"status\": \"completed\", \"conflict_resolution_status\": \"import_flow_renamed\", \"end_time\": \"2019-01-01T12:00:00.000Z\", \"errors\": [{\"type\": \"unsupported_stage_type\", \"name\": \"name\", \"description\": \"description\"}], \"warnings\": [{\"type\": \"unreleased_stage_type\", \"name\": \"name\", \"description\": \"description\"}]}], \"tally\": {\"total\": 5, \"imported\": 8, \"renamed\": 7, \"skipped\": 7, \"replaced\": 8, \"failed\": 6, \"deprecated\": 10, \"unsupported\": 11, \"pending\": 7, \"connections_total\": 16, \"parameter_sets_total\": 18, \"table_definitions_total\": 21}}}";
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
    Response<ImportResponse> response = ibmApiForDataFlowServiceService.migrationCreate(migrationCreateOptionsModel).execute();
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
    ibmApiForDataFlowServiceService.migrationCreate(null).execute();
  }

  @Test
  public void testMigrationGetWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"metadata\": {\"id\": \"id\", \"name\": \"name\", \"url\": \"url\", \"project_id\": \"projectId\", \"project_name\": \"projectName\", \"catalog_id\": \"catalogId\", \"created_at\": \"2019-01-01T12:00:00.000Z\", \"modified_at\": \"2019-01-01T12:00:00.000Z\", \"created_by\": \"user1@company1.com\"}, \"entity\": {\"name\": \"seat-reservation-jobs\", \"status\": \"in_progress\", \"start_time\": \"2019-01-01T12:00:00.000Z\", \"end_time\": \"2019-01-01T12:00:00.000Z\", \"remaining_time\": 13, \"cancelled_by\": \"user1@company1.com\", \"on_failure\": \"onFailure\", \"conflict_resolution\": \"conflictResolution\", \"import_data_flows\": [{\"id\": \"ccfdbbfd-810d-4f0e-b0a9-228c328a0136\", \"ref_asset_id\": \"ccfdbbfd-810d-4f0e-b0a9-228c328a0136\", \"name\": \"cancel-reservation-job\", \"original_name\": \"cancel-reservation-job\", \"type\": \"px_job\", \"job_type\": \"px_job\", \"job_id\": \"ccfaaafd-810d-4f0e-b0a9-228c328a0136\", \"job_name\": \"Aggregator12_DataStage_1\", \"status\": \"completed\", \"conflict_resolution_status\": \"import_flow_renamed\", \"end_time\": \"2019-01-01T12:00:00.000Z\", \"errors\": [{\"type\": \"unsupported_stage_type\", \"name\": \"name\", \"description\": \"description\"}], \"warnings\": [{\"type\": \"unreleased_stage_type\", \"name\": \"name\", \"description\": \"description\"}]}], \"tally\": {\"total\": 5, \"imported\": 8, \"renamed\": 7, \"skipped\": 7, \"replaced\": 8, \"failed\": 6, \"deprecated\": 10, \"unsupported\": 11, \"pending\": 7, \"connections_total\": 16, \"parameter_sets_total\": 18, \"table_definitions_total\": 21}}}";
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
    Response<ImportResponse> response = ibmApiForDataFlowServiceService.migrationGet(migrationGetOptionsModel).execute();
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
    ibmApiForDataFlowServiceService.migrationGet(null).execute();
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
    Response<Void> response = ibmApiForDataFlowServiceService.migrationDelete(migrationDeleteOptionsModel).execute();
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
    ibmApiForDataFlowServiceService.migrationDelete(null).execute();
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
    ibmApiForDataFlowServiceService = null;
  }
}