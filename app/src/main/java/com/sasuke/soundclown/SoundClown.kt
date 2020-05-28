package com.sasuke.soundclown

import android.app.Application
import android.widget.Toast
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sasuke.soundclown.data.event.NoInternetEvent
import com.sasuke.soundclown.di.components.DaggerSoundClownComponent
import com.sasuke.soundclown.di.components.SoundClownComponent
import com.sasuke.soundclown.util.Constants
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.github.inflationx.viewpump.ViewPump
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber
import javax.inject.Inject

class SoundClown : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private lateinit var component: SoundClownComponent

    override fun onCreate() {
        super.onCreate()
        initComponent()
        initTimber()
        initAndroidThreeTen()
        initViewPump()
        listenNoInternetEvent()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    private fun initAndroidThreeTen() {
        AndroidThreeTen.init(this)
    }

    private fun initTimber() {
        Timber.plant(component.timberTree())
    }

    private fun initViewPump() {
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(component.calligraphyInterceptor())
                .build()
        )
    }

    private fun initComponent() {
        component = DaggerSoundClownComponent.factory().create(applicationContext)
        component.inject(this)
    }

    private fun listenNoInternetEvent() {
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNoInternetEvent(noInternetEvent: NoInternetEvent) {
        Toast.makeText(this, getString(R.string.app_name), Toast.LENGTH_SHORT).show()
    }
}