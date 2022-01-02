package jp.cordea.notar.api

import retrofit2.http.Body
import retrofit2.http.POST

interface NotionApi {
    @POST("/v1/search")
    suspend fun search(@Body body: SearchRequestBody): SearchResponse
}
