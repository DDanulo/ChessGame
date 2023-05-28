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
            CellPieceParam.ROOK -> rookMove(firstClickCell.cellTeamParam)
            CellPieceParam.QUEEN -> queenMove(firstClickCell.cellTeamParam)
            CellPieceParam.KING -> kingMove(firstClickCell.cellTeamParam)
            CellPieceParam.DUCK -> duckMove()
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
                        if (secondClickCell.cellPieceParam != CellPieceParam.DUCK) {
                            variable = true
                        }
                    } else if (chessField.getCell(
                            firstClickRow + 1,
                            firstClickColumn
                        ).cellPieceParam != CellPieceParam.NOTHING
                    ) {
                        variable = false
                    } else if (firstClickColumn == secondClickColumn) {
                        if (firstClickRow == 1 && (secondClickRow == 2 || secondClickRow == 3) && secondClickCell.cellPieceParam != CellPieceParam.DUCK) {
                            variable = true
                        } else if (secondClickRow == firstClickRow + 1 && secondClickCell.cellPieceParam != CellPieceParam.DUCK) {
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
                    if (secondClickCell.cellPieceParam != CellPieceParam.DUCK) {
                        variable = true
                    }

                } else if (chessField.getCell(
                        firstClickRow - 1,
                        firstClickColumn
                    ).cellPieceParam != CellPieceParam.NOTHING
                ) {
                    variable = false
                } else if (firstClickColumn == secondClickColumn) {
                    if (firstClickRow == 6 && (secondClickRow == 5 || secondClickRow == 4) && secondClickCell.cellPieceParam != CellPieceParam.DUCK) {
                        variable = true
                    } else if (secondClickRow == firstClickRow - 1 && secondClickCell.cellPieceParam != CellPieceParam.DUCK) {
                        variable = true
                    }
                }
            }
        }
        return variable
    }

    private fun bishopMove(teamParam: CellTeamParam): Boolean {
        var variable = false
        var isPieceThere = 0
        var i: Int = firstClickRow
        var j: Int = firstClickColumn
        val diffRow: Int = abs(secondClickRow - firstClickRow)
        val diffColumn: Int = abs(secondClickColumn - firstClickColumn)
        if (diffRow == diffColumn) {
            if (secondClickColumn > firstClickColumn) {
                if (secondClickRow > firstClickRow) {
                    while (i <= secondClickRow && j <= secondClickColumn) {
                        if (i != firstClickRow && j != firstClickColumn) {
                            if (chessField.getCell(i, j).cellPieceParam != CellPieceParam.NOTHING) {
                                isPieceThere++
                            }
                            variable = true
                        }
                        i++
                        j++
                    }
                } else if (secondClickRow < firstClickRow) {
                    while (i >= secondClickRow && j <= secondClickColumn) {
                        if (i != firstClickRow && j != firstClickColumn) {
                            if (chessField.getCell(i, j).cellPieceParam != CellPieceParam.NOTHING) {
                                isPieceThere++
                            }
                            variable = true
                        }
                        i--
                        j++
                    }
                }
            } else if (secondClickColumn < firstClickColumn) {
                if (secondClickRow > firstClickRow) {
                    while (i <= secondClickRow || j >= secondClickColumn) {
                        if (i != firstClickRow && j != firstClickColumn) {
                            if (chessField.getCell(i, j).cellPieceParam != CellPieceParam.NOTHING) {
                                isPieceThere++
                            }
                            variable = true
                        }
                        i++
                        j--
                    }
                } else if (secondClickRow < firstClickRow) {
                    while (i >= secondClickRow || j >= secondClickColumn) {
                        if (i != firstClickRow && j != firstClickColumn) {
                            if (chessField.getCell(i, j).cellPieceParam != CellPieceParam.NOTHING) {
                                isPieceThere++
                            }
                            variable = true
                        }
                        i--
                        j--
                    }
                }
            }
        }
        return if (isPieceThere == 1 && chessField.getCellTeamParam(
                secondClickRow,
                secondClickColumn
            ) != teamParam && chessField.getCellTeamParam(
                secondClickRow,
                secondClickColumn
            ) != CellTeamParam.NONE || isPieceThere == 0
        ) {
            variable
        } else {
            false
        }
    }

    private fun knightMove(teamParam: CellTeamParam): Boolean {
        var variable = false
        if (secondClickRow == firstClickRow + 2
            && (secondClickColumn == firstClickColumn + 1 || secondClickColumn == firstClickColumn - 1)
            && teamParam != secondClickCell.cellTeamParam && secondClickCell.cellPieceParam != CellPieceParam.DUCK
        ) {
            variable = true
        }
        if (secondClickRow == firstClickRow - 2
            && (secondClickColumn == firstClickColumn + 1 || secondClickColumn == firstClickColumn - 1)
            && teamParam != secondClickCell.cellTeamParam && secondClickCell.cellPieceParam != CellPieceParam.DUCK
        ) {
            variable = true
        }
        if (secondClickColumn == firstClickColumn + 2
            && (secondClickRow == firstClickRow + 1 || secondClickRow == firstClickRow - 1)
            && teamParam != secondClickCell.cellTeamParam && secondClickCell.cellPieceParam != CellPieceParam.DUCK
        ) {
            variable = true
        }
        if (secondClickColumn == firstClickColumn - 2
            && (secondClickRow == firstClickRow + 1 || secondClickRow == firstClickRow - 1)
            && teamParam != secondClickCell.cellTeamParam && secondClickCell.cellPieceParam != CellPieceParam.DUCK
        ) {
            variable = true
        }
        return variable
    }

    private fun rookMove(teamParam: CellTeamParam): Boolean {
        var variable = false
        var isPieceThere = 0
        var i: Int = firstClickRow
        var j: Int = firstClickColumn
        if (firstClickRow == secondClickRow) {
            if (secondClickColumn > firstClickColumn) {
                while (j <= secondClickColumn) {
                    if (j != firstClickColumn) {
                        if (chessField.getCell(i, j).cellPieceParam != CellPieceParam.NOTHING) {
                            isPieceThere++
                        }
                        variable = true
                    }
                    j++
                }
            } else if (secondClickColumn < firstClickColumn) {
                while (j >= secondClickColumn) {
                    if (j != firstClickColumn) {
                        if (chessField.getCell(i, j).cellPieceParam != CellPieceParam.NOTHING) {
                            isPieceThere++
                        }
                        variable = true
                    }
                    j--
                }
            }
        }
        if (firstClickColumn == secondClickColumn) {
            if (secondClickRow > firstClickRow) {
                while (i <= secondClickRow) {
                    if (i != firstClickRow) {
                        if (chessField.getCell(i, j).cellPieceParam != CellPieceParam.NOTHING) {
                            isPieceThere++
                        }
                        variable = true
                    }
                    i++
                }
            } else if (secondClickRow < firstClickRow) {
                while (i >= secondClickRow) {
                    if (i != firstClickRow) {
                        if (chessField.getCell(i, j).cellPieceParam != CellPieceParam.NOTHING) {
                            isPieceThere++
                        }
                        variable = true
                    }
                    i--
                }
            }
        }
        return if (isPieceThere == 1 && chessField.getCellTeamParam(
                secondClickRow,
                secondClickColumn
            ) != teamParam && chessField.getCellTeamParam(
                secondClickRow,
                secondClickColumn
            ) != CellTeamParam.NONE || isPieceThere == 0
        ) {
            variable
        } else {
            false
        }
    }

    private fun queenMove(teamParam: CellTeamParam): Boolean {
        var variable = false
        if (bishopMove(teamParam) || rookMove(teamParam)) {
            variable = true
        }
        return variable
    }

    private fun kingMove(teamParam: CellTeamParam): Boolean {
        var variable = false
        if (secondClickRow == firstClickRow + 1 || secondClickRow == firstClickRow - 1 || secondClickRow == firstClickRow) {
            if ((secondClickColumn == firstClickColumn || secondClickColumn + 1 == firstClickColumn || secondClickColumn - 1 == firstClickColumn) && teamParam != secondClickCell.cellTeamParam && secondClickCell.cellPieceParam != CellPieceParam.DUCK) {
                variable = true
            }
        }
        return variable
    }

    private fun duckMove(): Boolean {
        var variable = false
        if (secondClickCell.cellPieceParam == CellPieceParam.NOTHING) {
            variable = true
        }
        return variable
    }
}