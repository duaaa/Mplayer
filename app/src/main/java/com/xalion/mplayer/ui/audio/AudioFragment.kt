package com.xalion.mplayer.ui.audio

import android.Manifest
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.permissionx.guolindev.PermissionX

import com.xalion.mplayer.R
import com.xalion.mplayer.contract.AudioContract
import com.xalion.mplayer.model.Audio
import com.xalion.mplayer.presenter.AudioPresenter
import com.xalion.mplayer.ui.adapter.AudioAdapter
import com.xalion.mplayer.ui.base.BaseFragment
import kotlinx.android.synthetic.main.audio_fragment.*
import kotlin.coroutines.CoroutineContext

class AudioFragment : BaseFragment<AudioContract.View, AudioPresenter>(),
    AudioContract.View {

    override var mPresenter: AudioPresenter = AudioPresenter()
    private lateinit var viewModel: AudioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PermissionX.init(activity)
            .permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    mPresenter.loadAudios(0)
                }
            }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        recycler_view.setItemAnimator(DefaultItemAnimator())

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.audio_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AudioViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onAudiosLoaded(lstAudio: ArrayList<Audio>) {
        val adapter = AudioAdapter(context, lstAudio)//{ video -> adapterOnClick(video) }
        recycler_view.adapter = adapter
    }

    override fun onAudioIdsLoaded(lstIds: ArrayList<Long>?) {}

    override fun showRepository() {
        TODO("Not yet implemented")
    }

    override fun showReloadButton() {
        TODO("Not yet implemented")
    }




}
