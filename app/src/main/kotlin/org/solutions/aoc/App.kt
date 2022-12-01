package org.solutions.aoc

import org.solutions.aoc.days.DayOne

fun main() {
    val (id, calories) = DayOne.one()
    println("Elf $id: $calories")

    val topThreeSum = DayOne.two()
    println("Top 3 sum: $topThreeSum")
}
