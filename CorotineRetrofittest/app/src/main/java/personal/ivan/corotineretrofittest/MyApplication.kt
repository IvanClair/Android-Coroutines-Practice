package personal.ivan.corotineretrofittest

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import personal.ivan.corotineretrofittest.di.DaggerAppComponent

class MyApplication : DaggerApplication() {

    /* ------------------------------ Dagger */

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)
}