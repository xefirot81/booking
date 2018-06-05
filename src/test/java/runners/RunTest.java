package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = "src/test/resources/features",
        monochrome = false,
        glue = {"classpath:config/step", "classpath:steps"},
        format = { /*"pretty",*/ "html:target/site/website-pretty", "rerun:target/rerun.txt", "json:target/website.json" }
)
public class RunTest {


}
