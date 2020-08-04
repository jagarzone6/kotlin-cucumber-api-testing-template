package support

import utils.SingletonFactory
import retrofit2.Call
import retrofit2.Response

class World {
    var latestResponse: Any? = null
    var bearerToken: String = ""

    fun <T>makeApiCall(call: Call<T>): Response<T>? {
        latestResponse = call.execute()
        return latestResponse as Response<T>
    }

    companion object : SingletonFactory<World> {

        override fun clear() {
            instance = World()
        }

        override var instance: World? = World()
    }
}
