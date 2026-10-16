package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest
{
    Playwright playwright;
    Browser browser;
    Page page;
    String base_url;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException
    {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser")!=null?System.getProperty("browser") : prop.getProperty("browser");
        String env = System.getProperty("env")!=null?System.getProperty("env") : prop.getProperty("env");

        playwright = Playwright.create();

        if(browserName.equalsIgnoreCase("firefox"))
        {
            browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        } else if (browserName.equalsIgnoreCase("safari"))
        {
            browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }
        else
        {
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }


        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        page = browser.newPage();
        page.setViewportSize(1920, 1080);
        base_url = prop.getProperty(env+".base_url");
        page.setDefaultTimeout(8000);  // Default timeout for any action to be performed is 30 seconds.
        PlaywrightAssertions.setDefaultAssertionTimeout(7000); //Default timeout for assertion is 5 seconds.
    }

    @AfterMethod
    public void tearDown()
    {

    }
}
