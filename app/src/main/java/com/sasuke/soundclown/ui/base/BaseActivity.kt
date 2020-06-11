package com.sasuke.soundclown.ui.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.jaeger.library.StatusBarUtil
import com.sasuke.soundclown.R
import dagger.android.support.DaggerAppCompatActivity
import es.dmoral.toasty.Toasty
import io.github.inflationx.viewpump.ViewPumpContextWrapper

open class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setColorNoTranslucent(
            this,
            ContextCompat.getColor(this, R.color.black)
        )
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    fun setOnlyDarkNavIcons() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }

    private fun setDarkNavIcons() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    fun setLightStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        } else {
            setStatusBarColor(R.color.white)
        }
        setDarkNavIcons()
    }

    fun setDarkStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        } else {
            setStatusBarColor(R.color.black)
        }
        setDarkNavIcons()
    }

    fun setStatusBarColor(@ColorRes color: Int) {
        StatusBarUtil.setColorNoTranslucent(
            this,
            ContextCompat.getColor(this, color)
        )
    }

    protected fun showErrorToast(message: String, view: View? = null, actionText: String = getString(R.string.ok), duration: Int = Toasty.LENGTH_LONG) {
        if (NotificationManagerCompat.from(this).areNotificationsEnabled())
            Toasty.error(this, message, duration).show()
        else if (view != null) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            snackbar.setAction(actionText) {
                snackbar.dismiss()
            }
            snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.text_red))
            snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.white))
            snackbar.show()
        }

    }

    protected fun showSuccessToast(message: String, view: View? = null, duration: Int = Toasty.LENGTH_LONG) {
        if (NotificationManagerCompat.from(this).areNotificationsEnabled())
            Toasty.success(this, message, duration).show()
        else if (view != null) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            snackbar.setAction(getString(R.string.ok)) {
                snackbar.dismiss()
            }
            snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.text_green))
            snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.white))
            snackbar.show()
        }
    }
}