package com.arkvis.overseer

class ServerManager(private val serverRepository: ServerRepository) {

    fun getServers(): List<Server> {
        return serverRepository.getServers()
    }

    fun registerServer(server: Server) {
        serverRepository.registerServer(server)
    }
}