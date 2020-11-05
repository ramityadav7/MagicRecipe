package com.simpragma.magicrecipe.features.search

import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.simpragma.magicrecipe.R
import com.simpragma.magicrecipe.common.AppConstant
import javax.inject.Inject

class SearchRepository @Inject constructor() {
    fun prepareSearchString(view : Sequence<View>) : String {
        var searchString = mutableListOf<String>()
        for(childView in view) {
            var ingredientTV = childView.findViewById(R.id.textViewIngredient) as TextView
            searchString.add(ingredientTV.text.toString())
        }
        return TextUtils.join(",", searchString)
    }

    fun navigateRecipeFragment(navController: NavController, searchQuery: String) {
        val bundle = bundleOf(AppConstant.BUNDLE_SEARCH_QUERY to searchQuery,)
        navController.navigate(R.id.action_searchFragment_to_recipeFragment, bundle)
    }
}