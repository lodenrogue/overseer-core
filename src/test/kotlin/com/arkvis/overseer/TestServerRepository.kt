package com.arkvis.overseer

class TestServerRepository: ServerRepository {

    private val servers = mutableListOf<Server>()

    override fun getServers(): List<Server> {
        return servers.toList()
    }

    override fun registerServer(server: Server) {
        servers.add(server)
    }
}