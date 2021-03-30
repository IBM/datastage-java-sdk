#How to run tests
##Pre-requisites
1. Make sure mvn is installed on your computer.

##Integration Tests
1. Create a `datastage_v3.env` file in the `datastage-java-sdk` directory using `datastage_v3.env.hide` as an example. 
2. Update `datastage_v3.env` file with your own APIKEY and PROJECT_ID.
3. Update AUTH_URL to point to the correct authentication server and URL to point to the correct DataStage URL.
4. Go to Project's root directory `datastage-java-sdk/` directory.
5. Run `mvn test -DfailIfNoTests=false -Dtest=DatastageIT`

##Unit Tests
1. Go to Project's root directory `datastage-java-sdk/` directory.
2. Run mvn test.
