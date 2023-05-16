package com.example.chess

typealias OnFieldChangedListener = (chessField: ChessField) -> Unit

class ChessField {

    companion object{
        const val SIZE_OF_BOARD: Int = 8
    }

    private val cells = CellsFactory().createStartingPosition()

    val listeners = mutableSetOf<OnFieldChangedListener>()

    fun getCellSelectedParam(row: Int, column: Int): CellSelectedParam {
        return cells[row][column].cellSelectedParam
    }

    fun setCellSelectedParam(row: Int, column: Int, cellSelectedParam: CellSelectedParam) {
//        cells[row][column].cellSelectedParam = cellSelectedParam
        if (cells[row][column].cellSelectedParam != cellSelectedParam) {
            cells[row][column].cellSelectedParam = cellSelectedParam
            listeners.forEach { it.invoke(this) }
        }
    }

    fun getCellPieceParam(row: Int, column: Int): CellPieceParam {
        return cells[row][column].cellPieceParam
    }

    fun setCellPieceParam(row: Int, column: Int, cellPieceParam: CellPieceParam) {
//        cells[row][column].cellPieceParam = cellPieceParam
        if (cells[row][column].cellPieceParam != cellPieceParam) {
            cells[row][column].cellPieceParam = cellPieceParam
            listeners.forEach { it.invoke(this) }
        }
    }

    fun getCellTeamParam(row: Int, column: Int): CellTeamParam {
        return cells[row][column].cellTeamParam
    }

    fun setCellTeamParam(row: Int, column: Int, cellTeamParam: CellTeamParam) {
//        cells[row][column].cellTeamParam = cellTeamParam
        if (cells[row][column].cellTeamParam != cellTeamParam) {
            cells[row][column].cellTeamParam = cellTeamParam
            listeners.forEach { it.invoke(this) }
        }
    }

    fun getCell(row: Int, column: Int) : Cell{
        return cells[row][column]
    }

    fun setCell(row: Int, column: Int, cell: Cell){
//        cells[row][column] = cell
//        if (cells[row][column] != cell) {
            cells[row][column] = cell
            listeners.forEach { it.invoke(this) }
//        }
    }

}