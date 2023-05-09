package com.example.chess

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.media.AsyncPlayer
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import java.lang.reflect.Field
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

    private lateinit var player1Paint: Paint
    private lateinit var player2Paint: Paint
    private lateinit var blackCellsPaint: Paint
    private lateinit var whiteCellsPaint: Paint

    private var blackCellColor by Delegates.notNull<Int>()
    private var whiteCellColor by Delegates.notNull<Int>()
    private val listener: OnFieldChangedListener = {

    }

    private val fieldRect = RectF(0f, 0f, 0f, 0f)
    private var cellSize: Float = 0f
    private var cellPadding: Float = 0f

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

    fun initPaints() {
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
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (fieldRect.width() <= 0) return
        if (fieldRect.height() <= 0) return
        drawField(canvas)
    }

    private fun drawField(canvas: Canvas) {
//        val field = this.chessField ?: return
        val xStart = fieldRect.left
        val yStart = fieldRect.top
        for (i in 0..8 step 2) {
            for (j in 0..6 step 2){
                canvas.drawRect(
                    xStart + cellSize * i,
                    yStart + cellSize * j,
                    xStart + cellSize * (i + 1),
                    yStart + cellSize * (j + 1),
                    whiteCellsPaint
                )
                canvas.drawRect(
                    xStart + cellSize * (i+1),
                    yStart + cellSize * j,
                    xStart + cellSize * (i + 2),
                    yStart + cellSize * (j + 1),
                    blackCellsPaint
                )
            }
        }
        for (i in 1..8 step 2) {
            for (j in 1..7 step 2){
                canvas.drawRect(
                    xStart + cellSize * i,
                    yStart + cellSize * j,
                    xStart + cellSize * (i + 1),
                    yStart + cellSize * (j + 1),
                    whiteCellsPaint
                )
                canvas.drawRect(
                    xStart + cellSize * (i-1),
                    yStart + cellSize * j,
                    xStart + cellSize * (i),
                    yStart + cellSize * (j + 1),
                    blackCellsPaint
                )
            }
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