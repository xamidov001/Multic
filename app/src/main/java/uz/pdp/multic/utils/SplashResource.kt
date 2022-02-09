package uz.pdp.multic.utils

sealed class SplashResource<out R> {

    data class Success<out T>(val data: T) : SplashResource<T>()
    data class Error(val error: String) : SplashResource<Nothing>()
    object Loading : SplashResource<Nothing>()

}