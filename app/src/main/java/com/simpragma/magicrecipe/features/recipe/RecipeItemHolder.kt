package com.simpragma.magicrecipe.features.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simpragma.magicrecipe.R
import com.simpragma.magicrecipe.network.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipt_item.view.*


class RecipeItemHolder(view: View, var itemClickHandler: ItemClickHandler) : RecyclerView.ViewHolder(view), View.OnClickListener {
    companion object {
        fun getView(parent: ViewGroup): View {
            return LayoutInflater.from(parent.context)
                .inflate(R.layout.recipt_item, parent, false)
        }
    }

    fun bindItemViewHolder(result: Result) {
        itemView.setOnClickListener(null)
        itemView.titleTV.text = result.title
        itemView.textViewIngredient.text = result.ingredients
        Picasso.get().load(result.thumbnail).into(itemView.imageViewRecipe)
    }

    override fun onClick(p0: View?) {
        itemClickHandler.handleItemClick(p0?.tag as String)
    }

}