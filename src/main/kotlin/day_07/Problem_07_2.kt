package day_07

import Solution
import sourcePath
import java.io.*

class Problem_07_2() : Solution {
    override fun solve(): String {
        val bagColorsMapping: MutableMap<String, Map<String, Int>> = mutableMapOf()
        val targetColor = "shiny gold"
        File("${sourcePath}/day_07/input.txt").forEachLine { input ->
            val splitInput = input.split(" contain ")
            val bagColor: String = splitInput[0].split(" ").let {
                it[0] + " " + it[1]
            }
            if (splitInput[1].contains("no other bags.")) {
                //ignore, it can't hold our target
                bagColorsMapping[bagColor] = emptyMap()
            } else {
                val containedBagColors = mutableMapOf<String, Int>()
                splitInput[1].split(" bag").dropLast(1).forEach {
                    it.split(" ").let { unparsedContainedBag ->
                        unparsedContainedBag.takeLast(2).joinToString(" ").let { containedBagColor ->
                            if (unparsedContainedBag[0].contains(",")) {
                                containedBagColors[containedBagColor] = unparsedContainedBag[1].toInt()
                            } else {
                                containedBagColors[containedBagColor] = unparsedContainedBag[0].toInt()
                            }
                        }
                    }
                }
                //println(findNextInstructionLoop@"Bag: $bagColor, contains the following bag colors: $containedBagColors")
                bagColorsMapping[bagColor] = containedBagColors
            }
        }
        val bagsContainedInTargetBag: Int = countBagsContainedInBag(targetColor, bagColorsMapping)
        return "${this::class.java.simpleName}:\n Total bag colors containing $targetColor bags is $bagsContainedInTargetBag"
    }

    private fun countBagsContainedInBag(targetBag: String, bagColorsMap: MutableMap<String, Map<String, Int>>): Int {
        return bagColorsMap[targetBag]?.let {
            var bagCount = 0
            it.forEach { containedBag ->
                bagCount += containedBag.value + containedBag.value * countBagsContainedInBag(
                    containedBag.key,
                    bagColorsMap
                )
            }
            bagCount
        } ?: 0
    }
}
