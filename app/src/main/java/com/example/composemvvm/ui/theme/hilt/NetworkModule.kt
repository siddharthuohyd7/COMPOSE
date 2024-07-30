package com.example.composemvvm.ui.theme.hilt

import com.example.composemvvm.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return OkHttpClient().newBuilder().addInterceptor(Interceptor { chain ->
            val original: Request = chain.request()

            val request: Request = original.newBuilder()
                .header(
                    "X-MASTER_KEY",
                    "\$2a\$10\$swUBZEzYkQAOMwRTEGspAOAUEToy2UpD2y17xoc/2Grct0krbGHj2"
                )
                .header("Accept", "application/json")
                .header(
                    "X-ACCESS-KEY",
                    "\$2a\$10\$qeHGZeMJAvWM/RK5qHIKyOs6HrAZMXbUSmpHfCMqHpF55Dew8nGl6"
                )
                .method(original.method, original.body)
                .build()

            chain.proceed(request)
        }).addInterceptor(logging).build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.jsonbin.io")
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}