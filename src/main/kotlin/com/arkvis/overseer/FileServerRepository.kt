package com.arkvis.overseer

import java.nio.file.Files
import kotlin.io.path.*

class FileServerRepository(private val location: String) : ServerRepository {

    private val path = Path(location)

    override fun getServers(): List<Server> {
        val servers = mutableListOf<Server>()

        if (path.isDirectory()) {
            path.listDirectoryEntries()
                .filter { entry -> entry.isRegularFile() }
                .forEach { entry -> servers.add(Server(entry.readText())) }
        }
        return servers.toList()
    }

    override fun registerServer(server: Server) {
        if (!path.isDirectory()) {
            path.createDirectory()
        }
        Files.write(Path(location, server.hostname), server.hostname.toByteArray())
    }
}