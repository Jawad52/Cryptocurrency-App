package com.jawad.cryptocurrencyapp.di

import com.jawad.cryptocurrencyapp.data.remote.service.ApiService
import com.jawad.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.jawad.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModules::class])
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun provideAPIService(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ) = provideServices(client, gsonConverterFactory, ApiService::class.java)

    @Provides
    @Singleton
    fun provideCoinRepository(apiService: ApiService): CoinRepository =
        CoinRepositoryImpl(apiService)

    private fun <T> provideServices(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        newClass: Class<T>
    ) =
        createRetrofit(client, gsonConverterFactory).create(newClass)

    private fun createRetrofit(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory) =
        Retrofit.Builder()
            .baseUrl("")
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
}