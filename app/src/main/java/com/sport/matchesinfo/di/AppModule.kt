package com.sport.matchesinfo.di

import com.sport.matchesinfo.BuildConfig
import com.sport.matchesinfo.api.Webservice
import com.sport.matchesinfo.data.NetworkClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Providing all other objects in the graph
 */
@Module
class AppModule {

    @Provides
    fun getWebservicesInterface(retrofit: Retrofit): Webservice {
        return retrofit.create(Webservice::class.java)
    }

    @Provides
    @Singleton
    fun getNetworkClient(retrofit: Retrofit): NetworkClient {
        return NetworkClient(retrofit)
    }

    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .readTimeout(120000, TimeUnit.MILLISECONDS) // 2 minutes
            .connectTimeout(120000, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun getClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}