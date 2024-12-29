package com.assessment.weatherapplication.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
 * AppModule is a Dagger-Hilt module that provides application-wide dependencies.
 * It uses Hilt's dependency injection to manage the lifecycle and scope of the provided objects.
 * This module is responsible for providing the application context as a singleton for use across the app.
 */
@Module
// Annotates this object as a Dagger module that will be installed into the SingletonComponent,
// meaning the provided dependencies will live for the lifetime of the application.
@InstallIn(SingletonComponent::class)
object AppModule {
    /*
         * Provides the application context to the Hilt dependency graph.
         * This context will be available for injection wherever a Context is required.
         *
         * @param application The Application instance, provided by Hilt at runtime.
         * @return The application context (ApplicationContext) to be injected.
         */
    @Provides
    // This annotation marks the method as a provider for dependencies.
    @Singleton
    // @Singleton ensures that this Context instance is shared across the entire application.
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}