package nl.cge.bdd;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class PlaywrightHooks {
    private static Playwright playwright;
    private static Browser browser;
    private static final ThreadLocal<Page> PAGE = new ThreadLocal<>();

    @BeforeAll
    public static void init() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }

    @AfterAll
    public static void cleanup() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @Before
    public void createPage() {
        PAGE.set(browser.newPage());
    }

    @After
    public void closePage() {
        Page page = PAGE.get();
        if (page != null) page.close();
        PAGE.remove();
    }

    public static Page page() {
        return PAGE.get();
    }
}
