package day_01

import Solution
import sourcePath
import java.io.*

class Problem_01_1() : Solution {
    override fun solve(): String {
        val inputSet: MutableSet<Int> = mutableSetOf<Int>()
        FileInputStream(File("${sourcePath}/day_01/input.txt")).forEachLine {
            val value = it.toInt()
            inputSet.add(value)
            val targetAddition = TARGET_NUMBER - value
            if (inputSet.contains(targetAddition)) {
                return "${this::class.java.simpleName}:\n $value and $targetAddition both add up to $TARGET_NUMBER. The solution for them multiplied is ${value * targetAddition}"
            }
        }
        return "${this::class.java.simpleName}:\n No Solution"
    }

    companion object {
        private const val TARGET_NUMBER = 2020
    }
}

// day_01.forEachLine doesn't support early returns, so adding this to make it slightly more efficient
inline fun InputStream.forEachLine(consumer: (line: String) -> Unit) {
    val reader = BufferedReader(InputStreamReader(this))
    var line: String?
    line = reader.readLine()
    while (line != null) {
        consumer(line)
        line = reader.readLine()
    }
}