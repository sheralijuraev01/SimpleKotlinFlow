package uz.sher.flowsimple.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.thdev.network.flowcalladapterfactory.FlowCallAdapterFactory

class ApiClient {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private var retrofit: Retrofit? = null

    fun retrofitInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().build())
                .addCallAdapterFactory(FlowCallAdapterFactory())
                .build()
        }
        return retrofit!!
    }
}