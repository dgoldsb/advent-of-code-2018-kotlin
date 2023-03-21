package advent.classes.day16

import java.lang.RuntimeException

data class RegisterState(val first: Int, val second: Int, val third: Int, val fourth: Int) {
  fun get(index: Int): Int {
    require((0 until 4).contains(index)) { "Index must be within register, was $index" }

    when (index) {
      0 -> {
        return this.first
      }
      1 -> {
        return this.second
      }
      2 -> {
        return this.third
      }
      3 -> {
        return this.fourth
      }
    }

    // TODO: Remove this throw with sealed class or enum, to make the when guaranteed to succeed.
    throw RuntimeException("Invalid index")
  }

  fun set(value: Int, index: Int): RegisterState {
    require((0 until 4).contains(index)) { "Index must be within register, was $index" }

    when (index) {
      0 -> {
        return RegisterState(value, this.second, this.third, this.fourth)
      }
      1 -> {
        return RegisterState(this.first, value, this.third, this.fourth)
      }
      2 -> {
        return RegisterState(this.first, this.second, value, this.fourth)
      }
      3 -> {
        return RegisterState(this.first, this.second, this.third, value)
      }
    }

    // TODO: Remove this throw with sealed class or enum, to make the when guaranteed to succeed.
    throw RuntimeException("Invalid index")

  }
}
