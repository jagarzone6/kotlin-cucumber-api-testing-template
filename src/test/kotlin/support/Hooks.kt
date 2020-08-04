package support

import io.cucumber.java8.En
import io.cucumber.java8.Scenario

class Hooks : En {

    init {

        Before { ->
            println("BeforeScenario")
            println(World.instance!!.bearerToken)
        }

        After { scenario: Scenario ->
            println("AfterScenario")
            println(World.instance!!.bearerToken)
            World.clear()
            scenario.attach("test data", "text/plain", "data")
        }
    }
}
