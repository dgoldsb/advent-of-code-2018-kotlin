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

  /** Find the guard that sleeps the most minutes. */
  private fun findNappiestGuard(guardNapsMap: HashMap<Int, MutableList<IntRange>>): Int {
    var nappiestGuard: Int? = null
    var nappiestGuardTimeNapped = 0

    // Iterate over guards.
    for (guardNapEntry in guardNapsMap.iterator()) {
      var guardTimeNapped = 0

      // Iterate over the naps.
      for (nap in guardNapEntry.value) {
        guardTimeNapped += nap.last - nap.first
      }

      if (guardTimeNapped > nappiestGuardTimeNapped) {
        nappiestGuardTimeNapped = guardTimeNapped
        nappiestGuard = guardNapEntry.key
      }
    }

    return nappiestGuard!!
  }

  /** Find the number of times at which this guard is asleep at a given minute. */
  private fun countTimesAsleepAtMinute(
      guardNaps: MutableList<IntRange>,
      minute: Int,
  ): Int {
    var count = 0
    for (nap in guardNaps) {
      if (nap.contains(minute)) {
        count += 1
      }
    }
    return count
  }

  /** Find the minute at which this guard is most likely asleep. */
  private fun findSleepiestMinute(
      guard: Int,
      guardNapsMap: HashMap<Int, MutableList<IntRange>>
  ): Int {
    val guardNaps = guardNapsMap[guard]!!

    var sleepiestMinute: Int? = null
    var countSleepiestMinuteInNaps = 0
    for (minute in 0 until 60) {
      var count = countTimesAsleepAtMinute(guardNaps, minute)
      if (count > countSleepiestMinuteInNaps) {
        countSleepiestMinuteInNaps = count
        sleepiestMinute = minute
      }
    }

    return sleepiestMinute!!
  }

  override fun part1(input: List<GuardEvent>): String {
    val guardNapsMap = parseEventsToNaps(input)
    val sleepiestGuard = findNappiestGuard(guardNapsMap)
    return (sleepiestGuard * findSleepiestMinute(sleepiestGuard, guardNapsMap)).toString()
  }

  override fun part2(input: List<GuardEvent>): String {
    val guardNapsMap = parseEventsToNaps(input)

    var drowsiestMinute: Int? = null
    var drowsiestGuard: Int? = null
    var countDrowsiestMinuteInNaps = 0
    for (guardNapEntry in guardNapsMap.iterator()) {
      for (minute in 0 until 60) {
        val count = countTimesAsleepAtMinute(guardNapEntry.value, minute)

        if (count > countDrowsiestMinuteInNaps) {
          countDrowsiestMinuteInNaps = count
          drowsiestGuard = guardNapEntry.key
          drowsiestMinute = minute
        }
      }
    }
    return (drowsiestGuard!! * drowsiestMinute!!).toString()
  }
}
