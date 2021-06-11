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

package com.ibm.cloud.datastage.v3.model;

import com.ibm.cloud.datastage.v3.model.DeleteDatastageSubflowsOptions;
import com.ibm.cloud.datastage.v3.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the DeleteDatastageSubflowsOptions model.
 */
public class DeleteDatastageSubflowsOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testDeleteDatastageSubflowsOptions() throws Throwable {
    DeleteDatastageSubflowsOptions deleteDatastageSubflowsOptionsModel = new DeleteDatastageSubflowsOptions.Builder()
      .id(new java.util.ArrayList<String>(java.util.Arrays.asList("testString")))
      .catalogId("testString")
      .projectId("bd0dbbfd-810d-4f0e-b0a9-228c328a8e23")
      .build();
    assertEquals(deleteDatastageSubflowsOptionsModel.id(), new java.util.ArrayList<String>(java.util.Arrays.asList("testString")));
    assertEquals(deleteDatastageSubflowsOptionsModel.catalogId(), "testString");
    assertEquals(deleteDatastageSubflowsOptionsModel.projectId(), "bd0dbbfd-810d-4f0e-b0a9-228c328a8e23");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteDatastageSubflowsOptionsError() throws Throwable {
    new DeleteDatastageSubflowsOptions.Builder().build();
  }

}