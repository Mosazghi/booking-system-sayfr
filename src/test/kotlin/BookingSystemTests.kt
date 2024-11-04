package org.example
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RoomBookingTests {

//    var rooms = listOf(
//        DropInRoom("DropInRoom1", WindowType.W_WINDOW, 20.0f),
//        DropInRoom("DropInRoom2", WindowType.NOT_W_WINDOW, 30.0f),
//        DropInRoom("DropInRoom3", WindowType.NOT_W_WINDOW, 37.0f),
//        NotDropInRoom("NotDropInRoom1", WindowType.W_WINDOW, 25.0f),
//        NotDropInRoom("NotDropInRoom2", WindowType.NOT_W_WINDOW, 35.0f),
//    )

    @Test
    fun testGetDropInRooms() {
        val dropInRooms = getDropInRooms()
        assertEquals(3, dropInRooms.size, "Expected 3 drop-in rooms")
    }

    @Test
    fun testGetBookableRooms() {
        rooms[0].bookRoom() // DropInRoom1
        rooms[4].bookRoom() // NotDropInRoom2
        val bookableRooms = getBookableRooms()
        println(bookableRooms.joinToString { it.name })
        assertEquals(4, bookableRooms.size, "Expected 4 bookable rooms after booking one")
        assertFalse(bookableRooms.contains(rooms[0]), "Booked drop-in room should not be in the bookable list")
        assertTrue(bookableRooms.contains(rooms[4]), "Booked NOT drop-in room should not be in the bookable list")
    }

    @Test
    fun testGetVarmestRoom() {
        val warmestRoom = getWarmestRoom(15.0f)
        assertEquals("DropInRoom3", warmestRoom?.name, "Expected DropInRoom3 to be the warmest")
    }

    @Test
    fun testGetWeekDayInAmountOfDays() {
        assertEquals("Monday", getWeekDayInAmountOfDays(0), "Expected Monday for day 1")
        assertEquals("Thursday", getWeekDayInAmountOfDays(59), "Expected Thrusday for day no. 60")
    }

    @Test
    fun testGetDateInAmountOfDays() {
        val date = getDateInAmountOfDays(59)
        assertEquals(Date(29, 2, 2024), date, "Expected date to be Feb 29, 2024")
    }

    @Test
    fun testBookWarmestRoomInDays() {
        val booking = bookWarmestRoomInDays(15.0f, 59)
        assertEquals("DropInRoom3", booking?.bookedRoomName, "Expected DropInRoom3 to be booked as warmest")
        assertEquals("Thursday", booking?.bookedWeekDay, "Expected booking weekday to be Thursday")
        assertEquals("29.02.2024", booking?.bookedDate, "Expected booking date to be 29.02.2024")
    }
}
