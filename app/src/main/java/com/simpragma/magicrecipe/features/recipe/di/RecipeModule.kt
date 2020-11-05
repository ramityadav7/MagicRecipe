package com.simpragma.magicrecipe.features.recipe.di

import androidx.lifecycle.ViewModel
import com.simpragma.magicrecipe.application.di.ViewModelKey
import com.simpragma.magicrecipe.features.detail.DetailViewModel
import com.simpragma.magicrecipe.features.recipe.RecipeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RecipeModule {
    @Binds
    @IntoMap
    @ViewModelKey(RecipeViewModel::class)
    abstract fun bindViewModel(viewModel: RecipeViewModel): ViewModel
}