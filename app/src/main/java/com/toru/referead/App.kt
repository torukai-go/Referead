package com.toru.referead

import android.app.Application
import com.toru.referead.di.ModuleProvider
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@HiltAndroidApp
class App: Application() {
//
//    override fun onCreate() {
//        super.onCreate()
//
////        startKoin {
////            androidLogger()
////            // use the Android context given there
////            androidContext(this@App)
////            // load properties from assets/koin.properties file
////            androidFileProperties()
////            // module list
////            modules(ModuleProvider.modules)
////        }
//    }
}