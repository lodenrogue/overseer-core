package com.arkvis.overseer

import com.arkvis.overseer.server.CommandBuilder
import com.arkvis.overseer.server.Server
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.*

class FileServerRepository(private val location: String, private val commandBuilder: CommandBuilder) :
    ServerRepository {

    private val path = Path(location)

    override fun getServers(): List<Server> {
        val servers = mutableListOf<Server>()

        if (path.isDirectory()) {
            path.listDirectoryEntries()
                .filter { entry -> entry.isRegularFile() }
                .forEach { entry -> servers.add(Server(getUser(entry), getHostname(entry), commandBuilder)) }
        }
        return servers.toList()
    }

    override fun registerServer(server: Server) {
        if (!path.isDirectory()) {
            path.createDirectory()
        }
        val content = "${server.user}:${server.hostname}"
        Files.write(Path(location, server.hostname), content.toByteArray())
    }

    private fun getUser(entry: Path): String {
        return entry.readText().split(":")[0]
    }

    private fun getHostname(entry: Path): String {
        return entry.readText().split(":")[1]
    }
}