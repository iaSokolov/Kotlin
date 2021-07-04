package board

import board.Direction.*
import java.util.ArrayList

class Board(override val width: Int) : SquareBoard {
    private val listCells: ArrayList<Cell> = ArrayList<Cell>()

    init {
        for (index in 0 until width * width) {
            listCells.add(Cell(index / width + 1, index % width + 1))
        }
    }

    private fun getListIndex(i: Int, j: Int): Int {
        when {
            i > this.width || i < 1 -> throw IllegalArgumentException()
            j > this.width || j < 1 -> throw IllegalArgumentException()
            else -> return (i - 1) * this.width + (j - 1)
        }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return try {
            this.listCells[this.getListIndex(i, j)]
        } catch (ex: IllegalArgumentException) {
            null
        }
    }

    override fun getCell(i: Int, j: Int): Cell {
        val index = this.getListIndex(i, j)
        return if (index > this.listCells.size)
            throw IllegalArgumentException()
        else
            this.listCells[index]
    }

    override fun getAllCells() = this.listCells

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val ret = ArrayList<Cell>()
        jRange.asSequence().iterator().forEach {
            val cell: Cell? = this.getCellOrNull(i, it)
            if (cell != null)
                ret.add(cell)
        }
        return ret
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val ret = ArrayList<Cell>()
        iRange.asSequence().iterator().forEach {
            val cell: Cell? = this.getCellOrNull(it, j)
            if (cell != null)
                ret.add(cell)
        }
        return ret
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            UP -> this@Board.getCellOrNull(this.i - 1, this.j)
            DOWN -> this@Board.getCellOrNull(this.i + 1, this.j)
            LEFT -> this@Board.getCellOrNull(this.i, this.j - 1)
            RIGHT -> this@Board.getCellOrNull(this.i, this.j + 1)
        }
    }
}

fun main() {
    val b = Board(9)
    print(b.getCell(10, 1))
}

fun createSquareBoard(width: Int): SquareBoard = Board(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = TODO()

