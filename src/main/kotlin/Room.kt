package org.example

sealed class Room(
    val name: String,
    private val windowType: WindowType,
    private val ovenTemperature: Float,
) {
    private var isBooked: Boolean = false

    fun bookRoom() {
        isBooked = true
    }

    fun isAvailable() = !isBooked

    fun calculateRoomTemperature(outDoorTemperature: Float): Float {
        if (windowType == WindowType.W_WINDOW) {
            return (ovenTemperature + outDoorTemperature) / 2
        }
        return (ovenTemperature + 2) * outDoorTemperature * 0.1f
    }
}

class DropInRoom(
    name: String,
    windowType: WindowType,
    ovenTemperature: Float,
) : Room(name, windowType, ovenTemperature)

class NotDropInRoom(
    name: String,
    windowType: WindowType,
    ovenTemperature: Float,
) : Room(name, windowType, ovenTemperature)
  
