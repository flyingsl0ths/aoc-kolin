package org.solutions.aoc.days

import readLines

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

private fun difference(x: Int, y: Int): Int = Math.abs(x - y)

private inline fun <T> withInput(
                ctx: T,
                crossinline onReadLine: (T, String) -> T,
): T =
                readLines<DayTwo, T>(
                                "./day/2.txt",
                                ctx,
                                onReadLine,
                )

private fun toShape(origin: Int, variant: Char, shapes: Array<Shape>): Shape =
                shapes[difference(origin, variant.code)]

final class DayTwo private constructor() {
        companion object {
                const val ENEMY_ROCK_INDEX: Int = 'A'.code
                const val PLAYER_ROCK_INDEX: Int = 'X'.code

                fun one(): Int {
                        val shapes = Shape.values()

                        val onReadLine = { ctx: Int, line: String ->
                                val enemyTurn = toShape(ENEMY_ROCK_INDEX, line.get(0), shapes)
                                val playerTurn = toShape(PLAYER_ROCK_INDEX, line.get(2), shapes)

                                val outcome = (playerTurn and enemyTurn).points

                                ctx + (playerTurn.ordinal + 1) + outcome
                        }

                        return withInput(0, onReadLine)
                }

                fun two(): Int {
                        val shapes = Shape.values()

                        val onValidResult = { ctx: Int, line: String ->
                                val playerChoice = toShape(PLAYER_ROCK_INDEX, line.get(2), shapes)

                                var outcome =
                                                when (playerChoice) {
                                                        Shape.ROCK -> Outcome.LOSS
                                                        Shape.PAPER -> Outcome.DRAW
                                                        Shape.SCISSORS -> Outcome.WIN
                                                }

                                val enemyTurn = toShape(ENEMY_ROCK_INDEX, line.get(0), shapes)

                                val playerTurn = (shapes.find { (it and enemyTurn) == outcome })!!

                                ctx + (playerTurn.ordinal + 1) + outcome.points
                        }

                        val onReadLine = { ctx: Int, line: String -> onValidResult(ctx, line) }

                        return withInput(0, onReadLine)
                }
        }
}
