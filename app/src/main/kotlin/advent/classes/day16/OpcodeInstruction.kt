package advent.classes.day16

/** Mysterious Opcode instruction, we do not know which Opcode each maps to! */
data class OpcodeInstruction(val opcode: Int, val inputA: Int, val inputB: Int, val output: Int) {
  companion object {
    private val REGEX: Regex = Regex("(\\d+) (\\d+) (\\d+) (\\d+)")

    fun fromString(input: String): OpcodeInstruction {
      val match: MatchResult = REGEX.matchEntire(input)!!

      return OpcodeInstruction(
          match.groupValues[1].toInt(),
          match.groupValues[2].toInt(),
          match.groupValues[3].toInt(),
          match.groupValues[4].toInt())
    }
  }
}
