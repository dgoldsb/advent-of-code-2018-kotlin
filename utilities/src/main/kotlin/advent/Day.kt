/** Base class for Advent of Code day implementations. */
package main.kotlin.advent

import main.kotlin.advent.utilities.ReadUtils

abstract class Day<T>(private val day: Int) {
  abstract fun deserialize(input: List<String>): T

  abstract fun part1(input: T): String

  abstract fun part2(input: T): String

  fun run(): DayResult {
    val input = ReadUtils.readInput(this.day.toString())
    val deserializedInput = this.deserialize(input)
    return DayResult(this.part1(deserializedInput), this.part2(deserializedInput))
  }
}
