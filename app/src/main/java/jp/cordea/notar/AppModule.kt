package jp.cordea.notar

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.cordea.notar.api.NotionApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideJson(): Json =
        Json { ignoreUnknownKeys = true }

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
                        .addHeader(
                            "Notion-Version",
                            "2021-08-16"
                        )
                        .build()
                )
            }
            .build()

    @Provides
    @ExperimentalSerializationApi
    fun provideRetrofit(client: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.notion.com")
            .addConverterFactory(
                json.asConverterFactory(MediaType.parse("application/json")!!)
            )
            .client(client)
            .build()

    @Provides
    fun provideNotionApi(retrofit: Retrofit): NotionApi =
        retrofit.create(NotionApi::class.java)
}
