package day_04

import Solution
import sourcePath
import java.io.*

class Problem_04_2(): Solution {
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
    return "day_04.Problem_04_2:\n Valid passport count: $validPassportsCount"
  }

  fun appendPassportValues(passport: MutableMap<String, String>, values: String) {
    val entries = values.split(' ')
    entries.forEach {
      val keyValuePair = it.split(':')
      /* 
        byr (Birth Year) - four digits; at least 1920 and at most 2002.
        iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
        hgt (Height) - a number followed by either cm or in:
            If cm, the number must be at least 150 and at most 193.
            If in, the number must be at least 59 and at most 76.
        hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
        ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
        pid (Passport ID) - a nine-digit number, including leading zeroes.
        cid (Country ID) - ignored, missing or not.
      */
      val hairColorRegex = Regex("#[0-9a-f]{6}")
      val validEyeColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
      val passportIdRegex = Regex("[0-9]{9}")
      val value = keyValuePair[1]
      val isValidEntry = when (keyValuePair[0]) {
        "byr" -> {
          try {
            val number = value.toInt()
            number >= 1920 && number <= 2002
          } catch (e: Exception) {
            false
          }
        }
        "iyr" -> {
          try {
            val number = value.toInt()
            number >= 2010 && number <= 2020
          } catch (e: Exception) {
            false
          }        }
        "eyr" -> {
          try {
            val number = value.toInt()
            number >= 2020 && number <= 2030
          } catch (e: Exception) {
            false
          }   
        }
        "hgt" -> {
          if (value[value.length-2] == 'c' && value[value.length-1] == 'm') {
            val heightCm = value.dropLast(2).toInt()
            heightCm >= 150 && heightCm <= 193
          } else if (value[value.length-2] == 'i' && value[value.length-1] == 'n') {
            val heightIn = value.dropLast(2).toInt()
            heightIn >= 59 && heightIn <= 76
          } else {
            false
          }
        }
        "hcl" -> {
          hairColorRegex.matches(value)
        }
        "ecl" -> {
          validEyeColors.any { it == value }
        }
        "pid" -> {
          passportIdRegex.matches(value)
        }
        "cid" -> {
          true
        }
        else -> false
      }
      // Never add an invalid entry to the Passport
      if (isValidEntry) {
        passport.put(keyValuePair[0], keyValuePair[1])
      }
    }
  }

  fun isPassportValid(passport: MutableMap<String, String>, requiredEntries: List<String>): Boolean {
    return requiredEntries.all {
      passport.contains(it) 
    }
  }
}
