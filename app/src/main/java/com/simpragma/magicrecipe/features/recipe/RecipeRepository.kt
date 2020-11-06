package com.simpragma.magicrecipe.features.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.simpragma.magicrecipe.features.recipe.paging.RecipeDataSource
import com.simpragma.magicrecipe.features.recipe.paging.RecipeDataSourceFactory
import com.simpragma.magicrecipe.network.ApiService
import com.simpragma.magicrecipe.network.model.Result
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val apiService: ApiService) {
    private lateinit var recipeLiveData: LiveData<PagedList<Result>>
    private lateinit var sourceFactory: RecipeDataSourceFactory
    private val pageSize = 20

    fun getRecipeLiveData(compositeDisposable: CompositeDisposable, query: String) : LiveData<PagedList<Result>> {
        sourceFactory = RecipeDataSourceFactory(apiService, compositeDisposable, query)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        recipeLiveData = LivePagedListBuilder<Int, Result>(sourceFactory, config).build()

        return recipeLiveData
    }
}