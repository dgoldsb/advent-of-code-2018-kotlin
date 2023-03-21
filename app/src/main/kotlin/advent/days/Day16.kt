package advent.days

import advent.Day
import advent.classes.day16.Opcode
import advent.classes.day16.OpcodeInstruction
import advent.classes.day16.OpcodeSample

object Day16 : Day<Pair<List<OpcodeSample>, List<OpcodeInstruction>>>(16) {

  override fun deserialize(input: List<String>): Pair<List<OpcodeSample>, List<OpcodeInstruction>> {
    // First join all lines together.

    // Split by double newline.

    // Then deserialize the opcode samples.

    // Finally deserialize the opcode instructions.

    return Pair(listOf(), listOf())
  }

  override fun part1(input: Pair<List<OpcodeSample>, List<OpcodeInstruction>>): String {
    val candidateOpcodes = listOf(Opcode.Addi(), Opcode.Addr())
    return ""
  }

  override fun part2(input: Pair<List<OpcodeSample>, List<OpcodeInstruction>>): String {
    return ""
  }
}
