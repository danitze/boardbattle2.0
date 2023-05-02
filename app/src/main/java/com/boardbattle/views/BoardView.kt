package com.boardbattle.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.boardbattle.*
import com.boardbattle.data.GameState
import com.boardbattle.utils.CELLS_HORIZONTAL
import com.boardbattle.utils.CELLS_VERTICAL
import com.boardbattle.utils.displayWidth
import com.boardbattle.utils.getColor

class BoardView(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val cells = Array(CELLS_VERTICAL) {
        arrayOfNulls<View>(CELLS_HORIZONTAL)
    }
    private val cellSize = context.displayWidth / CELLS_HORIZONTAL

    /**
     * Initializes board, creates rows and cells
     */
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
        cell.background = getColor(R.color.background_color_1)
        cells[row][column] = cell
        return cell
    }

    /**
     * Recolors cells according to game state
     */
    fun setBoard(state: GameState) {
        val canPlace = state.canPlace()
        for(i in cells.indices) {
            for(j in cells[i].indices) {
                cells[i][j]?.background = if(state.board[i][j][0] != 0) {
                    if(canPlace) getColor(R.color.can_place_color)
                    else getColor(R.color.cannot_place_color)
                } else when(state.board[i][j][1]) {
                    1 -> getColor(R.color.player_1_color)
                    2 -> getColor(R.color.player_2_color)
                    else -> getColor(R.color.background_color_1)
                }
            }
        }
    }

}