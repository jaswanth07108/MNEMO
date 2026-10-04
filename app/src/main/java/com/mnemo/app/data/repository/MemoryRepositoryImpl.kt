package com.mnemo.app.data.repository

import com.mnemo.app.ai.LocalMemoryAIService
import com.mnemo.app.ai.MemoryAIService
import com.mnemo.app.data.DemoData
import com.mnemo.app.domain.model.Memory
import com.mnemo.app.domain.model.MemoryCapsule
import com.mnemo.app.domain.model.MemoryType
import com.mnemo.app.domain.model.PrivacyLevel
import com.mnemo.app.domain.model.SearchResult
import com.mnemo.app.domain.repository.MemorySearchRepository

class MemoryRepositoryImpl(
    private val aiService: MemoryAIService = LocalMemoryAIService()
) : MemorySearchRepository {

    private val memoriesStore = mutableListOf<Memory>().apply {
        addAll(DemoData.INITIAL_MEMORIES)
    }

    override suspend fun getAllMemories(includeVault: Boolean): List<Memory> {
        return if (includeVault) {
            memoriesStore.toList()
        } else {
            memoriesStore.filter { it.privacyLevel != PrivacyLevel.HIGHLY_SENSITIVE }
        }
    }

    override suspend fun getMemoryById(id: String): Memory? {
        return memoriesStore.find { it.id == id }
    }

    override suspend fun addMemory(memory: Memory) {
        memoriesStore.add(0, memory)
    }

    override suspend fun deleteMemory(id: String) {
        memoriesStore.removeAll { it.id == id }
    }

    override suspend fun updateMemoryPrivacy(id: String, privacyLevel: PrivacyLevel) {
        val index = memoriesStore.indexOfFirst { it.id == id }
        if (index != -1) {
            memoriesStore[index] = memoriesStore[index].copy(privacyLevel = privacyLevel)
        }
    }

    override suspend fun searchMemories(query: String, includeVault: Boolean): SearchResult {
        val pool = getAllMemories(includeVault)
        return aiService.processNaturalLanguageQuery(query, pool)
    }

    override suspend fun getMemoriesByTopic(topic: String): List<Memory> {
        return memoriesStore.filter { mem -> 
            mem.topics.any { it.equals(topic, ignoreCase = true) } ||
            mem.tags.any { it.equals(topic, ignoreCase = true) }
        }
    }

    override suspend fun getMemoriesByType(type: MemoryType): List<Memory> {
        return memoriesStore.filter { it.type == type && it.privacyLevel != PrivacyLevel.HIGHLY_SENSITIVE }
    }

    override suspend fun getMemoryCapsules(): List<MemoryCapsule> {
        return DemoData.CAPSULES
    }

    override suspend fun getRecentlyOnYourMindTopics(): List<Pair<String, Int>> {
        val topicCounts = mutableMapOf<String, Int>()
        for (mem in memoriesStore) {
            for (topic in mem.topics) {
                val icon = when {
                    topic.contains("Laptop", ignoreCase = true) -> "💻 $topic"
                    topic.contains("Weather", ignoreCase = true) -> "🌦️ $topic"
                    topic.contains("Final", ignoreCase = true) -> "🎓 $topic"
                    topic.contains("Trip", ignoreCase = true) || topic.contains("Beach", ignoreCase = true) -> "🏖️ $topic"
                    topic.contains("Certificate", ignoreCase = true) || topic.contains("Education", ignoreCase = true) -> "📄 $topic"
                    else -> "📌 $topic"
                }
                topicCounts[icon] = (topicCounts[icon] ?: 0) + 1
            }
        }
        return if (topicCounts.isNotEmpty()) {
            topicCounts.toList().sortedByDescending { it.second }
        } else {
            listOf(
                "💻 Gaming Laptops" to 12,
                "🌦️ Weather Project" to 15,
                "🎓 Final Year" to 23,
                "🏖️ Puducherry Trip" to 18
            )
        }
    }

    override suspend fun getVaultMemories(): List<Memory> {
        return memoriesStore.filter { 
            it.privacyLevel == PrivacyLevel.SENSITIVE || it.privacyLevel == PrivacyLevel.HIGHLY_SENSITIVE 
        }
    }
}
