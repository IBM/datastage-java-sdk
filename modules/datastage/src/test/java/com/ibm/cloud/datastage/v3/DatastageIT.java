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
import com.ibm.cloud.datastage.v3.model.CloneDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.CloneDatastageSubflowsOptions;
import com.ibm.cloud.datastage.v3.model.CompileDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.CreateDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.CreateDatastageSubflowsOptions;
import com.ibm.cloud.datastage.v3.model.CreateMigrationOptions;
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
import com.ibm.cloud.datastage.v3.model.DeleteDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.DeleteDatastageSubflowsOptions;
import com.ibm.cloud.datastage.v3.model.DeleteMigrationOptions;
import com.ibm.cloud.datastage.v3.model.FlowCompileResponse;
import com.ibm.cloud.datastage.v3.model.GetDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.GetMigrationOptions;
import com.ibm.cloud.datastage.v3.model.ImportResponse;
import com.ibm.cloud.datastage.v3.model.ListDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.ListDatastageSubflowsOptions;
import com.ibm.cloud.datastage.v3.model.MigrationCreateOptions;
import com.ibm.cloud.datastage.v3.model.MigrationDeleteOptions;
import com.ibm.cloud.datastage.v3.model.MigrationGetOptions;
import com.ibm.cloud.datastage.v3.model.PipelineJson;
import com.ibm.cloud.datastage.v3.model.Pipelines;
import com.ibm.cloud.datastage.v3.model.UpdateDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.UpdateDatastageSubflowsOptions;
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
  private static String DATASTAGE_SUBFLOW_ID = null;
  private static String DATASTAGE_SUBFLOW_ID_CLONE = null;
  private static List<Object> nodes = null;
  private static Object appData = null;
  private static List<Object> schemas = null;
  private static List<Object> schemasUpdated = null;
  private static List<Object> nodesSubFlow = null;
  private static Object appDataSubFlow = null;
  private static List<Object> schemasSubFlow = null;
  private static List<Object> schemasSubFlowUpdated = null;
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

    InputStream nodeSubFlowStream = this.getClass().getClassLoader().getResourceAsStream("nodes_subflow.json");
    InputStream appDataSubFlowStream = this.getClass().getClassLoader().getResourceAsStream("appdata_subflow.json");
    InputStream schemaSubFlowStream = this.getClass().getClassLoader().getResourceAsStream("schemas_subflow.json");
    InputStream schemaSubFlowStreamForUpdate = this.getClass().getClassLoader().getResourceAsStream("schemas_subflow_updated.json");
    String nodesSubFlowStr = IOUtils.toString(nodeSubFlowStream, StandardCharsets.UTF_8);
    String appDataSubFlowStr = IOUtils.toString(appDataSubFlowStream, StandardCharsets.UTF_8);
    String schemaSubFlowStr = IOUtils.toString(schemaSubFlowStream, StandardCharsets.UTF_8);
    String schemaSubFlowStrForUpdate = IOUtils.toString(schemaSubFlowStreamForUpdate, StandardCharsets.UTF_8);
    nodesSubFlow = mapper.readValue(nodesSubFlowStr, new TypeReference<List<Object>>(){});
    appDataSubFlow = mapper.readValue(appDataSubFlowStr, Object.class);
    schemasSubFlow = mapper.readValue(schemaSubFlowStr, new TypeReference<List<Object>>(){});
    schemasSubFlowUpdated = mapper.readValue(schemaSubFlowStrForUpdate, new TypeReference<List<Object>>(){});
    System.out.println("Setup complete.");
  }

  @Test
  public void test01DatastageFlowsList() throws Exception {
    try {
      ListDatastageFlowsOptions datastageFlowsListOptions = new ListDatastageFlowsOptions.Builder()
      .projectId(PROJECT_ID)
      .limit(Long.valueOf("100"))
      .build();

      // Invoke operation
      Response<DataFlowPagedCollection> response = service.listDatastageFlows(datastageFlowsListOptions).execute();
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

      CreateDatastageFlowsOptions datastageFlowsCreateOptions = new CreateDatastageFlowsOptions.Builder()
      .dataIntgFlowName("testIntegrationFlow" + UUID.randomUUID().toString())
      .pipelineFlows(pipelineJsonModel)
      .projectId(PROJECT_ID)
      .assetCategory("system")
      .build();
      // Invoke operation
      Response<DataIntgFlow> response = service.createDatastageFlows(datastageFlowsCreateOptions).execute();
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
      GetDatastageFlowsOptions datastageFlowsGetOptions = new GetDatastageFlowsOptions.Builder()
      .dataIntgFlowId(DATASTAGE_FLOW_ID)
      .projectId(PROJECT_ID)
      .build();

      // Invoke operation
      Response<DataIntgFlowJson> response = service.getDatastageFlows(datastageFlowsGetOptions).execute();
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

      UpdateDatastageFlowsOptions datastageFlowsUpdateOptions = new UpdateDatastageFlowsOptions.Builder()
      .dataIntgFlowId(DATASTAGE_FLOW_ID)
      .dataIntgFlowName("testString" + UUID.randomUUID().toString())
      .pipelineFlows(pipelineJsonModel)
      .projectId(PROJECT_ID)
      .build();
      // Invoke operation
      Response<DataIntgFlow> response = service.updateDatastageFlows(datastageFlowsUpdateOptions).execute();
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
      CloneDatastageFlowsOptions datastageFlowsCloneOptions = new CloneDatastageFlowsOptions.Builder()
      .dataIntgFlowId(DATASTAGE_FLOW_ID)
      .projectId(PROJECT_ID)
      .build();

      // Invoke operation
      Response<DataIntgFlow> response = service.cloneDatastageFlows(datastageFlowsCloneOptions).execute();
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
      CompileDatastageFlowsOptions datastageFlowsCompileOptions = new CompileDatastageFlowsOptions.Builder()
      .dataIntgFlowId(DATASTAGE_FLOW_ID)
      .projectId(PROJECT_ID)
      .build();

      // Invoke operation
      Response<FlowCompileResponse> response = service.compileDatastageFlows(datastageFlowsCompileOptions).execute();
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
      CreateMigrationOptions migrationCreateOptions = new CreateMigrationOptions.Builder()
      .body(rowGenIsx)
      .projectId(PROJECT_ID)
      .onFailure("continue")
      .conflictResolution("rename")
      .attachmentType("isx")
      .fileName("rowgen_peek.isx")
      .build();

      // Invoke operation
      Response<ImportResponse> response = service.createMigration(migrationCreateOptions).execute();
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
        GetMigrationOptions migrationGetOptions = new GetMigrationOptions.Builder()
                .importId(importId)
                .projectId(PROJECT_ID)
                .build();

        // Invoke operation
        Response<ImportResponse> response = service.getMigration(migrationGetOptions).execute();
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
      DeleteMigrationOptions migrationDeleteOptions = new DeleteMigrationOptions.Builder()
      .importId(importId)
      .projectId(PROJECT_ID)
      .build();

      // Invoke operation
      Response<Void> response = service.deleteMigration(migrationDeleteOptions).execute();
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
      DeleteDatastageFlowsOptions datastageFlowsDeleteOptions = new DeleteDatastageFlowsOptions.Builder()
              .id(Arrays.asList(ids))
              .projectId(PROJECT_ID)
              .force(true)
              .build();

      // Invoke operation
      Response<Void> response = service.deleteDatastageFlows(datastageFlowsDeleteOptions).execute();

      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 204);
    } catch (ServiceResponseException e) {
      fail(String.format("Service returned status code %d: %s\nError details: %s",
              e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void test11DatastageSubFlowsList() throws Exception {
    try {
      ListDatastageSubflowsOptions datastageSubFlowsListOptions = new ListDatastageSubflowsOptions.Builder()
              .projectId(PROJECT_ID)
              .limit(Long.valueOf("100"))
              .build();

      // Invoke operation
      Response<DataFlowPagedCollection> response = service.listDatastageSubflows(datastageSubFlowsListOptions).execute();
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
  public void test12DataStageSubFlowsCreate() {
    try {
      Pipelines pipelinesModel = new Pipelines.Builder()
              .id("fa1b859a-d592-474d-b56c-2137e4efa4bc")
              .description("A test DataStage subflow")
              .runtimeRef("pxOsh")
              .nodes(nodes)
              .appData((Map<String, Object>) appDataSubFlow)
              .build();

      PipelineJson pipelineJsonModel = new PipelineJson.Builder()
              .docType("subpipeline")
              .version("3.0")
              .jsonSchema("http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json")
              .id("84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff")
              .primaryPipeline("fa1b859a-d592-474d-b56c-2137e4efa4bc")
              .pipelines(new java.util.ArrayList<Pipelines>(java.util.Arrays.asList(pipelinesModel)))
              .schemas(schemas)
              .build();

      CreateDatastageSubflowsOptions datastageSubFlowsCreateOptions = new CreateDatastageSubflowsOptions.Builder()
              .dataIntgSubflowName("testIntegrationSubFlow" + UUID.randomUUID().toString())
              .pipelineFlows(pipelineJsonModel)
              .projectId(PROJECT_ID)
              .assetCategory("system")
              .build();
      // Invoke operation
      Response<DataIntgFlow> response = service.createDatastageSubflows(datastageSubFlowsCreateOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 201);

      DataIntgFlow dataIntgFlowResult = response.getResult();

      assertNotNull(dataIntgFlowResult);
      assertNotNull(dataIntgFlowResult.getMetadata());
      DATASTAGE_SUBFLOW_ID = dataIntgFlowResult.getMetadata().getAssetId();
      assertNotNull(DATASTAGE_SUBFLOW_ID);
    } catch (ServiceResponseException e) {
      fail(String.format("Service returned status code %d: %s\nError details: %s",
              e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void test13DatastageSubFlowsUpdate() throws Exception {
    try {
      Pipelines pipelinesModel = new Pipelines.Builder()
              .id("fa1b859a-d592-474d-b56c-2137e4efa4bc")
              .description("A test DataStage subflow updated")
              .runtimeRef("pxOsh")
              .nodes(nodesSubFlow)
              .appData((Map<String, Object>) appDataSubFlow)
              .build();

      PipelineJson pipelineJsonModel = new PipelineJson.Builder()
              .docType("subpipeline")
              .version("3.0")
              .jsonSchema("http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json")
              .id("84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff")
              .primaryPipeline("fa1b859a-d592-474d-b56c-2137e4efa4bc")
              .pipelines(new java.util.ArrayList<Pipelines>(java.util.Arrays.asList(pipelinesModel)))
              .schemas(schemasSubFlowUpdated)
              .build();

      UpdateDatastageSubflowsOptions datastageSubFlowsUpdateOptions = new UpdateDatastageSubflowsOptions.Builder()
              .dataIntgSubflowId(DATASTAGE_SUBFLOW_ID)
              .dataIntgSubflowName("testSubFlowString" + UUID.randomUUID())
              .pipelineFlows(pipelineJsonModel)
              .projectId(PROJECT_ID)
              .build();
      // Invoke operation
      Response<DataIntgFlow> response = service.updateDatastageSubflows(datastageSubFlowsUpdateOptions).execute();
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
  public void test14DatastageSubFlowsClone() throws Exception {
    try {
      CloneDatastageSubflowsOptions datastageSubFlowsCloneOptions = new CloneDatastageSubflowsOptions.Builder()
              .dataIntgSubflowId(DATASTAGE_SUBFLOW_ID)
              .projectId(PROJECT_ID)
              .build();

      // Invoke operation
      Response<DataIntgFlow> response = service.cloneDatastageSubflows(datastageSubFlowsCloneOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      DataIntgFlow dataIntgFlowResult = response.getResult();

      assertNotNull(dataIntgFlowResult);
      assertNotNull(dataIntgFlowResult.getMetadata());
      DATASTAGE_SUBFLOW_ID_CLONE = dataIntgFlowResult.getMetadata().getAssetId();
      assertNotNull(DATASTAGE_SUBFLOW_ID_CLONE);
    } catch (ServiceResponseException e) {
      fail(String.format("Service returned status code %d: %s\nError details: %s",
              e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void test15DatastageSubFlowsDelete() throws Exception  {
    String ids[] = new String[] {DATASTAGE_SUBFLOW_ID, DATASTAGE_SUBFLOW_ID_CLONE};
    try {
      DeleteDatastageSubflowsOptions datastageSubFlowsDeleteOptions = new DeleteDatastageSubflowsOptions.Builder()
              .id(Arrays.asList(ids))
              .projectId(PROJECT_ID)
              .build();

      // Invoke operation
      Response<Void> response = service.deleteDatastageSubflows(datastageSubFlowsDeleteOptions).execute();

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
