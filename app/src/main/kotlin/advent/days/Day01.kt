/**
 * Starting with a frequency of zero, what is the resulting frequency after all of the changes in
 * frequency have been applied?
 */
package main.kotlin.advent.days

import kotlin.collections.HashSet
import main.kotlin.advent.Day
import main.kotlin.advent.classes.day01.FrequencyChange

object Day01 : Day<List<FrequencyChange>>(1) {

  override fun deserialize(input: List<String>): List<FrequencyChange> {
    return input.map { string: String -> FrequencyChange.fromString(string) }
  }

  override fun part1(input: List<FrequencyChange>): String {
    return input.map { frequencyChange: FrequencyChange -> frequencyChange.value }.sum().toString()
  }

  override fun part2(input: List<FrequencyChange>): String {
    var visitedStates = HashSet<Int>()
    var state = 0
    while (true) {
      for (change in input) {
        if (visitedStates.contains(state)) {
          return state.toString()
        }
        visitedStates.add(state)
        state += change.value
      }
    }
  }
}
