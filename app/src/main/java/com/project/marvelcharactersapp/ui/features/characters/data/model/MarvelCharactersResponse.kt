package com.project.marvelcharactersapp.ui.features.characters.data.model

data class MarvelCharactersResponse(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)
