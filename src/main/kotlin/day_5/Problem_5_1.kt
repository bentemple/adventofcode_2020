package day_5

import Solution
import sourcePath
import java.io.*

class Problem_5_1(): Solution {
  override fun solve(): String {
    var maxSeatNumber = 0
    val binary0 = Regex("[FL]")
    val binary1 = Regex("[BR]")
    File("${sourcePath}/day_5/input.txt").forEachLine { input ->
      var binaryInput: String = input
      binaryInput = binary0.replace(binaryInput, "0")
      binaryInput = binary1.replace(binaryInput, "1")
      val seatNumber = Integer.parseInt(binaryInput, 2)
      maxSeatNumber = maxSeatNumber.coerceAtLeast(seatNumber)
    }
    return "Problem 5-1:\n Max seat number: $maxSeatNumber"
  }
}