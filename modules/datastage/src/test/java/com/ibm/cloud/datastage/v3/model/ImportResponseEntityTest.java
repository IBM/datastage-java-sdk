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

import com.ibm.cloud.datastage.v3.model.DataImportError;
import com.ibm.cloud.datastage.v3.model.ImportCount;
import com.ibm.cloud.datastage.v3.model.ImportFlow;
import com.ibm.cloud.datastage.v3.model.ImportFlowWarning;
import com.ibm.cloud.datastage.v3.model.ImportResponseEntity;
import com.ibm.cloud.datastage.v3.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import com.ibm.cloud.sdk.core.util.DateUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the ImportResponseEntity model.
 */
public class ImportResponseEntityTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testImportResponseEntity() throws Throwable {
    ImportResponseEntity importResponseEntityModel = new ImportResponseEntity();
    assertNull(importResponseEntityModel.getName());
    assertNull(importResponseEntityModel.getStatus());
    assertNull(importResponseEntityModel.getStartTime());
    assertNull(importResponseEntityModel.getEndTime());
    assertNull(importResponseEntityModel.getRemainingTime());
    assertNull(importResponseEntityModel.getCancelledBy());
    assertNull(importResponseEntityModel.getOnFailure());
    assertNull(importResponseEntityModel.getConflictResolution());
    assertNull(importResponseEntityModel.getImportDataFlows());
    assertNull(importResponseEntityModel.getTally());
  }
}