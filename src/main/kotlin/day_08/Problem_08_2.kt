package day_08

import Solution
import sourcePath
import java.io.*

class Problem_08_2() : Solution {
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

        var nextInstructionToTestFrom = 0
        var accumulatorValue: Int? = null
        do {
            val modifiedInstructions = instructions.toMutableList()
            nextInstructionToTestFrom = modifiedInstructions.modifyNextInstruction(nextInstructionToTestFrom)
            accumulatorValue = calculateAccumulatorValue(modifiedInstructions)
        } while (accumulatorValue == null)

        return "${this::class.java.simpleName}:\n Accumulator after fixing the loop: $accumulatorValue"
    }

    private fun MutableList<Pair<Instruction, Int>>.modifyNextInstruction(nextInstructionToTestFrom: Int): Int {
        for (j in nextInstructionToTestFrom..size) {
            get(j).let {
                when (it.first) {
                    Instruction.Jmp -> {
                        set(j, Pair(Instruction.Nop, it.second))
                        return j+1
                    }
                    Instruction.Nop -> {
                        set(j, Pair(Instruction.Jmp, it.second))
                        return j+1
                    }
                    Instruction.Acc -> {
                        // Do nothing
                    }
                }
            }
        }
        return Integer.MAX_VALUE
    }

    private fun calculateAccumulatorValue(instructions: List<Pair<Instruction, Int>>): Int? {
        var accumulator: Int = 0
        var nextInstruction: Int = 0
        val runInstructions = mutableListOf<Int>()
        do {
            if (runInstructions.contains(nextInstruction)) {
//                println("Started repeating instructions. Quitting.")
                return null
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
        } while (nextInstruction < instructions.size)
        return accumulator
    }
}
