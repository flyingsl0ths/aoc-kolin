package org.solutions.aoc.days

import org.solutions.aoc.readTextResource

typealias CalorieReport = Pair<Int, Int>

private final class CaloriesReport(
                var maxCalories: Int = 0,
                var calorieCount: Int = 0,
                var id: Int = 0
) {
        fun result(): CalorieReport = this.id to this.maxCalories
}

private inline fun <T> withInput(
                ctx: T,
                crossinline onLine: (T, String) -> T,
                crossinline onNotLine: (T, String) -> T
): T =
                readTextResource<DayOne, T>(
                                "./day/1.txt",
                                ctx,
                                { line: String -> line.isEmpty() },
                                onLine,
                                onNotLine
                )

final class DayOne private constructor() {
        companion object {
                fun one(): CalorieReport {
                        val onLine = { ctx: CaloriesReport, _: String ->
                                ctx.id += 1
                                ctx.maxCalories = Math.max(ctx.maxCalories, ctx.calorieCount)
                                ctx.calorieCount = 0
                                ctx
                        }

                        val acc =
                                        withInput<CaloriesReport>(CaloriesReport(), onLine) {
                                                        ctx: CaloriesReport,
                                                        line: String ->
                                                ctx.calorieCount += line.toInt()
                                                ctx
                                        }

                        return acc.result()
                }

                fun two(): Int {
                        val onLine = { ctx: Pair<MutableList<Int>, Int>, _: String ->
                                ctx.first.add(ctx.second)
                                ctx.first to 0
                        }

                        val (results, _) =
                                        withInput((mutableListOf<Int>() to 0), onLine) {
                                                        ctx: Pair<MutableList<Int>, Int>,
                                                        line: String ->
                                                ctx.first to (ctx.second + line.toInt())
                                        }

                        results.sortDescending()
                        return results.take(3).sum()
                }
        }
}
