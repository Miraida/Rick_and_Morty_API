package com.geek.rick_and_morty_api.data.remote.dto.character

import com.geek.rick_and_morty_api.data.remote.dto.Info
import com.geek.rick_and_morty_api.domain.model.Characters

data class CharactersDto(
    val info: Info?,
    val results: List<CharacterDto>?
)

fun CharactersDto.mapTo(): List<Characters> {
    val list = ArrayList<Characters>()
    this.results?.forEach { i ->
        list.add(Characters(i.name, i.image, i.id))
    }
    return list
}