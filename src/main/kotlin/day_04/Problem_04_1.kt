package day_04

import Solution
import sourcePath
import java.io.*

class Problem_04_1(): Solution {
  override fun solve(): String {
    val passports: MutableList<MutableMap<String, String>> = mutableListOf<MutableMap<String, String>>().apply {
      this.add(mutableMapOf())
    }
    File("${sourcePath}/day_04/input.txt").forEachLine {
     if (it.trim().isEmpty()) {
      passports.add(mutableMapOf())
     } else {
       appendPassportValues(passports.last(), it)
     }
    }

    /*
      Valid fields
      byr (Birth Year)
      iyr (Issue Year)
      eyr (Expiration Year)
      hgt (Height)
      hcl (Hair Color)
      ecl (Eye Color)
      pid (Passport ID)
      cid (Country ID) // Not required
     */
    val requiredEntries = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    val validPassportsCount = passports.count { isPassportValid(it, requiredEntries)}
    return "day_04.Problem_04_1:\n Valid passport count: $validPassportsCount"
  }

  fun appendPassportValues(passport: MutableMap<String, String>, values: String) {
    val entries = values.split(' ')
    entries.forEach {
      val keyValuePair = it.split(':')
      passport.put(keyValuePair[0], keyValuePair[1])
    }
  }

  fun isPassportValid(passport: MutableMap<String, String>, requiredEntries: List<String>): Boolean {
    return requiredEntries.all { passport.contains(it) }
  }
}
