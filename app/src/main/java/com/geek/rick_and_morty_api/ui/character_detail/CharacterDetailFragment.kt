package com.geek.rick_and_morty_api.ui.character_detail

import androidx.fragment.app.viewModels
import com.geek.android_trainee_task_2021_mira.common.network.Status
import com.geek.rick_and_morty_api.common.Constants
import com.geek.rick_and_morty_api.common.base.BaseFragment
import com.geek.rick_and_morty_api.databinding.CharacterDetailFragmentBinding
import com.geek.rick_and_morty_api.domain.model.Character
import com.geek.rick_and_morty_api.extension.errorDialog
import com.geek.rick_and_morty_api.extension.loadImage
import com.geek.rick_and_morty_api.extension.visibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<CharacterDetailFragmentBinding>() {

    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun bind(): CharacterDetailFragmentBinding {
        return CharacterDetailFragmentBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
        viewModel.progressBar.observe(viewLifecycleOwner, {
            ui.progressBar.visibility(it)
        })
    }

    private fun setupData(data: Character?) {
        data?.image?.let { ui.imageCharacter.loadImage(it) }
        ui.nameTv.text = data?.name
        ui.statusTv.text= data?.status
        ui.genderTv.text = data?.gender
        ui.locationTv.text = data?.location?.name
        ui.speciesTv.text = data?.species
        ui.typeTv.text = data?.type
    }

    override fun setupUI() {
        if (arguments != null) {
            arguments?.getInt(Constants.character_id_key)?.let { getCharacter(it) }
        }
    }

    private fun getCharacter(id: Int) {
        viewModel.fetchCharacter(id).observe(viewLifecycleOwner, {
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

}