package com.geek.rick_and_morty_api.ui.location

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.geek.android_trainee_task_2021_mira.common.network.Status
import com.geek.rick_and_morty_api.R
import com.geek.rick_and_morty_api.common.Constants
import com.geek.rick_and_morty_api.common.base.BaseFragment
import com.geek.rick_and_morty_api.databinding.FragmentEpisodeBinding
import com.geek.rick_and_morty_api.databinding.FragmentLocationBinding
import com.geek.rick_and_morty_api.domain.model.Episodes
import com.geek.rick_and_morty_api.domain.model.Locations
import com.geek.rick_and_morty_api.extension.errorDialog
import com.geek.rick_and_morty_api.extension.visibility
import com.geek.rick_and_morty_api.ui.episode.EpisodeAdapter
import com.geek.rick_and_morty_api.ui.episode.EpisodeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationFragment : BaseFragment<FragmentLocationBinding>() {
    private val viewModel: LocationViewModel by viewModels()
    @Inject
    lateinit var adapter: LocationAdapter

    private fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt(Constants.location_id_key, id)
        findNavController().navigate(R.id.navigation_detail_location, bundle)
    }


    override fun bind(): FragmentLocationBinding {
        return FragmentLocationBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {

        viewModel.progressBar.observe(viewLifecycleOwner, {
            ui.progressBar.visibility(it)
        })

        viewModel.fetchLocations().observe(viewLifecycleOwner, {
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

    private fun setupData(data: List<Locations>?) {
        if (data!=null) adapter.update(data,this::onItemClick)
    }

    override fun setupUI() {
        ui.locationRv.adapter = adapter
    }

}