package com.simpragma.magicrecipe.features.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simpragma.magicrecipe.features.recipe.model.RecipeUiModel
import javax.inject.Inject

class RecipeViewModel @Inject constructor(private val recipeRepository: RecipeRepository) : ViewModel() {
    var recipeLiveData: MutableLiveData<RecipeUiModel> = recipeRepository.recipeLiveData

    fun getRecipe(ingredientQuery : String) {
        recipeRepository.getRecipe(ingredientQuery)
    }
}