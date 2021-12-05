package com.example.boardbattle2_0.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.boardbattle2_0.R

class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val callback = object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //TODO implement showing alert dialog
            }
        }
        //requireActivity().onBackPressedDispatcher.addCallback(callback)
        super.onCreate(savedInstanceState)
    }

}