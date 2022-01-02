package jp.cordea.notar.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {
    private var _query = MutableLiveData("")
    val query: LiveData<String> get() = _query

    fun onSearchQueryChanged(query: String) {
        _query.value = query
    }
}
