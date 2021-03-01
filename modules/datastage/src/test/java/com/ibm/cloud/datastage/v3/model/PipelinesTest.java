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

import com.google.gson.JsonObject;
import com.ibm.cloud.datastage.v3.model.Pipelines;
import com.ibm.cloud.datastage.v3.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the Pipelines model.
 */
public class PipelinesTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testPipelines() throws Throwable {
    JsonObject nodes = new JsonObject();
    nodes.addProperty("foo", "testString");
    JsonObject appData = new JsonObject();
    appData.addProperty("foo", "testString");
    Pipelines pipelinesModel = new Pipelines.Builder()
      .id("fa1b859a-d592-474d-b56c-2137e4efa4bc")
      .description("A test DataStage flow")
      .runtimeRef("pxOsh")
      .nodes(nodes)
      .appData(appData)
      .build();
    assertEquals(pipelinesModel.id(), "fa1b859a-d592-474d-b56c-2137e4efa4bc");
    assertEquals(pipelinesModel.description(), "A test DataStage flow");
    assertEquals(pipelinesModel.runtimeRef(), "pxOsh");
    assertEquals(pipelinesModel.nodes(), nodes);
    assertEquals(pipelinesModel.appData(), appData);

    String json = TestUtilities.serialize(pipelinesModel);
    Pipelines pipelinesModelNew = TestUtilities.deserialize(json, Pipelines.class);
    assertTrue(pipelinesModelNew instanceof Pipelines);
    assertEquals(pipelinesModelNew.id(), "fa1b859a-d592-474d-b56c-2137e4efa4bc");
    assertEquals(pipelinesModelNew.description(), "A test DataStage flow");
    assertEquals(pipelinesModelNew.runtimeRef(), "pxOsh");
    assertEquals(pipelinesModelNew.nodes().toString(), new java.util.HashMap<String, Object>() { { put("foo", "testString"); } }.toString());
    assertEquals(pipelinesModelNew.appData().toString(), new java.util.HashMap<String, Object>() { { put("foo", "testString"); } }.toString());
  }
}