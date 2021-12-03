package com.example.boardbattle2_0.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.boardbattle2_0.ApplicationFragment
import com.example.boardbattle2_0.R

class GameFragment : ApplicationFragment() {

    override fun navigateUp(): Boolean = findNavController().navigateUp()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

}