package com.example.chess.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chess.Cell
import com.example.chess.CellPieceParam
import com.example.chess.CellSelectedParam
import com.example.chess.CellTeamParam
import com.example.chess.ChessField
import com.example.chess.LegalMoveChecker
import com.example.chess.databinding.FragmentGameBinding
import kotlin.properties.Delegates

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private var turn = CellTeamParam.WHITE
    private var winner = CellTeamParam.NONE
    private var firstClickCell: Cell? = null
    private var firstClickRow by Delegates.notNull<Int>()
    private var firstClickColumn by Delegates.notNull<Int>()

    private var legalMoveCheck by Delegates.notNull<Boolean>()
    private var isDuckMove = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)

        binding.ChessBoard.chessField = ChessField()

        binding.ChessBoard.actionListener = { row, column, field ->
            if (winner == CellTeamParam.NONE){
                if (firstClickCell == null) {
                    if (!isDuckMove) {
                        if (field.getCellTeamParam(row, column) == turn) {
                            if (field.getCellPieceParam(
                                    row,
                                    column
                                ) != CellPieceParam.NOTHING && field.getCellPieceParam(
                                    row,
                                    column
                                ) != CellPieceParam.DUCK
                            ) {
                                field.setCellSelectedParam(
                                    row,
                                    column,
                                    CellSelectedParam.SELECTED
                                )
                                firstClickRow = row
                                firstClickColumn = column
                                firstClickCell = field.getCell(row, column)
                            }
                        }
                    } else {
                        if (field.getCellPieceParam(row, column) == CellPieceParam.DUCK) {
                            field.setCellSelectedParam(
                                row,
                                column,
                                CellSelectedParam.SELECTED
                            )
                            firstClickRow = row
                            firstClickColumn = column
                            firstClickCell = field.getCell(row, column)
                        }

                    }

                } else {
                    legalMoveCheck = LegalMoveChecker(
                        field,
                        firstClickCell!!,
                        field.getCell(row, column),
                        firstClickRow,
                        firstClickColumn,
                        row,
                        column
                    ).isMoveLegal
                    if (!legalMoveCheck) {
                        field.setCellSelectedParam(
                            firstClickRow,
                            firstClickColumn,
                            CellSelectedParam.UNSELECTED
                        )
                        firstClickCell = null
                        firstClickColumn = -1
                        firstClickRow = -1
                    } else {
                        if (field.getCell(row, column).cellPieceParam == CellPieceParam.KING) {
                            if (field.getCellTeamParam(row, column) == CellTeamParam.BLACK){
                                winner = CellTeamParam.WHITE
                                field.setWinner(winner)
                            }else if (field.getCellTeamParam(row, column) == CellTeamParam.WHITE){
                                winner = CellTeamParam.BLACK
                                field.setWinner(winner)
                            }
                        }
                        field.setCell(row, column, firstClickCell!!)
                        field.setCellSelectedParam(row, column, CellSelectedParam.UNSELECTED)
                        field.setCell(firstClickRow, firstClickColumn, Cell())
                        if (field.getCellPieceParam(0, column) == CellPieceParam.PAWN){
                            field.setCell(0, column, Cell(cellPieceParam = CellPieceParam.QUEEN, cellTeamParam = CellTeamParam.WHITE))
                        }else if (field.getCellPieceParam(7, column) == CellPieceParam.PAWN){
                            field.setCell(7, column, Cell(cellPieceParam = CellPieceParam.QUEEN, cellTeamParam = CellTeamParam.BLACK))
                        }
                        firstClickCell = null
                        firstClickColumn = -1
                        firstClickRow = -1
                        if (isDuckMove) {
                            if (turn == CellTeamParam.WHITE) {
                                turn = CellTeamParam.BLACK
                            } else if (turn == CellTeamParam.BLACK) {
                                turn = CellTeamParam.WHITE
                            }
                        }
                        isDuckMove = !isDuckMove

                    }
                }
            }
        }

        return binding.root
    }

}