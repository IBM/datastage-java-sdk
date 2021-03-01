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
import com.ibm.cloud.datastage.v3.model.PipelineJson;
import com.ibm.cloud.datastage.v3.model.Pipelines;
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
 * Unit test class for the PipelineJson model.
 */
public class PipelineJsonTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testPipelineJson() throws Throwable {
    JsonObject schemas = new JsonObject();
    schemas.addProperty("foo", "testString");
    JsonObject runtimes = new JsonObject();
    runtimes.addProperty("foo", "testString");
    JsonObject appData = new JsonObject();
    appData.addProperty("foo", "testString");
    Pipelines pipelinesModel = new Pipelines.Builder()
      .id("fa1b859a-d592-474d-b56c-2137e4efa4bc")
      .description("A test DataStage flow")
      .runtimeRef("pxOsh")
      .nodes(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
      .appData(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
      .build();
    assertEquals(pipelinesModel.id(), "fa1b859a-d592-474d-b56c-2137e4efa4bc");
    assertEquals(pipelinesModel.description(), "A test DataStage flow");
    assertEquals(pipelinesModel.runtimeRef(), "pxOsh");
    assertEquals(pipelinesModel.nodes(), new java.util.HashMap<String, Object>() { { put("foo", "testString"); } });
    assertEquals(pipelinesModel.appData(), new java.util.HashMap<String, Object>() { { put("foo", "testString"); } });

    PipelineJson pipelineJsonModel = new PipelineJson.Builder()
      .docType("pipeline")
      .version("3.0")
      .jsonSchema("http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json")
      .id("84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff")
      .primaryPipeline("fa1b859a-d592-474d-b56c-2137e4efa4bc")
      .pipelines(new java.util.ArrayList<Pipelines>(java.util.Arrays.asList(pipelinesModel)))
      .schemas(schemas)
      .runtimes(runtimes)
      .appData(appData)
      .build();
    assertEquals(pipelineJsonModel.docType(), "pipeline");
    assertEquals(pipelineJsonModel.version(), "3.0");
    assertEquals(pipelineJsonModel.jsonSchema(), "http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json");
    assertEquals(pipelineJsonModel.id(), "84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff");
    assertEquals(pipelineJsonModel.primaryPipeline(), "fa1b859a-d592-474d-b56c-2137e4efa4bc");
    assertEquals(pipelineJsonModel.pipelines(), new java.util.ArrayList<Pipelines>(java.util.Arrays.asList(pipelinesModel)));
    assertEquals(pipelineJsonModel.schemas().toString(), schemas.toString());
    assertEquals(pipelineJsonModel.runtimes().toString(), runtimes.toString());
    assertEquals(pipelineJsonModel.appData().toString(), appData.toString());

    String json = TestUtilities.serialize(pipelineJsonModel);

    PipelineJson pipelineJsonModelNew = TestUtilities.deserialize(json, PipelineJson.class);
    assertTrue(pipelineJsonModelNew instanceof PipelineJson);
    assertEquals(pipelineJsonModelNew.docType(), "pipeline");
    assertEquals(pipelineJsonModelNew.version(), "3.0");
    assertEquals(pipelineJsonModelNew.jsonSchema(), "http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json");
    assertEquals(pipelineJsonModelNew.id(), "84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff");
    assertEquals(pipelineJsonModelNew.primaryPipeline(), "fa1b859a-d592-474d-b56c-2137e4efa4bc");
    assertEquals(pipelineJsonModelNew.schemas().toString(), new java.util.HashMap<String, Object>() { { put("foo", "testString"); } }.toString());
    assertEquals(pipelineJsonModelNew.runtimes().toString(), new java.util.HashMap<String, Object>() { { put("foo", "testString"); } }.toString());
    assertEquals(pipelineJsonModelNew.appData().toString(), new java.util.HashMap<String, Object>() { { put("foo", "testString"); } }.toString());
  }
}