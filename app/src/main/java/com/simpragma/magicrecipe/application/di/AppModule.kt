package com.simpragma.magicrecipe.application.di

import android.content.Context
import com.countries.assignment.network.ServiceInterceptor
import com.google.gson.Gson
import com.simpragma.magicrecipe.BuildConfig
import com.simpragma.magicrecipe.common.AppConstant.BASE_URL
import com.simpragma.magicrecipe.network.ApiService
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitApi (context: Context, serviceInterceptor: ServiceInterceptor) : Retrofit {
        var url = URL(BASE_URL)
        var  builder = OkHttpClient
            .Builder()

        if(BuildConfig.DEBUG) {
            var httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addNetworkInterceptor(httpLoggingInterceptor)
        }

        builder.addInterceptor(serviceInterceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(builder.build())
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideTasksRemoteDataSource(context: Context, serviceInterceptor: ServiceInterceptor): ApiService {
        return provideRetrofitApi(context, serviceInterceptor).create(ApiService::class.java)
    }
}