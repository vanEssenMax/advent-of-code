// Day 6: Tuning Trouble
fun main() {
    fun getFirstMarker(packet: ArrayList<String>, distinctAmount: Int): Int {
        var checkArray: ArrayList<String> = arrayListOf()
        for ((curIndex, char) in packet.withIndex()) {
            checkArray += char

            if (checkArray.size == distinctAmount) {
                if (checkArray.distinct().size == distinctAmount) {
                    return curIndex + 1
                }
                checkArray.removeFirst()
            }
        }
        return 0
    }

    fun part1(input: List<String>): Int {
        return getFirstMarker(input[0].split("").dropLast(1).drop(1) as ArrayList<String>, 4)
    }

    fun part2(input: List<String>): Int {
        return getFirstMarker(input[0].split("").dropLast(1).drop(1) as ArrayList<String>, 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
