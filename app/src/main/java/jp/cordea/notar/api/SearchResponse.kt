package jp.cordea.notar.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val results: List<Result>
) {
    @Serializable
    data class Result(
        val id: String,
        val archived: Boolean,
        @SerialName("created_time")
        val createdAt: String,
        @Serializable(with = PagePropertiesSerializer::class)
        val properties: PageProperties,
        val url: String
    )
}
