package com.arkvis.overseer.server.stats

import com.arkvis.overseer.server.CommandBuilder

class ServerStats(user: String, hostname: String, commandBuilder: CommandBuilder) {

    val storageDetails: StorageDetails

    init {
        val commands = arrayOf("ssh", "$user@$hostname", "PATH=\$PATH:/home/$user/bin; stats")
        val command = commandBuilder.build(commands)
        command.run()

        storageDetails = if (command.getStatusCode() != 0) {
            println(command.getStdErr())
            StorageDetails("", "", "")
        } else {
            val output = command.getStdOut()
            StorageDetails(
                getStorageSize(output),
                getStorageUsed(output),
                getStorageAvailable(output)
            )
        }
    }

    private fun getStorageSize(output: String): String {
        return getStorageValue(output, 0)
    }

    private fun getStorageUsed(output: String): String {
        return getStorageValue(output, 1)
    }

    private fun getStorageAvailable(output: String): String {
        return getStorageValue(output, 2)
    }

    private fun getStorageValue(output: String, index: Int): String {
        val lines = output.lines()
        val storageIndex = lines.indexOf("Storage:")

        if (storageIndex == -1) return ""

        return lines.drop(storageIndex + 1)
            .dropWhile { !it.startsWith("Size") }
            .drop(1)
            .firstOrNull()
            ?.split("\t")
            ?.getOrNull(index)
            ?: ""
    }
}