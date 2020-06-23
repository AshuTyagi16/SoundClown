package com.sasuke.soundclown.ui.tracks_playlist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.network_models.Status
import com.sasuke.soundclown.ui.base.BaseFragment
import com.sasuke.soundclown.ui.base.ItemDecorator
import kotlinx.android.synthetic.main.cell_category_detail.view.*
import kotlinx.android.synthetic.main.fragment_playlist_tracks.*
import javax.inject.Inject

class PlaylistTracksFragment : BaseFragment() {

    @Inject
    lateinit var adapter: PlaylistTrackAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var itemDecorator: ItemDecorator

    @Inject
    lateinit var glide: RequestManager
    private val requestOptions = RequestOptions().override(Target.SIZE_ORIGINAL)

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewModel: PlaylistTracksViewModel
    private lateinit var playlistId: String
    private lateinit var playlistName: String
    private lateinit var playlistImageUrl: String

    companion object {
        private const val EXTRA_PLAYLIST_ID = "EXTRA_PLAYLIST_ID"
        private const val EXTRA_PLAYLIST_NAME = "EXTRA_PLAYLIST_NAME"
        private const val EXTRA_PLAYLIST_IMAGE_URL = "EXTRA_PLAYLIST_IMAGE_URL"
        fun newInstance(
            playlistId: String,
            playlistName: String,
            playlistImageUrl: String
        ): PlaylistTracksFragment {
            val fragment = PlaylistTracksFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_PLAYLIST_ID, playlistId)
            bundle.putString(EXTRA_PLAYLIST_NAME, playlistName)
            bundle.putString(EXTRA_PLAYLIST_IMAGE_URL, playlistImageUrl)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_playlist_tracks
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        getArgument()
        initialiseLayout()
        getData()
        setClickListeners()
        observeLiveData()
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(PlaylistTracksViewModel::class.java)
    }

    private fun getArgument() {
        arguments?.let {
            it.getString(EXTRA_PLAYLIST_ID)?.let { pId ->
                playlistId = pId
            }
            it.getString(EXTRA_PLAYLIST_NAME)?.let { pName ->
                playlistName = pName
            }
            it.getString(EXTRA_PLAYLIST_IMAGE_URL)?.let { pUrl ->
                playlistImageUrl = pUrl
            }
        }
    }

    private fun initialiseLayout() {

        if (::playlistImageUrl.isInitialized)
            glide.load(playlistImageUrl)
                .apply(requestOptions)
                .into(ivPlaylistImage)

        if (::playlistName.isInitialized)
            tvPlaylistName.text = playlistName

        linearLayoutManager = LinearLayoutManager(requireContext())
        rvTracks.layoutManager = linearLayoutManager
        rvTracks.addItemDecoration(itemDecorator)
        rvTracks.adapter = adapter
    }

    private fun getData() {
        if (::playlistId.isInitialized)
            viewModel.getPlaylistTracks(playlistId)
    }

    private fun setClickListeners() {

    }

    private fun observeLiveData() {
        viewModel.playlistTracksLiveData.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING->{

                }
                Status.SUCCESS->{
                    it.data?.let {
                        adapter.initPlaylistTrackList(it.itemPlaylistTrack)
                    }
                }
                Status.ERROR->{
                    showErrorToast("${it.message}")
                }
            }
        })
    }

}