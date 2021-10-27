package com.geek.rick_and_morty_api.data.remote.dto.character

import android.util.Log
import com.geek.rick_and_morty_api.domain.model.Character
import com.geek.rick_and_morty_api.domain.model.Characters

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

fun List<CharacterDto>.mapTo():List<Characters>{
    val list = ArrayList<Characters>()
    this.forEach { i ->
        list.add(Characters(i.name, i.image,i.id))
    }
    return list
}