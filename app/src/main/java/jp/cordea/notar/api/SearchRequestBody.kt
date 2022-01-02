package jp.cordea.notar.api

import kotlinx.serialization.Serializable

@Serializable
class SearchRequestBody(
    val query: String,
    val filter: Filter
) {
    @Serializable
    class Filter(
        val value: String?,
        val property: String?
    )
}
