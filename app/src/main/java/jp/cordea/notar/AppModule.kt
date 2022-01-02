package jp.cordea.notar

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor {
                it.proceed(
                    it
                        .request()
                        .newBuilder()
                        .addHeader(
                            "Authorization",
                            "Bearer ${BuildConfig.API_TOKEN}"
                        )
                        .build()
                )
            }
            .build()

    @Provides
    @ExperimentalSerializationApi
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.notion.com")
            .addConverterFactory(
                Json.asConverterFactory(MediaType.parse("application/json")!!)
            )
            .client(client)
            .build()
}
