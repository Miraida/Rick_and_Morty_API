package com.geek.rick_and_morty_api.data.remote.dto.episode

import com.geek.rick_and_morty_api.domain.model.Episode

data class EpisodeDto(
    val air_date: String?,
    val characters: List<String>?,
    val created: String?,
    val episode: String?,
    val id: Int?,
    val name: String?,
    val url: String?
)

fun EpisodeDto.mapTo(): Episode {
    return Episode(
        this.name,
        this.air_date,
        this.characters,
        this.created,
        this.episode,
        this.url
    )
}