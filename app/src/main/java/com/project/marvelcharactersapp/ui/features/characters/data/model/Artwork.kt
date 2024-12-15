package com.project.marvelcharactersapp.ui.features.characters.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artwork(
    val available: Int?,
    val collectionURI: String?,
    val items: List<ArtworkInfo>?,
    val returned: Int?
) : Parcelable