package com.sasuke.soundclown.ui.player

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.constraintlayout.motion.widget.MotionLayout

class SingleViewTouchableMotionLayout(context: Context, attributeSet: AttributeSet? = null) :
    MotionLayout(context, attributeSet) {

    private val gestureDetector =
        GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                transitionToEnd()
                return false
            }
        })

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }
}