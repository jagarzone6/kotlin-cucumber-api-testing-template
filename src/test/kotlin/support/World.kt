package support

import retrofit2.Response
import utils.SingletonFactory

class World {
    var latestResponse: Response<*>? = null
    var bearerToken: String = ""

    companion object : SingletonFactory<World> {

        override fun clear() {
            instance = World()
        }

        override var instance: World? = World()
    }
}
