package advent.days

import java.lang.RuntimeException
import advent.Day

object Day02 : Day<List<String>>(2) {

  override fun deserialize(input: List<String>): List<String> {
    return input
  }

  private fun countDifferences(first: String, second: String): Int {
    val pairs: List<Pair<Char, Char>> = first.toCharArray().zip(second.toCharArray())
    var count = 0
    for (pair in pairs) {
      if (pair.first != pair.second) {
        count += 1
      }
    }
    return count
  }

  private fun findSimilarities(first: String, second: String): String {
    val pairs: List<Pair<Char, Char>> = first.toCharArray().zip(second.toCharArray())
    var similar = ""
    for (pair in pairs) {
      if (pair.first == pair.second) {
        similar += pair.first
      }
    }
    return similar
  }

  override fun part1(input: List<String>): String {
    /** What is the checksum for your list of box IDs? */
    var twoCount = 0
    var threeCount = 0

    for (barcode in input) {
      val characterCounts = HashMap<Int, Int>()

      for (char in barcode.chars()) {
        val currentCount = characterCounts.getOrDefault(char, 0)
        characterCounts[char] = currentCount + 1
      }

      if (characterCounts.values.contains(2)) {
        twoCount += 1
      }

      if (characterCounts.values.contains(3)) {
        threeCount += 1
      }
    }

    return (twoCount * threeCount).toString()
  }

  override fun part2(input: List<String>): String {
    for (first in input) {
      for (second in input) {
        if (countDifferences(first, second) == 1) {
          return findSimilarities(first, second)
        }
      }
    }
    throw RuntimeException("Did not find a solution")
  }
}
