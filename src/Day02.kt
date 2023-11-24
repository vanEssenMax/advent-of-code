// Day 2: Rock Paper Scissors

enum class Move(val points: Int) {
    A(1),
    B(2),
    C(3),
    X(1),
    Y(2),
    Z(3)
}

enum class Result(val points: Int) {
    AX(3),
    AY(6),
    AZ(0),
    BX(0),
    BY(3),
    BZ(6),
    CX(6),
    CY(0),
    CZ(3),
}

enum class Strat(val points: Int) {
    AX(3),
    AY(4),
    AZ(8),
    BX(1),
    BY(5),
    BZ(9),
    CX(2),
    CY(6),
    CZ(7),
}

fun main() {

    fun round(move: String): Int {
        return Move.valueOf(move[1].toString()).points + Result.valueOf(move).points
    }

    fun part1(input: List<String>): Int {
        var total = 0

        for (single in input) {
            total += round(single.replace(" ", ""))
        }

        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0

        for (round in input) {
            total += Strat.valueOf(round.replace(" ", "")).points
        }

        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println(part1(testInput))
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
