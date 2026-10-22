package com.example.w3m.core.di

import com.example.w3m.feature.ads.data.remote.AdsApi
import com.example.w3m.feature.auth.data.remote.AuthApi
import com.example.w3m.feature.bots.data.remote.BotsApi
import com.example.w3m.feature.groups.data.remote.GroupApi
import com.example.w3m.feature.users.data.remote.UserApi
import com.example.w3m.core.common.Content
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule  {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi{
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideBuilder(moshi: Moshi): Retrofit{
        val client = OkHttpClient
            .Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit
            .Builder()
            .baseUrl(Content.Urls.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun apiBuilderAds(retrofit:Retrofit): AdsApi {
        return retrofit.create(AdsApi::class.java)
    }

    @Singleton
    @Provides
    fun apiBuilderAuth(retrofit:Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
    @Singleton
    @Provides
    fun apiBuilderBots(retrofit:Retrofit): BotsApi {
        return retrofit.create(BotsApi::class.java)
    }

    @Singleton
    @Provides
    fun apiBuilderUser(retrofit:Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun apiBuilderGroup(retrofit:Retrofit): GroupApi {
        return retrofit.create(GroupApi::class.java)
    }
}