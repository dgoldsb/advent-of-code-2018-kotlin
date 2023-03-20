package advent.days

import advent.Day
import advent.classes.day04.GuardEvent
import advent.classes.day04.GuardEventType

object Day04 : Day<List<GuardEvent>>(4) {

  override fun deserialize(input: List<String>): List<GuardEvent> {
    return input.map { string: String -> GuardEvent.fromString(string) }
  }

  private fun parseEventsToNaps(
      guardEvents: List<GuardEvent>
  ): HashMap<Int, MutableList<IntRange>> {
    // Define a return map.
    val guardNapsMap = HashMap<Int, MutableList<IntRange>>()

    // Sort all events chronologically by timestamp.
    val sortedGuardEvents = guardEvents.sortedBy { guardEvent -> guardEvent.timestamp }.toList()

    var currentGuard = 0
    var napStartMinute = 0
    // Parse the three different event types to piece together naps taken.
    for (event in sortedGuardEvents) {
      when (event.eventType) {
        GuardEventType.START -> {
          currentGuard = event.guardId!!
        }
        GuardEventType.SLEEP -> {
          napStartMinute = event.minute
        }
        GuardEventType.WAKE_UP -> {
          val napList = guardNapsMap.getOrDefault(currentGuard, mutableListOf())
          napList.add((napStartMinute until event.minute))
          guardNapsMap[currentGuard] = napList
        }
      }
    }

    return guardNapsMap
  }

  private fun findNappiestGuard(guardNapsMap: HashMap<Int, MutableList<IntRange>>): Int {
    return 0
  }

  private fun findSleepiestMinute(
      guard: Int,
      guardNapsMap: HashMap<Int, MutableList<IntRange>>
  ): Int {
    return 0
  }

  override fun part1(input: List<GuardEvent>): String {
    val guardNapsMap = parseEventsToNaps(input)
    val sleepiestGuard = findNappiestGuard(guardNapsMap)
    return findSleepiestMinute(sleepiestGuard, guardNapsMap).toString()
  }

  override fun part2(input: List<GuardEvent>): String {
    return ""
  }
}
