package advent.classes.day16

interface OpcodeInterface {
    fun apply(register: RegisterState, inputA: Int, inputB: Int, output: Int): RegisterState
}