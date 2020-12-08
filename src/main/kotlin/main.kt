import day_01.*
import day_02.*
import day_03.*
import day_04.*
import day_05.*
import day_06.*
import day_07.*
import day_08.*
import day_09.*
import day_10.*
import day_11.*
import day_12.*
import day_13.*
import day_14.*
import day_15.*
import day_16.*
import day_17.*
import day_18.*
import day_19.*
import day_20.*
import day_21.*
import day_22.*
import day_23.*
import day_24.*
import day_25.*

const val sourcePath = "/Users/benjamin/newHomeFolder/development/personal.work/AdventOfCode2020/src/main/kotlin/"

fun main() {
    listOf(
        Problem_01_1(),
        Problem_01_2(),
        Problem_02_1(),
        Problem_02_2(),
        Problem_03_1(),
        Problem_03_2(),
        Problem_04_1(),
        Problem_04_2(),
        Problem_05_1(),
        Problem_05_2(),
        Problem_06_1(),
        Problem_06_2(),
        Problem_07_1(),
        Problem_07_2(),
        Problem_08_1(),
        Problem_08_2(),
//        Problem_09_1(),
//        Problem_09_2(),
//        Problem_10_1(),
//        Problem_10_2(),
//        Problem_11_1(),
//        Problem_11_2(),
//        Problem_12_1(),
//        Problem_12_2(),
//        Problem_13_1(),
//        Problem_13_2(),
//        Problem_14_1(),
//        Problem_14_2(),
//        Problem_15_1(),
//        Problem_15_2(),
//        Problem_16_1(),
//        Problem_16_2(),
//        Problem_17_1(),
//        Problem_17_2(),
//        Problem_18_1(),
//        Problem_18_2(),
//        Problem_19_1(),
//        Problem_19_2(),
//        Problem_20_1(),
//        Problem_20_2(),
//        Problem_21_1(),
//        Problem_21_2(),
//        Problem_22_1(),
//        Problem_22_2(),
//        Problem_23_1(),
//        Problem_23_2(),
//        Problem_24_1(),
//        Problem_24_2(),
//        Problem_25_1(),
//        Problem_25_2(),
    ).forEach {
        val startTime = System.nanoTime()
        val solution = it.solve()
        val endTime = System.nanoTime()
        println(solution)
        println(" Problem took ${(endTime - startTime).toFloat() / 1_000_000f} Ms to complete.")
        println(" Average benchmark time for ${it.javaClass.simpleName} is ${benchmarkSolution(it)} Ms")
    }

    benchmarkSolution(Problem_08_1())
    benchmarkSolution(Problem_08_2())
}

private fun benchmarkSolution(solutionToProfile: Solution, shouldLog: Boolean = false): Float {
    if (shouldLog) {
        println()
        println("\nProfiling problem ${solutionToProfile.javaClass.simpleName}")
    }
    val runTimes = mutableListOf<Long>()
    for (i in 0..100) {
        val startTime = System.nanoTime()
        solutionToProfile.solve()
        val endTime = System.nanoTime()
        val duration = endTime - startTime
        runTimes.add(duration)
    }
    if (shouldLog) {
        println("Runs: ${runTimes.map { it.toFloat() / 1_000_000f }}")
    }

    val sortedRunTimes = runTimes.sorted()
    val medianRunTime = sortedRunTimes[runTimes.size / 2]
    // Throw out any value > 2x the median
    val cutOff = medianRunTime * 2
    val averageBelowCutoff = runTimes.filter { it < cutOff }.let { it.sum() / it.count()}
    if (shouldLog) {
        println("Median runtime was: ${medianRunTime.toFloat() / 1_000_000f} Ms")
        println("Average runtime was: ${averageBelowCutoff.toFloat() / 1_000_000f} Ms")
        println()
    }
    return averageBelowCutoff.toFloat() / 1_000_000f
}
