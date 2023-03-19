/** Data class to deserialize day 1 input into. */
package advent.classes.day01

data class FrequencyChange(val value: Int) {

  companion object {
    fun fromString(input: String): FrequencyChange {
      return FrequencyChange(input.toInt())
    }
  }
}
