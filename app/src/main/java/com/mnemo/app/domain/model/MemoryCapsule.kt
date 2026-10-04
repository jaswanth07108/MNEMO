package com.mnemo.app.domain.model

data class MemoryCapsule(
    val id: String,
    val title: String,
    val iconEmoji: String,
    val description: String,
    val memoryIds: List<String>,
    val documentCount: Int,
    val photoCount: Int,
    val noteCount: Int,
    val voiceCount: Int,
    val timeline: List<String> = emptyList()
)
