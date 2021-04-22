package com.example.testapp.di

import com.example.testapp.BuildConfig
import com.example.testapp.di.qualifiers.apiUrlQualifier
import com.example.testapp.di.qualifiers.logInterceptorQualifier
import com.example.testapp.features.generate.model.remote.GenerateCodeApi
import com.example.testapp.features.shibe.model.remote.ShibeApi
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    const val READ_TIMEOUT = 30L
    const val CONNECTION_TIMEOUT = 30L

    fun okHttpModule() = module {
        single {
            okHttpClient(
                loggingInterceptor = get(logInterceptorQualifier),
                tokenInterceptor = null,
                tokenAuthenticator = null,
                pingIntervalSec = 30
            )
        }

        single<Interceptor>(logInterceptorQualifier) {
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY }
            } else {
                HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.NONE }
            }
        }


    }

    fun retrofitModule() = module {
        single<Converter.Factory> { GsonConverterFactory.create(get()) }
//        single<Converter.Factory> { ScalarsConverterFactory.create() }

        single(apiUrlQualifier) { "https://shibe.online" }

        single<Retrofit>() {
            createRetrofit(
                url = get(apiUrlQualifier),
                okHttpClient =  get(),
                converterFactory = get()
            )
        }


        single { get<Retrofit>().create(GenerateCodeApi::class.java) }
        single { get<Retrofit>().create(ShibeApi::class.java) }

    }



    private fun okHttpClient(
        loggingInterceptor: Interceptor?,
        tokenInterceptor: Interceptor?,
        tokenAuthenticator: Authenticator?,
        pingIntervalSec: Long = 0
    ) = with(OkHttpClient.Builder()) {
        connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        pingInterval(pingIntervalSec, TimeUnit.SECONDS)

        /*if (BuildConfig.DEBUG)*/ loggingInterceptor?.let { addInterceptor(it) }
        tokenInterceptor?.let { addInterceptor(it) }
        tokenAuthenticator?.let { authenticator(it) }

        build()
    }

    private fun createRetrofit(
        url: String, okHttpClient: OkHttpClient, converterFactory: Converter.Factory
    ) = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()

}