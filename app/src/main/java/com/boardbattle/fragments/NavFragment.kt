package com.boardbattle.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.boardbattle.viewmodels.NavViewModel

/**
 * Superclass for all game fragments which provide convenient methods for correct navigation
 * and allows to avoid repeating of code
 */
abstract class NavFragment : Fragment() {

    protected abstract val navViewModel: NavViewModel

    protected fun navigateAndReset(navActionRes: Int, args: Bundle? = null) {
        navViewModel.reset()
        findNavController().navigate(navActionRes, args)
    }

    protected fun popBackStackAndReset() {
        navViewModel.reset()
        findNavController().popBackStack()
    }

}