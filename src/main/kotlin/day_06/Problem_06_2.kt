package day_06

import Solution
import sourcePath
import java.io.*

class Problem_06_2() : Solution {
    override fun solve(): String {
        var inputs: MutableList<MutableSet<Char>> = mutableListOf()
        inputs.add(mutableSetOf<Char>().apply {
            this.addAll("abcdefghijklmnopqrstuvwxyz".toList())
        })
        File("${sourcePath}/day_06/input.txt").forEachLine { input ->
            if (input.trim() == "") {
                inputs.add(mutableSetOf<Char>().apply {
                    this.addAll("abcdefghijklmnopqrstuvwxyz".toList())
                })
            } else {
                inputs.last().retainAll(input.toList())
            }
        }
        return "${this::class.java.simpleName}:\n Total questions answered yes to by everyone in each group: ${inputs.sumOf { it.size }}"
    }
}
