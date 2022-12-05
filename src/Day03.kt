// Day 3: Rucksack Reorganization
// Started AoC on NextJS but decided on day 4 to move over to Kotlin (Still need to move this one over)
//class RuckSack {
//    var compartmentTop: CharArray = charArrayOf()
//    var compartmentBottom: CharArray = charArrayOf()
//}
//
//fun main() {
//
//    val lowOff = 96
//    val highOff = 38
//
//    fun getCompartments(input: String): RuckSack {
//        val items = input.toCharArray()
//        var ruckSack = RuckSack()
//
//        ruckSack.compartmentTop = items.copyOfRange(0, items.size / 2)
//        ruckSack.compartmentBottom = items.copyOfRange(items.size / 2, items.size)
//
//        return ruckSack
//    }
//
//    fun part1(input: List<String>): Int {
//        var result = 0
//
//        for (rs in input) {
//            var ruckSack: RuckSack = getCompartments(rs)
//            var checkedItems: CharArray = charArrayOf()
//
//            for (item in ruckSack.compartmentTop) {
//                if (ruckSack.compartmentBottom.contains(item) && !checkedItems.contains(item)) {
//                    val itemCode = item.code
//                    result += if (itemCode > 90) (itemCode - lowOff)
//                    else (itemCode - highOff)
//                    checkedItems += item
//                }
//            }
//        }
//
//        return result
//    }
//
//    fun part2(input: List<String>): Int {
//        var group: CharArray
//        var i = 0
////        for(ruckSack in input){
////
////            if(((i+1) % 3) === 0){
////                var checkedItems: CharArray = charArrayOf()
////
////                for (item in group) {
////                    if (ruckSack.compartmentBottom.contains(item) && !checkedItems.contains(item)) {
////                        val itemCode = item.code
////                        result += if (itemCode > 90) (itemCode - lowOff)
////                        else (itemCode - highOff)
////                        checkedItems += item
////                    }
////                }
////                group = charArrayOf()
////            }
////
////            i++
////        }
//
//        return input.size
//    }
//
//    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day03_test")
//    println(part1(testInput))
//    check(part1(testInput) == 157)
//
//    val input = readInput("Day03")
//    println(part1(input))
//    println(part2(input))
//}
