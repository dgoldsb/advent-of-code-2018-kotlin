package advent.classes.day16

import java.util.*
import kotlin.collections.HashSet

data class OpcodeSample(
    val before: RegisterState,
    val after: RegisterState,
    val instruction: OpcodeInstruction
) {

  /** Test a list of possible opcodes on this sample, return candidates. */
  fun filterCandidateOpcodes(possibleOpcodes: List<Opcode>): HashSet<Opcode> {
    val result = HashSet<Opcode>()
    for (opcode in possibleOpcodes) {
      if (test(opcode)) {
        result.add(opcode)
      }
    }
    return result
  }

  /** Test if an opcode is a candidate for this sample. */
  private fun test(opcode: Opcode): Boolean {
    return this.after ==
        opcode.apply(
            this.before, this.instruction.inputA, this.instruction.inputB, this.instruction.output)
  }

  companion object {
    private val REGEX: Regex =
        Regex(
            "Before: \\[(\\d+), (\\d+), (\\d+), (\\d+)\\]" +
                    "\\n(\\d+) (\\d+) (\\d+) (\\d+)" +
                    "\\nAfter:  \\[(\\d+), (\\d+), (\\d+), (\\d+)\\]")

    fun fromString(input: String): OpcodeSample {
      val match: MatchResult = REGEX.matchEntire(input)!!

      return OpcodeSample(
          RegisterState(
              match.groupValues[1].toInt(),
              match.groupValues[2].toInt(),
              match.groupValues[3].toInt(),
              match.groupValues[4].toInt()),
          RegisterState(
              match.groupValues[9].toInt(),
              match.groupValues[10].toInt(),
              match.groupValues[11].toInt(),
              match.groupValues[12].toInt()),
          OpcodeInstruction(
              match.groupValues[5].toInt(),
              match.groupValues[6].toInt(),
              match.groupValues[7].toInt(),
              match.groupValues[8].toInt()))
    }
  }
}
