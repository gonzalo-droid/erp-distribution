package com.solti.solti_inmobiliaria.di.module

import android.content.Context
import com.solti.solti_inmobiliaria.data.Prefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PrefsModule {

    @Provides
    @Singleton
    fun providePrefs(context: Context) = Prefs(context)
}