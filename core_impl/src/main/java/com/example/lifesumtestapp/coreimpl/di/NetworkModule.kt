package com.example.lifesumtestapp.coreimpl.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    fun provideUrl(): HttpUrl {
        return "https://api.lifesum.com/".toHttpUrl()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(moshi: Moshi): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        httpUrl: HttpUrl,
        okHttpClient: OkHttpClient,
        retrofitBuilder: Retrofit.Builder
    ): Retrofit {
        return retrofitBuilder
            .client(okHttpClient)
            .baseUrl(httpUrl)
            .build()
    }
}
