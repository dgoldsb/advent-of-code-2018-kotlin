package advent.classes.day19

import advent.classes.day16.RegisterState

class OpcodeExecuter(private var registerState: RegisterState, private var pointer: Int) {
  fun execute(instructions: List<OpcodeInstruction>): RegisterState {
    while (true) {
      require(instructions.indices.contains((registerState.get(pointer)))) {
        "Program halted unexpectedly!"
      }
      var newState =
          instructions[registerState.get(pointer)]
              .execute(this.registerState)
      newState = newState.set(newState.get(pointer) + 1, pointer)

      this.registerState = newState

      // Halting condition.
      if (!instructions.indices.contains((registerState.get(pointer)))) {
        return registerState
      }
    }
  }
}
