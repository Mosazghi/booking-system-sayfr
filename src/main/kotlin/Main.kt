package org.example

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

var rooms = listOf(
    DropInRoom("DropInRoom1", WindowType.W_WINDOW, 20.0f),
    DropInRoom("DropInRoom2", WindowType.NOT_W_WINDOW, 30.0f),
    DropInRoom("DropInRoom3", WindowType.NOT_W_WINDOW, 37.0f),
    NotDropInRoom("NotDropInRoom1", WindowType.W_WINDOW, 25.0f),
    NotDropInRoom("NotDropInRoom2", WindowType.NOT_W_WINDOW, 35.0f),
)

fun main() {
    // Eksempel på bruk
    val dropInRooms = getDropInRooms()
    println("Drop-in rom: ${dropInRooms!!.joinToString { it.name }}")
    val bookableRooms = getBookableRooms()
    println("Bookbare rom: ${bookableRooms!!.joinToString { it.name }}")

    val varmestRoom = getWarmestRoom(10.0f)
    println("Varmest rom med utetemperatur 10*C: ${varmestRoom?.name}")
    
    val booking = bookWarmestRoomInDays(5.0f, 10)
    println("Booking: ${booking?.bookedRoomName} på ${booking?.bookedWeekDay} ${booking?.bookedDate}")    
}

fun getAllRooms(): List<Room> = rooms

// Oppg. 1
fun getDropInRooms() : List<Room>? {
   return getAllRooms().filterIsInstance<DropInRoom>()
}

// Oppg. 2
fun getBookableRooms() : List<Room>? {
    return getAllRooms().filter {
        it.isAvailable() || it is NotDropInRoom
    }
}

// Oppg. 3
fun getWarmestRoom(outDoorTemperature: Float) : Room? {
    return getAllRooms().maxByOrNull {
        it.calculateRoomTemperature(outDoorTemperature)
    }
}


// Oppg. 4 
fun getWeekDayInAmountOfDays(days: Int) : String {
    val startDate = LocalDate.of(2024, 1, 1)
    val targetDate = startDate.plusDays(days.toLong())
    return targetDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
}

fun getDateInAmountOfDays(days: Int) : Date {
    val startDate = LocalDate.of(2024, 1, 1)
    val targetDate = startDate.plusDays(days.toLong())
    return Date(targetDate.dayOfMonth, targetDate.monthValue, targetDate.year)
}

// Oppg. 5
fun bookWarmestRoomInDays(outDoorTemperature: Float, days: Int) : Booking? {
    val room = getWarmestRoom(outDoorTemperature) ?: return null
    val date = getDateInAmountOfDays(days)
    val weekDay = getWeekDayInAmountOfDays(days)
    room.bookRoom()
    
    return Booking(room.name, weekDay, String.format("%02d.%02d.%d", date.day, date.month, date.year))
}