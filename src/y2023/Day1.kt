package y2023

import utils.Advent

// Day 1
fun main() {
    fun part1(input: List<String>): Int {
        var total = 0;
        for (line in input) {
            val (_, numbers) = line.toCharArray().partition { it.isLetter() }

            if (numbers.size > 1) {
                total += "${numbers.first()}${numbers.last()}".toInt()
            } else if (numbers.size == 1) {
                total += "${numbers.first()}${numbers.first()}".toInt()
            }
        }
        return total;
    }

    fun part2(input: List<String>): Int {
        var total = 0;

        val numberMap: Map<String, Int> = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )

        for (line in input) {
            val indexes: MutableList<Int> = mutableListOf()
            val data: MutableList<Int> = mutableListOf()
            var word = ""

            for (char in line.toCharArray()) {
                if (char.isLetter()) {
                    word += char

                    for (entry in numberMap.entries) {
                        val index = word.lastIndexOf(entry.key);
                        if(word.contains(entry.key) && !indexes.contains(index)) {
                            indexes.add(index)
                            data.add(entry.value)
                        }
                    }
                }else {
                    data += "$char".toInt()
                }

            }

            if (data.size > 1) {
                total += "${data.first()}${data.last()}".toInt()
            } else if (data.size == 1) {
                total += "${data.first()}${data.first()}".toInt()
            }

        }
        return total;
    }

    // Set up the Advent Class (year is optional, uses current year by default)
    val advent = Advent(day = 1)
    val input = advent.data()

    println(part1(input))
    println(part2(input))
}
