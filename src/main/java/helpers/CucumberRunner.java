package helpers;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        strict = true,
        features = "classpath:features",
        glue = "steps"
)

public class CucumberRunner extends AbstractTestNGCucumberTests {
}
