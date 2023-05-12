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
import com.example.chess.databinding.FragmentGameBinding
import kotlin.properties.Delegates

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val turn = CellTeamParam.WHITE
    private var firstClickCell: Cell? = null
    private var firstClickRow by Delegates.notNull<Int>()
    private var firstClickColumn by Delegates.notNull<Int>()

    //    private var legalMoveCheck by Delegates.notNull<Boolean>()
    private var legalMoveCheck = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)

        binding.ChessBoard.chessField = ChessField()

        binding.ChessBoard.actionListener = { row, column, field ->
            if (firstClickCell == null) {
                field.setCellSelectedParam(
                    row,
                    column,
                    CellSelectedParam.SELECTED
                )
                firstClickRow = row
                firstClickColumn = column
                firstClickCell = field.getCell(row, column)
            } else if (!legalMoveCheck) { //TODO create legal move checker
                field.setCellSelectedParam(
                    firstClickRow,
                    firstClickColumn,
                    CellSelectedParam.UNSELECTED
                )
                firstClickCell = null
            } else {
                field.setCell(row, column, firstClickCell!!)
                field.setCell(firstClickRow, firstClickColumn, Cell())
            }
        }

        return binding.root
    }

}