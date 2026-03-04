package com.jarval.core.di

import com.jarval.core.data.remote.KidoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit{
        val url = "https://someurl.com/"
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
        return retrofit.build()
    }


    @Provides
    fun provideKidoApi(retrofit: Retrofit): KidoApi{
        return retrofit.create(KidoApi::class.java)
    }

}