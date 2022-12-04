package org.solutions.aoc

import org.junit.Assert
import org.junit.Test
import org.solutions.aoc.days.DayOne

class DayOneTest {
        @Test
        fun maxCalories() {
                Assert.assertEquals(251 to 68802, DayOne.one())
        }

        @Test
        fun top3CaloriesSum() {
                Assert.assertEquals(205370, DayOne.two())
        }
}
