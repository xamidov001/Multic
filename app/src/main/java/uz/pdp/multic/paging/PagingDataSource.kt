package uz.pdp.multic.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import uz.pdp.multic.models.page.Result
import uz.pdp.multic.retro.ApiClient
//
//class PagingDataSource: PagingSource<Int, Result>() {
//    private val photoRepository = SplashRepository(ApiClient.retro)
//
//    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
//        return state.anchorPosition
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
//        try {
//            val currentPage = params.key ?: 1
//
//            var loadResult: LoadResult.Page<Int, Result>? = null
//
//            if (params.key ?: 1 >= 1) {
//                photoRepository.getData(currentPage)
//                    .catch {
//                        loadResult = LoadResult.Page(
//                            emptyList(),
//                            currentPage - 1, currentPage + 1
//                        )
//                    }.collect {
//                        if (it.isSuccessful) {
//                            loadResult =
//                                LoadResult.Page(
//                                    it.body()?.results?: emptyList(),
//                                    currentPage - 1, currentPage + 1
//                                )
//                        }
//                    }
//
//            } else {
//                loadResult =
//                    LoadResult.Page(
//                        emptyList(),
//                        null, currentPage + 1
//                    )
//            }
//
//            return loadResult!!
//        } catch (e: Exception) {
//            return LoadResult.Error(e.fillInStackTrace())
//        }
//    }
//}