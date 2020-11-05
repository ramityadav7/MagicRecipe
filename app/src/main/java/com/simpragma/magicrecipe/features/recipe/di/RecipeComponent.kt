package com.simpragma.magicrecipe.features.recipe.di

import com.simpragma.magicrecipe.features.detail.DetailFragment
import com.simpragma.magicrecipe.features.recipe.RecipeFragment
import dagger.Subcomponent

@Subcomponent(modules = [RecipeModule::class])
interface RecipeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): RecipeComponent
    }

    fun inject(fragment: RecipeFragment)
}