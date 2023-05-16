package com.example.chess

class CellsFactory {
    private val cells = Array(ChessField.SIZE_OF_BOARD) {
        Array(ChessField.SIZE_OF_BOARD) {
            Cell()
        }
    }

    fun createStartingPosition() : Array<Array<Cell>>{
        cells[0][0] = Cell(cellPieceParam = CellPieceParam.ROOK, cellTeamParam = CellTeamParam.BLACK)
        cells[0][1] = Cell(cellPieceParam = CellPieceParam.KNIGHT, cellTeamParam = CellTeamParam.BLACK)
        cells[0][2] = Cell(cellPieceParam = CellPieceParam.BISHOP, cellTeamParam = CellTeamParam.BLACK)
        cells[0][3] = Cell(cellPieceParam = CellPieceParam.QUEEN, cellTeamParam = CellTeamParam.BLACK)
        cells[0][4] = Cell(cellPieceParam = CellPieceParam.KING, cellTeamParam = CellTeamParam.BLACK)
        cells[0][5] = Cell(cellPieceParam = CellPieceParam.BISHOP, cellTeamParam = CellTeamParam.BLACK)
        cells[0][6] = Cell(cellPieceParam = CellPieceParam.KNIGHT, cellTeamParam = CellTeamParam.BLACK)
        cells[0][7] = Cell(cellPieceParam = CellPieceParam.ROOK, cellTeamParam = CellTeamParam.BLACK)
        cells[1][0] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.BLACK)
        cells[1][1] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.BLACK)
        cells[1][2] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.BLACK)
        cells[1][3] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.BLACK)
        cells[1][4] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.BLACK)
        cells[1][5] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.BLACK)
        cells[1][6] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.BLACK)
        cells[1][7] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.BLACK)

        cells[7][0] = Cell(cellPieceParam = CellPieceParam.ROOK, cellTeamParam = CellTeamParam.WHITE)
        cells[7][1] = Cell(cellPieceParam = CellPieceParam.KNIGHT, cellTeamParam = CellTeamParam.WHITE)
        cells[7][2] = Cell(cellPieceParam = CellPieceParam.BISHOP, cellTeamParam = CellTeamParam.WHITE)
        cells[7][3] = Cell(cellPieceParam = CellPieceParam.QUEEN, cellTeamParam = CellTeamParam.WHITE)
        cells[7][4] = Cell(cellPieceParam = CellPieceParam.KING, cellTeamParam = CellTeamParam.WHITE)
        cells[7][5] = Cell(cellPieceParam = CellPieceParam.BISHOP, cellTeamParam = CellTeamParam.WHITE)
        cells[7][6] = Cell(cellPieceParam = CellPieceParam.KNIGHT, cellTeamParam = CellTeamParam.WHITE)
        cells[7][7] = Cell(cellPieceParam = CellPieceParam.ROOK, cellTeamParam = CellTeamParam.WHITE)
        cells[6][0] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.WHITE)
        cells[6][1] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.WHITE)
        cells[6][2] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.WHITE)
        cells[6][3] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.WHITE)
        cells[6][4] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.WHITE)
        cells[6][5] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.WHITE)
        cells[6][6] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.WHITE)
        cells[6][7] = Cell(cellPieceParam = CellPieceParam.PAWN, cellTeamParam = CellTeamParam.WHITE)
        return cells
    }
}