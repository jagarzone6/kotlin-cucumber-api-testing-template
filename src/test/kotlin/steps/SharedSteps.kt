package steps

import io.cucumber.java8.En
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.*
import retrofit2.Response
import support.World.Companion.instance as world

class SharedSteps : En {

    init {

        Then("^(?:.*)? should be rejected with status code (\\d{3})$") { statusCode: Integer ->
            val res: Response<ResponseBody> = world!!.latestResponse as Response<ResponseBody>
            assertEquals(statusCode, res.code())
        }
    }
}
