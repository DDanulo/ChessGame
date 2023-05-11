package com.example.chess

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlin.math.max
import kotlin.math.min
import kotlin.properties.Delegates

class ChessFieldView(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
) : View(context, attrs, defStyleAttr, defStyleRes) {

    var chessField: ChessField? = null
        set(value) {
            field?.listeners?.remove(listener)
            field = value
            value?.listeners?.add(listener)
            invalidate()

        }
    private lateinit var blackCellsPaint: Paint
    private lateinit var whiteCellsPaint: Paint
    private val piecesBitmaps: PiecesBitmaps = PiecesBitmaps()
    private val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.pieces)


    private var blackCellColor by Delegates.notNull<Int>()
    private var whiteCellColor by Delegates.notNull<Int>()
    private val listener: OnFieldChangedListener = {

    }

    private val fieldRect: RectF = RectF(0f, 0f, 0f, 0f)
    private var cellSize: Float = 0f
    private var cellPadding: Float = 0f

    private lateinit var boardSquares: BoardSquares


    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        R.style.DefaultChessBoardStyle
    )

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.chessFieldStyle
    )

    init {
        if (attrs != null) {
            initAttributes(attrs, defStyleAttr, defStyleRes)
        } else {
            initDefaultColors()
        }

        initPaints()
    }

    private fun initPaints() {
        blackCellsPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        blackCellsPaint.color = BLACK_CELL_COLOR
        blackCellsPaint.style = Paint.Style.FILL

        whiteCellsPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        whiteCellsPaint.color = WHITE_CELL_COLOR
        whiteCellsPaint.style = Paint.Style.FILL

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        chessField?.listeners?.add(listener)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        chessField?.listeners?.remove(listener)
    }

    private fun initAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.ChessFieldView,
            defStyleAttr,
            defStyleRes
        )
        whiteCellColor =
            typedArray.getColor(R.styleable.ChessFieldView_whiteCellColor, WHITE_CELL_COLOR)
        blackCellColor =
            typedArray.getColor(R.styleable.ChessFieldView_blackCellColor, BLACK_CELL_COLOR)
        typedArray.recycle()
    }

    private fun initDefaultColors() {
        whiteCellColor = WHITE_CELL_COLOR
        blackCellColor = BLACK_CELL_COLOR
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
//        val field = this.chessField ?: return

        val safeWidth = w - paddingRight - paddingLeft
        val safeHeight = h - paddingBottom - paddingTop

        val cellWidth = safeWidth / 8f
        val cellHeight = safeHeight / 8f

        cellSize = min(cellHeight, cellWidth)
        cellPadding = cellSize * 0.1f

        val fieldWidth = cellSize * 8
        val fieldHeight = cellHeight * 8

        fieldRect.left = paddingLeft + (safeWidth - fieldWidth) / 2
        fieldRect.top = paddingTop + (safeHeight - fieldHeight) / 2
        fieldRect.right = fieldRect.left + fieldWidth
        fieldRect.bottom = fieldRect.top + fieldHeight

        boardSquares = BoardSquares(fieldRect.left, fieldRect.top, cellSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (fieldRect.width() <= 0) return
        if (fieldRect.height() <= 0) return
        drawField(canvas)
        drawPieces(canvas)
    }

    private fun drawField(canvas: Canvas) {
        for (i in 0..7) {
            for (j in 0..7) {
                if ((i + j) % 2 == 1) {
                    canvas.drawRect(boardSquares.squares[i][j], blackCellsPaint)
                } else {
                    canvas.drawRect(boardSquares.squares[i][j], whiteCellsPaint)
                }
            }
        }
    }


    private fun drawPieces(canvas: Canvas) {
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.blackKing,
            boardSquares.squares[4][0], blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.blackQueen,
            boardSquares.squares[3][0], blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.blackTour,
            boardSquares.squares[0][0], blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.blackTour,
            boardSquares.squares[7][0], blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.blackBishop,
            boardSquares.squares[2][0],
            blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.blackBishop,
            boardSquares.squares[5][0],
            blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.blackKnight,
            boardSquares.squares[1][0],
            blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.blackKnight,
            boardSquares.squares[6][0],
            blackCellsPaint
        )
        for (i in 0..7) {
            canvas.drawBitmap(
                bitmap,
                piecesBitmaps.blackPawn,
                boardSquares.squares[i][1],
                blackCellsPaint
            )
        }
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.whiteKing,
            boardSquares.squares[4][7], blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.whiteQueen,
            boardSquares.squares[3][7], blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.whiteTour,
            boardSquares.squares[0][7], blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.whiteTour,
            boardSquares.squares[7][7], blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.whiteBishop,
            boardSquares.squares[2][7],
            blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.whiteBishop,
            boardSquares.squares[5][7],
            blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.whiteKnight,
            boardSquares.squares[1][7],
            blackCellsPaint
        )
        canvas.drawBitmap(
            bitmap,
            piecesBitmaps.whiteKnight,
            boardSquares.squares[6][7],
            blackCellsPaint
        )
        for (i in 0..7) {
            canvas.drawBitmap(
                bitmap,
                piecesBitmaps.whitePawn,
                boardSquares.squares[i][6],
                blackCellsPaint
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val minHeight = suggestedMinimumHeight + paddingTop + paddingBottom
        val desiredCellSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            DESIRED_CELL_SIZE.toFloat(),
            resources.displayMetrics
        ).toInt()
        val rows = 8
        val columns = 8

        val desiredWidth = max(minWidth, columns * desiredCellSize + paddingLeft + paddingRight)
        val desiredHeight = max(minHeight, rows * desiredCellSize + paddingBottom + paddingTop)

        setMeasuredDimension(
            resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec)
        )
    }

    companion object {
        @JvmField
        val BLACK_CELL_COLOR = Color.parseColor("#4b7399")

        @JvmField
        val WHITE_CELL_COLOR = Color.parseColor("#eae9d2")
        const val DESIRED_CELL_SIZE = 50
    }
}