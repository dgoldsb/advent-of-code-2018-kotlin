package advent.classes.day19

import advent.classes.day16.RegisterState
import java.lang.RuntimeException

class OpcodeExecuter(private var registerState: RegisterState, private var pointer: Int) {
  var opCount = 0

  fun execute(instructions: List<OpcodeInstruction>): RegisterState {
    return execute(instructions, null)
  }

  fun execute(instructions: List<OpcodeInstruction>, until: Int?): RegisterState {
    while (until == null || opCount < until) {
      require(instructions.indices.contains((registerState.get(pointer)))) {
        "Program halted unexpectedly!"
      }
      var newState =
          instructions[registerState.get(pointer)]
              .execute(this.registerState)
      newState = newState.set(newState.get(pointer) + 1, pointer)

      this.registerState = newState

      opCount += 1

      // Halting condition.
      if (!instructions.indices.contains((registerState.get(pointer)))) {
        return registerState
      }
    }
    throw RuntimeException("Did not halt within allotted instructions")
  }
}
