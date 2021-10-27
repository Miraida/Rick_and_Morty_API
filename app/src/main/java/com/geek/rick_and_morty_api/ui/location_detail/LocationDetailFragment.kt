package com.geek.rick_and_morty_api.ui.location_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.geek.android_trainee_task_2021_mira.common.network.Status
import com.geek.rick_and_morty_api.R
import com.geek.rick_and_morty_api.common.Constants
import com.geek.rick_and_morty_api.common.base.BaseFragment
import com.geek.rick_and_morty_api.databinding.LocationDetailFragmentBinding
import com.geek.rick_and_morty_api.domain.model.Characters
import com.geek.rick_and_morty_api.domain.model.Location
import com.geek.rick_and_morty_api.extension.errorDialog
import com.geek.rick_and_morty_api.extension.mapTo
import com.geek.rick_and_morty_api.extension.visibility
import com.geek.rick_and_morty_api.ui.character.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationDetailFragment : BaseFragment<LocationDetailFragmentBinding>() {

    private val viewModel: LocationDetailViewModel by viewModels()
    private var ids = ""
    @Inject
    lateinit var adapter: CharacterAdapter

    override fun bind(): LocationDetailFragmentBinding {
        return LocationDetailFragmentBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
        viewModel.progressBar.observe(viewLifecycleOwner, {
            ui.progressBar.visibility(it)
        })

        ui.moreCharacters.setOnClickListener {
            getCharacters()
            ui.charactersLayout.visibility(true)
        }
    }

    private fun getCharacters() {
        Log.e("TAG", "getCharacters: $ids" )
        viewModel.fetchCharacters(ids).observe(viewLifecycleOwner,{
            when (it.status) {
                Status.LOADING -> {
                    viewModel.progressBar.value = true
                }
                Status.SUCCESS -> {
                    viewModel.progressBar.value = false
                    setupCharactersData(it.data)
                }
                Status.ERROR -> {
                    viewModel.progressBar.value = false
                    it.msg?.let { it1 -> requireContext().errorDialog(it.code.toString(), it1) }
                }
            }
        })
    }

    private fun setupCharactersData(data: List<Characters>?) {
        if (data!=null)
        {
            ui.rvCharacters.adapter = adapter
            adapter.update(data,this::onItemClick)
        }
    }

    private fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt(Constants.character_id_key,id)
        findNavController().navigate(R.id.navigation_detail_character,bundle)
    }

    private fun setupData(data: Location?) {
        ui.nameTv.text = data?.name
        ui.typeTv.text = data?.type
        ui.dimensionTv.text = data?.dimension

        ids = data?.residents?.mapTo().toString()
    }

    override fun setupUI() {
        if (arguments != null) {
            arguments?.getInt(Constants.location_id_key)?.let { getLocation(it) }
        }
    }

    private fun getLocation(id: Int) {
        viewModel.fetchLocation(id).observe(viewLifecycleOwner, {
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