package com.solti.solti_inmobiliaria.di.module

import android.content.Context
import com.solti.solti_inmobiliaria.utils.Methods
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MethodsModule {

    @Provides
    @Singleton
    fun provideMethods(context: Context) = Methods(context)
}