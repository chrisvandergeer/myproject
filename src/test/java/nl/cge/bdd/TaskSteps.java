package nl.cge.bdd;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.common.http.TestHTTPResource;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class TaskSteps {

    @TestHTTPResource("/tasks")
    URL tasksUrl;

    private Page page() {
        return PlaywrightHooks.page();
    }

    @Given("the user opens the tasks page")
    public void open_tasks_page() {
        page().navigate(tasksUrl.toString());
    }

    @When("the user adds a task named {string} with description {string} and date {string}")
    public void add_task(String name, String description, String date) {
        page().fill("input[name=name]", name);
        page().fill("input[name=description]", description);
        page().fill("input[name=endDate]", date);
        page().click("button[type=submit]");
    }

    @Then("the task list shows a task named {string}")
    public void task_list_shows(String name) {
        page().waitForSelector("#task-list");
        String text = page().textContent("#task-list");
        assertThat(text).contains(name);
    }
}
