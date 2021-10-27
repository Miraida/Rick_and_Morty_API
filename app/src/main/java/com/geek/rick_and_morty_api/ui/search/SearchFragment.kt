package com.geek.rick_and_morty_api.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.geek.android_trainee_task_2021_mira.common.network.Status
import com.geek.rick_and_morty_api.R
import com.geek.rick_and_morty_api.common.Constants
import com.geek.rick_and_morty_api.common.base.BaseFragment
import com.geek.rick_and_morty_api.databinding.SearchFragmentBinding
import com.geek.rick_and_morty_api.domain.model.Characters
import com.geek.rick_and_morty_api.extension.errorDialog
import com.geek.rick_and_morty_api.extension.visibility
import com.geek.rick_and_morty_api.ui.character.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchFragmentBinding>() {

    private val viewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var adapter: CharacterAdapter
    private var status = ""
    private var gender = ""
    private var name = ""

    private fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt(Constants.character_id_key, id)
        findNavController().navigate(R.id.navigation_detail_character, bundle)
    }


    override fun bind(): SearchFragmentBinding {
        return SearchFragmentBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
        viewModel.progressBar.observe(viewLifecycleOwner, {
            ui.progressBar.visibility(it)
        })

        viewModel.name.observe(viewLifecycleOwner,{
            name = it
        })
        viewModel.gender.observe(viewLifecycleOwner,{
            gender = it
        })
        viewModel.status.observe(viewLifecycleOwner,{
            status = it
        })
        ui.searchEdt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.name.value = p0.toString()
                getCharacters()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        ui.moreIv.setOnClickListener {
            AlertDialogFragment(this::onAlertDialogClick).show(childFragmentManager, "")
        }
    }

    private fun onAlertDialogClick(status: String, gender: String) {
        viewModel.status.value = status
        viewModel.gender.value = gender
        getCharacters()
    }

    private fun getCharacters() {
        viewModel.fetchCharactersByName(name, status, gender).observe(viewLifecycleOwner, {
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
        if (data != null) adapter.update(data, this::onItemClick)
    }

    override fun setupUI() {
        ui.rvSearch.adapter = adapter
    }

}