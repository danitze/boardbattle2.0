package com.example.boardbattle2_0.views

import android.content.Context
import android.media.MediaPlayer
import android.util.AttributeSet
import com.example.boardbattle2_0.R

class AppButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatButton(context, attrs) {

    private val mediaPlayer = MediaPlayer.create(context, R.raw.click)

    override fun performClick(): Boolean {
        mediaPlayer.start()
        return super.performClick()
    }
}