package com.simpragma.magicrecipe.features.recipe

import androidx.lifecycle.MutableLiveData
import com.simpragma.magicrecipe.features.recipe.model.RecipeUiModel
import com.simpragma.magicrecipe.network.ApiService
import com.simpragma.magicrecipe.network.model.RecipeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val apiService: ApiService) {
    var recipeLiveData: MutableLiveData<RecipeUiModel> = MutableLiveData()

    fun getRecipe(ingredientQuery : String) {
        val recipeUiModel = RecipeUiModel()
        recipeUiModel.showProgress = true
        recipeLiveData.value = recipeUiModel

        apiService.getRecipe(ingredientQuery, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response: Response<RecipeResponse> -> processCountryResponse(response) }) { throwable: Throwable -> handleError(throwable)}
    }

    private fun processCountryResponse(response: Response<RecipeResponse>) {
        if(response.code() == 200) {
            val recipeUiModel = RecipeUiModel()
            recipeUiModel.recipeList = response.body()?.results
            recipeUiModel.showProgress = false
            recipeLiveData.value = recipeUiModel

        } else {
            handleError(Throwable())
        }
    }

    private fun handleError(throwable: Throwable) {
        val recipeUiModel = RecipeUiModel()
        recipeUiModel.showProgress = false
        recipeUiModel.showError = true
        recipeLiveData.value = recipeUiModel
    }

}