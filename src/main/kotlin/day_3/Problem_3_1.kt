package day_3

import Point
import Solution
import sourcePath
import java.io.*

class Problem_3_1(): Solution {
  override fun solve(): String {
    val treeMap: MutableList<List<Char>> = mutableListOf()
    File("${sourcePath}/day_3/input.txt").forEachLine {
     treeMap.add(it.toList())
    }
    
    return "Problem 3-1:\n Encountered ${countTrees(treeMap, 3, 1)} trees."
  }

  fun countTrees(treeMap: List<List<Char>>, velocityX: Int, velocityY: Int): Int {
    val repeatingWidth = treeMap[0].size
    var location = Point(0, 0)
    var encounteredTrees = 0
    for (y in location.y until treeMap.size step velocityY) {
      if (treeMap[location.y][location.x % repeatingWidth] == '#') {
        encounteredTrees++
      }
      location = Point(location.x + velocityX, location.y + velocityY)
    }
    return encounteredTrees
  }
}