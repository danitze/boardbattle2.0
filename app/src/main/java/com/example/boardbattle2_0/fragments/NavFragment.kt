package com.example.boardbattle2_0.fragments

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.boardbattle2_0.viewmodels.NavViewModel

abstract class NavFragment : Fragment() {

    protected abstract val navViewModel: NavViewModel

    protected fun navigateAndReset(navActionRes: Int) {
        navViewModel.reset()
        findNavController().navigate(navActionRes)
    }

    protected fun popBackStackAndReset() {
        navViewModel.reset()
        findNavController().popBackStack()
    }

}