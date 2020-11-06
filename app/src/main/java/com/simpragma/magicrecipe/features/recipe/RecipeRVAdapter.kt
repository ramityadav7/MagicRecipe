package com.simpragma.magicrecipe.features.recipe

import android.content.Context
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.simpragma.magicrecipe.network.model.Result

class RecipeRVAdapter(var resultList : List<Result>, context : Context, var itemClickHandler: ItemClickHandler) :
    PagedListAdapter<Result, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecipeItemHolder(RecipeItemHolder.getView(parent), itemClickHandler)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RecipeItemHolder).bindItemViewHolder(getItem(position)!!)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>() {
            // The ID property identifies when items are the same.
            override fun areItemsTheSame(oldItem: Result, newItem: Result) =
                oldItem.title == newItem.title

            // If you use the "==" operator, make sure that the object implements
            // .equals(). Alternatively, write custom data comparison logic here.
            override fun areContentsTheSame(
                oldItem: Result, newItem: Result
            ) = oldItem == newItem
        }
    }
}