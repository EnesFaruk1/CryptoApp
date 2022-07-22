package com.enesfaruk.cryptoapp.ui.detail

import com.enesfaruk.cryptoapp.base.BaseRepository
import com.enesfaruk.cryptoapp.network.ApiFactory
import javax.inject.Inject

/**
 * Created by Enes Faruk Işık on 22.07.2022.
 */
class DetailRepository @Inject constructor(private val apiFactory: ApiFactory) : BaseRepository() {
    suspend fun getDetailData(
        apiKey: String,
        symbol: String
    ) = safeApiRequest {
        apiFactory.getDetailData(apiKey, symbol)
    }
}