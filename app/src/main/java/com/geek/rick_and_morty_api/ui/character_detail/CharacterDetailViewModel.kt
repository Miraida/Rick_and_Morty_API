package com.geek.rick_and_morty_api.ui.character_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geek.android_trainee_task_2021_mira.common.network.Resource
import com.geek.rick_and_morty_api.data.repository.RemoteRepositoryImpl
import com.geek.rick_and_morty_api.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repo: RemoteRepositoryImpl) :
    ViewModel() {
    val progressBar = MutableLiveData<Boolean>()
    fun fetchCharacter(id: Int): LiveData<Resource<Character>> = repo.getCharacter(id)
}