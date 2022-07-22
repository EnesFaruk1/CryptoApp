package com.enesfaruk.cryptoapp.ui.home

import com.enesfaruk.cryptoapp.base.BaseRepository
import com.enesfaruk.cryptoapp.network.ApiFactory
import javax.inject.Inject

/**
 * Created by Enes Faruk Işık on 21.07.2022.
 */
class HomeRepository @Inject constructor(private val apiFactory: ApiFactory) : BaseRepository() {
    suspend fun getData(
        apiKey: String,
        limit: String
    ) = safeApiRequest {
        apiFactory.getData(apiKey, limit)
    }
}