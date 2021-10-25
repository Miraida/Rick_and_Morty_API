package com.geek.rick_and_morty_api.data.remote.dto.character

import com.geek.rick_and_morty_api.data.remote.dto.Info

data class CharactersDto(
    val info: Info?,
    val results: List<CharacterDto>?
)