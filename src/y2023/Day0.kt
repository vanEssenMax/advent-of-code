package y2023

import utils.Advent

// Example Day
fun main() {
    val advent = Advent(1)
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = advent.data(suffix = "_test")
    check(part1(testInput) == 1)

    val input = advent.data()
    println(part1(input))
    println(part2(input))
}
