package com.boardbattle.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import com.boardbattle.R

/**
 * Class with game controller
 */
class ControllerView(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val leftButton: Button
    private val rightButton: Button
    private val upButton: Button
    private val downButton: Button

    init {
        val view = LayoutInflater.from(context).inflate(
            R.layout.layout_controller,
            this,
            true
        )
        leftButton = view.findViewById(R.id.leftBtn)
        rightButton = view.findViewById(R.id.rightBtn)
        upButton = view.findViewById(R.id.upBtn)
        downButton = view.findViewById(R.id.downBtn)
    }

    fun setClickListeners(
        leftClick: () -> Any,
        rightClick: () -> Any,
        upClick: () -> Any,
        downClick: () -> Any
    ) {
        leftButton.setOnClickListener { leftClick() }
        rightButton.setOnClickListener { rightClick() }
        upButton.setOnClickListener { upClick() }
        downButton.setOnClickListener { downClick() }
    }

    fun setLongClickListeners(
        leftLongClick: () -> Any,
        rightLongClick: () -> Any,
        upLongClick: () -> Any,
        downLongClick: () -> Any
    ) {
        leftButton.setOnLongClickListener {
            leftLongClick()
            true
        }

        rightButton.setOnLongClickListener {
            rightLongClick()
            true
        }

        upButton.setOnLongClickListener {
            upLongClick()
            true
        }

        downButton.setOnLongClickListener {
            downLongClick()
            true
        }
    }
}