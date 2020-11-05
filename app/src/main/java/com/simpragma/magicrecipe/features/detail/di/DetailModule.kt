package com.simpragma.magicrecipe.features.detail.di

import androidx.lifecycle.ViewModel
import com.simpragma.magicrecipe.application.di.ViewModelKey
import com.simpragma.magicrecipe.features.detail.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindViewModel(viewModel: DetailViewModel): ViewModel
}