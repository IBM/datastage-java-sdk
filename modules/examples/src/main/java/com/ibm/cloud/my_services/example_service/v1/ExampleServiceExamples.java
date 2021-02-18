/*
 * (C) Copyright IBM Corp. 2020.
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

package com.ibm.cloud.my_services.example_service.v1;

import com.ibm.cloud.my_services.example_service.v1.model.CreateResourceOptions;
import com.ibm.cloud.my_services.example_service.v1.model.GetResourceOptions;
import com.ibm.cloud.my_services.example_service.v1.model.ListResourcesOptions;
import com.ibm.cloud.my_services.example_service.v1.model.Resource;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleServiceExamples {
  private static final Logger logger = LoggerFactory.getLogger(ExampleServiceExamples.class);
  protected ExampleServiceExamples() { }

  public static void main(String[] args) throws Exception {
    ExampleService service = ExampleService.newInstance();

    // Globlal variables to hold link values
    String getResource = null;

    try {
    // begin-create_resource
      CreateResourceOptions createResourceOptions = new CreateResourceOptions.Builder()
      .resourceId("testString")
      .name("testString")
      .build();

      Response<Resource> response = service.createResource(createResourceOptions).execute();
      Resource resourceResult = response.getResult();
      getResource = resourceResult.resourceId();
    // end-create_resource
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
    // begin-list_resources
      ListResourcesOptions listResourcesOptions = new ListResourcesOptions.Builder()
      .build();

      service.listResources().execute().getResult();
    // end-list_resources
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
    // begin-get_resource
      GetResourceOptions getResourceOptions = new GetResourceOptions.Builder()
      .resourceId("testString")
      .build();

      service.getResource(getResourceOptions).execute().getResult();
    // end-get_resource
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

  }
}
