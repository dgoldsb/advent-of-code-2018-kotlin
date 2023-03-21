package advent.classes.day16

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
}
