package day_02

import Solution
import sourcePath
import java.io.*

class Problem_02_2() : Solution {
    override fun solve(): String {
        var validPasswordCount = 0
        File("${sourcePath}/day_02/input.txt").forEachLine {
            if (isValidPassword(it)) {
                validPasswordCount++
            }
        }
        return "Problem 02-2:\n Valid password count: $validPasswordCount"
    }

    private fun isValidPassword(passwordLine: String): Boolean {
        val splitPassword = passwordLine.split(" ")
        val characterCounts = splitPassword[0].split("-")
        val firstCharacterIndex = characterCounts[0].toInt() - 1
        val secondCharacterIndex = characterCounts[1].toInt() - 1
        val characterRequired = splitPassword[1][0]
        val password = splitPassword[2]
        return (password[firstCharacterIndex] == characterRequired).xor(password[secondCharacterIndex] == characterRequired)
    }
}