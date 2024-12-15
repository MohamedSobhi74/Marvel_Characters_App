package com.project.marvelcharactersapp.network


import com.project.marvelcharactersapp.ui.features.characterDetails.data.model.ArtworkDataResponse
import com.project.marvelcharactersapp.ui.features.characters.data.model.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MarvelApi {

    @GET(EndPoints.CHARACTERS)
    suspend fun getMarvelCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("nameStartsWith") nameStartsWith: String? = null

    ): Response<DataResponse>

    @GET
    suspend fun getArtworkResource(
        @Url resourceUrl: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Response<ArtworkDataResponse>

}