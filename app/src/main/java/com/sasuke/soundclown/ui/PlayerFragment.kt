package com.sasuke.soundclown.ui

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sasuke.soundclown.R
import com.sasuke.soundclown.ui.base.BaseFragment
import com.sasuke.soundclown.util.dpToPx
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_player.*
import javax.inject.Inject
import kotlin.math.abs

class PlayerFragment : BaseFragment() {

    private lateinit var onTransitionProgressListener: OnTransitionProgressListener

    @Inject
    lateinit var glide: RequestManager

    companion object {
        fun newInstance() = PlayerFragment()
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_player
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        setupListeners()
    }

    private fun loadData() {
        glide
            .load("https://i.pinimg.com/originals/c3/12/16/c31216424b811a9770b5b7dacb06fa3e.jpg")
            .apply(
                RequestOptions()
                    .transform(RoundedCorners(2.dpToPx()))
            )
            .into(ivSongCover)


        tvSongName.text = "Bhot Tej(feat. Badshah)"
        tvArtistName.text = "Fotty Seven"

        tvSongNameExpanded.text = "Bhot Tej(feat. Badshah)"
        tvArtistNameExpanded.text = "Fotty Seven"
    }

    private fun setupListeners() {
        videoMotionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                (activity as MainActivity).also {
                    if (progress < 0.05) {
                        if (::onTransitionProgressListener.isInitialized)
                            onTransitionProgressListener.onTransitionProgress(0f)
                    } else {
                        if (::onTransitionProgressListener.isInitialized)
                            onTransitionProgressListener.onTransitionProgress(abs(progress))
                    }
                }
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }
        })
    }

    interface OnTransitionProgressListener {
        fun onTransitionProgress(progress: Float)
    }

    fun setOnTransitionProgressListener(onTransitionProgressListener: OnTransitionProgressListener) {
        this.onTransitionProgressListener = onTransitionProgressListener
    }
}