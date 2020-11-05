package com.simpragma.magicrecipe.network

import com.simpragma.magicrecipe.network.model.RecipeResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(".")
    fun getRecipe(
        @Query("i") ingredientQuery: String,
        @Query("p") pageCount: Int
    ): Observable<Response<RecipeResponse>>

}