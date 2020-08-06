import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.yaml.snakeyaml.Yaml
import support.Retrofit
import java.io.InputStream

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
            val env: String = System.getenv("ENV") ?: "dev"
            Retrofit.build(getBaseUrl(env))
        }

        @JvmStatic
        fun getBaseUrl(env: String): String {
            val yaml = Yaml()
            val inputStream: InputStream = this::class.java
                    .getResourceAsStream("data/environments.yaml")
            val obj: Map<String, Any> = yaml.load(inputStream)
            return (obj[env] as Map<*, *>)["baseUrl"].toString()
        }
    }
}
