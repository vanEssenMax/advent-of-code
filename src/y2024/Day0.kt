package y2024

import utils.Advent

// Example Day0
fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Set up the Advent Class (year is optional, uses current year by default)
    val advent = Advent(day = 1)

    // Test if implementation meets criteria from the description, like:
    val testInput = advent.data("test")
    check(part1(testInput) == 1)

    val input = advent.data()
    println(part1(input))
    println(part2(input))
}
