package uz.pdp.multic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.pdp.multic.network.NetworkHelper
import uz.pdp.multic.paging.SplashRepository
import uz.pdp.multic.retro.ApiClient

class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(SplashRepository(ApiClient.retro)) as T
        }
        throw Exception("Error")
    }
}