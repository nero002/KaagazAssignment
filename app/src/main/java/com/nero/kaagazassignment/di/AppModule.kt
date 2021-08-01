package com.nero.kaagazassignment.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


//    @Singleton
//    @Provides
//    fun providesMovieApi(): MovieClient {
//
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        return Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(OkHttpClient.Builder().addInterceptor(interceptor = interceptor).build())
//
//            .baseUrl(BASE_URL).build().create(MovieClient::class.java)
//
//    }
}