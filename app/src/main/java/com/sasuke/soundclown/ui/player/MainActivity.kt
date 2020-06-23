package com.sasuke.soundclown.ui.player

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.ItemPlaylist
import com.sasuke.soundclown.data.model.PlaylistResponse
import com.sasuke.soundclown.data.model.network_models.Status
import com.sasuke.soundclown.ui.DemoFragment
import com.sasuke.soundclown.ui.base.BaseActivity
import com.sasuke.soundclown.ui.category_details.CategoryDetailsFragment
import com.sasuke.soundclown.ui.search.SearchFragment
import com.sasuke.soundclown.ui.tracks_playlist.PlaylistTracksFragment
import com.sasuke.soundclown.util.dpToPx
import com.sasuke.soundclown.util.getViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.lang.Math.abs
import javax.inject.Inject

class MainActivity : BaseActivity(),
    SongAdapter.OnItemClickListener, SearchFragment.OnCategoryItemClickListener,
    CategoryDetailsFragment.OnCategoryDetailsItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var glide: RequestManager

    private lateinit var mainActivityViewModel: MainActivityViewModel

    private lateinit var adapter: SongAdapter

    private lateinit var layoutManager: LinearLayoutManager

    private var dominantColor = Color.BLACK
    private lateinit var searchFragment: SearchFragment
    private lateinit var categoryDetailsFragment: CategoryDetailsFragment
    private lateinit var playlistTracksFragment: PlaylistTracksFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        observeLiveData()
        setupListeners()
        getData()
        loadData("https://images.genius.com/495a29bfaf1daac2fc3ced087983b3d1.1000x1000x1.jpg")
    }

    private fun inject() {
        mainActivityViewModel =
            getViewModel(this, viewModelFactory)
        layoutManager = LinearLayoutManager(this)
        adapter = SongAdapter(glide)
        searchFragment = SearchFragment.newInstance()
    }

    private fun loadData(url: String) {
        glide
            .load(url)
            .apply(
                RequestOptions()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .override(Target.SIZE_ORIGINAL)
                    .transform(RoundedCorners(2.dpToPx()))
            )
            .into(ivSongCover)


        tvSongName.text = "Youngblood"
        tvArtistName.text = "5SOS"

        tvSongNameExpanded.text = "Youngblood"
        tvArtistNameExpanded.text = "5SOS"
    }

    private fun getData() {
        mainActivityViewModel.getPlaylistsForCategory("pop")
        searchFragment.setOnCategoryItemClickListener(this)
    }

    private fun observeLiveData() {

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

    private fun setData(playlistResponse: PlaylistResponse) {
        rvSongs.layoutManager = layoutManager
        rvSongs.adapter = adapter
        adapter.setOnItemClickListener(this)
        adapter.setSongs(playlistResponse.playlists.playlistItemList)
    }

    private fun replaceFragment(container: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .commit()
    }

    private fun addFragmentToBackStack(container: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(fragment.tag)
            .add(container, fragment)
            .commit()
    }

    private fun popFragmentFromBackStack(fragment: Fragment) {
        supportFragmentManager.popBackStack(fragment.tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun setupListeners() {
        bottomMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(
                        R.id.fragmentContainer,
                        DemoFragment.newInstance()
                    )
                }
                R.id.search -> {
                    if (::searchFragment.isInitialized)
                        replaceFragment(
                            R.id.fragmentContainer,
                            searchFragment
                        )
                }
                R.id.library -> {
                    replaceFragment(
                        R.id.fragmentContainer,
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
                    } else {
                        videoViewContainer.setBackgroundColor(dominantColor)
                        clBottomLayout.setBackgroundColor(dominantColor)
                    }
                }
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {

            }

        })

        rvSongs.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    if (!recyclerView.canScrollVertically(-1)) {
                        Timber.i("DOING TRANSITION")
                        //TODO: FIX CASE WHERE ANIM DOESNT WORK SOMETIME ON EXPANDING
                        mainMotionLayout.transitionToState(
                            R.id.expanded
                        )
                    }
                }
            }
        })
    }

    @SuppressLint("CheckResult")
    override fun onItemClick(position: Int, itemPlaylist: ItemPlaylist) {
        loadData(itemPlaylist.imageList[0].url)
        glide
            .asBitmap()
            .load(itemPlaylist.imageList[0].url)
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

    override fun onBackPressed() {
        when (mainMotionLayout.currentState) {
            R.id.expanded -> mainMotionLayout.transitionToState(R.id.collapsed)
            R.id.bottomExpanded -> mainMotionLayout.transitionToState(R.id.expanded)
            else -> super.onBackPressed()
        }
    }

    override fun onSearchedCategoryClicked(
        categoryId: String,
        categoryName: String,
        position: Int
    ) {
        categoryDetailsFragment = CategoryDetailsFragment.newInstance(categoryId, categoryName)
        categoryDetailsFragment.setOnCategoryDetailsItemClickListener(this)
        addFragmentToBackStack(R.id.fragmentContainer, categoryDetailsFragment)

    }

    override fun onCategoryClicked(
        playlistId: String,
        playlistName: String,
        playlistImageUrl: String
    ) {
        playlistTracksFragment = PlaylistTracksFragment.newInstance(playlistId,playlistName, playlistImageUrl)
        addFragmentToBackStack(R.id.fragmentContainer, playlistTracksFragment)
    }

    override fun onRemoveCategoryDetailsFragment() {
        if (::categoryDetailsFragment.isInitialized)
            popFragmentFromBackStack(categoryDetailsFragment)
    }
}
