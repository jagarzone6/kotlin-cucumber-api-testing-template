package steps

import api.Auth
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import model.UserAuth
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.*
import retrofit2.Response
import support.World.Companion.instance as world

class AuthSteps : En {

    init {
        DataTableType { entry: Map<String, String> ->
            UserAuth(entry["username"], entry["password"])
        }


        Given("I am registered in the app with:") { credentialsTable: DataTable ->
            val credentials: List<UserAuth> = credentialsTable.asList(UserAuth::class.java)
            val res = world!!.makeApiCall(Auth.authService().signUp(credentials[0]))
            assertTrue(res!!.code() == 200 || res!!.code() == 403)
        }

        When("I login with credentials:") { credentialsTable: DataTable ->
            val credentials: List<UserAuth> = credentialsTable.asList(UserAuth::class.java)
            val res = world!!.makeApiCall(Auth.authService().login(credentials[0]))
            if (res != null) {
                world!!.bearerToken = res.headers().get("Authorization").toString()
            }
        }

        Then("I should be logged in successfully") { ->
            val res: Response<ResponseBody> = world!!.latestResponse as Response<ResponseBody>
            assertEquals(200, res!!.code())
        }

        Then("Login should be rejected with status code {int}") { statusCode: Integer ->
            val res: Response<ResponseBody> = world!!.latestResponse as Response<ResponseBody>
            assertEquals(statusCode, res!!.code())
        }

    }
}
