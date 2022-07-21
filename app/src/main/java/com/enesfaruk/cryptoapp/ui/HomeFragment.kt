package com.enesfaruk.cryptoapp.ui

import androidx.fragment.app.viewModels
import com.enesfaruk.cryptoapp.base.BaseFragment
import com.enesfaruk.cryptoapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel by viewModels<HomeViewModel>()

    override fun onCreateFinished() {
    }

    override fun initializeListeners() {
    }

    override fun observeEvents() {
    }

}