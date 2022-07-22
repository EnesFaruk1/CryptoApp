package com.enesfaruk.cryptoapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesfaruk.cryptoapp.model.detail.DetailResponse
import com.enesfaruk.cryptoapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Enes Faruk Işık on 22.07.2022.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepository: DetailRepository): ViewModel(){

    val detailResponse: MutableLiveData<DetailResponse?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val onError: MutableLiveData<String?> = MutableLiveData()

    fun getDetailData(
        apiKey: String,
        symbol: String
    ) = viewModelScope.launch {
        isLoading.value = true
        when (val request = detailRepository.getDetailData(apiKey, symbol)) {
            is NetworkResult.Success -> {
                detailResponse.value = request.data
                isLoading.value = false
            }
            is NetworkResult.Error -> {
                onError.value = request.message
                isLoading.value = false
            }
        }
    }
}