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

import com.ibm.cloud.datastage.v3.model.AssetSystemMetadata;
import com.ibm.cloud.datastage.v3.model.AssetSystemMetadataUsage;
import com.ibm.cloud.datastage.v3.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import com.ibm.cloud.sdk.core.util.DateUtils;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the AssetSystemMetadata model.
 */
public class AssetSystemMetadataTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testAssetSystemMetadata() throws Throwable {
    AssetSystemMetadata assetSystemMetadataModel = new AssetSystemMetadata();
    assertNull(assetSystemMetadataModel.getAssetId());
    assertNull(assetSystemMetadataModel.getAssetType());
    assertNull(assetSystemMetadataModel.getCatalogId());
    assertNull(assetSystemMetadataModel.getCreateTime());
    assertNull(assetSystemMetadataModel.getCreatorId());
    assertNull(assetSystemMetadataModel.getHref());
    assertNull(assetSystemMetadataModel.getName());
    assertNull(assetSystemMetadataModel.getOriginCountry());
    assertNull(assetSystemMetadataModel.getSize());
    assertNull(assetSystemMetadataModel.getProjectId());
    assertNull(assetSystemMetadataModel.getResourceKey());
    assertNull(assetSystemMetadataModel.getDescription());
    assertNull(assetSystemMetadataModel.getTags());
    assertNull(assetSystemMetadataModel.getSourceSystem());
    assertNull(assetSystemMetadataModel.getUsage());
  }
}