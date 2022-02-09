package uz.pdp.multic.models

import uz.pdp.multic.models.episode.EpisodeClass
import uz.pdp.multic.models.page.Result

data class MainDataModel(
    var result: Result,
    var episodeClass: EpisodeClass
)
