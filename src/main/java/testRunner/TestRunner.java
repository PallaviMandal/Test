package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import utilities.LaunchBrowsers;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "Features",
        glue = {"stepDefinations"},
        plugin = {"html:target/cucumber-reports.html"},
        monochrome = true
)

public class TestRunner {
    @AfterClass
    public static void afterClass() {
        LaunchBrowsers.driver.quit();
    }
}
