package com.example.chess

enum class CellSelectedParam {
    UNSELECTED,
    SELECTED
}

enum class CellPieceParam {
    NOTHING,
    PAWN,
    BISHOP,
    KNIGHT,
    ROOK,
    QUEEN,
    KING,
    DUCK
}

enum class CellTeamParam {
    NONE,
    BLACK,
    WHITE
}