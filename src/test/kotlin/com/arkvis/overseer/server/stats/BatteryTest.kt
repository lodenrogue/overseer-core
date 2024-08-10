package com.arkvis.overseer.server.stats

import com.arkvis.overseer.TestCommandBuilder
import com.arkvis.overseer.server.Server
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BatteryTest {

    @Test
    fun testBatteryPercentCharged() {
        val server = Server("user", "localhost", TestCommandBuilder())
        val battery = server.getStats().battery
        assertEquals("98.0739%", battery.percentCharged)
    }
}