package com.geek.rick_and_morty_api.domain.repository

import androidx.lifecycle.LiveData
import com.geek.android_trainee_task_2021_mira.common.network.Resource
import com.geek.rick_and_morty_api.domain.model.*

interface RemoteRepository {

    suspend fun getCharacters(): LiveData<Resource<List<Characters>>>
    suspend fun getCharacter(id:Int): LiveData<Resource<Character>>
    suspend fun getEpisodes(): LiveData<Resource<List<Episodes>>>
    suspend fun getEpisode(id:Int): LiveData<Resource<Episode>>
    suspend fun getLocations(): LiveData<Resource<List<Locations>>>
    suspend fun getLocation(id:Int): LiveData<Resource<Location>>

}