package com.example.catsanddogs.di

import android.content.Context
import com.example.catsanddogs.net.CatApiService
import com.example.catsanddogs.net.ConnectivityInterceptorImpl
import com.example.catsanddogs.net.DogApiService
import com.example.catsanddogs.ui.main.PageViewModel
import com.example.catsanddogs.utility.CAT_BASE_URL
import com.example.catsanddogs.utility.DOG_BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        PageViewModel(get(), get())
    }
}

val networkModule = module {
    single {
        provideRetrofitBuilder(
            androidContext(),
            DOG_BASE_URL
        ).create(DogApiService::class.java)
    }
    single {
        provideRetrofitBuilder(
            androidContext(),
            CAT_BASE_URL
        ).create(CatApiService::class.java)
    }
}

private fun provideRetrofitBuilder(context: Context, baseUrl: String) = Retrofit.Builder()
    .client(provideOkHttpClient(context))
    .baseUrl(baseUrl)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun provideOkHttpClient(context: Context) = OkHttpClient.Builder()
    .addInterceptor(ConnectivityInterceptorImpl(context))
    .build()