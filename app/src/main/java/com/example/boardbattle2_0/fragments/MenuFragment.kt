package com.example.boardbattle2_0.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import com.example.boardbattle2_0.viewmodels.NavViewModel
import com.example.boardbattle2_0.R
import com.example.boardbattle2_0.data.GameState
import com.example.boardbattle2_0.fragments.dialog.ContinueGameDialogFragment
import com.example.boardbattle2_0.utils.*
import com.example.boardbattle2_0.viewmodels.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first

@AndroidEntryPoint
class MenuFragment : NavFragment() {

    private val viewModel: MenuViewModel by viewModels()
    override val navViewModel: NavViewModel by hiltNavGraphViewModels(R.id.main_nav)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpListeners(view)
        setUpObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpListeners(view: View) {
        view.findViewById<Button>(R.id.playBtn).setOnClickListener(::onClick)
        view.findViewById<Button>(R.id.settingsBtn).setOnClickListener(::onClick)
        view.findViewById<Button>(R.id.exitBtn).setOnClickListener(::onClick)
    }

    private fun setUpObservers() {
        lifecycleScope.launchWhenStarted {
            navViewModel.navFlow.collect {
                when(it) {
                    TO_GAME -> navigateAndReset(R.id.action_menu_to_game)
                    TO_GAME_NEW -> navigateAndReset(
                        R.id.action_menu_to_game,
                        bundleOf(CONTINUE_GAME_KEY to false)
                    )
                    TO_GAME_CONTINUE -> navigateAndReset(
                        R.id.action_menu_to_game,
                        bundleOf(CONTINUE_GAME_KEY to true)
                    )
                    TO_SETTINGS -> navigateAndReset(R.id.action_menu_to_settings)
                    TO_EXIT -> requireActivity().finish()
                }
            }
        }
    }

    private fun onClick(view: View) = when(view.id) {
        R.id.playBtn -> {
            lifecycleScope.launchWhenStarted {
                val gameState = viewModel.savedGameStateFlow.first().copy()
                if(gameState == GameState()) {
                    navViewModel.navigate(TO_GAME)
                } else {
                    ContinueGameDialogFragment().show(childFragmentManager, CONTINUE_GAME_FRAGMENT)
                }
            }
            Unit
        }
        R.id.settingsBtn -> navViewModel.navigate(TO_SETTINGS)
        R.id.exitBtn -> navViewModel.navigate(TO_EXIT)
        else -> {}
    }

}