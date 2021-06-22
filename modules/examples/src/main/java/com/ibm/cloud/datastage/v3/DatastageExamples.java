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
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.cloud.sdk.core.util.CredentialUtils;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatastageExamples {
  private static final Logger logger = LoggerFactory.getLogger(DatastageExamples.class);
  protected DatastageExamples() { }

  @SuppressWarnings("checkstyle:methodlength")
  public static void main(String[] args) throws Exception {
    Datastage service = Datastage.newInstance();

    // Load up our test-specific config properties.
    Map<String, String> config = CredentialUtils.getServiceProperties(Datastage.DEFAULT_SERVICE_NAME);

    try {
      // begin-list_datastage_flows
      ListDatastageFlowsOptions listDatastageFlowsOptions = new ListDatastageFlowsOptions.Builder()
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .limit(Long.valueOf("100"))
        .build();

      Response<DataFlowPagedCollection> response = service.listDatastageFlows(listDatastageFlowsOptions).execute();
      DataFlowPagedCollection dataFlowPagedCollection = response.getResult();

      System.out.println(dataFlowPagedCollection);
      // end-list_datastage_flows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-create_datastage_flows
      CreateDatastageFlowsOptions createDatastageFlowsOptions = new CreateDatastageFlowsOptions.Builder()
        .dataIntgFlowName("testString")
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .build();

      Response<DataIntgFlow> response = service.createDatastageFlows(createDatastageFlowsOptions).execute();
      DataIntgFlow dataIntgFlow = response.getResult();

      System.out.println(dataIntgFlow);
      // end-create_datastage_flows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-get_datastage_flows
      GetDatastageFlowsOptions getDatastageFlowsOptions = new GetDatastageFlowsOptions.Builder()
        .dataIntgFlowId("testString")
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .build();

      Response<DataIntgFlowJson> response = service.getDatastageFlows(getDatastageFlowsOptions).execute();
      DataIntgFlowJson dataIntgFlowJson = response.getResult();

      System.out.println(dataIntgFlowJson);
      // end-get_datastage_flows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-update_datastage_flows
      UpdateDatastageFlowsOptions updateDatastageFlowsOptions = new UpdateDatastageFlowsOptions.Builder()
        .dataIntgFlowId("testString")
        .dataIntgFlowName("testString")
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .build();

      Response<DataIntgFlow> response = service.updateDatastageFlows(updateDatastageFlowsOptions).execute();
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
        .dataIntgFlowId("testString")
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .build();

      Response<DataIntgFlow> response = service.cloneDatastageFlows(cloneDatastageFlowsOptions).execute();
      DataIntgFlow dataIntgFlow = response.getResult();

      System.out.println(dataIntgFlow);
      // end-clone_datastage_flows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-compile_datastage_flows
      CompileDatastageFlowsOptions compileDatastageFlowsOptions = new CompileDatastageFlowsOptions.Builder()
        .dataIntgFlowId("testString")
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .build();

      Response<FlowCompileResponse> response = service.compileDatastageFlows(compileDatastageFlowsOptions).execute();
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
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .limit(Long.valueOf("100"))
        .build();

      Response<DataFlowPagedCollection> response = service.listDatastageSubflows(listDatastageSubflowsOptions).execute();
      DataFlowPagedCollection dataFlowPagedCollection = response.getResult();

      System.out.println(dataFlowPagedCollection);
      // end-list_datastage_subflows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-create_datastage_subflows
      CreateDatastageSubflowsOptions createDatastageSubflowsOptions = new CreateDatastageSubflowsOptions.Builder()
        .dataIntgSubflowName("testString")
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .build();

      Response<DataIntgFlow> response = service.createDatastageSubflows(createDatastageSubflowsOptions).execute();
      DataIntgFlow dataIntgFlow = response.getResult();

      System.out.println(dataIntgFlow);
      // end-create_datastage_subflows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-get_datastage_subflows
      GetDatastageSubflowsOptions getDatastageSubflowsOptions = new GetDatastageSubflowsOptions.Builder()
        .dataIntgSubflowId("testString")
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .build();

      Response<DataIntgFlowJson> response = service.getDatastageSubflows(getDatastageSubflowsOptions).execute();
      DataIntgFlowJson dataIntgFlowJson = response.getResult();

      System.out.println(dataIntgFlowJson);
      // end-get_datastage_subflows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-update_datastage_subflows
      UpdateDatastageSubflowsOptions updateDatastageSubflowsOptions = new UpdateDatastageSubflowsOptions.Builder()
        .dataIntgSubflowId("testString")
        .dataIntgSubflowName("testString")
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .build();

      Response<DataIntgFlow> response = service.updateDatastageSubflows(updateDatastageSubflowsOptions).execute();
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
        .dataIntgSubflowId("testString")
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .build();

      Response<DataIntgFlow> response = service.cloneDatastageSubflows(cloneDatastageSubflowsOptions).execute();
      DataIntgFlow dataIntgFlow = response.getResult();

      System.out.println(dataIntgFlow);
      // end-clone_datastage_subflows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-create_migration
      CreateMigrationOptions createMigrationOptions = new CreateMigrationOptions.Builder()
        .body(new java.io.ByteArrayInputStream("This is a mock file.".getBytes()))
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .onFailure("continue")
        .conflictResolution("rename")
        .attachmentType("isx")
        .fileName("myFlows.isx")
        .build();

      Response<ImportResponse> response = service.createMigration(createMigrationOptions).execute();
      ImportResponse importResponse = response.getResult();

      System.out.println(importResponse);
      // end-create_migration
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-get_migration
      GetMigrationOptions getMigrationOptions = new GetMigrationOptions.Builder()
        .importId("testString")
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .build();

      Response<ImportResponse> response = service.getMigration(getMigrationOptions).execute();
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
        .importId("cc6dbbfd-810d-4f0e-b0a9-228c328aff29")
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .build();

      service.deleteMigration(deleteMigrationOptions).execute();
      // end-delete_migration
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-delete_datastage_subflows
      DeleteDatastageSubflowsOptions deleteDatastageSubflowsOptions = new DeleteDatastageSubflowsOptions.Builder()
        .id(new java.util.ArrayList<String>(java.util.Arrays.asList("testString")))
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .build();

      service.deleteDatastageSubflows(deleteDatastageSubflowsOptions).execute();
      // end-delete_datastage_subflows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-delete_datastage_flows
      DeleteDatastageFlowsOptions deleteDatastageFlowsOptions = new DeleteDatastageFlowsOptions.Builder()
        .id(new java.util.ArrayList<String>(java.util.Arrays.asList("testString")))
        .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
        .build();

      service.deleteDatastageFlows(deleteDatastageFlowsOptions).execute();
      // end-delete_datastage_flows
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

  }
}
