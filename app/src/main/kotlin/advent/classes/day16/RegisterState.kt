package advent.classes.day16

data class RegisterState(val registerValues: List<Int>) {
  fun get(index: Int): Int {
    require((registerValues.indices).contains(index)) {
      "Index must be within register, was $index"
    }

    return registerValues[index]
  }

  fun set(value: Int, index: Int): RegisterState {
    require((registerValues.indices).contains(index)) {
      "Index must be within register, was $index"
    }
    var newRegisterValues = registerValues.toMutableList()
    newRegisterValues[index] = value
    return RegisterState(newRegisterValues.toList())
  }
}
