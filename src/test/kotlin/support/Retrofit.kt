package support

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.SingletonFactory


object Retrofit : SingletonFactory<Retrofit> {
    override var instance: Retrofit? = null

    @JvmStatic
    fun build(baseUrl: String) {
        val gson: Gson = GsonBuilder().serializeNulls().create()
        instance = Retrofit.Builder()
                // here we set the base url of our API
                .baseUrl(baseUrl)
                // add the JSON dependency so we can handle json APIs
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        println("Created retrofit instance: $instance")
    }
}
