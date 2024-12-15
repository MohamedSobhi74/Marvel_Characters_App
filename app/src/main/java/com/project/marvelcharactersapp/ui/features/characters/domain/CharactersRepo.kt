package com.project.marvelcharactersapp.ui.features.characters.domain

import com.project.marvelcharactersapp.network.remote.Resource
import com.project.marvelcharactersapp.ui.features.characters.data.model.DataResponse
import kotlinx.coroutines.flow.Flow

interface CharactersRepo {
    fun getMarvelCharacters(
        query: String? = null
    ): Flow<Resource<DataResponse>>
}