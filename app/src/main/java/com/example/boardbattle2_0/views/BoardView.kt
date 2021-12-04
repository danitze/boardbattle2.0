package com.example.boardbattle2_0.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.boardbattle2_0.*

class BoardView(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val cells = Array(CELLS_VERTICAL) {
        arrayOfNulls<View>(CELLS_HORIZONTAL)
    }
    private val cellSize = context.displayWidth / CELLS_HORIZONTAL

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        val matrixLayout = LinearLayout(context)
        matrixLayout.layoutParams = layoutParams
        matrixLayout.orientation = LinearLayout.VERTICAL
        for(i in cells.indices) {
            val row = LinearLayout(context)
            row.layoutParams = layoutParams
            row.orientation = LinearLayout.HORIZONTAL
            for(j in cells[i].indices) {
                row.addView(createCell(i, j))
            }
            matrixLayout.addView(row)
        }
        addView(matrixLayout)
    }

    private fun createCell(row: Int, column: Int): View {
        val cell = View(context)
        cell.layoutParams = LayoutParams(cellSize, cellSize)
        cell.background = context.getDrawable(R.color.black) //TODO standard background image
        cells[row][column] = cell
        return cell
    }

}