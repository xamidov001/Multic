package uz.pdp.multic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.pdp.multic.models.MainDataModel
import uz.pdp.multic.paging.SplashRepository
import uz.pdp.multic.utils.SplashResource

class SplashViewModel(private var repository: SplashRepository) : ViewModel() {

    private val _mainDataListData = MutableLiveData<List<MainDataModel>?>(null)
    val mainDataListData: LiveData<List<MainDataModel>?>
        get() = _mainDataListData

    private val _errorData = MutableLiveData<String>(null)
    val errorData: LiveData<String>
        get() = _errorData

    private val _loading = MutableLiveData<Boolean>(null)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _totalPageData = MutableLiveData<Int>(0)
    val totalPageData: LiveData<Int>
        get() = _totalPageData

    var currentPage = 1
    var isLastPage = false
    var isLoading = false


    fun getMainDataModels(page: Int) = viewModelScope.launch {
        _totalPageData.value = repository.totalPage
        repository.getData(page).collect {
            when (it) {
                is SplashResource.Loading -> {
                    _loading.value = true
                }
                is SplashResource.Success -> {
                    _loading.value = false
                    _mainDataListData.value = it.data
                }
                is SplashResource.Error -> {
                    _loading.value = false
                    _errorData.value = it.error
                }
            }
        }
    }

}