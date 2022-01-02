package jp.cordea.notar.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SearchResponse(
    val results: List<Result>
) {
    @Serializable
    class Result(
        val archived: Boolean,
        @SerialName("created_time")
        val createdAt: String,
        val title: List<Title>
    ) {
        @Serializable
        class Title(
            @SerialName("plain_text")
            val plainText: String
        )
    }
}
