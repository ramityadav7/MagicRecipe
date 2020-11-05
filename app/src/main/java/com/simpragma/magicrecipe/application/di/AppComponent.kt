package com.simpragma.magicrecipe.application.di

import android.content.Context
import com.simpragma.magicrecipe.features.detail.di.DetailComponent
import com.simpragma.magicrecipe.features.recipe.di.RecipeComponent
import com.simpragma.magicrecipe.features.search.di.SearchComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelBuilderModule::class,
        SubComponentsModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun searchComponent(): SearchComponent.Factory
    fun recipeComponent(): RecipeComponent.Factory
    fun detailComponent() : DetailComponent.Factory
}

@Module(
    subcomponents = [
        SearchComponent::class,
        RecipeComponent::class,
        DetailComponent::class
    ]
)
object SubComponentsModule