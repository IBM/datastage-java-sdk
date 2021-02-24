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
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import com.ibm.cloud.sdk.core.util.CredentialUtils;
import com.ibm.cloud.sdk.core.util.DateUtils;
import com.ibm.cloud.datastage.test.SdkIntegrationTestBase;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Integration test class for the IbmApiForDataFlowService service.
 */
public class IbmApiForDataFlowServiceIT extends SdkIntegrationTestBase {
  public IbmApiForDataFlowService service = null;
  public static Map<String, String> config = null;
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();
  /**
   * This method provides our config filename to the base class.
   */

  public String getConfigFilename() {
    return "../../ibm_api_for_data_flow_service_v3.env";
  }

  @BeforeClass
  public void constructService() {
    // Ask super if we should skip the tests.
    if (skipTests()) {
      return;
    }

    service = IbmApiForDataFlowService.newInstance();
    assertNotNull(service);
    assertNotNull(service.getServiceUrl());

    // Load up our test-specific config properties.
    config = CredentialUtils.getServiceProperties(IbmApiForDataFlowService.DEFAULT_SERVICE_NAME);
    assertNotNull(config);
    assertFalse(config.isEmpty());
    assertEquals(service.getServiceUrl(), config.get("URL"));

    System.out.println("Setup complete.");
  }

  @Test
  public void testDatastageFlowsList() throws Exception {
    try {
      DatastageFlowsListOptions datastageFlowsListOptions = new DatastageFlowsListOptions.Builder()
      .catalogId("testString")
      .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
      .sort("testString")
      .start("testString")
      .limit(Long.valueOf("100"))
      .entityName("testString")
      .entityDescription("testString")
      .build();

      // Invoke operation
      Response<DataFlowPagedCollection> response = service.datastageFlowsList(datastageFlowsListOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      DataFlowPagedCollection dataFlowPagedCollectionResult = response.getResult();

      assertNotNull(dataFlowPagedCollectionResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void testDatastageFlowsCreate() throws Exception {
    try {
      Pipelines pipelinesModel = new Pipelines.Builder()
      .id("fa1b859a-d592-474d-b56c-2137e4efa4bc")
      .description("A test DataStage flow")
      .runtimeRef("pxOsh")
      .nodes(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
      .appData(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
      .build();

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

      DatastageFlowsCreateOptions datastageFlowsCreateOptions = new DatastageFlowsCreateOptions.Builder()
      .dataIntgFlowName("testString")
      .pipelineFlows(pipelineJsonModel)
      .catalogId("testString")
      .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
      .assetCategory("system")
      .build();

      // Invoke operation
      Response<DataIntgFlow> response = service.datastageFlowsCreate(datastageFlowsCreateOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 201);

      DataIntgFlow dataIntgFlowResult = response.getResult();

      assertNotNull(dataIntgFlowResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void testDatastageFlowsGet() throws Exception {
    try {
      DatastageFlowsGetOptions datastageFlowsGetOptions = new DatastageFlowsGetOptions.Builder()
      .dataIntgFlowId("testString")
      .catalogId("testString")
      .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
      .build();

      // Invoke operation
      Response<DataIntgFlow> response = service.datastageFlowsGet(datastageFlowsGetOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      DataIntgFlow dataIntgFlowResult = response.getResult();

      assertNotNull(dataIntgFlowResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void testDatastageFlowsUpdate() throws Exception {
    try {
      Pipelines pipelinesModel = new Pipelines.Builder()
      .id("fa1b859a-d592-474d-b56c-2137e4efa4bc")
      .description("A test DataStage flow")
      .runtimeRef("pxOsh")
      .nodes(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
      .appData(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
      .build();

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

      DatastageFlowsUpdateOptions datastageFlowsUpdateOptions = new DatastageFlowsUpdateOptions.Builder()
      .dataIntgFlowId("testString")
      .dataIntgFlowName("testString")
      .pipelineFlows(pipelineJsonModel)
      .catalogId("testString")
      .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
      .build();

      // Invoke operation
      Response<DataIntgFlow> response = service.datastageFlowsUpdate(datastageFlowsUpdateOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 201);

      DataIntgFlow dataIntgFlowResult = response.getResult();

      assertNotNull(dataIntgFlowResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void testDatastageFlowsClone() throws Exception {
    try {
      DatastageFlowsCloneOptions datastageFlowsCloneOptions = new DatastageFlowsCloneOptions.Builder()
      .dataIntgFlowId("testString")
      .catalogId("testString")
      .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
      .build();

      // Invoke operation
      Response<DataIntgFlow> response = service.datastageFlowsClone(datastageFlowsCloneOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 201);

      DataIntgFlow dataIntgFlowResult = response.getResult();

      assertNotNull(dataIntgFlowResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void testDatastageFlowsCompile() throws Exception {
    try {
      DatastageFlowsCompileOptions datastageFlowsCompileOptions = new DatastageFlowsCompileOptions.Builder()
      .dataIntgFlowId("testString")
      .catalogId("testString")
      .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
      .runtimeType("testString")
      .build();

      // Invoke operation
      Response<FlowCompileResponse> response = service.datastageFlowsCompile(datastageFlowsCompileOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      FlowCompileResponse flowCompileResponseResult = response.getResult();

      assertNotNull(flowCompileResponseResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void testMigrationCreate() throws Exception {
    try {
      MigrationCreateOptions migrationCreateOptions = new MigrationCreateOptions.Builder()
      .body(TestUtilities.createMockStream("This is a mock file."))
      .catalogId("testString")
      .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
      .onFailure("continue")
      .conflictResolution("rename")
      .attachmentType("isx")
      .fileName("myFlows.isx")
      .build();

      // Invoke operation
      Response<ImportResponse> response = service.migrationCreate(migrationCreateOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 202);

      ImportResponse importResponseResult = response.getResult();

      assertNotNull(importResponseResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void testMigrationGet() throws Exception {
    try {
      MigrationGetOptions migrationGetOptions = new MigrationGetOptions.Builder()
      .importId("testString")
      .catalogId("testString")
      .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
      .build();

      // Invoke operation
      Response<ImportResponse> response = service.migrationGet(migrationGetOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      ImportResponse importResponseResult = response.getResult();

      assertNotNull(importResponseResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void testMigrationDelete() throws Exception {
    try {
      MigrationDeleteOptions migrationDeleteOptions = new MigrationDeleteOptions.Builder()
      .importId("cc6dbbfd-810d-4f0e-b0a9-228c328aff29")
      .catalogId("testString")
      .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
      .build();

      // Invoke operation
      Response<Void> response = service.migrationDelete(migrationDeleteOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 204);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void testDatastageFlowsDelete() throws Exception {
    try {
      DatastageFlowsDeleteOptions datastageFlowsDeleteOptions = new DatastageFlowsDeleteOptions.Builder()
      .id(new java.util.ArrayList<String>(java.util.Arrays.asList("testString")))
      .catalogId("testString")
      .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
      .force(true)
      .build();

      // Invoke operation
      Response<Void> response = service.datastageFlowsDelete(datastageFlowsDeleteOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 202);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @AfterClass
  public void tearDown() {
    // Add any clean up logic here
    System.out.println("Clean up complete.");
  }
 }
