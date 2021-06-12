    package com.example.repository.network.di

    import android.content.Context
    import com.example.repository.BuildConfig
    import com.example.repository.network.AppApi
    import com.example.repository.network.utils.BASE_URL
    import com.example.repository.network.utils.TIMEOUT
    import okhttp3.Cache
    import okhttp3.Interceptor
    import okhttp3.OkHttpClient
    import okhttp3.Response
    import okhttp3.logging.HttpLoggingInterceptor
    import org.koin.dsl.module
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory
    import java.util.concurrent.TimeUnit


    val retrofitModule = module {
        single { providerHttpLoggingInterceptor() }
        single { providerCache(get()) }
        single { ApiInterceptor(get()) }
        single { providerOkHttpClient(get(), get()) }
        single { providerRetrofit(getProperty(BASE_URL), get()) }
        single { providerApi(get()) }

    }

    fun providerApi(retrofit: Retrofit): AppApi {
        return retrofit.create(AppApi::class.java)
    }

    fun providerRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        println("providerRetrofit::: ")
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            //.client(httpClient.build())
            .baseUrl(baseUrl)
            .build()
    }

    fun providerOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiInterceptor: ApiInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiInterceptor)

            //.authenticator(TokenAuthenticator())
            .build()
    }

    fun providerCache(context: Context): Cache {
        val cacheSize: Long = 10485760
        return Cache(context.cacheDir, cacheSize)
    }

    fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
       /* logging.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE*/
        return logging
    }

    class ApiInterceptor(
        private val context: Context
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            println("context::: ${context.javaClass.simpleName.toString()}")
            var request = chain.request()
            try {
                request = request.newBuilder()
                    .build()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
            return chain.proceed(request)
        }


    }