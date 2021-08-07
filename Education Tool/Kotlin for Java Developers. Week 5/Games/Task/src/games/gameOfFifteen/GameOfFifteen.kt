package games.gameOfFifteen

import board.Cell
import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game
import games.game2048.Game2048Initializer

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
    GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {

    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        var count = 0
        for (i in 1..4) {
            for (j in 1..4) {
                if (count < 15)
                    this.board[this.board.getCell(i,j)] = initializer.initialPermutation[count]
                count++
            }
        }
    }

    override fun canMove(): Boolean = true

    override fun hasWon(): Boolean {
        var count = 0
        for (i in 1..4) {
            for (j in 1..4) {
                count++
                if (count < 15 && this.board[this.board.getCell(i,j)] != count)
                    return false
            }
        }
        return true
    }

    override fun processMove(direction: Direction) {
        val cell = this.board.filter { it == null }.first()
        var i = cell.i
        var j = cell.j
        when (direction) {
            Direction.RIGHT -> j--
            Direction.LEFT -> j++
            Direction.DOWN -> i--
            Direction.UP -> i++
        }
        if (i in 1..4 && j in 1..4) {
            val tmp = this.board[cell]
            this.board[cell] = this.board[this.board.getCell(i,j)]
            this.board[this.board.getCell(i,j)] = tmp
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }
}

