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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import com.ibm.cloud.datastage.v3.model.PipelineJson;
import com.ibm.cloud.datastage.v3.model.UpdateDatastageFlowsOptions;
import com.ibm.cloud.datastage.v3.model.UpdateDatastageSubflowsOptions;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.cloud.sdk.core.util.CredentialUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatastageExamples {
  private static final Logger logger = LoggerFactory.getLogger(DatastageExamples.class);
  private static String projectID = null;
  private static String flowID = null;
  private static String cloneFlowID = null;
  private static String importID = null;
  private static String subflowID = null;
  private static String cloneSubflowID = null;
  private static final String flowName = "exampleFlow" + UUID.randomUUID().toString();
  private static final String subflowName = "exampleSubFlow" + UUID.randomUUID().toString();

  protected DatastageExamples() { }

  @SuppressWarnings("checkstyle:methodlength")
  public static void main(String[] args) throws Exception {
    InputStream flowInputStream = DatastageExamples.class.getClassLoader().getResourceAsStream("exampleFlow.json");
    InputStream updatedFlowInputStream = DatastageExamples.class.getClassLoader().getResourceAsStream("exampleFlowUpdated.json");
    InputStream subFlowInputStream = DatastageExamples.class.getClassLoader().getResourceAsStream("exampleSubFlow.json");
    InputStream updatedSubFlowInputStream = DatastageExamples.class.getClassLoader().getResourceAsStream("exampleSubFlowUpdated.json");
    InputStream rowGenIsx = DatastageExamples.class.getClassLoader().getResourceAsStream("rowgen_peek.isx");

    JsonObject flowJson = JsonParser.parseReader(new InputStreamReader(flowInputStream, StandardCharsets.UTF_8)).getAsJsonObject();
    JsonObject updatedFlowJson = JsonParser.parseReader(new InputStreamReader(updatedFlowInputStream, StandardCharsets.UTF_8)).getAsJsonObject();
    JsonObject subFlowJson = JsonParser.parseReader(new InputStreamReader(subFlowInputStream, StandardCharsets.UTF_8)).getAsJsonObject();
    JsonObject updatedSubFlowJson = JsonParser.parseReader(new InputStreamReader(updatedSubFlowInputStream, StandardCharsets.UTF_8)).getAsJsonObject();
    Datastage datastageService = Datastage.newInstance();

    // Load up our test-specific config properties.
    Map<String, String> config = CredentialUtils.getServiceProperties(Datastage.DEFAULT_SERVICE_NAME);
    projectID = config.get("PROJECT_ID");

    try {
      // begin-list_datastage_flows
      ListDatastageFlowsOptions listDatastageFlowsOptions = new ListDatastageFlowsOptions.Builder()
        .projectId(projectID)
        .limit(Long.valueOf("100"))
        .build();

      Response<DataFlowPagedCollection> response = datastageService.listDatastageFlows(listDatastageFlowsOptions).execute();
      DataFlowPagedCollection dataFlowPagedCollection = response.getResult();

      System.out.println(dataFlowPagedCollection);
      // end-list_datastage_flows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-create_datastage_flows
      PipelineJson exampleFlow = PipelineFlowHelper.buildPipelineFlow(flowJson);
      CreateDatastageFlowsOptions createDatastageFlowsOptions = new CreateDatastageFlowsOptions.Builder()
        .dataIntgFlowName(flowName)
        .pipelineFlows(exampleFlow)
        .projectId(projectID)
        .build();

      Response<DataIntgFlow> response = datastageService.createDatastageFlows(createDatastageFlowsOptions).execute();
      DataIntgFlow dataIntgFlow = response.getResult();
      System.out.println(dataIntgFlow);
      // end-create_datastage_flows
      if (dataIntgFlow != null && dataIntgFlow.getMetadata() != null) {
        flowID = dataIntgFlow.getMetadata().getAssetId();
      }
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-get_datastage_flows
      GetDatastageFlowsOptions getDatastageFlowsOptions = new GetDatastageFlowsOptions.Builder()
        .dataIntgFlowId(flowID)
        .projectId(projectID)
        .build();

      Response<DataIntgFlowJson> response = datastageService.getDatastageFlows(getDatastageFlowsOptions).execute();
      DataIntgFlowJson dataIntgFlowJson = response.getResult();

      System.out.println(dataIntgFlowJson);
      // end-get_datastage_flows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-update_datastage_flows
      PipelineJson exampleFlowUpdated = PipelineFlowHelper.buildPipelineFlow(updatedFlowJson);
      UpdateDatastageFlowsOptions updateDatastageFlowsOptions = new UpdateDatastageFlowsOptions.Builder()
        .dataIntgFlowId(flowID)
        .dataIntgFlowName(flowName)
        .pipelineFlows(exampleFlowUpdated)
        .projectId(projectID)
        .build();

      Response<DataIntgFlow> response = datastageService.updateDatastageFlows(updateDatastageFlowsOptions).execute();
      DataIntgFlow dataIntgFlow = response.getResult();

      System.out.println(dataIntgFlow);
      // end-update_datastage_flows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-clone_datastage_flows
      CloneDatastageFlowsOptions cloneDatastageFlowsOptions = new CloneDatastageFlowsOptions.Builder()
        .dataIntgFlowId(flowID)
        .projectId(projectID)
        .build();

      Response<DataIntgFlow> response = datastageService.cloneDatastageFlows(cloneDatastageFlowsOptions).execute();
      DataIntgFlow dataIntgFlow = response.getResult();

      System.out.println(dataIntgFlow);
      // end-clone_datastage_flows
      if (dataIntgFlow != null && dataIntgFlow.getMetadata() != null) {
        cloneFlowID = dataIntgFlow.getMetadata().getAssetId();
      }
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-compile_datastage_flows
      CompileDatastageFlowsOptions compileDatastageFlowsOptions = new CompileDatastageFlowsOptions.Builder()
        .dataIntgFlowId(flowID)
        .projectId(projectID)
        .build();

      Response<FlowCompileResponse> response = datastageService.compileDatastageFlows(compileDatastageFlowsOptions).execute();
      FlowCompileResponse flowCompileResponse = response.getResult();

      System.out.println(flowCompileResponse);
      // end-compile_datastage_flows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-list_datastage_subflows
      ListDatastageSubflowsOptions listDatastageSubflowsOptions = new ListDatastageSubflowsOptions.Builder()
        .projectId(projectID)
        .limit(Long.valueOf("100"))
        .build();

      Response<DataFlowPagedCollection> response = datastageService.listDatastageSubflows(listDatastageSubflowsOptions).execute();
      DataFlowPagedCollection dataFlowPagedCollection = response.getResult();

      System.out.println(dataFlowPagedCollection);
      // end-list_datastage_subflows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-create_datastage_subflows
      PipelineJson exampleSubFlow = PipelineFlowHelper.buildPipelineFlow(subFlowJson);
      CreateDatastageSubflowsOptions createDatastageSubflowsOptions = new CreateDatastageSubflowsOptions.Builder()
        .dataIntgSubflowName(subflowName)
        .pipelineFlows(exampleSubFlow)
        .projectId(projectID)
        .build();

      Response<DataIntgFlow> response = datastageService.createDatastageSubflows(createDatastageSubflowsOptions).execute();
      DataIntgFlow dataIntgFlow = response.getResult();

      System.out.println(dataIntgFlow);
      // end-create_datastage_subflows
      if (dataIntgFlow != null && dataIntgFlow.getMetadata() != null) {
        subflowID = dataIntgFlow.getMetadata().getAssetId();
      }
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-get_datastage_subflows
      GetDatastageSubflowsOptions getDatastageSubflowsOptions = new GetDatastageSubflowsOptions.Builder()
        .dataIntgSubflowId(subflowID)
        .projectId(projectID)
        .build();

      Response<DataIntgFlowJson> response = datastageService.getDatastageSubflows(getDatastageSubflowsOptions).execute();
      DataIntgFlowJson dataIntgFlowJson = response.getResult();

      System.out.println(dataIntgFlowJson);
      // end-get_datastage_subflows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-update_datastage_subflows
      PipelineJson exampleSubFlowUpdated = PipelineFlowHelper.buildPipelineFlow(updatedSubFlowJson);
      UpdateDatastageSubflowsOptions updateDatastageSubflowsOptions = new UpdateDatastageSubflowsOptions.Builder()
        .dataIntgSubflowId(subflowID)
        .dataIntgSubflowName(subflowName)
        .pipelineFlows(exampleSubFlowUpdated)
        .projectId(projectID)
        .build();

      Response<DataIntgFlow> response = datastageService.updateDatastageSubflows(updateDatastageSubflowsOptions).execute();
      DataIntgFlow dataIntgFlow = response.getResult();

      System.out.println(dataIntgFlow);
      // end-update_datastage_subflows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-clone_datastage_subflows
      CloneDatastageSubflowsOptions cloneDatastageSubflowsOptions = new CloneDatastageSubflowsOptions.Builder()
        .dataIntgSubflowId(subflowID)
        .projectId(projectID)
        .build();

      Response<DataIntgFlow> response = datastageService.cloneDatastageSubflows(cloneDatastageSubflowsOptions).execute();
      DataIntgFlow dataIntgFlow = response.getResult();

      System.out.println(dataIntgFlow);
      // end-clone_datastage_subflows
      if (dataIntgFlow != null && dataIntgFlow.getMetadata() != null) {
        cloneSubflowID = dataIntgFlow.getMetadata().getAssetId();
      }
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-create_migration
      CreateMigrationOptions createMigrationOptions = new CreateMigrationOptions.Builder()
        .body(rowGenIsx)
        .projectId(projectID)
        .onFailure("continue")
        .conflictResolution("rename")
        .attachmentType("isx")
        .fileName("rowgen_peek.isx")
        .build();

      Response<ImportResponse> response = datastageService.createMigration(createMigrationOptions).execute();
      ImportResponse importResponse = response.getResult();

      System.out.println(importResponse);
      // end-create_migration
      if (importResponse != null && importResponse.getMetadata() != null) {
        importID = importResponse.getMetadata().getId();
      }
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-get_migration
      GetMigrationOptions getMigrationOptions = new GetMigrationOptions.Builder()
        .importId(importID)
        .projectId(projectID)
        .build();

      Response<ImportResponse> response = datastageService.getMigration(getMigrationOptions).execute();
      ImportResponse importResponse = response.getResult();

      System.out.println(importResponse);
      // end-get_migration
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-delete_migration
      DeleteMigrationOptions deleteMigrationOptions = new DeleteMigrationOptions.Builder()
        .importId(importID)
        .projectId(projectID)
        .build();

      datastageService.deleteMigration(deleteMigrationOptions).execute();
      // end-delete_migration
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-delete_datastage_subflows
      String[] ids = new String[] {subflowID, cloneSubflowID};
      DeleteDatastageSubflowsOptions deleteDatastageSubflowsOptions = new DeleteDatastageSubflowsOptions.Builder()
        .id(Arrays.asList(ids))
        .projectId(projectID)
        .build();

      datastageService.deleteDatastageSubflows(deleteDatastageSubflowsOptions).execute();
      // end-delete_datastage_subflows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-delete_datastage_flows
      String[] ids = new String[] {flowID, cloneFlowID};
      DeleteDatastageFlowsOptions deleteDatastageFlowsOptions = new DeleteDatastageFlowsOptions.Builder()
        .id(Arrays.asList(ids))
        .projectId(projectID)
        .build();

      datastageService.deleteDatastageFlows(deleteDatastageFlowsOptions).execute();
      // end-delete_datastage_flows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

  }
}
