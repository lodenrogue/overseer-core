package com.arkvis.overseer.server.stats

import com.arkvis.overseer.TestCommandBuilder
import com.arkvis.overseer.server.Server
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MemoryTest {

    @Test
    fun testTotal() {
        val server = Server("user", "localhost", TestCommandBuilder())
        val memory = server.getStats().memory
        assertEquals("3.7Gi", memory.total)
    }

    @Test
    fun testUsed() {
        val server = Server("user", "localhost", TestCommandBuilder())
        val memory = server.getStats().memory
        assertEquals("742Mi", memory.used)
    }

    @Test
    fun testAvailable() {
        val server = Server("user", "localhost", TestCommandBuilder())
        val memory = server.getStats().memory
        assertEquals("3.0Gi", memory.available)
    }
}