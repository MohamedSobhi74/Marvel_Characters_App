package com.project.marvelcharactersapp.ui.features.characterDetails.data.repoImpl

import com.project.marvelcharactersapp.BuildConfig
import com.project.marvelcharactersapp.network.MarvelApi
import com.project.marvelcharactersapp.network.remote.BaseRemoteDataSource
import com.project.marvelcharactersapp.network.remote.Resource
import com.project.marvelcharactersapp.ui.features.characterDetails.data.model.ArtworkDataResponse
import com.project.marvelcharactersapp.ui.features.characterDetails.domain.CharacterDetailsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class CharacterDetailsRepoImpl(
    private val api: MarvelApi,
) : CharacterDetailsRepo, BaseRemoteDataSource() {


    override fun getArtworkResource(resourceUrl: String): Flow<Resource<ArtworkDataResponse>> =
        flow {
            val ts = System.currentTimeMillis().toString()
            val apiKey = BuildConfig.API_KEY;
            val apiPrivateKey = BuildConfig.API_PRIVATE_KEY;
            val hash = createHash("$ts$apiPrivateKey$apiKey")


            val result = safeApiCall {

                api.getArtworkResource(resourceUrl,ts, apiKey, hash, /*resourceUrl.replace("http://gateway.marvel.com","")*/)
            }
            emitAll(result)
        }

}