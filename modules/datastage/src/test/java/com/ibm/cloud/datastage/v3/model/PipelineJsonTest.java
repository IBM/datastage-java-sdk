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
    Pipelines pipelinesModel = new Pipelines.Builder()
      .appData(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
      .description("A test DataStage flow")
      .id("fa1b859a-d592-474d-b56c-2137e4efa4bc")
      .nodes(new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")))
      .runtimeRef("pxOsh")
      .build();
    assertEquals(pipelinesModel.appData(), new java.util.HashMap<String, Object>() { { put("foo", "testString"); } });
    assertEquals(pipelinesModel.description(), "A test DataStage flow");
    assertEquals(pipelinesModel.id(), "fa1b859a-d592-474d-b56c-2137e4efa4bc");
    assertEquals(pipelinesModel.nodes(), new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")));
    assertEquals(pipelinesModel.runtimeRef(), "pxOsh");

    PipelineJson pipelineJsonModel = new PipelineJson.Builder()
      .appData(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
      .docType("pipeline")
      .externalParamsets(new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")))
      .id("84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff")
      .jsonSchema("http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json")
      .parameters(new java.util.HashMap<String, Object>() { { put("foo", "testString"); } })
      .pipelines(new java.util.ArrayList<Pipelines>(java.util.Arrays.asList(pipelinesModel)))
      .primaryPipeline("fa1b859a-d592-474d-b56c-2137e4efa4bc")
      .runtimes(new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")))
      .schemas(new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")))
      .version("3.0")
      .build();
    assertEquals(pipelineJsonModel.appData(), new java.util.HashMap<String, Object>() { { put("foo", "testString"); } });
    assertEquals(pipelineJsonModel.docType(), "pipeline");
    assertEquals(pipelineJsonModel.externalParamsets(), new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")));
    assertEquals(pipelineJsonModel.id(), "84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff");
    assertEquals(pipelineJsonModel.jsonSchema(), "http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json");
    assertEquals(pipelineJsonModel.parameters(), new java.util.HashMap<String, Object>() { { put("foo", "testString"); } });
    assertEquals(pipelineJsonModel.pipelines(), new java.util.ArrayList<Pipelines>(java.util.Arrays.asList(pipelinesModel)));
    assertEquals(pipelineJsonModel.primaryPipeline(), "fa1b859a-d592-474d-b56c-2137e4efa4bc");
    assertEquals(pipelineJsonModel.runtimes(), new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")));
    assertEquals(pipelineJsonModel.schemas(), new java.util.ArrayList<Object>(java.util.Arrays.asList("testString")));
    assertEquals(pipelineJsonModel.version(), "3.0");

    String json = TestUtilities.serialize(pipelineJsonModel);

    PipelineJson pipelineJsonModelNew = TestUtilities.deserialize(json, PipelineJson.class);
    assertTrue(pipelineJsonModelNew instanceof PipelineJson);
    assertEquals(pipelineJsonModelNew.appData().toString(), new java.util.HashMap<String, Object>() { { put("foo", "testString"); } }.toString());
    assertEquals(pipelineJsonModelNew.docType(), "pipeline");
    assertEquals(pipelineJsonModelNew.id(), "84c2b6fb-1dd5-4114-b4ba-9bb2cb364fff");
    assertEquals(pipelineJsonModelNew.jsonSchema(), "http://api.dataplatform.ibm.com/schemas/common-pipeline/pipeline-flow/pipeline-flow-v3-schema.json");
    assertEquals(pipelineJsonModelNew.parameters().toString(), new java.util.HashMap<String, Object>() { { put("foo", "testString"); } }.toString());
    assertEquals(pipelineJsonModelNew.primaryPipeline(), "fa1b859a-d592-474d-b56c-2137e4efa4bc");
    assertEquals(pipelineJsonModelNew.version(), "3.0");
  }
}