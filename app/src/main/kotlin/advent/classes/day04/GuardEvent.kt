package advent.classes.day04

import java.util.InputMismatchException

data class GuardEvent(
    val timestamp: String,
    val minute: Int,
    val eventType: GuardEventType,
    val guardId: Int?,
) {

  companion object {
    private val BEGIN_REGEX: Regex = Regex(".(.+):(\\d+). Guard #(\\d+) begins shift")
    private val SLEEP_REGEX: Regex = Regex(".(.+):(\\d+). falls asleep")
    private val WAKE_REGEX: Regex = Regex(".(.+):(\\d+). wakes up")

    fun fromString(input: String): GuardEvent {
      val beginMatch: MatchResult? = BEGIN_REGEX.matchEntire(input)
      if (beginMatch != null) {
        return GuardEvent(
            "${beginMatch.groupValues[1]}:${beginMatch.groupValues[2]}",
            beginMatch.groupValues[2].toInt(),
            GuardEventType.START,
            beginMatch.groupValues[3].toInt())
      }

      val sleepMatch: MatchResult? = SLEEP_REGEX.matchEntire(input)
      if (sleepMatch != null) {
        return GuardEvent(
            "${sleepMatch.groupValues[1]}:${sleepMatch.groupValues[2]}",
            sleepMatch.groupValues[2].toInt(),
            GuardEventType.SLEEP,
            null)
      }

      val wakeMatch: MatchResult? = WAKE_REGEX.matchEntire(input)
      if (wakeMatch != null) {
        return GuardEvent(
            "${wakeMatch.groupValues[1]}:${wakeMatch.groupValues[2]}",
            wakeMatch.groupValues[2].toInt(),
            GuardEventType.WAKE_UP,
            null)
      }

      throw InputMismatchException("No valid input found")
    }
  }
}
