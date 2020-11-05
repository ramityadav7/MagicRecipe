package com.simpragma.magicrecipe.features.search

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {
    fun handleSearch(view : Sequence<View>, navController: NavController){
        val search = searchRepository.prepareSearchString(view)
        searchRepository.navigateRecipeFragment(navController, search)
    }
}