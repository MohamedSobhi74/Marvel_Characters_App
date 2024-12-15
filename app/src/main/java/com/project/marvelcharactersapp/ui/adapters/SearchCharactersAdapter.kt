package com.project.marvelcharactersapp.ui.adapters

import android.content.Context
import com.project.marvelcharactersapp.R
import com.project.marvelcharactersapp.databinding.SearchCharacterItemBinding
import com.project.marvelcharactersapp.ui.features.characters.data.model.Character
import com.project.marvelcharactersapp.utils.GenericRecyclerViewAdapter
import com.squareup.picasso.Picasso

class SearchCharactersAdapter(
    private val context: Context,
) : GenericRecyclerViewAdapter<Character, SearchCharacterItemBinding>(
    GenericDiffUtilCallback(
        { oldItem, newItem -> oldItem.id == newItem.id },
        { oldItem, newItem -> oldItem == newItem }
    )
) {
    override fun getLayout(): Int = R.layout.search_character_item


    override fun onBindViewHolder(holder: BaseViewHolder<SearchCharacterItemBinding>, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.binding.character = item

            Picasso
                .get()
                .load(item.thumbnail.getImageUrl())
                .placeholder(R.drawable.marvel_logo)
                .error(R.drawable.marvel_logo)
                .into(holder.binding.characterImg)
        }

//        holder.binding.root.setOnClickListener { item?.let { it1 -> onItemClick.onItemClick(it1.id) } }
    }




    interface OnItemClick {
        fun onItemClick(building: Int)
    }
}
