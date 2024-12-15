package com.project.marvelcharactersapp.ui.features.characters.data.model

import com.project.marvelcharactersapp.network.BaseResponse

data class DataResponse(
    val data: MarvelCharactersResponse
):BaseResponse()
