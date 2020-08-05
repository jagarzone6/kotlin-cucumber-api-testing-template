package api

import model.Customer
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import support.Retrofit.instance as retrofit

interface CustomerI {

    @Headers("Content-Type: application/json")
    @POST("/customers")
    fun create(@Body customer: Customer, @Header("Authorization") authorization: String): Call<Customer>

    @GET("/customers/{id}")
    fun get(@Path(value = "id") id: String, @Header("Authorization") authorization: String): Call<Customer>

    companion object {
        fun customerService(): CustomerI {
            return retrofit!!.create(CustomerI::class.java)
        }
    }
}
