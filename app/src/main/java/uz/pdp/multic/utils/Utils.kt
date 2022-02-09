package uz.pdp.multic.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException

object Utils {

    fun <T> Flow<SplashResource<T>>.handleFlowException(): Flow<SplashResource<T>> {
        return catch { e ->
            when (e) {
                is HttpException -> {
                    emit(SplashResource.Error(e.message()))
                }
                else -> SplashResource.Error(e.message ?: "Something went wrong!")
            }
        }
    }
}