package com.project.marvelcharactersapp.ui.features.characters.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val comics: Artwork?,
    val series: Artwork?,
    val stories: Artwork?,
    val events: Artwork?,
//    val urls: Artwork,
    val name: String,
    val description: String,
    val resourceURI: String,
    val thumbnail: Thumbnail,
    val id: Int,
) : Parcelable
