package com.project.marvelcharactersapp.ui.adapters

import android.content.Context
import com.project.marvelcharactersapp.R
import com.project.marvelcharactersapp.databinding.ArtworkItemBinding
import com.project.marvelcharactersapp.ui.features.characters.data.model.ArtworkInfo
import com.project.marvelcharactersapp.utils.GenericRecyclerViewAdapter
import com.squareup.picasso.Picasso

class ArtworkAdapter(
    private val context: Context,
    private val fetchArtworkResource: (String, (String?) -> Unit) -> Unit
) : GenericRecyclerViewAdapter<ArtworkInfo, ArtworkItemBinding>(
    GenericDiffUtilCallback(
        { oldItem, newItem -> oldItem.resourceURI == newItem.resourceURI },
        { oldItem, newItem -> oldItem == newItem }
    )
) {
    private val imageCache = mutableMapOf<String, String?>()

    override fun getLayout(): Int = R.layout.artwork_item

    override fun onBindViewHolder(holder: BaseViewHolder<ArtworkItemBinding>, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.binding.resources = item

            // If image URL is cached, load the image
            imageCache[item.resourceURI]?.let {
                Picasso.get().load(it)
                    .placeholder(R.drawable.marvel_logo)
                    .error(R.drawable.marvel_logo)
                    .into(holder.binding.artworkImg)
            } ?: run {
                // If image URL is not cached, fetch the image
                fetchArtworkResource(item.resourceURI) { imageUrl ->
                    // Cache the image URL once fetched
                    imageCache[item.resourceURI] = imageUrl

                    // Load the image into the ImageView
                    Picasso.get().load(imageUrl)
                        .placeholder(R.drawable.marvel_logo)
                        .error(R.drawable.marvel_logo)
                        .into(holder.binding.artworkImg)
                }
            }
        }
    }
}

