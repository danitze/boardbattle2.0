package com.example.boardbattle2_0.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.navigation.navGraphViewModels
import com.example.boardbattle2_0.R
import com.example.boardbattle2_0.utils.TO_GAME_CONTINUE
import com.example.boardbattle2_0.utils.TO_GAME_NEW
import com.example.boardbattle2_0.utils.TO_MENU
import com.example.boardbattle2_0.viewmodels.NavViewModel

class ContinueGameDialogFragment : DialogFragment() {

    private val navViewModel: NavViewModel by navGraphViewModels(R.id.main_nav)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.continue_game_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpControllers(view)
    }

    private fun setUpControllers(view: View) {
        view.findViewById<Button>(R.id.acceptBtn).setOnClickListener {
            navViewModel.navigate(TO_GAME_CONTINUE)
            dismiss()
        }
        view.findViewById<Button>(R.id.denyBtn).setOnClickListener {
            navViewModel.navigate(TO_GAME_NEW)
            dismiss()
        }
    }

}