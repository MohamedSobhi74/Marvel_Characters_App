package com.project.marvelcharactersapp.ui.features.characters.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    val path: String,
    val extension: String
) : Parcelable {

    fun getImageUrl() = "$path.$extension"
}
