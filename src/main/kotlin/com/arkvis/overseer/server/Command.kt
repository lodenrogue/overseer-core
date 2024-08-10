package com.arkvis.overseer.server

interface Command {

    fun run()

    fun getStatusCode(): Int

    fun getStdOut(): String

    fun getStdErr(): String
}