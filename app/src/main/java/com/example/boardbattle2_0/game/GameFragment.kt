package com.example.boardbattle2_0.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.boardbattle2_0.R
import com.example.boardbattle2_0.views.BoardView
import com.example.boardbattle2_0.views.ControllerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        setUpControllers(view)
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

    private fun setUpObservers(view: View) {
        val boardView = view.findViewById<BoardView>(R.id.boardView)
        lifecycleScope.launch {
            viewModel.gameStatesFlow.collect {
                if(it.freeSpace == 0) {
                    findNavController().popBackStack()
                }
                boardView.setBoard(it)
            }
        }
    }

    private fun setUpControllers(view: View) {
        val controllerView = view.findViewById<ControllerView>(R.id.controllerView)
        controllerView.setClickListeners(
            leftClick = viewModel::moveLeft,
            rightClick = viewModel::moveRight,
            upClick = viewModel::moveUp,
            downClick = viewModel::moveDown
        )

        controllerView.setLongClickListeners(
            leftLongClick = viewModel::moveLeftTillStart,
            rightLongClick = viewModel::moveRightTillEnd,
            upLongClick = viewModel::moveUpTillStart,
            downLongClick = viewModel::moveDownTillEnd
        )

        view.findViewById<Button>(R.id.turnBtn).setOnClickListener {
            viewModel.turn()
        }

        view.findViewById<Button>(R.id.placeBtn).setOnClickListener {
            viewModel.place()
        }
    }

}