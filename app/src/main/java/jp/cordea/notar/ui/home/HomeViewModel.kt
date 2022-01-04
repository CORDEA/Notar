package jp.cordea.notar.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.cordea.notar.api.PageProperty
import jp.cordea.notar.api.SearchResponse
import jp.cordea.notar.usecase.GetObjectsUseCase
import jp.cordea.notar.usecase.SearchObjectsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getObjectsUseCase: GetObjectsUseCase,
    private val searchObjectsUseCase: SearchObjectsUseCase
) : ViewModel() {
    private var _query = MutableLiveData("")
    val query: LiveData<String> get() = _query

    private var _items = MutableLiveData(emptyList<HomeItemViewModel>())
    val items: LiveData<List<HomeItemViewModel>> get() = _items

    init {
        refresh()
    }

    fun onSearchQueryChanged(query: String) {
        _query.value = query
    }

    fun onSearchQuerySubmitted() {
        val query = _query.value
        if (query.isNullOrBlank()) {
            refresh()
            return
        }
        viewModelScope.launch {
            _items.value = searchObjectsUseCase.execute(query)
                .tapLeft { it.printStackTrace() }
                .foldResponse()
        }
    }

    private fun refresh() = viewModelScope.launch {
        _items.value = getObjectsUseCase.execute()
            .tapLeft { it.printStackTrace() }
            .foldResponse()
    }

    private fun Either<Throwable, SearchResponse>.foldResponse(): List<HomeItemViewModel> {
        return fold(
            { emptyList() },
            { it.results.map(HomeItemViewModel::from) }
        )
    }
}

class HomeItemViewModel(
    val title: String,
    val url: String
) {
    companion object {
        fun from(result: SearchResponse.Result): HomeItemViewModel {
            return HomeItemViewModel(
                title = result.properties.title.joinToString("") {
                    when (it) {
                        is PageProperty.Text -> it.content
                        PageProperty.Unknown -> ""
                    }
                },
                url = result.url
            )
        }
    }
}
