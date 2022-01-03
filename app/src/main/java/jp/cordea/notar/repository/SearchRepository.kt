package jp.cordea.notar.repository

import arrow.core.Either
import jp.cordea.notar.api.NotionApi
import jp.cordea.notar.api.SearchRequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val api: NotionApi
) {
    suspend fun search(query: String) = Either.catch {
        api.search(SearchRequestBody(query))
    }
}
