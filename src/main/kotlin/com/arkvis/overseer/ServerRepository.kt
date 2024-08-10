package com.arkvis.overseer

import com.arkvis.overseer.server.Server

interface ServerRepository {

    fun getServers(): List<Server>

    fun registerServer(server: Server)
}