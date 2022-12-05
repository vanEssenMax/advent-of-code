// Day 5: Supply Stacks

class Ship {
    private var id: Int = 0
    private var cargo: ArrayList<String> = arrayListOf()

    constructor(id: Int, cargo: ArrayList<String>) {
        this.id = id
        this.cargo = cargo
    }

    fun getID(): Int {
        return this.id
    }

    fun addCargo(cargo: String) {
        this.cargo += cargo
    }

    fun getCargo(): ArrayList<String> {
        return this.cargo
    }

    fun setCargo(cargo: ArrayList<String>) {
        this.cargo = cargo
    }

    override fun toString(): String {
        return "Ship $id, has cargo: $cargo"
    }
}

class Command {
    private var amount: Int = 0
    private var from: Int = 0
    private var to: Int = 0

    fun run(port: Port, improvedLoader: Boolean = false) {
        val source = port.ships.find { it.getID() == this.from }
        val target = port.ships.find { it.getID() == this.to }

        if (!improvedLoader) {
            repeat(this.amount) { _ ->
                val cargo = source?.getCargo()?.removeFirst()

                if (cargo != null) {
                    target?.getCargo()?.add(0, cargo)
                }
            }
        } else {
            val cargo = source?.getCargo()?.subList(0, this.amount)

            if (cargo != null) {
                target?.getCargo()?.addAll(0, cargo)
                cargo.clear()
            }
        }

        source.apply {
            if (source != null) {
                this?.setCargo(source.getCargo())
            }
        }
        target.apply {
            if (target != null) {
                this?.setCargo(target.getCargo())
            }
        }
    }

    constructor(cmd: String) {
        var lastWord = ""
        for (it in cmd.split(" ")) {
            if (lastWord == "move") {
                this.amount = it.toInt()
            }

            if (lastWord == "from") {
                this.from = it.toInt()
            }

            if (lastWord == "to") {
                this.to = it.toInt()
            }

            lastWord = it
        }
    }

    override fun toString(): String {
        return "Move $amount cargo, from $from, to $to"
    }
}

class Port {
    var ships: ArrayList<Ship> = arrayListOf()
    private fun addToShip(id: Int, item: String) {
        val selectedShip = ships.find { it.getID() == id }

        if (selectedShip != null) {
            selectedShip.addCargo(item)
        } else {
            ships += Ship(id, arrayListOf(item))
        }
    }

    fun addShipsToPort(data: ArrayList<String>) {
        for (single in data) {
            var countEmpty = 0
            var lastChar = ""
            var index = 0
            for (item in single.replace("[", "").replace("]", "").split("")) {
                if (item.isBlank()) countEmpty++

                if (lastChar.isBlank() && item.isNotBlank() && countEmpty > 2) {
                    index += (countEmpty / 4)
                    countEmpty = 0
                }

                if (item.isNotBlank() && item.toIntOrNull() === null) {
                    addToShip(index + 1, item)
                    index++
                }

                lastChar = item
            }
        }
    }

    override fun toString(): String {
        var details = ""

        for (ship in ships.sortedWith(compareBy { it.getID() })) {
            details += ship.toString() + "\n"
        }
        return details
    }
}

fun main() {
    fun checkCargo(input: List<String>, improvedLoader: Boolean = false): String {
        val port = Port()
        val portData: ArrayList<String> = arrayListOf()
        var portFilled = false
        for (single in input) {
            if (!portFilled) {
                portData.add(single)
                if (single.isEmpty()) {
                    port.addShipsToPort(portData)
                    portFilled = true
                }
            } else {
                Command(single).run(port, improvedLoader)
            }
        }

        var finalWord = ""
        for (ship in port.ships.sortedWith(compareBy { it.getID() })) {
            finalWord += ship.getCargo()[0]
        }
        return finalWord
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(checkCargo(testInput) == "CMZ")

    val input = readInput("Day05")
    println(checkCargo(input))
    println(checkCargo(input, true))
}
