package com.arkvis.overseer

import com.arkvis.overseer.server.Command

class TestCommand: Command {

    override fun run() {

    }

    override fun getStatusCode(): Int {
        return 0
    }

    override fun getStdOut(): String {
        return """
            Temperature:
            29.4 C
            52 C

            Storage:
            Size	Used	Avail	Use%
            455G	23G	414G	6%

            Memory:
            	total	used	available
            Mem:	3.7Gi	742Mi	3.0Gi

            Battery:
            98.0739%
        """.trimIndent()
    }

    override fun getStdErr(): String {
        return ""
    }
}