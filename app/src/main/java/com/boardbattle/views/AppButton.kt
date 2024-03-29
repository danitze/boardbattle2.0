package com.boardbattle.views

import android.content.Context
import android.media.MediaPlayer
import android.util.AttributeSet
import com.boardbattle.R

/**
 * Class for button which plays custom sound instead of basic one when clicked
 */
class AppButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatButton(context, attrs) {

    init {
        isSoundEffectsEnabled = false
    }

    private val mediaPlayer = MediaPlayer.create(context, R.raw.click)

    override fun performClick(): Boolean {
        mediaPlayer.start()
        return super.performClick()
    }
}