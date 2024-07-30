package com.betrybe.trybnb.di

import com.betrybe.trybnb.BuildConfig.BASE_URL
import com.betrybe.trybnb.data.api.ApiService
import com.betrybe.trybnb.data.network.BookingDataSource
import com.betrybe.trybnb.data.network.LoginDataSource
import com.betrybe.trybnb.data.repository.BookingRepository
import com.betrybe.trybnb.data.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBookingDataSource(api: ApiService): BookingDataSource{
        return BookingDataSource(api)
    }

    @Provides
    @Singleton
    fun provideBookingRepository(dataSource: BookingDataSource): BookingRepository {
        return BookingRepository(dataSource)
    }

    @Provides
    @Singleton
    fun provideLoginDataSource(api : ApiService): LoginDataSource {
        return LoginDataSource(api)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(dataSource: LoginDataSource) : LoginRepository {
        return LoginRepository(dataSource)
    }
}
