package com.arkvis.overseer.server.stats

import com.arkvis.overseer.TestCommandBuilder
import com.arkvis.overseer.server.Server
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StorageTest {

    @Test
    fun testStorageSize() {
        val server = Server("user", "localhost", TestCommandBuilder())
        val storageDetails = server.getStats().storage
        assertEquals("455G", storageDetails.size)
    }

    @Test
    fun testStorageUsed() {
        val server = Server("user", "localhost", TestCommandBuilder())
        val storageDetails = server.getStats().storage
        assertEquals("23G", storageDetails.used)
    }

    @Test
    fun testStorageAvailable() {
        val server = Server("user", "localhost", TestCommandBuilder())
        val storageDetails = server.getStats().storage
        assertEquals("414G", storageDetails.available)
    }

    @Test
    fun testStoragePercentUsed() {
        val server = Server("user", "localhost", TestCommandBuilder())
        val storageDetails = server.getStats().storage
        assertEquals("6%", storageDetails.percentUsed)
    }
}