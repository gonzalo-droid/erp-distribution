package com.gonlg.erp_distribution.di.module

import android.content.Context
import com.gonlg.erp_distribution.data.Prefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PrefsModule {

    @Provides
    @Singleton
    fun providePrefs(context: Context) = Prefs(context)
}