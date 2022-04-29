package com.example.boardbattle2_0

import android.content.Context
import android.view.View
import androidx.appcompat.content.res.AppCompatResources

val Context.displayWidth
get() = resources.displayMetrics.widthPixels

val Context.displayHeight
get() = resources.displayMetrics.heightPixels

fun View.getColor(colorRes: Int) = AppCompatResources.getDrawable(context, colorRes)