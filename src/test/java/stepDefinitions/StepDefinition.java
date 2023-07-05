package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import org.junit.Assert;
import pojo.CreateIssue;
import pojo.CreateProject;
import pojo.DeleteProject;
import utils.RestMethods;
import utils.RequestResources;
import utils.Utils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static utils.Utils.getJsonPath;

public class StepDefinition {

    private String endpointUrl;
    private Response response;
    CreateProject createProject = new CreateProject();
    CreateIssue createIssue = new CreateIssue();
    DeleteProject deleteProject = new DeleteProject();

    RestMethods restMethods = new RestMethods();

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @Given("User is an authorized user")
    public void userIsAnAuthorizedUser() {
        // valid user token to be added in the request header for user authentication
    }

    @And("Valid  project name {string} given in the request payload")
    public void setValidProjectNameToRequestPayload(String projectName) {
        createProject.setName(projectName + "-" + timestamp.getTime());
    }


    @When("User call the create project request")
    public void userCallTheCreateProjectRequest() throws IOException {
        endpointUrl = Utils.getBaseUrl()+ RequestResources.projectsPath.getResource();
        response = restMethods.post(endpointUrl, createProject);
        String projectId = getJsonPath(response, "id");

    }

    @Then("Project should be created successfully with response code {int}")
    public void project_created_successfully(int statusCode) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @And("The project name should start with {string}")
    public void theProjectNameShouldStartWith(String expectedName) {
        String actualName = getJsonPath(response, "name");
        Assert.assertTrue(actualName.startsWith(expectedName));

    }


}
