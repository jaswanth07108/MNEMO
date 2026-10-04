package com.mnemo.app.ai

import com.mnemo.app.domain.model.Memory
import com.mnemo.app.domain.model.SearchResult

interface MemoryAIService {
    suspend fun processNaturalLanguageQuery(
        query: String,
        availableMemories: List<Memory>
    ): SearchResult

    suspend fun askAboutSpecificMemory(
        question: String,
        memory: Memory,
        relatedMemories: List<Memory>
    ): String

    suspend fun extractMetadataFromContent(
        rawText: String,
        filename: String?
    ): Map<String, String>
}
