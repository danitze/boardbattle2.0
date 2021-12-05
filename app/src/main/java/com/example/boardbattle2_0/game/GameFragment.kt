package com.example.boardbattle2_0.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.boardbattle2_0.R
import com.example.boardbattle2_0.views.BoardView
import kotlinx.coroutines.flow.collect

class GameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers(view)
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

    fun setUpObservers(view: View) {
        val boardView = view.findViewById<BoardView>(R.id.boardView)
        lifecycleScope.launchWhenStarted {
            viewModel.gameFlow.collect {
                boardView.setBoard(it)
            }
        }
    }

}