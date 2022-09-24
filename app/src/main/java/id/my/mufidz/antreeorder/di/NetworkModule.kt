package id.my.mufidz.antreeorder.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.mufidz.antreeorder.data.ApiServices
import id.my.mufidz.antreeorder.utils.Const
import id.my.mufidz.antreeorder.utils.Utils
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggerInterceptor = HttpLoggingInterceptor { message ->
            Timber.tag("Network Request").d(message)
        }.also {
            it.redactHeader("Authorization")
            it.redactHeader("Cookies")
            it.level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient().newBuilder().apply {
            addInterceptor(loggerInterceptor)
            connectTimeout(20, TimeUnit.SECONDS)
            readTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
        }.build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Const.baseUrl).client(okHttpClient)
            .addConverterFactory(Utils.json.asConverterFactory(Const.applicationJson.toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideApiServices(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)
}