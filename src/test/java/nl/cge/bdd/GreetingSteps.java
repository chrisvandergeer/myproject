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
public class GreetingSteps {

    @TestHTTPResource("/greeting")
    URL greetingUrl;

    private Page page() {
        return PlaywrightHooks.page();
    }

    @Given("the user opens the greeting page")
    public void open_greeting_page() {
        page().navigate(greetingUrl.toString());
    }

    @When("the user enters the name {string} and submits")
    public void enter_name(String name) {
        page().fill("input[name=name]", name);
        page().click("button[type=submit]");
    }

    @Then("the page shows the greeting for {string}")
    public void page_shows_greeting(String name) {
        page().waitForSelector("#result");
        String text = page().textContent("#result");
        assertThat(text).contains(name);
    }
}
