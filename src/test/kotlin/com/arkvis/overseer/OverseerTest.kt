package com.arkvis.overseer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OverseerTest {

    private lateinit var serverRepository: ServerRepository
    private lateinit var serverManager: ServerManager

    @BeforeEach
    fun setUp() {
        serverRepository = TestServerRepository()
        serverManager = ServerManager(serverRepository)
    }

    @Test
    fun testReturnNoServers() {
        val servers = serverManager.getServers()
        assertTrue(servers.isEmpty())
    }

    @Test
    fun testReturnOneServer() {
        val server = Server("TEST_SERVER")
        serverManager.registerServer(server)

        val servers = serverManager.getServers()
        assertEquals(1, servers.size)
        assertEquals(server.hostname, servers[0].hostname)
    }
}