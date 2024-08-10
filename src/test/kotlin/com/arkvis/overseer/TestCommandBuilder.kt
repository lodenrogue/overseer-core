package com.arkvis.overseer

import com.arkvis.overseer.server.Command
import com.arkvis.overseer.server.CommandBuilder

class TestCommandBuilder: CommandBuilder {

    override fun build(commands: Array<String>): Command {
        return TestCommand()
    }
}