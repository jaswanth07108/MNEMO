package com.mnemo.app.domain.repository

import com.mnemo.app.domain.model.Memory
import com.mnemo.app.domain.model.MemoryCapsule
import com.mnemo.app.domain.model.MemoryType
import com.mnemo.app.domain.model.PrivacyLevel
import com.mnemo.app.domain.model.SearchResult

interface MemorySearchRepository {
    suspend fun getAllMemories(includeVault: Boolean = false): List<Memory>
    suspend fun getMemoryById(id: String): Memory?
    suspend fun addMemory(memory: Memory)
    suspend fun deleteMemory(id: String)
    suspend fun updateMemoryPrivacy(id: String, privacyLevel: PrivacyLevel)
    
    suspend fun searchMemories(query: String, includeVault: Boolean = false): SearchResult
    suspend fun getMemoriesByTopic(topic: String): List<Memory>
    suspend fun getMemoriesByType(type: MemoryType): List<Memory>
    suspend fun getMemoryCapsules(): List<MemoryCapsule>
    suspend fun getRecentlyOnYourMindTopics(): List<Pair<String, Int>>
    suspend fun getVaultMemories(): List<Memory>
}
