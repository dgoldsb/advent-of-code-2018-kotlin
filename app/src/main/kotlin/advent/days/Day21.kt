package advent.days

import advent.Day
import advent.classes.day16.RegisterState
import advent.classes.day19.OpcodeExecuter
import advent.classes.day19.OpcodeInstruction
import kotlin.streams.toList

object Day21 : Day<Pair<Int, List<OpcodeInstruction>>>(21) {

  override fun deserialize(input: List<String>): Pair<Int, List<OpcodeInstruction>> {
    val pointerIndex = input[0].last().toString().toInt()

    val instructions =
        input
            .stream()
            .skip(1)
            .map { string: String -> OpcodeInstruction.fromString(string) }
            .toList()

    return Pair(pointerIndex, instructions)
  }

  override fun part1(input: Pair<Int, List<OpcodeInstruction>>): String {
      // TODO
    return OpcodeExecuter(RegisterState(listOf(0, 0, 0, 0, 0, 0)), input.first)
        .execute(input.second, 0)
        .get(0)
        .toString()
  }

  override fun part2(input: Pair<Int, List<OpcodeInstruction>>): String {
    return ""
  }
}
