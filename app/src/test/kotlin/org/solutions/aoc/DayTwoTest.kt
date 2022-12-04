package org.solutions.aoc

import org.junit.Assert
import org.junit.Test
import org.solutions.aoc.days.DayTwo

class DayTwoTest {
        @Test
        fun assumedScore() {
                Assert.assertEquals(10404, DayTwo.one())
        }

        @Test
        fun actualScore() {
                Assert.assertEquals(10334, DayTwo.two())
        }
}
