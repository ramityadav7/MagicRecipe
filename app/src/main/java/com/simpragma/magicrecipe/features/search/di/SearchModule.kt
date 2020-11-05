package com.simpragma.magicrecipe.features.search.di

import androidx.lifecycle.ViewModel
import com.simpragma.magicrecipe.application.di.ViewModelKey
import com.simpragma.magicrecipe.features.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindViewModel(viewModel: SearchViewModel): ViewModel
}