package advent.classes.day19

import advent.classes.day16.Opcode
import advent.classes.day16.RegisterState

/** Opcode instruction. */
data class OpcodeInstruction(val opcode: Opcode, val inputA: Int, val inputB: Int, val output: Int) {
    fun execute(registerState: RegisterState): RegisterState {
        return opcode.apply(registerState, inputA, inputB, output)
    }

    companion object {
      private val STRING_OPCODE_MAP = mapOf(
          Pair("addi", Opcode.Addi),
          Pair("addr", Opcode.Addr),
          Pair("mulr", Opcode.Mulr),
          Pair("muli", Opcode.Muli),
          Pair("banr", Opcode.Banr),
          Pair("bani", Opcode.Bani),
          Pair("borr", Opcode.Borr),
          Pair("bori", Opcode.Bori),
          Pair("setr", Opcode.Setr),
          Pair("seti", Opcode.Seti),
          Pair("gtir", Opcode.Gtir),
          Pair("gtri", Opcode.Gtri),
          Pair("gtrr", Opcode.Gtrr),
          Pair("eqir", Opcode.Eqir),
          Pair("eqri", Opcode.Eqri),
          Pair("eqrr", Opcode.Eqrr)
      )
    private val REGEX: Regex = Regex("(\\w+) (\\d+) (\\d+) (\\d+)")

    fun fromString(input: String): OpcodeInstruction {
      val match: MatchResult = REGEX.matchEntire(input)!!

      return OpcodeInstruction(
          STRING_OPCODE_MAP[match.groupValues[1]]!!,
          match.groupValues[2].toInt(),
          match.groupValues[3].toInt(),
          match.groupValues[4].toInt())
    }
  }
}
