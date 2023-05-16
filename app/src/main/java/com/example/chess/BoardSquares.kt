package com.example.chess

import android.graphics.Rect

data class BoardSquares(val xStart: Float, val yStart: Float, val cellSize: Float) {

    var squares = Array(8) { Array(8) { Rect() } }

    init {
        for (i in 0..7) {
            for (j in 0..7) {
                val cellStartX: Float = xStart + cellSize * i
                val cellStartY: Float = yStart + cellSize * j
                val cellEndX: Float = xStart + cellSize * (i + 1)
                val cellEndY: Float = yStart + cellSize * (j + 1)
                squares[j][i] = Rect(
                    cellStartX.toInt(), cellStartY.toInt(),
                    cellEndX.toInt(), cellEndY.toInt()
                )
            }
        }
    }
}