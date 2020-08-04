package support

import io.cucumber.java8.En
import io.cucumber.java8.Scenario

class Hooks : En {

    init {

        Before { ->
            println("BeforeScenario")
        }

        After { scenario: Scenario ->
            println("AfterScenario")
            World.clear()
        }
    }
}
