/*
 * Utils for ingesting input files to AoC puzzles.
 */
package main.kotlin.advent.utilities

import java.io.File

class ReadUtils {
  companion object {

    /** Reads lines from the given input txt file. */
    fun readInput(name: String) = File("src", "$name.txt").readLines()
  }
}
