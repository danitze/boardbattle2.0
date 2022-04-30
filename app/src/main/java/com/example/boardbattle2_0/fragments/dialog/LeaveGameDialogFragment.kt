package com.example.boardbattle2_0.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.navigation.navGraphViewModels
import com.example.boardbattle2_0.viewmodels.NavViewModel
import com.example.boardbattle2_0.R
import com.example.boardbattle2_0.utils.TO_MENU

class LeaveGameDialogFragment : DialogFragment() {

    private val navViewModel: NavViewModel by navGraphViewModels(R.id.main_nav)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.leave_game_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpControllers(view)
    }

    private fun setUpControllers(view: View) {
        view.findViewById<Button>(R.id.acceptBtn).setOnClickListener {
            navViewModel.navigate(TO_MENU)
            dismiss()
        }
        view.findViewById<Button>(R.id.denyBtn).setOnClickListener {
            dismiss()
        }
    }

}