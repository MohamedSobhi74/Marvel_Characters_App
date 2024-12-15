package com.project.marvelcharactersapp.ui.features.characters.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtworkInfo(
    val name: String,
    val resourceURI: String,
    var imageUrl:String?
) : Parcelable