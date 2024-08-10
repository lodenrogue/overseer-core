package com.arkvis.overseer.server

interface CommandBuilder {

    fun build(commands: Array<String>): Command
}