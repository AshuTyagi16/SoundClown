package com.sasuke.soundclown.ui.player

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.Item
import com.sasuke.soundclown.data.model.Playlist
import com.sasuke.soundclown.data.model.Status
import com.sasuke.soundclown.ui.DemoFragment
import com.sasuke.soundclown.ui.base.BaseActivity
import com.sasuke.soundclown.util.dpToPx
import com.sasuke.soundclown.util.getViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.lang.Math.abs
import javax.inject.Inject

class MainActivity : BaseActivity(),
    SongAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Inject
    lateinit var glide: RequestManager

    private lateinit var adapter: SongAdapter

    private var dominantColor = Color.BLACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        observeLiveData()
        setupListeners()
        getData()
        loadData()
    }

    private fun inject() {
        mainActivityViewModel =
            getViewModel(this, viewModelFactory)
        adapter = SongAdapter(glide)
    }

    private fun loadData() {
        glide
            .load("https://i.pinimg.com/originals/c3/12/16/c31216424b811a9770b5b7dacb06fa3e.jpg")
            .apply(
                RequestOptions()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .override(Target.SIZE_ORIGINAL)
                    .transform(RoundedCorners(2.dpToPx()))
            )
            .into(ivSongCover)


        tvSongName.text = "Bhot Tej"
        tvArtistName.text = "Fotty Seven"

        tvSongNameExpanded.text = "Bhot Tej"
        tvArtistNameExpanded.text = "Fotty Seven"

        adapter = SongAdapter(glide)
    }

    private fun getData() {
        mainActivityViewModel.getPlaylistsForCategory()
    }

    private fun observeLiveData() {
        mainActivityViewModel.trackLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Timber.i("LOADING")
                }
                Status.SUCCESS -> {
                    Timber.i("SUCCESS")
                }
                Status.ERROR -> {
                    Timber.i("ERROR")
                }
            }
        })

        mainActivityViewModel.playlistLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    it.data?.let {
                        setData(it)
                    }
                }
                Status.ERROR -> {
                }
            }
        })
    }

    private fun setData(playlist: Playlist) {
        rvSongs.layoutManager = LinearLayoutManager(this)
        rvSongs.adapter = adapter
        adapter.setOnItemClickListener(this)
        adapter.setSongs(playlist.playlists.items)
    }

    private fun replaceVideoFragment(container: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .commit()
    }

    private fun setupListeners() {
        bottomMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceVideoFragment(R.id.fragmentContainer,
                        DemoFragment.newInstance()
                    )
                }
                R.id.search -> {
                    replaceVideoFragment(R.id.fragmentContainer,
                        DemoFragment.newInstance()
                    )
                }
                R.id.library -> {
                    replaceVideoFragment(R.id.fragmentContainer,
                        DemoFragment.newInstance()
                    )
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

        mainMotionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(
                p0: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                if (endId != R.id.bottomExpanded) {
                    if (abs(progress) < 0.05) {
                        videoViewContainer.setBackgroundColor(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.darker_grey
                            )
                        )
                        clBottomLayout.setBackgroundColor(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.darker_grey
                            )
                        )
                    }else {
                        videoViewContainer.setBackgroundColor(dominantColor)
                        clBottomLayout.setBackgroundColor(dominantColor)
                    }
                }
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {

            }

        })
    }

    @SuppressLint("CheckResult")
    override fun onItemClick(position: Int, item: Item) {
        glide
            .asBitmap()
            .load(item.images[0].url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    resource.let {
                        Palette.Builder(it).generate {
                            it?.let { palette ->
                                dominantColor = palette.getDominantColor(0)
                                videoViewContainer.setBackgroundColor(dominantColor)
                                clBottomLayout.setBackgroundColor(dominantColor)
                            }
                        }
                    }
                }

            })
    }
}
