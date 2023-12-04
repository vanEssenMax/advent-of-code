package y2023

import utils.Advent

// Day 3
fun main() {
    fun isSpecialCharacter(char: Char, special: Char? = null): Boolean {
        if (special != null) {
            return char == special
        }
        return !char.isLetterOrDigit() && char != '.'
    }

    fun getDigit(
        data: MutableList<Map<Map<Int, Map<Int, Int>>, Int>>,
        x: Int,
        y: Int
    ): Map<Map<Int, Map<Int, Int>>, Int> {
        val rowData = data.filter { it.keys.first().keys.first() == y }
        val finalData = rowData.filter {
            it.keys.first().values.first().keys.first() <= x &&
                    it.keys.first().values.first().values.first() >= x
        }

        return finalData.first();
    }

    fun getCoords(
        input: List<String>,
        checkSize: Int,
        special: Char? = null
    ): Pair<MutableList<Map<Int, Int>>, MutableList<Map<Map<Int, Map<Int, Int>>, Int>>> {
        val specialCoords: MutableList<Map<Int, Int>> = mutableListOf()
        val digitData: MutableList<Map<Map<Int, Map<Int, Int>>, Int>> = mutableListOf()

        val rows = input.map { it.toCharArray() }
        for ((rowIndex, row) in rows.withIndex()) {
            var startIndex: Int = -1
            var digits: MutableList<Int> = mutableListOf()
            for ((colIndex, col) in row.withIndex()) {
                if (isSpecialCharacter(col, special)) {
                    specialCoords.add(mapOf(rowIndex to colIndex))
                }
                if (col.isDigit()) {
                    if (startIndex == -1) {
                        startIndex = colIndex
                    }
                    digits.add(col.digitToInt())
                    if (colIndex + checkSize == row.size || (colIndex > 0 && !row[colIndex + checkSize].isDigit())) {
                        digitData.add(
                            mapOf(
                                mapOf(rowIndex to mapOf(startIndex to colIndex)) to digits.joinToString("").toInt()
                            )
                        )
                        digits = mutableListOf()
                        startIndex = -1
                    }
                }
            }
        }
        return specialCoords to digitData
    }

    fun parseCoords(
        input: List<String>,
        digitData: MutableList<Map<Map<Int, Map<Int, Int>>, Int>>,
        specialCoords: MutableList<Map<Int, Int>>,
        checkSize: Int,
        gearRatio: Int? = null
    ): Int {
        var total = 0
        val rows = input.map { it.toCharArray() }
        for (coord in specialCoords) {
            val y = coord.keys.first()
            val x = coord.values.first()

            val foundDigits: MutableList<Map<Map<Int, Map<Int, Int>>, Int>> = mutableListOf()
            // Above
            if (y > 0 && rows[y - checkSize][x].isDigit()) {
//                println("[$y][$x] ↑ ${rows[y - checkSize][x]}")
                foundDigits.add(getDigit(digitData, x, y - checkSize))
            }

            // Below
            if (y + checkSize < rows.size && rows[y + checkSize][x].isDigit()) {
//                println("[$y][$x] ↓ ${rows[y + checkSize][x]}")
                foundDigits.add(getDigit(digitData, x, y + checkSize))
            }

            // Left
            if (x > 0 && rows[y][x - checkSize].isDigit()) {
//                println("[$y][$x] ← ${rows[y][x - checkSize]}")
                foundDigits.add(getDigit(digitData, x - checkSize, y))
            }

            // Right
            if (x + checkSize < rows[y].size && rows[y][x + checkSize].isDigit()) {
//                println("[$y][$x] → ${rows[y][x + checkSize]}")
                foundDigits.add(getDigit(digitData, x + checkSize, y))
            }

            // Left - Top
            if (y > 0 && x > 0 && rows[y - checkSize][x - checkSize].isDigit()) {
//                println("[$y][$x] ↖ ${rows[y - checkSize][x - checkSize]}")
                foundDigits.add(getDigit(digitData, x - checkSize, y - checkSize))
            }

            // Right - Top
            if (y > 0 && x + checkSize < rows[y].size && rows[y - checkSize][x + checkSize].isDigit()) {
//                println("[$y][$x] ↗ ${rows[y - checkSize][x + checkSize]}")
                foundDigits.add(getDigit(digitData, x + checkSize, y - checkSize))
            }

            // Left - Bottom
            if (y + checkSize < rows.size && x > 0 && rows[y + checkSize][x - checkSize].isDigit()) {
//                println("[$y][$x] ↙ ${rows[y + checkSize][x - checkSize]}")
                foundDigits.add(getDigit(digitData, x - checkSize, y + checkSize))
            }

            // Right - Bottom
            if (y + checkSize < rows.size && x + checkSize < rows[y].size && rows[y + checkSize][x + checkSize].isDigit()) {
//                println("[$y][$x] ↘ ${rows[y + checkSize][x + checkSize]}")
                foundDigits.add(getDigit(digitData, x + checkSize, y + checkSize))
            }

            val parts = foundDigits.distinct();
            if (gearRatio == null) {
                total += parts.sumOf { it.values.first() }
            } else if (parts.size == gearRatio) {
                total += parts.map{ it.values.first()}.reduce { acc: Int, i: Int -> acc * i  }
            }

        }
        return total;
    }

    fun part1(input: List<String>): Int {
        val checkSize = 1
        val (specialCoords, digitData) = getCoords(input, checkSize)
        return parseCoords(input, digitData, specialCoords, checkSize)
    }

    fun part2(input: List<String>): Int {
        val checkSize = 1
        val (specialCoords, digitData) = getCoords(input, checkSize, '*')

        return parseCoords(input, digitData, specialCoords, checkSize, 2)
    }

    // Set up the Advent Class (year is optional, uses current year by default)
    val advent = Advent(day = 3)

    // Test if implementation meets criteria from the description, like:
    val testInput = advent.data("test")
    check(part1(testInput) == 1)

    val input = advent.data()
    println(part1(input))
    println(part2(input))
}
