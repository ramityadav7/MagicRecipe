package com.simpragma.magicrecipe.application

import android.app.Application
import com.simpragma.magicrecipe.application.di.AppComponent
import com.simpragma.magicrecipe.application.di.DaggerAppComponent

class MagicRecipeApplication : Application(){
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().create(applicationContext)
    }
}