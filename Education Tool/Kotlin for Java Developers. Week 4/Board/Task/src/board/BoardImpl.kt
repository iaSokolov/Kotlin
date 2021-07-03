package board

import board.Direction.*
import java.util.ArrayList

class Board(override val width: Int) : SquareBoard {
    private val listCells: ArrayList<Cell> = ArrayList<Cell>()

    init {
        for (index in 1..width) {
            listCells.add(Cell(index))
        }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        TODO("Not yet implemented")
    }

    override fun getCell(i: Int, j: Int): Cell {
        TODO("Not yet implemented")
    }

    override fun getAllCells(): Collection<Cell> {
        TODO("Not yet implemented")
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        TODO("Not yet implemented")
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        TODO("Not yet implemented")
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        TODO("Not yet implemented")
    }
}

fun createSquareBoard(width: Int): SquareBoard = TODO()
fun <T> createGameBoard(width: Int): GameBoard<T> = TODO()

