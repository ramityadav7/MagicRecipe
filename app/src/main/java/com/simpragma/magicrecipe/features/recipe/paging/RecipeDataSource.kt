package com.simpragma.magicrecipe.features.recipe.paging

import androidx.paging.PageKeyedDataSource
import com.simpragma.magicrecipe.network.ApiService
import com.simpragma.magicrecipe.network.model.Result
import io.reactivex.disposables.CompositeDisposable

class RecipeDataSource(private val apiService: ApiService
                       ,private val compositeDisposable: CompositeDisposable
                       ,private val query : String
) : PageKeyedDataSource<Int, Result>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        compositeDisposable.add(apiService.getRecipe(query, 1).subscribe(
            {response ->
                callback.onResult(response.body()?.results!!, null, 2)},
            {callback.onResult(listOf(), null, 2)}
        ))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        compositeDisposable.add(apiService.getRecipe(query, params.key).subscribe(
            { response ->
                callback.onResult(response.body()?.results!!, params.key.dec()) },
            { callback.onResult(listOf(), params.key.dec())}))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        compositeDisposable.add(apiService.getRecipe(query, params.key).subscribe(
            { response ->
                callback.onResult(response.body()?.results!!, params.key.inc()) },
            { callback.onResult(listOf(), params.key.inc())}))
    }
}