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

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ibm.cloud.datastage.test.SdkIntegrationTestBase;
import com.ibm.cloud.datastage.v3.model.DataFlowPagedCollection;
import com.ibm.cloud.datastage.v3.model.DataIntgFlow;
import com.ibm.cloud.datastage.v3.model.DataIntgFlowJson;
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
import com.ibm.cloud.datastage.v3.model.PipelineJson;
import com.ibm.cloud.datastage.v3.model.Pipelines;
import com.ibm.cloud.datastage.v3.utils.TestUtilities;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import com.ibm.cloud.sdk.core.util.CredentialUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.IOUtils;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.lang.Thread.sleep;
import static org.testng.Assert.*;

/**
 * Integration test class for the Datastage service.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatastageIT extends SdkIntegrationTestBase {
  public Datastage service = null;
  public static Map<String, String> config = null;
  private static final String IN_PROGRESS = "in_progress";
  private static String PROJECT_ID = null;
  private static String DATASTAGE_FLOW_ID = null;
  private static String DATASTAGE_FLOW_ID_CLONE = null;
  private static String DATASTAGE_FLOW_ID_MIGRATION = null;
  private static List<Object> nodes = null;
  private static Object appData = null;
  private static List<Object> schemas = null;
  private static List<Object> schemasUpdated = null;
  private InputStream rowGenIsx = null;
  private static String importId = null;
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();
  /**
   * This method provides our config filename to the base class.
   */

  public String getConfigFilename() {
    return "../../datastage_v3.env";
  }

  @BeforeClass
  public void constructService() throws IOException {
    // Ask super if we should skip the tests.
    if (skipTests()) {
      return;
    }

    service = Datastage.newInstance();
    assertNotNull(service);
    assertNotNull(service.getServiceUrl());

    // Load up our test-specific config properties.
    config = CredentialUtils.getServiceProperties(Datastage.DEFAULT_SERVICE_NAME);
    PROJECT_ID = config.get("PROJECT_ID");
    assertNotNull(config);
    assertFalse(config.isEmpty());
    assertEquals(service.getServiceUrl(), config.get("URL"));

    ObjectMapper mapper = new ObjectMapper();
    InputStream nodeStream = this.getClass().getClassLoader().getResourceAsStream("nodes.json");
    InputStream appDataStream = this.getClass().getClassLoader().getResourceAsStream("appdata.json");
    InputStream schemaStream = this.getClass().getClassLoader().getResourceAsStream("schemas.json");
    InputStream schemaStreamForUpdate = this.getClass().getClassLoader().getResourceAsStream("schemas_updated.json");
    rowGenIsx = this.getClass().getClassLoader().getResourceAsStream("rowgen_peek.isx");
    String nodesStr = IOUtils.toString(nodeStream, StandardCharsets.UTF_8);
    String appDataStr = IOUtils.toString(appDataStream, StandardCharsets.UTF_8);
    String schemaStr = IOUtils.toString(schemaStream, StandardCharsets.UTF_8);
    String schemaStrForUpdate = IOUtils.toString(schemaStreamForUpdate, StandardCharsets.UTF_8);
    nodes = mapper.readValue(nodesStr, new TypeReference<List<Object>>(){});
    appData = mapper.readValue(appDataStr, Object.class);
    schemas = mapper.readValue(schemaStr, new TypeReference<List<Object>>(){});
    schemasUpdated = mapper.readValue(schemaStrForUpdate, new TypeReference<List<Object>>(){});
    System.out.println("Setup complete.");
  }

  @Test
  public void test01DatastageFlowsList() throws Exception {
    try {
      DatastageFlowsListOptions datastageFlowsListOptions = new DatastageFlowsListOptions.Builder()
      .projectId(PROJECT_ID)
      .limit(Long.valueOf("100"))
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
  public void test02DatastageFlowsCreate() throws Exception {
    try {
      Pipelines pipelinesModel = new Pipelines.Builder()
      .id("fa1b859a-d592-474d-b56c-2137e4efa4bc")
      .description("A test DataStage flow")
      .runtimeRef("pxOsh")
      .nodes(nodes)
      .appData((Map<String, Object>) appData)
      .build();

      PipelineJson pipelineJsonModel = new PipelineJson.Builder()
      .docType("pipeline")
      .version("3.0")
      .jsonSchema("http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json")
      .id("84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff")
      .primaryPipeline("fa1b859a-d592-474d-b56c-2137e4efa4bc")
      .pipelines(new java.util.ArrayList<Pipelines>(java.util.Arrays.asList(pipelinesModel)))
      .schemas(schemas)
      .build();

      DatastageFlowsCreateOptions datastageFlowsCreateOptions = new DatastageFlowsCreateOptions.Builder()
      .dataIntgFlowName("testString" + UUID.randomUUID().toString())
      .pipelineFlows(pipelineJsonModel)
      .projectId(PROJECT_ID)
      .assetCategory("system")
      .build();
      // Invoke operation
      Response<DataIntgFlow> response = service.datastageFlowsCreate(datastageFlowsCreateOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 201);

      DataIntgFlow dataIntgFlowResult = response.getResult();

      assertNotNull(dataIntgFlowResult);
      assertNotNull(dataIntgFlowResult.getMetadata());
      DATASTAGE_FLOW_ID = dataIntgFlowResult.getMetadata().getAssetId();
      assertNotNull(DATASTAGE_FLOW_ID);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void test03DatastageFlowsGet() throws Exception {
    try {
      DatastageFlowsGetOptions datastageFlowsGetOptions = new DatastageFlowsGetOptions.Builder()
      .dataIntgFlowId(DATASTAGE_FLOW_ID)
      .projectId(PROJECT_ID)
      .build();

      // Invoke operation
      Response<DataIntgFlowJson> response = service.datastageFlowsGet(datastageFlowsGetOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      DataIntgFlowJson dataIntgFlowResult = response.getResult();

      assertNotNull(dataIntgFlowResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void test04DatastageFlowsUpdate() throws Exception {
    try {
      Pipelines pipelinesModel = new Pipelines.Builder()
      .id("fa1b859a-d592-474d-b56c-2137e4efa4bc")
      .description("A test DataStage flow updated")
      .runtimeRef("pxOsh")
      .nodes(nodes)
      .appData((Map<String, Object>) appData)
      .build();

      PipelineJson pipelineJsonModel = new PipelineJson.Builder()
      .docType("pipeline")
      .version("3.0")
      .jsonSchema("http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json")
      .id("84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff")
      .primaryPipeline("fa1b859a-d592-474d-b56c-2137e4efa4bc")
      .pipelines(new java.util.ArrayList<Pipelines>(java.util.Arrays.asList(pipelinesModel)))
      .schemas(schemasUpdated)
      .build();

      DatastageFlowsUpdateOptions datastageFlowsUpdateOptions = new DatastageFlowsUpdateOptions.Builder()
      .dataIntgFlowId(DATASTAGE_FLOW_ID)
      .dataIntgFlowName("testString" + UUID.randomUUID().toString())
      .pipelineFlows(pipelineJsonModel)
      .projectId(PROJECT_ID)
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
  public void test05DatastageFlowsClone() throws Exception {
    try {
      DatastageFlowsCloneOptions datastageFlowsCloneOptions = new DatastageFlowsCloneOptions.Builder()
      .dataIntgFlowId(DATASTAGE_FLOW_ID)
      .projectId(PROJECT_ID)
      .build();

      // Invoke operation
      Response<DataIntgFlow> response = service.datastageFlowsClone(datastageFlowsCloneOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      DataIntgFlow dataIntgFlowResult = response.getResult();

      assertNotNull(dataIntgFlowResult);
      assertNotNull(dataIntgFlowResult.getMetadata());
      DATASTAGE_FLOW_ID_CLONE = dataIntgFlowResult.getMetadata().getAssetId();
      assertNotNull(DATASTAGE_FLOW_ID_CLONE);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void test06DatastageFlowsCompile() throws Exception {
    try {
      DatastageFlowsCompileOptions datastageFlowsCompileOptions = new DatastageFlowsCompileOptions.Builder()
      .dataIntgFlowId(DATASTAGE_FLOW_ID)
      .projectId(PROJECT_ID)
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
  public void test07MigrationCreate() throws Exception {
    try {
      MigrationCreateOptions migrationCreateOptions = new MigrationCreateOptions.Builder()
      .body(rowGenIsx)
      .projectId(PROJECT_ID)
      .onFailure("continue")
      .conflictResolution("rename")
      .attachmentType("isx")
      .fileName("rowgen_peek.isx")
      .build();

      // Invoke operation
      Response<ImportResponse> response = service.migrationCreate(migrationCreateOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 202);

      ImportResponse importResponseResult = response.getResult();

      assertNotNull(importResponseResult);
      assertNotNull(importResponseResult.getMetadata());
      importId = importResponseResult.getMetadata().getId();
      assertNotNull(importId);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void test08MigrationGet() throws Exception {
    String importStatus = IN_PROGRESS;
    while (importStatus.equalsIgnoreCase(IN_PROGRESS)) {
      Thread.sleep(5000);
      try {
        MigrationGetOptions migrationGetOptions = new MigrationGetOptions.Builder()
                .importId(importId)
                .projectId(PROJECT_ID)
                .build();

        // Invoke operation
        Response<ImportResponse> response = service.migrationGet(migrationGetOptions).execute();
        // Validate response
        assertNotNull(response);
        assertEquals(response.getStatusCode(), 200);

        ImportResponse importResponseResult = response.getResult();

        assertNotNull(importResponseResult);
        assertNotNull(importResponseResult.getEntity());
        assertNotNull(importResponseResult.getEntity().getImportDataFlows());
        DATASTAGE_FLOW_ID_MIGRATION = importResponseResult.getEntity().getImportDataFlows().get(0).getId();
        importStatus = importResponseResult.getEntity().getImportDataFlows().get(0).getStatus();
        System.out.println("Import status : " + importStatus);

      } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
                e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
      }
    }
  }

  @Test
  public void test09MigrationDelete() throws Exception {
    try {
      MigrationDeleteOptions migrationDeleteOptions = new MigrationDeleteOptions.Builder()
      .importId(importId)
      .projectId(PROJECT_ID)
      .build();

      // Invoke operation
      Response<Void> response = service.migrationDelete(migrationDeleteOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 202);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void test10DatastageFlowsDelete() throws Exception  {
    String ids[] = new String[] {DATASTAGE_FLOW_ID, DATASTAGE_FLOW_ID_CLONE, DATASTAGE_FLOW_ID_MIGRATION};
    try {
      DatastageFlowsDeleteOptions datastageFlowsDeleteOptions = new DatastageFlowsDeleteOptions.Builder()
              .id(Arrays.asList(ids))
              .projectId(PROJECT_ID)
              .force(true)
              .build();

      // Invoke operation
      Response<Void> response = service.datastageFlowsDelete(datastageFlowsDeleteOptions).execute();

      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 204);
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
