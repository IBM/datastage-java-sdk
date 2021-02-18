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

import com.ibm.cloud.my_services.example_service.v1.ExampleService;
import com.ibm.cloud.my_services.example_service.v1.model.CreateResourceOptions;
import com.ibm.cloud.my_services.example_service.v1.model.GetResourceEncodedOptions;
import com.ibm.cloud.my_services.example_service.v1.model.GetResourceOptions;
import com.ibm.cloud.my_services.example_service.v1.model.ListResourcesOptions;
import com.ibm.cloud.my_services.example_service.v1.model.Resource;
import com.ibm.cloud.my_services.example_service.v1.model.Resources;
import com.ibm.cloud.my_services.example_service.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.NoAuthAuthenticator;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;

import com.ibm.cloud.sdk.core.util.EnvironmentUtils;

import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the ExampleService service.
 */
@PrepareForTest({ EnvironmentUtils.class })
@PowerMockIgnore({"javax.net.ssl.*", "org.mockito.*"})
public class ExampleServiceTest extends PowerMockTestCase {

  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  protected MockWebServer server;
  protected ExampleService exampleServiceService;

  // Creates a mock set of environment variables that are returned by EnvironmentUtils.getenv().
  private Map<String, String> getTestProcessEnvironment() {
    Map<String, String> env = new HashMap<>();
    env.put("TESTSERVICE_AUTH_TYPE", "noAuth");
    return env;
  }

  public void constructClientService() throws Throwable {
    PowerMockito.spy(EnvironmentUtils.class);
    PowerMockito.when(EnvironmentUtils.getenv()).thenReturn(getTestProcessEnvironment());
    final String serviceName = "testService";

    exampleServiceService = ExampleService.newInstance(serviceName);
    String url = server.url("/").toString();
    exampleServiceService.setServiceUrl(url);
  }

  /**
  * Negative Test - construct the service with a null authenticator.
  */
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testConstructorWithNullAuthenticator() throws Throwable {
    final String serviceName = "testService";

    new ExampleService(serviceName, null);
  }

  @Test
  public void testListResourcesWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"offset\": 6, \"limit\": 5, \"resources\": [{\"resource_id\": \"resourceId\", \"name\": \"name\", \"tag\": \"tag\", \"read_only\": \"readOnly\"}]}";
    String listResourcesPath = java.net.URLEncoder.encode("/resources", "UTF-8").replace("%2F", "/");

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the ListResourcesOptions model
    ListResourcesOptions listResourcesOptionsModel = new ListResourcesOptions.Builder()
    .limit(Long.valueOf("26"))
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Resources> response = exampleServiceService.listResources(listResourcesOptionsModel).execute();
    assertNotNull(response);
    Resources responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(Long.valueOf(query.get("limit")), Long.valueOf("26"));
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listResourcesPath);
  }

  @Test
  public void testCreateResourceWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"resource_id\": \"resourceId\", \"name\": \"name\", \"tag\": \"tag\", \"read_only\": \"readOnly\"}";
    String createResourcePath = java.net.URLEncoder.encode("/resources", "UTF-8").replace("%2F", "/");

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(201)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the CreateResourceOptions model
    CreateResourceOptions createResourceOptionsModel = new CreateResourceOptions.Builder()
    .resourceId("testString")
    .name("testString")
    .tag("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Resource> response = exampleServiceService.createResource(createResourceOptionsModel).execute();
    assertNotNull(response);
    Resource responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, createResourcePath);
  }

  // Test the createResource operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testCreateResourceNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    exampleServiceService.createResource(null).execute();
  }

  @Test
  public void testGetResourceWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"resource_id\": \"resourceId\", \"name\": \"name\", \"tag\": \"tag\", \"read_only\": \"readOnly\"}";
    String getResourcePath = java.net.URLEncoder.encode("/resources/testString", "UTF-8").replace("%2F", "/");

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the GetResourceOptions model
    GetResourceOptions getResourceOptionsModel = new GetResourceOptions.Builder()
    .resourceId("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Resource> response = exampleServiceService.getResource(getResourceOptionsModel).execute();
    assertNotNull(response);
    Resource responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getResourcePath);
  }

  // Test the getResource operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetResourceNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    exampleServiceService.getResource(null).execute();
  }

  @Test
  public void testGetResourceEncodedWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"resource_id\": \"resourceId\", \"name\": \"name\", \"tag\": \"tag\", \"read_only\": \"readOnly\"}";
    String getResourceEncodedPath = java.net.URLEncoder.encode("/resources/encoded/url%3encoded%3resource%3id", "UTF-8").replace("%2F", "/");

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the GetResourceEncodedOptions model
    GetResourceEncodedOptions getResourceEncodedOptionsModel = new GetResourceEncodedOptions.Builder()
    .urlEncodedResourceId("url%3encoded%3resource%3id")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Resource> response = exampleServiceService.getResourceEncoded(getResourceEncodedOptionsModel).execute();
    assertNotNull(response);
    Resource responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getResourceEncodedPath);
  }

  // Test the getResourceEncoded operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetResourceEncodedNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    exampleServiceService.getResourceEncoded(null).execute();
  }

  /** Initialize the server */
  @BeforeMethod
  public void setUpMockServer() {
    try {
        server = new MockWebServer();
        // register handler
        server.start();
        }
    catch (IOException err) {
        fail("Failed to instantiate mock web server");
    }
  }

  @AfterMethod
  public void tearDownMockServer() throws IOException {
    server.shutdown();
    exampleServiceService = null;
  }
}