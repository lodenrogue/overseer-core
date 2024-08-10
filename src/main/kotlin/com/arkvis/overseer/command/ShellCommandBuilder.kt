package com.arkvis.overseer.command

import com.arkvis.overseer.server.Command
import com.arkvis.overseer.server.CommandBuilder

class ShellCommandBuilder: CommandBuilder {

    override fun build(commands: Array<String>): Command {
        return ShellCommand(commands)
    }
}