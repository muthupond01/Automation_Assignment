package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/Web/features",
        glue = "stepDefinitions",plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true
)


@RunWith(Cucumber.class)

public class TestRunner {
	
}
