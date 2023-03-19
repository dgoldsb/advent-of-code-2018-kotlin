/*
 * This Kotlin source file was generated by the Gradle "init" task.
 */package advent.days

import main.kotlin.advent.classes.day01.FrequencyChange
import main.kotlin.advent.days.Day01
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Test {
  @Test
  fun testPart1() {
    var input =
        listOf(FrequencyChange(1), FrequencyChange(-2), FrequencyChange(3), FrequencyChange(1))
    assertEquals("3", Day01.part1(input))
  }
  @Test
  fun testPart2() {
    var input =
      listOf(FrequencyChange(1), FrequencyChange(-2), FrequencyChange(3), FrequencyChange(1))
    assertEquals("2", Day01.part2(input))
  }
}
