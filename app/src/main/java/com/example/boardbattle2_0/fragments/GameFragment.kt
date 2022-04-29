package com.example.boardbattle2_0.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.boardbattle2_0.viewmodels.NavViewModel
import com.example.boardbattle2_0.R
import com.example.boardbattle2_0.utils.END_GAME_FRAGMENT
import com.example.boardbattle2_0.utils.LEAVE_GAME_FRAGMENT
import com.example.boardbattle2_0.data.GameState
import com.example.boardbattle2_0.fragments.dialog.EndGameDialogFragment
import com.example.boardbattle2_0.fragments.dialog.LeaveGameDialogFragment
import com.example.boardbattle2_0.viewmodels.GameViewModel
import com.example.boardbattle2_0.utils.TO_MENU
import com.example.boardbattle2_0.views.BoardView
import com.example.boardbattle2_0.views.ControllerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()
    private val navViewModel: NavViewModel by hiltNavGraphViewModels(R.id.main_nav)

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
                val leaveGameDialogFragment = LeaveGameDialogFragment()
                leaveGameDialogFragment.show(childFragmentManager, LEAVE_GAME_FRAGMENT)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        super.onCreate(savedInstanceState)
    }

    override fun onStop() {
        super.onStop()
        viewModel.onGameExit()
    }

    private fun setUpObservers(view: View) {
        val boardView = view.findViewById<BoardView>(R.id.boardView)
        val firstPlayerTv = view.findViewById<TextView>(R.id.player1ScoreTv)
        val secondPlayerTv = view.findViewById<TextView>(R.id.player2ScoreTv)
        lifecycleScope.launch {
            viewModel.gameStatesFlow.collect {
                if(it.freeSpace == 0) {
                    val endGameDialogFragment = EndGameDialogFragment.newInstance(
                        viewModel.getPlayerWithBiggestScore()
                    )
                    endGameDialogFragment.show(childFragmentManager, END_GAME_FRAGMENT)
                }
                boardView.setBoard(it)
                firstPlayerTv.text = getString(
                    R.string.game_points,
                    viewModel.getPlayerSpace(1),
                    viewModel.getPlayerSpacePercent(1)
                )
                secondPlayerTv.text = getString(
                    R.string.game_points,
                    viewModel.getPlayerSpace(2),
                    viewModel.getPlayerSpacePercent(2)
                )
                selectCurrentPlayer(firstPlayerTv, secondPlayerTv, state = it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            navViewModel.navFlow.collect {
                if(it == TO_MENU) {
                    navViewModel.reset()
                    findNavController().popBackStack()
                }
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

    private fun selectCurrentPlayer(vararg views: TextView, state: GameState) {
        views.forEach {
            it.setTypeface(null, Typeface.NORMAL)
        }
        views[state.playerNum - 1].setTypeface(null, Typeface.BOLD)
    }

}