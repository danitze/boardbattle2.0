package com.example.boardbattle2_0.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.boardbattle2_0.ApplicationFragment
import com.example.boardbattle2_0.R
import kotlinx.coroutines.flow.collect

class MenuFragment : ApplicationFragment() {
    private val viewModel: MenuViewModel by viewModels()

    override fun navigateUp(): Boolean = false

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
                    TO_GAME -> findNavController().navigate(R.id.action_menu_to_game)
                    TO_SETTINGS -> findNavController().navigate(R.id.action_menu_to_settings)
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

}