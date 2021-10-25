package com.geek.rick_and_morty_api.data.remote.dto.episode

import com.geek.rick_and_morty_api.data.remote.dto.Info

data class EpisodesDto(
    val info: Info?,
    val results: List<EpisodeDto>?
)