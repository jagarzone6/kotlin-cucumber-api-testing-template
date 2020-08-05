package steps

import api.AuthI.Companion.authService
import utils.makeApiCall
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
            val res = signUp(credentialsTable)
            assertTrue(res.code() == 200 || res.code() == 403)
        }

        Given("I am logged in the app") { credentialsTable: DataTable ->
            signUp(credentialsTable)
            val res = logIn(credentialsTable)
            assertEquals(200, res.code())
        }

        Given("I am not logged in the app") { ->
            world!!.bearerToken = ""
        }

        When("I login with credentials:") { credentialsTable: DataTable ->
            logIn(credentialsTable)
        }

        Then("I should be logged in successfully") { ->
            val res: Response<ResponseBody> = world!!.latestResponse as Response<ResponseBody>
            assertEquals(200, res.code())
        }

    }
}

val signUp = fun(credentialsTable: DataTable): Response<*> {
    val credentials: List<UserAuth> = credentialsTable.asList(UserAuth::class.java)
    return makeApiCall(authService().signUp(credentials[0]))
}

val logIn = fun(credentialsTable: DataTable): Response<*> {
    val credentials: List<UserAuth> = credentialsTable.asList(UserAuth::class.java)
    val res = makeApiCall(authService().login(credentials[0]))
    world!!.bearerToken = res.headers().get("Authorization").toString()
    return res
}
