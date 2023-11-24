package y2023

import utils.getInput

// Template
fun main() {
    println(getInput(1));
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = useFile("Day01_test")
//    check(part1(testInput) == 1)

    val input = getInput(1)
    println(part1(input))
    println(part2(input))
}
