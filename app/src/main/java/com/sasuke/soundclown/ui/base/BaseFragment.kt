package com.sasuke.soundclown.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.sasuke.soundclown.R
import dagger.android.support.DaggerFragment
import es.dmoral.toasty.Toasty

abstract class BaseFragment : DaggerFragment() {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    protected fun showErrorToast(message: String, view: View? = null, actionText: String = getString(
        R.string.ok), duration: Int = Toasty.LENGTH_LONG) {
        if (NotificationManagerCompat.from(requireContext()).areNotificationsEnabled())
            Toasty.error(requireContext(), message, duration).show()
        else if (view != null) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            snackbar.setAction(actionText) {
                snackbar.dismiss()
            }
            snackbar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.text_red))
            snackbar.setActionTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            snackbar.show()
        }

    }

    protected fun showSuccessToast(message: String, view: View? = null, duration: Int = Toasty.LENGTH_LONG) {
        if (NotificationManagerCompat.from(requireContext()).areNotificationsEnabled())
            Toasty.success(requireContext(), message, duration).show()
        else if (view != null) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            snackbar.setAction(getString(R.string.ok)) {
                snackbar.dismiss()
            }
            snackbar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.text_green))
            snackbar.setActionTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            snackbar.show()
        }
    }
}