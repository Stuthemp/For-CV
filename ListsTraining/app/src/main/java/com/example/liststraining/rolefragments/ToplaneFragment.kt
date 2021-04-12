package com.example.liststraining.rolefragments

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.liststraining.ChampsAdapter
import com.example.liststraining.OnRecyclerItemClicked
import com.example.liststraining.R
import com.example.liststraining.data.models.Champion
import com.example.liststraining.domain.ChampionsDataSource
import com.example.liststraining.domain.ToplaneChampionsDataSource

class ToplaneFragment: Fragment() {
    private var recycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toplaners, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.rv_champions)
        recycler?.adapter= ChampsAdapter(clickListener)
    }

    override fun onStart() {
        super.onStart()

        updateData()
    }

    override fun onDetach() {
        recycler=null
        super.onDetach()
    }

    private fun updateData(){
        (recycler?.adapter as? ChampsAdapter)?.apply {
            bindChampions(ToplaneChampionsDataSource().getChampions())
        }
    }

    private fun doOnClick(champion: Champion) {
        var player: MediaPlayer = MediaPlayer.create(context,champion.sound)
        player.start()

    }

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(champion: Champion) {
            doOnClick(champion)
        }
    }


    companion object {
        fun newInstance() = ToplaneFragment()
    }

}