package day_06

import Solution
import sourcePath
import java.io.*

class Problem_06_1(): Solution {
  override fun solve(): String {
    var inputs: MutableList<MutableSet<Char>> = mutableListOf()
    inputs.add(mutableSetOf())
    File("${sourcePath}/day_06/input.txt").forEachLine { input ->
      if (input.trim() == "") {
        inputs.add(mutableSetOf())
      } else {
        input.forEach { answeredQuestion ->
          inputs.last().add(answeredQuestion)
        }
      }
    }
    return "Problem 06-1:\n Total questions answered yes to: ${inputs.sumOf { it.size }}"
  }
}
