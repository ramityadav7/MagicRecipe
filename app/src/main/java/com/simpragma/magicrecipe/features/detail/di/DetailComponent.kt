package com.simpragma.magicrecipe.features.detail.di

import com.simpragma.magicrecipe.features.detail.DetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [DetailModule::class])
interface DetailComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailComponent
    }

    fun inject(fragment: DetailFragment)
}