package com.example.boardbattle2_0.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.boardbattle2_0.R

class ControllerView(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(
            R.layout.layout_controller,
            this,
            true
        )
    }
}