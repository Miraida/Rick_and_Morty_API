package com.geek.rick_and_morty_api.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geek.android_trainee_task_2021_mira.common.network.Resource
import com.geek.rick_and_morty_api.data.repository.RemoteRepositoryImpl
import com.geek.rick_and_morty_api.domain.model.Characters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterModel @Inject constructor(private val repo: RemoteRepositoryImpl) : ViewModel() {

    fun fetchCharacters(): LiveData<Resource<List<Characters>>> =
        repo.getCharacters()

}