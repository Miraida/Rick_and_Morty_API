package com.geek.rick_and_morty_api.ui.character

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.geek.android_trainee_task_2021_mira.common.network.Status
import com.geek.rick_and_morty_api.R
import com.geek.rick_and_morty_api.common.Constants
import com.geek.rick_and_morty_api.common.base.BaseFragment
import com.geek.rick_and_morty_api.databinding.FragmentCharacterBinding
import com.geek.rick_and_morty_api.domain.model.Characters
import com.geek.rick_and_morty_api.extension.errorDialog
import com.geek.rick_and_morty_api.extension.visibility
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>() {

    private val viewModel: CharacterModel by viewModels()
    @Inject
    lateinit var adapter:CharacterAdapter

    private fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt(Constants.character_id_key, id)
        findNavController().navigate(R.id.navigation_detail_character, bundle)
    }


    override fun bind(): FragmentCharacterBinding {
        return FragmentCharacterBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
        viewModel.progressBar.observe(viewLifecycleOwner, {
            ui.characterProgress.visibility(it)
        })
        viewModel.fetchCharacters().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    viewModel.progressBar.value = true
                }
                Status.SUCCESS -> {
                    viewModel.progressBar.value = false
                    setupData(it.data)
                }
                Status.ERROR -> {
                    viewModel.progressBar.value = false
                    it.msg?.let { it1 -> requireContext().errorDialog(it.code.toString(), it1) }
                }
            }
        })
    }

    private fun setupData(data: List<Characters>?) {
        if (data!=null) adapter.update(data,this::onItemClick)
    }

    override fun setupUI() {
        ui.rvCharacter.adapter = adapter
    }


}