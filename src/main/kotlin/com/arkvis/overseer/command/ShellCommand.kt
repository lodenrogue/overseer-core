package com.arkvis.overseer.command

import com.arkvis.overseer.server.Command

class ShellCommand(private val commands: Array<String>) : Command {

    private lateinit var output: String
    private lateinit var error: String
    private var statusCode: Int = 0

    override fun run() {
        val process = Runtime.getRuntime().exec(commands)
        output = process.inputStream.bufferedReader().readText()
        error = process.errorStream.bufferedReader().readText()
        statusCode = process.waitFor()
    }

    override fun getStatusCode(): Int {
        return statusCode
    }

    override fun getStdOut(): String {
        return output
    }

    override fun getStdErr(): String {
        return error
    }
}