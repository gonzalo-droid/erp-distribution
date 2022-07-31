package com.solti.inmobiliaria.di.module

import android.content.Context
import com.solti.inmobiliaria.data.Prefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PrefsModule {

    @Provides
    @Singleton
    fun providePrefs(context: Context) = Prefs(context)
}