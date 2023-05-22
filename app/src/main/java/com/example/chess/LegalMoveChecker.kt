package com.example.chess

import kotlin.math.abs

class LegalMoveChecker(
    private val chessField: ChessField,
    private val firstClickCell: Cell,
    private val secondClickCell: Cell,
    private val firstClickRow: Int,
    private val firstClickColumn: Int,
    private val secondClickRow: Int,
    private val secondClickColumn: Int
) {
    val isMoveLegal: Boolean
        get() = checkLegalMove()

    private fun checkLegalMove(): Boolean {
        val variable = when (firstClickCell.cellPieceParam) {
            CellPieceParam.NOTHING -> TODO()
            CellPieceParam.PAWN -> pawnMove(firstClickCell.cellTeamParam)
            CellPieceParam.BISHOP -> bishopMove(firstClickCell.cellTeamParam)
            CellPieceParam.KNIGHT -> knightMove(firstClickCell.cellTeamParam)
            CellPieceParam.ROOK -> TODO()
            CellPieceParam.QUEEN -> TODO()
            CellPieceParam.KING -> TODO()
        }

        return variable
    }

    private fun pawnMove(teamParam: CellTeamParam): Boolean {
        var variable = false
        when (teamParam) {
            CellTeamParam.NONE -> TODO()
            CellTeamParam.BLACK -> {
                if (firstClickRow != 7) {
                    if (firstClickColumn != 7 && chessField.getCell(
                            firstClickRow + 1,
                            firstClickColumn + 1
                        ).cellTeamParam == CellTeamParam.WHITE && secondClickRow == firstClickRow + 1 && secondClickColumn == firstClickColumn + 1
                        || firstClickColumn != 0 && chessField.getCell(
                            firstClickRow + 1,
                            firstClickColumn - 1
                        ).cellTeamParam == CellTeamParam.WHITE && secondClickRow == firstClickRow + 1 && secondClickColumn == firstClickColumn - 1
                    ) {
                        variable = true
                    } else if (chessField.getCell(
                            firstClickRow + 1,
                            firstClickColumn
                        ).cellPieceParam != CellPieceParam.NOTHING
                    ) {
                        variable = false
                    } else if (firstClickColumn == secondClickColumn) {
                        if (firstClickRow == 1 && (secondClickRow == 2 || secondClickRow == 3)) {
                            variable = true
                        } else if (secondClickRow == firstClickRow + 1) {
                            variable = true
                        }
                    }
                }
            }

            CellTeamParam.WHITE -> if (firstClickRow != 0) {
                if (firstClickColumn != 7 && chessField.getCell(
                        firstClickRow - 1,
                        firstClickColumn + 1
                    ).cellTeamParam == CellTeamParam.BLACK && secondClickRow == firstClickRow - 1 && secondClickColumn == firstClickColumn + 1
                    || firstClickColumn != 0 && chessField.getCell(
                        firstClickRow - 1,
                        firstClickColumn - 1
                    ).cellTeamParam == CellTeamParam.BLACK && secondClickRow == firstClickRow - 1 && secondClickColumn == firstClickColumn - 1
                ) {
                    variable = true
                } else if (chessField.getCell(
                        firstClickRow - 1,
                        firstClickColumn
                    ).cellPieceParam != CellPieceParam.NOTHING
                ) {
                    variable = false
                } else if (firstClickColumn == secondClickColumn) {
                    if (firstClickRow == 6 && (secondClickRow == 5 || secondClickRow == 4)) {
                        variable = true
                    } else if (secondClickRow == firstClickRow - 1) {
                        variable = true
                    }
                }
            }
        }
        return variable
    }

    private fun bishopMove(teamParam: CellTeamParam): Boolean {
        var variable = false
        var helper = true
        var i: Int = firstClickRow
        var j: Int = firstClickColumn
        val diffRow: Int = abs(secondClickRow - firstClickRow)
        val diffColumn: Int = abs(secondClickColumn - firstClickColumn)
        if (diffRow == diffColumn) {
            if (secondClickColumn > firstClickColumn) {
                if (secondClickRow > firstClickRow) {
                    while (i <= secondClickRow && j <= secondClickColumn) {
                        if (chessField.getCell(i, j).cellTeamParam != teamParam){
                            variable = true
                        }else if (chessField.getCell(i, j).cellPieceParam != CellPieceParam.NOTHING){
                            helper = false
                        }


                        i++
                        j++
                    }
                } else if (secondClickRow < firstClickRow) {
                    while (i >= secondClickRow && j <= secondClickColumn) {
                        if (chessField.getCell(i, j).cellTeamParam != teamParam){
                            variable = true
                        } else if (chessField.getCell(i, j).cellPieceParam != CellPieceParam.NOTHING){
                            helper = false
                        }

                        i--
                        j++
                    }
                }
            } else if (secondClickColumn < firstClickColumn) {
                if (secondClickRow > firstClickRow) {
                    while (i <= secondClickRow || j >= secondClickColumn) {
                        if (chessField.getCell(i, j).cellTeamParam != teamParam){
                            variable = true
                        }else if (chessField.getCell(i, j).cellPieceParam != CellPieceParam.NOTHING){
                            helper = false
                        }

                        i++
                        j--
                    }
                } else if (secondClickRow < firstClickRow) {
                    while (i >= secondClickRow || j >= secondClickColumn) {
                        if (chessField.getCell(i, j).cellTeamParam != teamParam){
                            variable = true
                        }else if (chessField.getCell(i, j).cellPieceParam != CellPieceParam.NOTHING){
                            helper = false
                        }

                        i--
                        j--
                    }
                }
            }
        }
        return if (helper){
            variable
        }else{
            false
        }
    }

    private fun knightMove(teamParam: CellTeamParam): Boolean {
        var variable = false
        if (firstClickRow + 2 < 8) {
            if (firstClickColumn + 1 < 8 && firstClickColumn - 1 > -1) {
                if (secondClickRow == firstClickRow + 2
                    && (secondClickColumn == firstClickColumn + 1 || secondClickColumn == firstClickColumn - 1)
                    && teamParam != secondClickCell.cellTeamParam
                ) {
                    variable = true
                }
            }
        }
        if (firstClickRow - 2 < 8) {
            if (firstClickColumn + 1 < 8 && firstClickColumn - 1 > -1) {
                if (secondClickRow == firstClickRow - 2
                    && (secondClickColumn == firstClickColumn + 1 || secondClickColumn == firstClickColumn - 1)
                    && teamParam != secondClickCell.cellTeamParam
                ) {
                    variable = true
                }
            }
        }
        if (firstClickColumn + 2 < 8) {
            if (firstClickRow + 1 < 8 && firstClickRow - 1 > -1) {
                if (secondClickColumn == firstClickColumn + 2
                    && (secondClickRow == firstClickRow + 1 || secondClickRow == firstClickRow - 1)
                    && teamParam != secondClickCell.cellTeamParam
                ) {
                    variable = true
                }
            }
        }
        if (firstClickColumn - 2 < 8) {
            if (firstClickRow + 1 < 8 && firstClickRow - 1 > -1) {
                if (secondClickColumn == firstClickColumn - 2
                    && (secondClickRow == firstClickRow + 1 || secondClickRow == firstClickRow - 1)
                    && teamParam != secondClickCell.cellTeamParam
                ) {
                    variable = true
                }
            }
        }
        return variable
    }
}