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

import com.ibm.cloud.datastage.v3.model.AssetEntityROV;
import com.ibm.cloud.datastage.v3.model.AssetSystemMetadata;
import com.ibm.cloud.datastage.v3.model.AssetSystemMetadataUsage;
import com.ibm.cloud.datastage.v3.model.DataIntgFlowEntity;
import com.ibm.cloud.datastage.v3.model.DataIntgFlowJson;
import com.ibm.cloud.datastage.v3.model.DataIntgFlowLock;
import com.ibm.cloud.datastage.v3.model.DataIntgFlowLockEntity;
import com.ibm.cloud.datastage.v3.model.DataIntgFlowLockMetadata;
import com.ibm.cloud.datastage.v3.model.PipelineJson;
import com.ibm.cloud.datastage.v3.model.Pipelines;
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
 * Unit test class for the DataIntgFlowJson model.
 */
public class DataIntgFlowJsonTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testDataIntgFlowJson() throws Throwable {
    DataIntgFlowJson dataIntgFlowJsonModel = new DataIntgFlowJson();
    assertNull(dataIntgFlowJsonModel.getAttachments());
    assertNull(dataIntgFlowJsonModel.getEntity());
    assertNull(dataIntgFlowJsonModel.getMetadata());
  }
}