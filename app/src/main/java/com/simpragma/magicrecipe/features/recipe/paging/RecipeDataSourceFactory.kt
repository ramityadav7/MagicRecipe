package com.simpragma.magicrecipe.features.recipe.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.simpragma.magicrecipe.network.ApiService
import com.simpragma.magicrecipe.network.model.Result
import io.reactivex.disposables.CompositeDisposable


class RecipeDataSourceFactory constructor(
    private val apiService: ApiService,
    private val compositeDisposable: CompositeDisposable,
    private val query: String
) : DataSource.Factory<Int, Result>() {
    private var recipeDataSource: RecipeDataSource? = null
    private var mutableLiveData: MutableLiveData<RecipeDataSource> = MutableLiveData<RecipeDataSource>()

    override fun create(): DataSource<Int, Result>? {
        recipeDataSource = RecipeDataSource(apiService, compositeDisposable, query)
        mutableLiveData.postValue(recipeDataSource)
        return recipeDataSource
    }
}