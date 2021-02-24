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

package com.ibm.cloud.ibm_api_for_data_flow_service.v3.model;

import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.MigrationCreateOptions;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the MigrationCreateOptions model.
 */
public class MigrationCreateOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testMigrationCreateOptions() throws Throwable {
    MigrationCreateOptions migrationCreateOptionsModel = new MigrationCreateOptions.Builder()
      .body(TestUtilities.createMockStream("This is a mock file."))
      .catalogId("testString")
      .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
      .onFailure("continue")
      .conflictResolution("rename")
      .attachmentType("isx")
      .fileName("myFlows.isx")
      .build();
    assertEquals(IOUtils.toString(migrationCreateOptionsModel.body()), IOUtils.toString(TestUtilities.createMockStream("This is a mock file.")));
    assertEquals(migrationCreateOptionsModel.catalogId(), "testString");
    assertEquals(migrationCreateOptionsModel.projectId(), "bd0dbbfd-810d-4f0e-b0a9-228c328a8e23");
    assertEquals(migrationCreateOptionsModel.onFailure(), "continue");
    assertEquals(migrationCreateOptionsModel.conflictResolution(), "rename");
    assertEquals(migrationCreateOptionsModel.attachmentType(), "isx");
    assertEquals(migrationCreateOptionsModel.fileName(), "myFlows.isx");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testMigrationCreateOptionsError() throws Throwable {
    new MigrationCreateOptions.Builder().build();
  }

}