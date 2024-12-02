package y2024

import utils.Advent
import kotlin.math.abs

// Day1
fun main() {
    // Split lists on triple space (so we get to separate lists)
    fun parse(input: List<String>): MutableList<List<Int>> {
        val listOne: MutableList<Int> = mutableListOf()
        val listTwo: MutableList<Int> = mutableListOf()

        for (i in input) {
            val res = i.split("   ")
            listOne.add(res[0].toInt())
            listTwo.add(res[1].toInt())
        }

        return mutableListOf(listOne, listTwo)
    }

    // Part 1: Get difference between (sorted and index of other list)
    fun part1(input: List<String>): Int {
        val (listOne, listTwo) = parse(input)
        var total = 0

        for ((index,value) in listOne.sorted().withIndex()) {
            total += abs(value-listTwo.sorted().elementAt(index))
        }

        return total
    }

    // Part 2: Get a total 'similarity index'
    fun part2(input: List<String>): Int {
        val (listOne, listTwo) = parse(input)
        var total = 0

        for (i in listOne) {
           val res = listTwo.count { it == i }
           total += i*res
        }

        return total
    }

    // Set up the Advent Class (year is optional, uses current year by default)
    val advent = Advent(day = 1)

    // Test if implementation meets criteria from the description, like:
    val testInput = advent.data("test")
    check(part1(testInput) == 11)

    val input = advent.data()
    println(part1(input))

    val test2 = advent.data()
    check(part2(testInput) == 31)

    println(part2(test2))
}
