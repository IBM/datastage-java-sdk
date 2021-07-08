package com.ibm.cloud.datastage.v3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ibm.cloud.datastage.v3.model.PipelineJson;
import com.ibm.cloud.datastage.v3.model.Pipelines;

import java.io.IOException;
import java.util.List;
import java.util.Map;

class PipelineFlowHelper {
    public static PipelineJson buildPipelineFlow(JsonObject pipelineFlow) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonArray pipelines = pipelineFlow.getAsJsonArray("pipelines");
        JsonObject pipeline = (JsonObject) pipelines.get(0);
        String runtimeRef = pipeline.get("runtime_ref").getAsString();
        String pipelineID = pipeline.get("id").getAsString();
        String docType = pipelineFlow.get("doc_type").getAsString();
        String version = pipelineFlow.get("version").getAsString();
        String jsonSchema = pipelineFlow.get("json_schema").getAsString();
        String id = pipelineFlow.get("id").getAsString();
        String primaryPipeline = pipelineFlow.get("primary_pipeline").getAsString();
        String description = null;
        if (pipeline.has("description")) {
            description = pipeline.get("description").getAsString();
        }
        List<Object> externalParamsets = null;
        if (pipelineFlow.has("external_paramsets")) {
            externalParamsets = mapper.readValue(String.valueOf(pipelineFlow.get("external_paramsets")), new TypeReference<List<Object>>() {
            });
        }

        List<Object> nodes = mapper.readValue(String.valueOf(pipeline.get("nodes")), new TypeReference<List<Object>>() {
        });
        Map<String, Object> appData = mapper.readValue(String.valueOf(pipeline.get("app_data")), new TypeReference<Object>() {
        });

        Pipelines pipelinesModel = new Pipelines.Builder()
                .id(pipelineID)
                .description(description)
                .runtimeRef(runtimeRef)
                .nodes(nodes)
                .appData(appData)
                .build();

        List<Object> schemas = mapper.readValue(String.valueOf(pipelineFlow.get("schemas")), new TypeReference<List<Object>>() {
        });
        PipelineJson pipelineJsonModel = new PipelineJson.Builder()
                .docType(docType)
                .version(version)
                .jsonSchema(jsonSchema)
                .id(id)
                .primaryPipeline(primaryPipeline)
                .pipelines(new java.util.ArrayList<>(java.util.Arrays.asList(pipelinesModel)))
                .schemas(schemas)
                .externalParamsets(externalParamsets)
                .build();
        return pipelineJsonModel;
    }
}
