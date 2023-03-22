package advent.days

import advent.Day
import advent.classes.day16.Opcode
import advent.classes.day16.OpcodeInstruction
import advent.classes.day16.OpcodeSample
import advent.classes.day16.RegisterState

object Day16 : Day<Pair<List<OpcodeSample>, List<OpcodeInstruction>>>(16) {

  private val candidateOpcodes =
      listOf(
          Opcode.Addi,
          Opcode.Addr,
          Opcode.Mulr,
          Opcode.Muli,
          Opcode.Banr,
          Opcode.Bani,
          Opcode.Borr,
          Opcode.Bori,
          Opcode.Setr,
          Opcode.Seti,
          Opcode.Gtir,
          Opcode.Gtri,
          Opcode.Gtrr,
          Opcode.Eqir,
          Opcode.Eqri,
          Opcode.Eqrr)

  override fun deserialize(input: List<String>): Pair<List<OpcodeSample>, List<OpcodeInstruction>> {
    // First join all lines together.
    val joinedInput = input.joinToString("\n")

    // Split by double newline.
    val splitInputPair = joinedInput.split("\n\n\n\n")
    require(splitInputPair.size == 2) { "Expected two components to this input!" }

    // Then deserialize the opcode samples.
    val rawSamples = splitInputPair[0].split("\n\n")
    val samples = rawSamples.map { string: String -> OpcodeSample.fromString(string) }

    // Finally deserialize the opcode instructions.
    val rawInstructions = splitInputPair[1].split("\n")
    val instructions =
        rawInstructions.map { string: String -> OpcodeInstruction.fromString(string) }

    return Pair(samples, instructions)
  }

  override fun part1(input: Pair<List<OpcodeSample>, List<OpcodeInstruction>>): String {
    var count = 0
    for (sample in input.first) {
      if (sample.filterCandidateOpcodes(candidateOpcodes).size >= 3) {
        count += 1
      }
    }
    return count.toString()
  }

  /** Find the possible opcodes per integer, judging from the input samples. */
  private fun checkPossibleOpcodesPerInteger(
      samples: List<OpcodeSample>
  ): HashMap<Int, Set<Opcode>> {
    val intOpcodesMap = HashMap<Int, Set<Opcode>>()
    for (sample in samples) {
      val currentSet =
          intOpcodesMap.getOrDefault(sample.instruction.opcode, candidateOpcodes.toSet())
      val candidates = sample.filterCandidateOpcodes(candidateOpcodes)
      intOpcodesMap[sample.instruction.opcode] = currentSet.intersect(candidates)
    }
    return intOpcodesMap
  }

  /** We can find Opcodes by looking if any key in the `puzzledState` only has one option left. */
  private fun eliminateByExclusion(
      target: HashMap<Int, Opcode>,
      puzzledState: HashMap<Int, Set<Opcode>>
  ): Boolean {
    for (entry in puzzledState.entries) {
      if (entry.value.size == 1) {
        val opcode = entry.value.first()

        // Save in the target.
        target[entry.key] = opcode

        // Remove opcode from all opcode sets, as we have found our match!
        for (otherEntry in puzzledState.entries) {
          puzzledState[otherEntry.key] = otherEntry.value.minus(opcode)
        }

        // Remove from the puzzled state.
        puzzledState.remove(entry.key)
        return true
      }
    }
    return false
  }

  private fun eliminateByRemaining(
      target: HashMap<Int, Opcode>,
      puzzledState: HashMap<Int, Set<Opcode>>
  ): Boolean {
    for (opcode in candidateOpcodes) {
      var matchingInteger: Int? = null
      var memberCount = 0
      for (entry in puzzledState.entries) {
        if (entry.value.contains(opcode)) {
          memberCount += 1
          matchingInteger = entry.key
        }
      }

      if (memberCount == 1) {
        puzzledState.remove(matchingInteger)
        target[matchingInteger!!] = opcode
        return true
      }
    }
    return false
  }

  override fun part2(input: Pair<List<OpcodeSample>, List<OpcodeInstruction>>): String {
    val intOpcodesMap = checkPossibleOpcodesPerInteger(input.first)

    // Construct the final map by eliminating one each round.
    val intOpcodeMap = HashMap<Int, Opcode>()
    while (intOpcodesMap.isNotEmpty()) {
      val resultExclusion = eliminateByExclusion(intOpcodeMap, intOpcodesMap)
      val resultRemaining = eliminateByRemaining(intOpcodeMap, intOpcodesMap)
      require(resultExclusion or resultRemaining) { "The samples are ambiguous!" }
    }

    // Run the program.
    var register = RegisterState(0, 0, 0, 0)
    for (opcodeInstruction in input.second) {
      val opcode = intOpcodeMap[opcodeInstruction.opcode]!!
      register =
          opcode.apply(
              register,
              opcodeInstruction.inputA,
              opcodeInstruction.inputB,
              opcodeInstruction.output)
    }
    return register.get(0).toString()
  }
}
