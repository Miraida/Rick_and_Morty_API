package com.geek.rick_and_morty_api.data.remote.dto.episode

import com.geek.rick_and_morty_api.data.remote.dto.Info
import com.geek.rick_and_morty_api.domain.model.Episodes

data class EpisodesDto(
    val info: Info?,
    val results: List<EpisodeDto>?
)

fun EpisodesDto.mapTo(): List<Episodes> {
    val list = ArrayList<Episodes>()

    for (i in this.results!!) {
        list.add(Episodes(i.name, i.air_date,i.id))
    }
    return list
}