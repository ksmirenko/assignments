@file:Suppress("unused")

package patterns.Facade

/*
    Example of Facade pattern
    Sergeev Evgeny 17.10.2015
 */

/* PC Subsystem */

class PowerSupplyUnit {
    fun powerOn() = println("Power unit : Power on")
    fun powerOff() = println("Power unit :Power off")
    fun testVoltage() = println("Power unit : Test voltage")
}

class CPU {
    var registers = Array<Byte>(32, { 0 })
    fun reset() = println("CPU        : Reset")
    fun jump(position: Byte) = println("CPU        : Jump to $position")
    fun execute() = println("CPU        : Execute")
}

class DataBus() {
    private val capacity = 16
    private var data: Array<Byte> = Array<Byte>(capacity, { 0 })

    fun read(): Array<Byte> {
        println("Data bus   : Get data")
        return data
    }

    fun write(value: Array<Byte>) {
        println("Data bus   : Set data")
        for (i in 0..Math.min(value.size - 1, capacity - 1))
            data[i] = value[i]

    }
}

class RAM {
    private var memory = Array<Byte>(640 * 1024, { 0 })         //ought to be enough for anybody
    fun load(position: Byte, data: Array<Byte>) {
        println("RAM        : Load data at $position")
        for (i in 0..data.size - 1)
            memory[position + i] = data[i]
    }

    fun upload(position: Byte, length: Byte): Array<Byte> {
        println("RAM        : Upload data from $position")
        val data = Array<Byte>(length.toInt(), { 0 })
        for (i in 0..data.size - 1)
            data[i] = memory[position + i]
        return data
    }
}

class HardDrive {

    private var memory = Array<Byte>(3 * 1024 * 1024, { 0 })

    fun read(position: Byte, size : Byte) : Array<Byte> {
        println("Hard drive : Upload data from $position")
        val data = Array<Byte>(size.toInt(), { 0 })
        for (i in 0..size - 1)
            data[i] = memory[position + i]
        return data
    }
}

/* Facade */

class ComputerFacade {
    private val BOOT_ADDRESS : Byte = 0
    private val BOOT_SECTOR : Byte = 0
    private val SECTOR_SIZE : Byte = 16

    private val psu = PowerSupplyUnit()
    private val cpu = CPU()
    private val bus = DataBus()
    private val ram = RAM()
    private val hd = HardDrive()

    fun start() {
        psu.testVoltage()
        psu.powerOn()
        cpu.reset()
        bus.write(hd.read(BOOT_SECTOR, SECTOR_SIZE))
        ram.load(BOOT_ADDRESS, bus.read())
        cpu.jump(BOOT_ADDRESS)
        cpu.execute()
    }

    fun read(position : Byte, length : Byte) : Array<Byte> {
        bus.write(ram.upload(position, length))
        return bus.read()
    }

    fun write(position : Byte, data : Array<Byte>) {
        bus.write(data)
        ram.load(position, bus.read())
    }
}

/*
fun main(args: Array<String>) {
    val pc = ComputerFacade()
    println("Computer starting : ")
    pc.start()
    println()

    val position : Byte = 8
    println("Write data in RAM at $position")
    pc.write(8, arrayOf<Byte>(1, 1, 1, 1))
    println()

    println("Read data from RAM at $position")
    val data = pc.read(8, 16)
    for (i in 0..data.size() - 1) print(data[i])
    println()
}
*/