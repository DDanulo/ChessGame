package com.example.chess

data class Cell(
    var cellSelectedParam: CellSelectedParam = CellSelectedParam.UNSELECTED,
    var cellPieceParam: CellPieceParam = CellPieceParam.NOTHING,
    var cellTeamParam: CellTeamParam = CellTeamParam.NONE
)