package org.solutions.aoc.days

import readResource

typealias CalorieReport = Pair<Int, Int>

class CaloriesReport(var maxCalories: Int = 0, var calorieCount: Int = 0, var id: Int = 0) {
        fun result(): CalorieReport = this.id to this.maxCalories
}

inline fun withInput(
                noinline pred: (String) -> Boolean,
                crossinline onLine: (String) -> Unit,
                crossinline onNotLine: (String) -> Unit
): Unit {
        readResource(DayOne::class, "./day/1.txt", pred, onLine, onNotLine)
}

final class DayOne constructor() {
        companion object {
                fun one(): CalorieReport {
                        val ctx = CaloriesReport()

                        val pred = { line: String -> line.isEmpty() }

                        val onLine = { _: String ->
                                ctx.id += 1
                                ctx.maxCalories = Math.max(ctx.maxCalories, ctx.calorieCount)
                                ctx.calorieCount = 0
                        }

                        withInput(pred, onLine) { line: String -> ctx.calorieCount += line.toInt() }

                        return ctx.result()
                }

                fun two(): Int {
                        val ctx = mutableListOf<Int>()

                        var sum = 0

                        val pred = { line: String -> line.isEmpty() }

                        val onLine = { _: String ->
                                ctx.add(sum)
                                sum = 0
                        }

                        withInput(pred, onLine) { line: String -> sum += line.toInt() }

                        return ctx.sortedDescending().take(3).sum()
                }
        }
}
