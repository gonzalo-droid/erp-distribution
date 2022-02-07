package com.gonlg.erp_distribution.di.module

import android.content.Context
import com.gonlg.erp_distribution.utils.Methods
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MethodsModule {

    @Provides
    @Singleton
    fun provideMethods(context: Context) = Methods(context)
}