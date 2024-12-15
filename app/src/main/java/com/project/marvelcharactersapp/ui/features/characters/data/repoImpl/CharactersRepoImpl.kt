package com.project.marvelcharactersapp.ui.features.characters.data.repoImpl

import com.project.marvelcharactersapp.BuildConfig
import com.project.marvelcharactersapp.network.MarvelApi
import com.project.marvelcharactersapp.network.remote.BaseRemoteDataSource
import com.project.marvelcharactersapp.network.remote.Resource
import com.project.marvelcharactersapp.ui.features.characters.data.model.DataResponse
import com.project.marvelcharactersapp.ui.features.characters.domain.CharactersRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class CharactersRepoImpl(
    private val api: MarvelApi,
) : CharactersRepo, BaseRemoteDataSource() {

    override fun getMarvelCharacters(
        query: String?
    ): Flow<Resource<DataResponse>> = flow {

        val ts = System.currentTimeMillis().toString()
        val apiKey = BuildConfig.API_KEY;
        val apiPrivateKey = BuildConfig.API_PRIVATE_KEY;
        val hash = createHash("$ts$apiPrivateKey$apiKey")


        val result = safeApiCall {

            api.getMarvelCharacters(ts, apiKey, hash,query)
        }
        emitAll(result)
    }

}