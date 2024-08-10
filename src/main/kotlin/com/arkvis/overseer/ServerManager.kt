package com.arkvis.overseer

import com.arkvis.overseer.server.Server

class ServerManager(private val serverRepository: ServerRepository) {

    fun getServers(): List<Server> {
        return serverRepository.getServers()
    }

    fun registerServer(server: Server) {
        serverRepository.registerServer(server)
    }
}