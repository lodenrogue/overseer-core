package com.arkvis.overseer

import com.arkvis.overseer.server.Server
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ServerTest {

    @Test
    fun testGetStorage() {
        val server = Server("user", "localhost", TestCommandBuilder())
        val storageDetails = server.getStats().storageDetails
        assertEquals("455G", storageDetails.size)
    }
}