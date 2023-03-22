package advent.classes.day16

sealed class Opcode : OpcodeInterface {

  /** addr (add register) stores into register C the result of adding register A and register B. */
  class Addr : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      val valueA = register.get(inputA)
      val valueB = register.get(inputB)
      return register.set(valueA + valueB, output)
    }
  }

  /** addi (add immediate) stores into register C the result of adding register A and value B. */
  class Addi : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      val valueA = register.get(inputA)
      return register.set(valueA + inputB, output)
    }
  }

  /**
   * mulr (multiply register) stores into register C the result of multiplying register A and
   * register B.
   */
  class Mulr : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      val valueA = register.get(inputA)
      val valueB = register.get(inputB)
      return register.set(valueA * valueB, output)
    }
  }

  /**
   * muli (multiply immediate) stores into register C the result of multiplying register A and value
   * B.
   */
  class Muli : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      val valueA = register.get(inputA)
      return register.set(valueA * inputB, output)
    }
  }

  /**
   * banr (bitwise AND register) stores into register C the result of the bitwise AND of register A
   * and register B.
   */
  class Banr : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      val valueA = register.get(inputA)
      val valueB = register.get(inputB)
      return register.set(valueA and valueB, output)
    }
  }

  /**
   * bani (bitwise AND immediate) stores into register C the result of the bitwise AND of register A
   * and value B.
   */
  class Bani : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      val valueA = register.get(inputA)
      return register.set(valueA and inputB, output)
    }
  }

  /**
   * borr (bitwise OR register) stores into register C the result of the bitwise OR of register A
   * and register B.
   */
  class Borr : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      val valueA = register.get(inputA)
      val valueB = register.get(inputB)
      return register.set(valueA or valueB, output)
    }
  }

  /**
   * bori (bitwise OR immediate) stores into register C the result of the bitwise OR of register A
   * and value B.
   */
  class Bori : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      val valueA = register.get(inputA)
      return register.set(valueA or inputB, output)
    }
  }

  /**
   * setr (set register) copies the contents of register A into register C. (Input B is ignored.)
   */
  class Setr : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      val valueA = register.get(inputA)
      return register.set(valueA, output)
    }
  }

  /** seti (set immediate) stores value A into register C. (Input B is ignored.) */
  class Seti : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      return register.set(inputA, output)
    }
  }

  /**
   * gtir (greater-than immediate/register) sets register C to 1 if value A is greater than register
   * B. Otherwise, register C is set to 0.
   */
  class Gtir : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      val valueA = register.get(inputA)
      val valueB = register.get(inputB)
      if (valueA > valueB) {
        return register.set(1, output)
      } else {
        return register.set(0, output)
      }
    }
  }

  /**
   * gtri (greater-than register/immediate) sets register C to 1 if register A is greater than value
   * B. Otherwise, register C is set to 0.
   */
  class Gtri : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      val valueA = register.get(inputA)
      if (valueA > inputB) {
        return register.set(1, output)
      } else {
        return register.set(0, output)
      }
    }
  }

  /**
   * gtrr (greater-than register/register) sets register C to 1 if register A is greater than
   * register B. Otherwise, register C is set to 0.
   */
  class Gtrr : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      if (inputA > inputB) {
        return register.set(1, output)
      } else {
        return register.set(0, output)
      }
    }
  }

  /**
   * eqir (equal immediate/register) sets register C to 1 if value A is equal to register B.
   * Otherwise, register C is set to 0.
   */
  class Eqir : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      val valueA = register.get(inputA)
      val valueB = register.get(inputB)
      if (valueA == valueB) {
        return register.set(1, output)
      } else {
        return register.set(0, output)
      }
    }
  }

  /**
   * eqri (equal register/immediate) sets register C to 1 if register A is equal to value B.
   * Otherwise, register C is set to 0.
   */
  class Eqri : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      val valueA = register.get(inputA)
      if (valueA == inputB) {
        return register.set(1, output)
      } else {
        return register.set(0, output)
      }
    }
  }

  /**
   * eqrr (equal register/register) sets register C to 1 if register A is equal to register B.
   * Otherwise, register C is set to 0.
   */
  class Eqrr : Opcode() {
    override fun apply(
        register: RegisterState,
        inputA: Int,
        inputB: Int,
        output: Int
    ): RegisterState {
      if (inputA == inputB) {
        return register.set(1, output)
      } else {
        return register.set(0, output)
      }
    }
  }
}
