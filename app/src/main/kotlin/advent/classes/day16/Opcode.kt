package advent.classes.day16

sealed class Opcode:OpcodeInterface {

    /**
     * addr (add register) stores into register C the result of adding register A and register B.
     */
    class Addr: Opcode() {
        override fun apply(register: RegisterState, inputA: Int, inputB: Int, output: Int): RegisterState {
            val valueA = register.get(inputA)
            val valueB = register.get(inputB)
            return register.set(valueA + valueB, output)
        }
    }

    /**
     * addi (add immediate) stores into register C the result of adding register A and value B.
     */
    class Addi: Opcode() {
        override fun apply(register: RegisterState, inputA: Int, inputB: Int, output: Int): RegisterState {
            val valueA = register.get(inputA)
            return register.set(valueA + inputB, output)
        }
    }
}