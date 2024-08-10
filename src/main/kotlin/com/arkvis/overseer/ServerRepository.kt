package com.arkvis.overseer

interface ServerRepository {

    fun getServers(): List<Server>

    fun registerServer(server: Server)
}