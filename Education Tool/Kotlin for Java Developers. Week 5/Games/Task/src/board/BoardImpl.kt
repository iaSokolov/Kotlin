package board

import board.Direction.*
import java.util.ArrayList

open class Board(override val width: Int) : SquareBoard {
    protected val listCells: ArrayList<Cell> = ArrayList<Cell>()

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

class Game<T>(width: Int) : GameBoard<T>, Board(width) {
    private val map: HashMap<Cell, T?> = java.util.HashMap()

    override fun get(cell: Cell): T? = this.map[cell]


    override fun set(cell: Cell, value: T?) {
        this.map[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        val ret: ArrayList<Cell> = ArrayList()
        this.listCells.forEach { cell: Cell ->
            if (predicate(this.map[cell]))
                ret.add(cell)
        }
        return ret;
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        this.map.forEach { (cell, value) ->
            if (predicate(value)) {
                return cell
            }
        }
        return null
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        this.listCells.forEach { cell: Cell ->
            if (predicate(this.map[cell]))
                return true
        }
        return false
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        this.listCells.forEach { cell: Cell ->
            if (!predicate(this.map[cell]))
                return false
        }
        return true
    }
}

fun createSquareBoard(width: Int): SquareBoard = Board(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = Game<T>(width)
