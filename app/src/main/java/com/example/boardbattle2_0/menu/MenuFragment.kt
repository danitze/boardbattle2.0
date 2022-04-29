package com.example.boardbattle2_0.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.boardbattle2_0.NavViewModel
import com.example.boardbattle2_0.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private val viewModel: NavViewModel by hiltNavGraphViewModels(R.id.main_nav)

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
            viewModel.navFlow.collect {
                when(it) {
                    TO_GAME -> navigateAndReset(R.id.action_menu_to_game)
                    TO_SETTINGS -> navigateAndReset(R.id.action_menu_to_settings)
                    TO_EXIT -> requireActivity().finish()
                }
            }
        }
    }

    private fun onClick(view: View) = when(view.id) {
        R.id.playBtn -> viewModel.navigate(TO_GAME)
        R.id.settingsBtn -> viewModel.navigate(TO_SETTINGS)
        R.id.exitBtn -> viewModel.navigate(TO_EXIT)
        else -> {}
    }

    private fun navigateAndReset(navActionRes: Int) {
        viewModel.reset()
        findNavController().navigate(navActionRes)
    }

}