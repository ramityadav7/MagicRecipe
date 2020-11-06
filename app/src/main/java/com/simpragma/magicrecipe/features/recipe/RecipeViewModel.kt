package com.simpragma.magicrecipe.features.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.paging.PagedList
import com.simpragma.magicrecipe.network.model.Result
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RecipeViewModel @Inject constructor(private val recipeRepository: RecipeRepository) : ViewModel() {
    fun getRecipeLiveData(compositeDisposable: CompositeDisposable, query: String) : LiveData<PagedList<Result>> {
        return recipeRepository.getRecipeLiveData(compositeDisposable, query)
    }

    fun navigateDetailFragment(navController: NavController, url : String) {
        recipeRepository.navigateDetailFragment(navController, url)
    }
}