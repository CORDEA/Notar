package jp.cordea.notar.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.cordea.notar.api.PageProperty
import jp.cordea.notar.api.SearchResponse
import jp.cordea.notar.repository.SearchRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {
    private var _query = MutableLiveData("")
    val query: LiveData<String> get() = _query

    private var _items = MutableLiveData(emptyList<HomeItemViewModel>())
    val items: LiveData<List<HomeItemViewModel>> get() = _items

    fun onSearchQueryChanged(query: String) {
        _query.value = query
    }

    fun onSearchQuerySubmitted() {
        val query = _query.value
        if (query.isNullOrBlank()) {
            return
        }
        viewModelScope.launch {
            repository.search(query)
                .map { response ->
                    response.results.map {
                        HomeItemViewModel.from(it)
                    }
                }
                .tap { _items.value = it }
                .tapLeft {
                    it.printStackTrace()
                    _items.value = emptyList()
                }
        }
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
