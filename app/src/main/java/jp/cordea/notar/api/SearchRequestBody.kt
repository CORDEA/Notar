package jp.cordea.notar.api

import kotlinx.serialization.Serializable

@Serializable
class SearchRequestBody(
    val query: String
)
