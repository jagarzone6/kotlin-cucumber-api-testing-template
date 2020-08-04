package api

import model.UserAuth
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import support.Retrofit.instance as retrofit

interface Auth {

    @Headers("Content-Type: application/json")
    @POST("/sign-up")
    fun signUp(@Body userAuth: UserAuth): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("/login")
    fun login(@Body userAuth: UserAuth): Call<ResponseBody>

    companion object {
        fun authService(): Auth {
            return retrofit!!.create(Auth::class.java)
        }
    }
}
