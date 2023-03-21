package advent.days

import advent.Day
import advent.classes.day16.Opcode
import advent.classes.day16.OpcodeInstruction
import advent.classes.day16.OpcodeSample

object Day16 : Day<Pair<List<OpcodeSample>, List<OpcodeInstruction>>>(16) {

  override fun deserialize(input: List<String>): Pair<List<OpcodeSample>, List<OpcodeInstruction>> {
    // First join all lines together.
    val joinedInput = input.joinToString("\n")

    // Split by double newline.
    val splitInputPair = joinedInput.split("\n\n\n")
    require(splitInputPair.size == 2) { "Expected two components to this input!" }

    // Then deserialize the opcode samples.
    val rawSamples = splitInputPair[0].split("\n\n")
    val samples = rawSamples.map { string: String -> OpcodeSample.fromString(string) }

    // Finally deserialize the opcode instructions.
    // TODO

    return Pair(samples, listOf())
  }

  override fun part1(input: Pair<List<OpcodeSample>, List<OpcodeInstruction>>): String {
    val candidateOpcodes = listOf(Opcode.Addi(), Opcode.Addr())
    for (sample in input.first) {
      val filteredOpcodes = sample.filterCandidateOpcodes(candidateOpcodes)
    }
    return ""
  }

  override fun part2(input: Pair<List<OpcodeSample>, List<OpcodeInstruction>>): String {
    return ""
  }
}
