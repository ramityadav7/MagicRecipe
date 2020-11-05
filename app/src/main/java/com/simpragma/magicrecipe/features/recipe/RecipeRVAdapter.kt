package com.simpragma.magicrecipe.features.recipe

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simpragma.magicrecipe.network.model.Result

class RecipeRVAdapter(var resultList : List<Result>, context : Context, var itemClickHandler: ItemClickHandler) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecipeItemHolder(RecipeItemHolder.getView(parent), itemClickHandler)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RecipeItemHolder).bindItemViewHolder(resultList.get(position))
    }

    override fun getItemCount(): Int {
        return resultList.size
    }
}