package com.geek.rick_and_morty_api.ui.search

import com.geek.rick_and_morty_api.common.base.BaseFragment
import com.geek.rick_and_morty_api.databinding.SearchFragmentBinding

class SearchFragment : BaseFragment<SearchFragmentBinding>() {
    override fun bind(): SearchFragmentBinding {
        return SearchFragmentBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
    }

    override fun setupUI() {
    }


}