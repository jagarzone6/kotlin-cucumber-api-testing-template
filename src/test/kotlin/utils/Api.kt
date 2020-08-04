package utils

import retrofit2.Call
import retrofit2.Response
import support.World.Companion.instance as world

fun <T> makeApiCall(call: Call<T>): Response<*> {
    world?.latestResponse = call.execute()
    return world?.latestResponse as Response<*>
}
