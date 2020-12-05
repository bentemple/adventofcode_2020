package day_01

import Solution
import sourcePath
import java.io.*

class Problem_1_2(): Solution {
  override fun solve(): String {
    val inputSet: MutableSet<Int> = mutableSetOf<Int>()
    File("${sourcePath}/day_1/input.txt").forEachLine {
      inputSet.add(it.toInt())
    }

    for (value1 in inputSet.distinct()) {
      for (value2 in inputSet.distinct()) {
        val targetAddition = TARGET_NUMBER - value1 - value2
        if (inputSet.contains(targetAddition)) {
          return "Problem 1-2:\n $value1 and $value2 and $targetAddition all add up to $TARGET_NUMBER. The solution for them multiplied is ${value1 * value2 * targetAddition}"
        }
      }
    }
    return "Problem 1-2:\n No Solution"
  }

  companion object {
      private const val TARGET_NUMBER = 2020
  }
}