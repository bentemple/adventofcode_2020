package day_07

import Solution
import sourcePath
import java.io.*

class Problem_07_1(): Solution {
  override fun solve(): String {
    val bagColorsMapping: MutableMap<String, List<String>> = mutableMapOf()
    val targetColor = "shiny gold"
    File("${sourcePath}/day_07/input.txt").forEachLine { input ->
      val splitInput = input.split(" contain ")
      val bagColor: String = splitInput[0].split(" ").let {
        it[0] + " " + it[1]
      }
      if (splitInput[1].contains("no other bags.")) {
        //ignore, it can't hold our target
        bagColorsMapping[bagColor] = emptyList()
      } else {
        val containedBagColors = mutableListOf<String>()
        splitInput[1].split(" bag").dropLast(1).forEach {
          it.split(" ").takeLast(2).joinToString(" ").let { containedBagColor ->
            containedBagColors.add(containedBagColor)
          }
        }
        //println("Bag: $bagColor, contains the following bag colors: $containedBagColors")
        bagColorsMapping[bagColor] = containedBagColors
      }
    }
    val bagsContainingTargetColor: Int = countBagsContainingBagColors(setOf(targetColor), bagColorsMapping)
    return "Problem 07-1:\n Total bag colors containing $targetColor bags is $bagsContainingTargetColor"
  }

  private fun countBagsContainingBagColors(targetBagColors: Set<String>, bagColorsMap: MutableMap<String, List<String>>): Int {
    val bagsContainingATargetBagColor = bagColorsMap.filterValues { bagContainedColors ->
      bagContainedColors.any {
        targetBagColors.contains(it)
      }
    }.keys

    val remainingColorsToFind = bagsContainingATargetBagColor - targetBagColors
    if (remainingColorsToFind.isEmpty()) {
      return 0
    }
    return remainingColorsToFind.size + countBagsContainingBagColors(bagsContainingATargetBagColor + targetBagColors, bagColorsMap)
  }
}
