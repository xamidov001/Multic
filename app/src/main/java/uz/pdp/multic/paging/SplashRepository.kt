package uz.pdp.multic.paging

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import uz.pdp.multic.models.MainDataModel
import uz.pdp.multic.models.page.Result
import uz.pdp.multic.retro.ApiService
import uz.pdp.multic.utils.SplashResource
import uz.pdp.multic.utils.Utils.handleFlowException

class SplashRepository(private val apiService: ApiService) {

    var totalPage: Int = 0

    fun getData(page: Int) = flow {
        emit(SplashResource.Loading)
        val pageList = apiService.getPageList(page)

        if (pageList.isSuccessful) {
            val body = pageList.body()
            totalPage = body?.info?.pages ?: 0
            val list = ArrayList<MainDataModel>()
            body?.results?.forEach { result ->
                getEpi(result).collect {
                    when (it) {
                        is SplashResource.Loading -> {

                        }
                        is SplashResource.Success -> {
                            val episode = it.data.body()
                            list.add(MainDataModel(result, episode!!))
                        }
                        is SplashResource.Error -> {

                        }
                    }
                }
            }
            emit(SplashResource.Success(list))
        }
    }.handleFlowException()

    fun getEpi(result: Result) = flow {
        emit(SplashResource.Loading)
        if (result.episode.isNotEmpty()) {
            val s = result.episode[0]
            val episode = apiService.getEpisode(s)
            emit(SplashResource.Success(episode))
        }
    }.handleFlowException()

}