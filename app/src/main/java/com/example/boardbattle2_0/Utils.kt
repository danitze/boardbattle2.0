package com.example.boardbattle2_0

import android.content.Context
import android.view.View

val Context.displayWidth
get() = resources.displayMetrics.widthPixels

val Context.displayHeight
get() = resources.displayMetrics.heightPixels

fun View.getColor(colorRes: Int) = context.getDrawable(colorRes)