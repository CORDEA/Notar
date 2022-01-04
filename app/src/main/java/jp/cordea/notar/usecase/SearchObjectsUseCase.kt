package jp.cordea.notar.usecase

import dagger.Reusable
import jp.cordea.notar.repository.SearchRepository
import javax.inject.Inject

@Reusable
class SearchObjectsUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend fun execute(query: String) = repository.search(query)
}
