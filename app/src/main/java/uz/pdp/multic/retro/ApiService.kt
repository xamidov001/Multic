package uz.pdp.multic.retro

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import uz.pdp.multic.models.episode.EpisodeClass
import uz.pdp.multic.models.page.PageClass

interface ApiService {

    @GET("api/character")
    suspend fun getPageList(@Query("page") page: Int): Response<PageClass>

    @GET
    suspend fun getEpisode(@Url url: String): Response<EpisodeClass>

}