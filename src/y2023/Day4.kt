package y2023

import utils.Advent

// Day 4
fun main() {
    data class ScratchCard(val id: Int, val winningNumbers: List<Number>, val numbers: List<Number>) {
        fun getMatches(): Set<Number> {
            return this.numbers.intersect(this.winningNumbers.toSet())
        }
    }

    fun getScratchCards(input: List<String>): List<ScratchCard> {
        return input.map {
            ScratchCard(
                it.split(":").first().split(" ").last().toInt(),
                it.split(":").last().split("|").first().split(" ").filter { nr -> nr.isNotBlank() && nr.isNotEmpty() }
                    .toList().map { nr -> nr.toInt() },
                it.split(":").last().split("|").last().split(" ").filter { nr -> nr.isNotBlank() && nr.isNotEmpty() }
                    .toList().map { nr -> nr.toInt() }
            )
        }
    }


    fun parsePoints(cards: List<ScratchCard>): Int {
        var total = 0

        for (card in cards) {
            var points = 0

            for (nr in card.getMatches()) {
                if (points == 0) {
                    points = 1
                } else {
                    points *= 2
                }
            }

            total += points
        }

        return total
    }

    fun part1(input: List<String>): Int {
        return parsePoints(getScratchCards(input))
    }

    fun recursiveScratchCards(
        stack: List<ScratchCard>
    ): Int {
        val stacks = stack.map { 1 }.toMutableList()

        for ((index, card) in stack.withIndex()) {
            for (i in index + 1..<index + 1 + card.getMatches().size) {
                stacks[i] += stacks[index]
            }
        }

        return stacks.sum()
    }

    fun part2(input: List<String>): Int {
        val cards = getScratchCards(input)
        return recursiveScratchCards(cards)
    }

    // Set up the Advent Class (year is optional, uses current year by default)
    val advent = Advent(day = 4)

    // Test if implementation meets criteria from the description, like:
//    val testInput = advent.data("test")
//    check(part1(testInput) == 1)

    val input = advent.data()
    println(part1(input))
    println(part2(input))
}
