package com.iml1s.epa.koin.module

import com.iml1s.epa.retrofit.FlowCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

val networkModule = module {
    factory { provideHttpLoggingInterceptor() }
    factory { provideMoshi() }
    factory { provideFlowCallAdapter() }
    factory { provideOkHttpClient(get()) }
    single { provideRetrofit(get(), get(), get()) }
}

fun provideHttpLoggingInterceptor(): Interceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

fun provideOkHttpClient(
    loggingInterceptor: Interceptor,
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

fun provideMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
    .build()

fun provideFlowCallAdapter(): CallAdapter.Factory = FlowCallAdapterFactory.create()

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi,
    flowCallAdapter: CallAdapter.Factory
): Retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(flowCallAdapter)
//    .baseUrl(BuildConfig.API_BASE_URL) // TODO:
    .build()
