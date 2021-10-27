package com.geek.rick_and_morty_api.domain.model

import com.geek.rick_and_morty_api.data.remote.dto.character.Location
import com.geek.rick_and_morty_api.data.remote.dto.character.Origin

data class Character(
    val created: String?,
    val episode: List<String>?,
    val gender: String?,
    val image: String?,
    val location: Location?,
    val name: String?,
    val origin: Origin?,
    val species: String?,
    val status: String?,
    val type: String?,
)
