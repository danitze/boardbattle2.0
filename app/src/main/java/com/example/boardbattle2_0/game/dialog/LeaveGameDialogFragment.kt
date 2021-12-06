package com.example.boardbattle2_0.game.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.boardbattle2_0.R
import com.example.boardbattle2_0.game.viewmodel.GameNavViewModel

class LeaveGameDialogFragment : DialogFragment() {

    private val gameNavViewModel: GameNavViewModel by activityViewModels()

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
            gameNavViewModel.navToMenu()
            dismiss()
        }
        view.findViewById<Button>(R.id.denyBtn).setOnClickListener {
            dismiss()
        }
    }

}