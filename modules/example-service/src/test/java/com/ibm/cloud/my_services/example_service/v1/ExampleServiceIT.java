package com.ibm.cloud.my_services.example_service.v1;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import static org.testng.Assert.assertNotNull;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ibm.cloud.my_services.example_service.v1.model.CreateResourceOptions;
import com.ibm.cloud.my_services.example_service.v1.model.GetResourceOptions;
import com.ibm.cloud.my_services.example_service.v1.model.Resource;
import com.ibm.cloud.my_services.example_service.v1.model.Resources;
import com.ibm.cloud.my_services.test.SdkIntegrationTestBase;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.util.CredentialUtils;
import com.ibm.cloud.sdk.core.service.exception.NotFoundException;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;

/**
 * This class contains integration tests for example service.
 *
 * Notes:
 * 1. By providing the name of our config file ("example-service.env") via the
 *    getConfigFilename() method below, the base class (SdkIntegrationTestBase) will be able to
 *    mock up the getenv() method to cause the Java core's CredentialUtils class to "see" the
 *    config file via the mocked value of the IBM_CREDENTIALS_FILE env var.
 *
 * 2. The base class will automatically set the "skipTests" flag to true if it can't find the config file.
 *
 * 3. The base class contains a "before method" function that will automatically skip each test method if
 *    the "skipTests" flag is true.   This means that this subclass doesn't need to concern
 *    itself with skipping tests in the event that the config file is not available.
 *
 * 4. This example testcase uses the "dependsOnMethods" attribute of the @Test annotation to ensure that the test
 *    methods are executed in the the order they appear in this file.  Without this, there's no guaranteed ordering
 *    imposed by TestNG.
 *
 * 5. Be sure to following the instructions here:
 *    https://github.ibm.com/CloudEngineering/java-sdk-template/blob/main/README_FIRST.md#integration-tests
 *    to start up an instance of the Example Service prior to running the integraton test.
 *
 * 6. Before running this test, rename example-service.env.hide to example-service.env.
 */
public class ExampleServiceIT extends SdkIntegrationTestBase {

    // Example service v1 integration
    public ExampleService service = null;
    public static Map<String, String> config = null;

    /**
     * This method provides our config filename to the base class.
     */
    public String getConfigFilename() {
        return "../../example-service.env";
    }

    /**
     * This method is invoked before any of the @Test-annotated methods, and is responsible for
     * creating the instance of the service that will be used by the rest of the test methods,
     * as well as any other required initialization.
     */
    @BeforeClass
    public void constructService() {
        // Ask super if we should skip the tests.
        if (skipTests()) {
            return;
        }

        /**
         * Construct our service client instance via external config (see the example-service.env file for details).
         * The newInstance() method will load up our config file and look for properties that start with
         * "EXAMPLE_SERVICE_" (the default service name associated with the Example Service, as specified by the
         * ExampleService.DEFAULT_SERVICE_NAME constant).
         * Specifically, newInstance() will construct an authenticator based on the value of the
         * EXAMPLE_SERVICE_AUTH_TYPE property.   After constructing the appropriate authenticator, it will construct
         * an instance of the service and then set the URL to the value specified by the EXAMPLE_SERVICE_URL property.
         */
        service = ExampleService.newInstance();
        assertNotNull(service);
        assertNotNull(service.getServiceUrl());

        // Load up our test-specific config properties.
        config = CredentialUtils.getServiceProperties(ExampleService.DEFAULT_SERVICE_NAME);
        assertNotNull(config);
        assertFalse(config.isEmpty());
        assertEquals(service.getServiceUrl(), config.get("URL"));

        System.out.println("Setup complete.");
    }

    /**
     * Each of the @Test-annotated methods below will be skipped if the config file (example-service.env)
     * does not exist.
     */

     @Test
    public void testCreateResource() {
        try {
            CreateResourceOptions options = new CreateResourceOptions.Builder()
                .resourceId("3")
                .name("To Kill a Mockingbird")
                .tag("Book")
                .build();
            // Invoke operation
            Response<Resource> response = service.createResource(options).execute();
            // Validate response
            assertNotNull(response);
            assertEquals(response.getStatusCode(), 201);

            Resource result = response.getResult();
            assertNotNull(result);
            assertEquals(result.resourceId(), "3");
            assertEquals(result.name(), "To Kill a Mockingbird");
            assertEquals(result.tag(), "Book");
        } catch (ServiceResponseException e) {
            fail(String.format("Service returned status code %s: %s\nError details: %s",
                    e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
        }
    }

    @Test
    public void testGetResource() {
        try {
            GetResourceOptions options = new GetResourceOptions.Builder()
                .resourceId("1")
                .build();
            // Invoke operation
            Response<Resource> response = service.getResource(options).execute();
            // Validate response
            assertNotNull(response);
            assertEquals(response.getStatusCode(), 200);

            Resource result = response.getResult();
            assertNotNull(result);
            assertEquals(result.resourceId(), "1");
            assertEquals(result.name(), "The Great Gatsby");
            assertEquals(result.tag(), "Book");
        } catch (ServiceResponseException e) {
            fail(String.format("Service returned status code %s: %s\nError details: %s",
                    e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
        }
    }

    @Test(expectedExceptions = { NotFoundException.class })
    public void testGetResourceNegativeTest() {
        try {
            GetResourceOptions options = new GetResourceOptions.Builder()
                .resourceId("BAD_RESOURCE_ID")
                .build();
            // Invoke operation
            service.getResource(options).execute().getResult();
        } catch (ServiceResponseException e) {
            // Validate response
            assertEquals(e.getStatusCode(), 404);
            System.out.println(String.format("Service returned status code %s: %s\nError details: %s",
                    e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
            throw e;
        }
    }

    @Test
    public void testListResources() {
        try {
            // Invoke operation
            Response<Resources> response = service.listResources().execute();
            // Validate response
            assertNotNull(response);
            assertEquals(response.getStatusCode(), 200);

            Resources result = response.getResult();
            assertNotNull(result);
            assertNotNull(result.getResources());
        } catch (ServiceResponseException e) {
            fail(String.format("Service returned status code %s: %s\nError details: %s", e.getStatusCode(),
                    e.getMessage(), e.getDebuggingInfo()));
        }
    }
    @AfterClass
    public void tearDown() {
        // Add any clean up logic here
        System.out.println("Clean up complete.");
    }
}
