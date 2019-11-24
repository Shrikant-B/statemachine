package com.shrikantb.statemachine.data.di

import com.shrikantb.statemachine.data.remote.StateMachineApi
import com.shrikantb.statemachine.data.remote.StateMachineApiImpl
import okhttp3.Interceptor.Companion.invoke
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val apiKeyInterceptor = invoke {
            val originalReq = it.request()
            val newUrl = originalReq.url.newBuilder().addQueryParameter(
                "api_key",
                "b7cd3340a794e5a2f35e3abb820b497f"
            ).build()
            val newReq = originalReq.newBuilder().url(newUrl).build()
            it.proceed(newReq)
        }

        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<StateMachineApi> { StateMachineApiImpl(retrofit = get()) }
}