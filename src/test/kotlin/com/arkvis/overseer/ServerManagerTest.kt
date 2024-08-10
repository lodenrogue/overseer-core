package com.arkvis.overseer

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.deleteRecursively

class ServerManagerTest {

    private lateinit var serverRepository: ServerRepository
    private lateinit var serverManager: ServerManager

    @OptIn(ExperimentalPathApi::class)
    @BeforeEach
    fun setUp() {
        serverRepository = FileServerRepository(TEST_SERVERS_FILE_LOCATION)
        serverManager = ServerManager(serverRepository)

        Path(TEST_SERVERS_FILE_LOCATION).deleteRecursively()
    }

    @OptIn(ExperimentalPathApi::class)
    @AfterEach
    fun tearDown() {
        Path(TEST_SERVERS_FILE_LOCATION).deleteRecursively()
    }

    @Test
    fun testReturnNoServers() {
        val servers = serverManager.getServers()
        assertTrue(servers.isEmpty())
    }

    @Test
    fun testReturnOneServer() {
        val server = Server("TEST_USER", "TEST_SERVER")
        serverManager.registerServer(server)

        val servers = serverManager.getServers()
        assertEquals(1, servers.size)
        assertEquals(server.user, servers[0].user)
        assertEquals(server.hostname, servers[0].hostname)
    }

    @Test
    fun testReturnMultipleServers() {
        val server1 = Server("TEST_USER_1", "TEST_SERVER_1")
        val server2 = Server("TEST_USER_2", "TEST_SERVER_2")

        serverManager.registerServer(server1)
        serverManager.registerServer(server2)

        val servers = serverManager.getServers()
        assertEquals(2, servers.size)

        assertEquals(server1.user, servers[0].user)
        assertEquals(server1.hostname, servers[0].hostname)

        assertEquals(server2.user, servers[1].user)
        assertEquals(server2.hostname, servers[1].hostname)
    }

    companion object {
        private const val TEST_SERVERS_FILE_LOCATION = "test_servers"
    }
}