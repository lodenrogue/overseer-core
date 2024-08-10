package com.arkvis.overseer.server

class Server(
    val user: String,
    val hostname: String,
    val commandBuilder: CommandBuilder
) {

    fun getStats(): ServerStats {
        return ServerStats(user, hostname, commandBuilder)
    }
}