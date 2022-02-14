package id.bts.movietestassesment.data.retrofit

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import id.bts.movietestassesment.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitClient @Inject constructor(
    @ApplicationContext context: Context
) {

    private var retrofit: Retrofit? = null
    private val baseUrl: String get() = BASE_URL

    init {
        createRetrofitInstance()
    }

    private fun createRetrofitInstance() {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getClient())
            .addConverterFactory(getConverter())
            .build()
    }

    private fun getClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor { chain ->
            val original = chain.request()
            val modified = original.newBuilder()
            modified.addHeader("Content-Type", "application/json")
            modified.method(original.method, original.body)
            val request: Request = modified.build()
            return@addInterceptor chain.proceed(request)
        }

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)

        builder.callTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)

        return builder.build()
    }

    private fun getConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    fun <T> create(defClass: Class<T>): T {
        return retrofit!!.create(defClass)
    }
}