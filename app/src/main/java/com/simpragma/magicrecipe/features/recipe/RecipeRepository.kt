package com.simpragma.magicrecipe.features.recipe

import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.simpragma.magicrecipe.R
import com.simpragma.magicrecipe.common.AppConstant
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

    fun navigateDetailFragment(navController: NavController, url: String) {
        val bundle = bundleOf(AppConstant.BUNDLE_URL to url)
        navController.navigate(R.id.action_recipeFragment_to_detailFragment, bundle)
    }
}