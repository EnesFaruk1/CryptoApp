package com.enesfaruk.cryptoapp.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.enesfaruk.cryptoapp.R
import com.enesfaruk.cryptoapp.base.BaseFragment
import com.enesfaruk.cryptoapp.databinding.FragmentDetailBinding
import com.enesfaruk.cryptoapp.model.detail.CoinDetail
import com.enesfaruk.cryptoapp.model.detail.DetailResponse
import com.enesfaruk.cryptoapp.utils.Constants.API_KEY
import com.enesfaruk.cryptoapp.utils.loadImage
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import org.json.JSONObject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    FragmentDetailBinding::inflate
) {

    override val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateFinished() {
        viewModel.getDetailData(API_KEY, args.symbol)
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun initializeListeners() {
    }

    override fun observeEvents() {
        with(viewModel) {
            detailResponse.observe(viewLifecycleOwner, Observer {
                parseData(it)
            })
            isLoading.observe(viewLifecycleOwner, Observer {
                handleViews(it)
            })
            onError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun parseData(it: DetailResponse?) {
        val gson = Gson()
        val json = gson.toJson(it?.data)
        val jsonObject = JSONObject(json)
        val jsonArray = jsonObject[args.symbol] as JSONArray

        val coin = gson.fromJson(jsonArray.getJSONObject(0).toString(), CoinDetail::class.java)

        coin?.let {
            with(binding) {
                ivCoin.loadImage(it.logo)
                tvCoinName.text = it.name
                tvSymbol.text = it.symbol
                tvCoinInfo.text = it.description
                tvToolbarTitle.text = it.symbol

            }
        }

    }

    private fun handleViews(isLoading: Boolean = false) {
        binding.detailGroup.isVisible = !isLoading
        binding.pbDetail.isVisible = isLoading
    }

}