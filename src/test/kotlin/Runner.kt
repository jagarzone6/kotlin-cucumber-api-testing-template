import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.BeforeClass
import org.junit.runner.RunWith
import support.Retrofit

@RunWith(Cucumber::class)
@CucumberOptions(
        plugin = ["pretty", "json:build/cucumber/report.json",
            "de.monochromata.cucumber.report.PrettyReports:build/cucumber/pretty-cucumber"],
        tags = "")
class Runner {

    companion object {
        @BeforeClass()
        @JvmStatic
        fun setUp() {
            Retrofit.build("http://localhost:8080")
        }
    }
}
