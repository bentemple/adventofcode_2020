package day_02

import Solution
import sourcePath
import java.io.*

class Problem_02_1() : Solution {
    override fun solve(): String {
        var validPasswordCount = 0
        File("${sourcePath}/day_02/input.txt").forEachLine {
            if (isValidPassword(it)) {
                validPasswordCount++
            }
        }
        return "${this::class.java.simpleName}:\n Valid password count: $validPasswordCount"
    }

    fun isValidPassword(passwordLine: String): Boolean {
        val splitPassword = passwordLine.split(" ")
        val characterCounts = splitPassword[0].split("-")
        val minCharacterCount = characterCounts[0].toInt()
        val maxCharacterCount = characterCounts[1].toInt()
        val characterRequired = splitPassword[1].get(0)
        val password = splitPassword[2]
        val passwordRequiredCharacterCount = password.count { it == characterRequired }
        return passwordRequiredCharacterCount >= minCharacterCount && passwordRequiredCharacterCount <= maxCharacterCount
    }
}