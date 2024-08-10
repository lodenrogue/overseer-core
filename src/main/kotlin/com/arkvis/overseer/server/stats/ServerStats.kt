package com.arkvis.overseer.server.stats

import com.arkvis.overseer.server.Command
import com.arkvis.overseer.server.CommandBuilder

class ServerStats(user: String, hostname: String, commandBuilder: CommandBuilder) {

    val storage: Storage
    val memory: Memory

    init {
        val commands = arrayOf("ssh", "$user@$hostname", "PATH=\$PATH:/home/$user/bin; stats")
        val command = commandBuilder.build(commands)
        command.run()

        storage = getStorage(command)
        memory = getMemory(command)
    }

    private fun getMemory(command: Command): Memory {
        return if (command.getStatusCode() != 0) {
            println(command.getStdErr())
            Memory("", "", "")
        } else {
            val output = command.getStdOut()
            Memory(
                getMemoryTotal(output),
                getMemoryUsed(output),
                getMemoryAvailable(output)
            )
        }
    }

    private fun getMemoryTotal(output: String): String {
        return getMemoryValue(output, 1)
    }

    private fun getMemoryUsed(output: String): String {
        return getMemoryValue(output, 2)
    }

    private fun getMemoryAvailable(output: String): String {
        return getMemoryValue(output, 3)
    }

    private fun getMemoryValue(output: String, index: Int): String {
        val lines = output.lines()
        val storageIndex = lines.indexOf("Memory:")

        if (storageIndex == -1) return ""

        return lines.drop(storageIndex + 1)
            .dropWhile { !it.startsWith("Mem:") }
            .firstOrNull()
            ?.split("\t")
            ?.getOrNull(index)
            ?: ""
    }

    private fun getStorage(command: Command): Storage {
        return if (command.getStatusCode() != 0) {
            println(command.getStdErr())
            Storage("", "", "", "")
        } else {
            val output = command.getStdOut()
            Storage(
                getStorageSize(output),
                getStorageUsed(output),
                getStorageAvailable(output),
                getStoragePercentUsed(output)
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

    private fun getStoragePercentUsed(output: String): String {
        return getStorageValue(output, 3)
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