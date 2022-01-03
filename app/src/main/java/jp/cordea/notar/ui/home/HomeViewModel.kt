package jp.cordea.notar.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.cordea.notar.repository.SearchRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {
    private var _query = MutableLiveData("")
    val query: LiveData<String> get() = _query

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
                .tapLeft { it.printStackTrace() }
        }
    }
}
