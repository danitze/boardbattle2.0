package com.example.boardbattle2_0.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.navigation.navGraphViewModels
import com.example.boardbattle2_0.viewmodels.NavViewModel
import com.example.boardbattle2_0.R
import com.example.boardbattle2_0.utils.TO_MENU
import com.example.boardbattle2_0.utils.WON_PLAYER_TAG

class EndGameDialogFragment : DialogFragment() {

    private val navViewModel: NavViewModel by navGraphViewModels(R.id.main_nav)

    private val wonPlayerNum
    get() = arguments?.getInt(WON_PLAYER_TAG, 1)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.end_game_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpControllers(view)
    }

    private fun setUpViews(view: View) {
        with(view.findViewById<TextView>(R.id.winTv)) {
            text = getString(R.string.win_message, wonPlayerNum)
            val color = when(wonPlayerNum) {
                1 -> R.color.player_1_color
                2 -> R.color.player_2_color
                else -> {return@with}
            }
            setTextColor(ContextCompat.getColor(context, color))
        }
    }

    private fun setUpControllers(view: View) {
        view.findViewById<Button>(R.id.toMenuBtn).setOnClickListener {
            navViewModel.navigate(TO_MENU)
            dismiss()
        }
    }

    companion object {
        fun newInstance(wonPlayer: Int): EndGameDialogFragment {
            val fragment = EndGameDialogFragment()
            val args = bundleOf(WON_PLAYER_TAG to wonPlayer)
            fragment.arguments = args
            return fragment
        }
    }
}