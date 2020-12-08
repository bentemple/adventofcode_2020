package day_08

import Solution
import sourcePath
import java.io.*

class Problem_08_1() : Solution {
    private enum class Instruction(val value: String) {
        Acc("acc"),
        Jmp("jmp"),
        Nop("nop");

        companion object {
            fun from(value: String): Instruction {
                return values().first { it.value == value }
            }
        }
    }

    override fun solve(): String {
        val instructions: MutableList<Pair<Instruction, Int>> = mutableListOf()
        File("${sourcePath}/day_08/input.txt").forEachLine { input ->
            input.split(" ").let {
                instructions.add(Pair(Instruction.from(it[0]), it[1].toInt()))
            }
        }
        var accumulator: Int = 0
        var nextInstruction: Int = 0
        val runInstructions = mutableListOf<Int>()
        do {
            if (runInstructions.contains(nextInstruction)) {
//                println("Started repeating instructions. Quitting.")
                break
            }
            runInstructions.add(nextInstruction)
            instructions[nextInstruction].let {
                when (it.first) {
                    Instruction.Acc -> {
                        accumulator += it.second
                        nextInstruction++
                    }
                    Instruction.Jmp -> {
                        nextInstruction += it.second
                    }
                    Instruction.Nop -> {
                        nextInstruction++
                    }
                }
            }
        } while (nextInstruction != 0)

        return "${this::class.java.simpleName}:\n Accumulator after first loop: $accumulator"
    }
}
