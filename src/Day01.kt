// Day 1: Calorie Counting
fun main() {

    fun getRanking(input: List<String>): Array<Int>{
        var ranking = arrayOf<Int>()
        var tempRank = 0

        for (single in input) {
            if (single.isEmpty()) {
                ranking += tempRank
                tempRank = 0
            } else {
                tempRank += single.toInt()
            }
        }

        ranking.sortDescending()

        return ranking
    }

    fun part1(input: List<String>): Int {
        return getRanking(input) [0]
    }

    fun part2(input: List<String>): Int {
        var sumTop3 = 0
        var ranking = getRanking(input)

        for(i in 1..3){
            sumTop3 += ranking[i-1]
        }

        return sumTop3
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
