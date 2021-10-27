package com.geek.rick_and_morty_api.data.remote.dto.character

import com.geek.rick_and_morty_api.domain.model.Character

data class CharacterDto(
    val created: String?,
    val episode: List<String>?,
    val gender: String?,
    val id: Int?,
    val image: String?,
    val location: Location?,
    val name: String?,
    val origin: Origin?,
    val species: String?,
    val status: String?,
    val type: String?,
    val url: String?
)

fun CharacterDto.mapTo(): Character {
    return Character(
        this.created,
        this.episode,
        this.gender,
        this.image,
        this.location,
        this.name,
        this.origin,
        this.species,
        this.status,
        this.type
    )
}