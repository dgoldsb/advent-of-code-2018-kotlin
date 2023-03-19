package advent.days

import advent.Day
import advent.classes.day03.ClothClaim
import java.lang.RuntimeException
import kotlin.collections.HashSet

object Day03 : Day<List<ClothClaim>>(3) {

  override fun deserialize(input: List<String>): List<ClothClaim> {
    return input.map { string: String -> ClothClaim.fromString(string) }
  }

  override fun part1(input: List<ClothClaim>): String {
    val seenInches = HashSet<Pair<Int, Int>>()
    val duplicateInches = HashSet<Pair<Int, Int>>()
    for (claim in input) {
      val claimInches = claim.getClaimCoordinates()
      duplicateInches.addAll(seenInches.intersect(claimInches))
      seenInches.addAll(claimInches)
    }
    return duplicateInches.size.toString()
  }

  override fun part2(input: List<ClothClaim>): String {
    for (firstClaim in input) {
      var isDisjointWithAll = true
      for (secondClaim in input) {

        if (firstClaim.uid == secondClaim.uid) {
          continue
        }

        if (firstClaim
            .getClaimCoordinates()
            .intersect(secondClaim.getClaimCoordinates())
            .isNotEmpty()) {
          isDisjointWithAll = false
          break
        }
      }
      if (isDisjointWithAll) {
        return firstClaim.uid
      }
    }
    throw RuntimeException("Did not find a solution")
  }
}
