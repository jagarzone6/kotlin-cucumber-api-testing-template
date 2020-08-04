package support

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.SingletonFactory


object Retrofit : SingletonFactory<Retrofit> {
    override var instance: Retrofit? = null
    @JvmStatic
    fun build(baseUrl: String) {
        instance = Retrofit.Builder()
                // here we set the base url of our API
                .baseUrl(baseUrl)
                // add the JSON dependency so we can handle json APIs
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        println("Created retrofit instance: $instance")
    }
}
