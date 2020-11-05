package com.simpragma.magicrecipe.features.search.di

import com.simpragma.magicrecipe.features.search.SearchFragment
import dagger.Subcomponent

@Subcomponent(modules = [SearchModule::class])
interface SearchComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchComponent
    }

    fun inject(fragment: SearchFragment)
}