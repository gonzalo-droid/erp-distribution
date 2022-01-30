package com.example.erp_distribution.di.module

import com.example.erp_distribution.BuildConfig
import com.example.erp_distribution.data.retrofit.ApiService
import com.example.erp_distribution.utils.Util
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Suppress("PrivatePropertyName")
    private val TIMEOUT: Long = 30


    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesRetrofit(providesGsonConverterFactory: GsonConverterFactory,
                         rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
                         okHttpClient: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Util.URL_BASE)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(providesGsonConverterFactory)// TODO ANTES GsonConverterFactory.create() DESPUES providesGsonConverterFactory
            .client(okHttpClient)
            .build()
    }


    @Suppress("ConstantConditionIf")
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = if (true) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder().apply {
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(logging)
            retryOnConnectionFailure(false)
        }.build()
    }


    @Provides
    @Singleton
    fun providesMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()

        return MoshiConverterFactory.create(moshi).asLenient()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder().setLenient().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).serializeNulls().create()

        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

}
