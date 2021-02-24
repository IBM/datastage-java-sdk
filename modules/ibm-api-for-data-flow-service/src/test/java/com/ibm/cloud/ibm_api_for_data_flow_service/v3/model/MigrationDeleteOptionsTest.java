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

import com.ibm.cloud.ibm_api_for_data_flow_service.v3.model.MigrationDeleteOptions;
import com.ibm.cloud.ibm_api_for_data_flow_service.v3.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the MigrationDeleteOptions model.
 */
public class MigrationDeleteOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testMigrationDeleteOptions() throws Throwable {
    MigrationDeleteOptions migrationDeleteOptionsModel = new MigrationDeleteOptions.Builder()
      .importId("cc6dbbfd-810d-4f0e-b0a9-228c328aff29")
      .catalogId("testString")
      .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
      .build();
    assertEquals(migrationDeleteOptionsModel.importId(), "cc6dbbfd-810d-4f0e-b0a9-228c328aff29");
    assertEquals(migrationDeleteOptionsModel.catalogId(), "testString");
    assertEquals(migrationDeleteOptionsModel.projectId(), "bd0dbbfd-810d-4f0e-b0a9-228c328a8e23");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testMigrationDeleteOptionsError() throws Throwable {
    new MigrationDeleteOptions.Builder().build();
  }

}