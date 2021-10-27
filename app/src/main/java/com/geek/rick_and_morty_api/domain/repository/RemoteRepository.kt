package com.geek.rick_and_morty_api.domain.repository

import androidx.lifecycle.LiveData
import com.geek.android_trainee_task_2021_mira.common.network.Resource
import com.geek.rick_and_morty_api.data.remote.dto.character.CharactersDto
import com.geek.rick_and_morty_api.domain.model.*

interface RemoteRepository {

    fun getCharacters(): LiveData<Resource<List<Characters>>>
    fun getCharacter(id: Int): LiveData<Resource<Character>>
    fun getEpisodes(): LiveData<Resource<List<Episodes>>>
    fun getEpisode(id: Int): LiveData<Resource<Episode>>
    fun getLocations(): LiveData<Resource<List<Locations>>>
    fun getLocation(id: Int): LiveData<Resource<Location>>
    fun getCharactersById(ids: String): LiveData<Resource<List<Characters>>>
    fun getCharactersByName(
        name: String,
        status: String,
        gender: String
    ): LiveData<Resource<List<Characters>>>

}