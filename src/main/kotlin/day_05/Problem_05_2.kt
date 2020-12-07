package day_05

import Solution
import sourcePath
import java.io.*

class Problem_05_2() : Solution {
    override fun solve(): String {
        var seats: MutableList<Int> = mutableListOf()
        val binary0 = Regex("[FL]")
        val binary1 = Regex("[BR]")
        File("${sourcePath}/day_05/input.txt").forEachLine { input ->
            var binaryInput: String = input
            binaryInput = binary0.replace(binaryInput, "0")
            binaryInput = binary1.replace(binaryInput, "1")
            val seatNumber = Integer.parseInt(binaryInput, 2)
            seats.add(seatNumber)
        }
        var previousSeat: Int? = null
        seats.sorted().forEach { seat ->
            val lastSeat = previousSeat
            if (lastSeat != null && lastSeat + 2 == seat) {
                return "Problem 05-2:\n Your seat number is ${lastSeat + 1}"
            } else {
                previousSeat = seat
            }
        }
        return "Problem 05-2:\n Unable to find your seat."
    }
}