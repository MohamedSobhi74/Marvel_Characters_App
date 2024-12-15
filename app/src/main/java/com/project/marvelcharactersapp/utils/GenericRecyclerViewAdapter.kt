package com.project.marvelcharactersapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter

abstract class GenericRecyclerViewAdapter<T : Any, VB : ViewDataBinding>(callback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, GenericRecyclerViewAdapter.BaseViewHolder<VB>>(callback) {

    abstract fun getLayout(): Int

    var listener: ((view: View, item: T, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder<VB>(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayout(),
            parent,
            false
        )
    )

    class BaseViewHolder<VB : ViewDataBinding>(val binding: VB) :
        RecyclerView.ViewHolder(binding.root)

    class GenericDiffUtilCallback<T : Any>(
        private val itemsTheSame: (T, T) -> Boolean,
        private val contentsTheSame: (T, T) -> Boolean
    ) : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return itemsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return contentsTheSame(oldItem, newItem)
        }
    }
}