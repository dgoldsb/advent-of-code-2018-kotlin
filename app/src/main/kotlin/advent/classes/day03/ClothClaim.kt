package advent.classes.day03

data class ClothClaim(
    val uid: String,
    val xCoordinate: Int,
    val yCoordinate: Int,
    val xLength: Int,
    val yLength: Int,
) {
  fun getClaimCoordinates(): HashSet<Pair<Int, Int>> {
    val resultHashSet = HashSet<Pair<Int, Int>>()
    for (xDelta in 0 until this.xLength) {
      for (yDelta in 0 until this.yLength) {
        resultHashSet.add(Pair(this.xCoordinate + xDelta, this.yCoordinate + yDelta))
      }
    }
    return resultHashSet
  }

  companion object {
    private val REGEX: Regex = Regex("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)")

    fun fromString(input: String): ClothClaim {
      val match: MatchResult = REGEX.matchEntire(input)!!
      val groups = match.groupValues
      return ClothClaim(
          groups[1], groups[2].toInt(), groups[3].toInt(), groups[4].toInt(), groups[5].toInt())
    }
  }
}
