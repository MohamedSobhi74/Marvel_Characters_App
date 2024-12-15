package com.project.marvelcharactersapp.ui.features.characterDetails.domain

import com.project.marvelcharactersapp.network.remote.Resource
import com.project.marvelcharactersapp.ui.features.characterDetails.data.model.ArtworkDataResponse
import kotlinx.coroutines.flow.Flow

interface CharacterDetailsRepo {
    fun getArtworkResource(
        resourceUrl: String
    ): Flow<Resource<ArtworkDataResponse>>
}