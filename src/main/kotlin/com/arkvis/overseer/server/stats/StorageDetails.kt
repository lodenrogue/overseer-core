package com.arkvis.overseer.server.stats

data class StorageDetails(
    val size: String,
    val used: String,
    val available: String,
    val percentUsed: String
)