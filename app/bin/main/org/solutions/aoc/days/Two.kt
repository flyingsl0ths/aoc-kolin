package org.solutions.aoc.days

import readLines

/*
 * Conditions:
 *   - Left column == Enemy, Right column == Player
 *   - Map letters to one of Paper,Rock,Scissors
 *   - "Compute score" for each round for each player
 *   - Winner == player with highest "total score"
 *
 * Input:
 *   - Enemy:
 *     - A -> Rock
 *     - B -> Paper
 *     - C -> Scissors
 *
 *   - Player:
 *     - X -> Rock
 *     - Y -> Paper
 *     - Z -> Scissors
 *
 *     Rock       Rock    Paper     Scissors
 *                 D       L           W
 *     Paper       W       D           L
 *     Scissors    L       W           D
 *
 * Invariants:
 *   - Score for round
 *     - Selected shape: Rock => 1, Paper => 2, Scissors => 3
 *     - Outcome: Lose => 0, Draw => 3, Win => 6
 *
 *   - Total score => Score for every round
 *
 * */

private interface Table<I, R> {
        infix fun and(rhs: I): R
}

private enum class Outcome(val points: Int) {
        LOSS(0),
        DRAW(3),
        WIN(6)
}

private enum class Shape : Table<Shape, Outcome> {
        ROCK {
                override infix fun and(rhs: Shape): Outcome {
                        return when (rhs) {
                                Shape.PAPER -> Outcome.LOSS
                                Shape.SCISSORS -> Outcome.WIN
                                Shape.ROCK -> Outcome.DRAW
                        }
                }
        },
        PAPER {
                override infix fun and(rhs: Shape): Outcome {
                        return when (rhs) {
                                Shape.PAPER -> Outcome.DRAW
                                Shape.SCISSORS -> Outcome.LOSS
                                Shape.ROCK -> Outcome.WIN
                        }
                }
        },
        SCISSORS {
                override infix fun and(rhs: Shape): Outcome {
                        return when (rhs) {
                                Shape.PAPER -> Outcome.WIN
                                Shape.SCISSORS -> Outcome.DRAW
                                Shape.ROCK -> Outcome.LOSS
                        }
                }
        }
}

private inline fun difference(x: Int, y: Int): Int = Math.abs(x - y)

private inline fun <T> withInput(
                ctx: T,
                crossinline onReadLine: (T, String) -> T,
): T {
        return readLines<DayTwo, T>(
                        "./day/2.txt",
                        ctx,
                        onReadLine,
        )
}

final class DayTwo {
        companion object {
                fun one(): Int {
                        val enemyRock = 'A'.code
                        val playerRock = 'X'.code
                        val shapes = Shape.values()

                        val onReadLine = { ctx: Int, line: String ->
                                val enemyShape=
                                                line.get(0).code 
val  playerShape = line.get(2).code

                                val enemyChoice = difference(enemyRock, enemyShape)
                                val playerChoice = difference(playerRock, playerShape)

                                val enemyTurn = shapes[enemyChoice]
                                val playerTurn = shapes[playerChoice]

                                val outcome = (playerTurn and enemyTurn).points

                                val result = (playerTurn.ordinal + 1) + outcome

                                ctx + result
                        }

                        return withInput(0, onReadLine)
                }
        }
}
