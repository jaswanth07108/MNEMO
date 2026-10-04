package com.mnemo.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnemo.app.data.repository.MemoryRepositoryImpl
import com.mnemo.app.domain.model.Memory
import com.mnemo.app.domain.model.MemoryCapsule
import com.mnemo.app.domain.model.MemoryType
import com.mnemo.app.domain.model.PrivacyLevel
import com.mnemo.app.domain.model.SearchResult
import com.mnemo.app.domain.model.SensitiveDetectionResult
import com.mnemo.app.domain.repository.MemorySearchRepository
import com.mnemo.app.ui.components.NavTab
import com.mnemo.app.utils.SensitivityDetector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MemoryUiState(
    val selectedTab: NavTab = NavTab.HOME,
    val memories: List<Memory> = emptyList(),
    val recentlyOnYourMind: List<Pair<String, Int>> = emptyList(),
    val capsules: List<MemoryCapsule> = emptyList(),
    val searchResult: SearchResult? = null,
    val searchQuery: String = "",
    val selectedMemory: Memory? = null,
    val selectedCapsule: MemoryCapsule? = null,
    val isVaultUnlocked: Boolean = false,
    val vaultMemories: List<Memory> = emptyList(),
    val pendingSensitivity: SensitiveDetectionResult? = null,
    val pendingMemory: Memory? = null,
    val activeDetailScreen: String? = null // "DETAIL", "PHOTO_ASK", "CAPSULE_DETAIL", "COMPARE"
)

class MemoryViewModel(
    private val repository: MemorySearchRepository = MemoryRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow(MemoryUiState())
    val uiState: StateFlow<MemoryUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            val mems = repository.getAllMemories(includeVault = false)
            val topics = repository.getRecentlyOnYourMindTopics()
            val caps = repository.getMemoryCapsules()
            _uiState.update {
                it.copy(
                    memories = mems,
                    recentlyOnYourMind = topics,
                    capsules = caps
                )
            }
        }
    }

    fun selectTab(tab: NavTab) {
        _uiState.update { it.copy(selectedTab = tab, activeDetailScreen = null) }
        if (tab == NavTab.VAULT && _uiState.value.isVaultUnlocked) {
            loadVaultMemories()
        }
    }

    fun executeSearch(query: String) {
        viewModelScope.launch {
            val result = repository.searchMemories(query, includeVault = _uiState.value.isVaultUnlocked)
            _uiState.update { 
                it.copy(
                    searchQuery = query,
                    searchResult = result,
                    selectedTab = NavTab.ASK,
                    activeDetailScreen = null
                ) 
            }
        }
    }

    fun openMemoryDetail(memory: Memory) {
        _uiState.update { 
            it.copy(
                selectedMemory = memory,
                activeDetailScreen = if (memory.type == MemoryType.PHOTO) "PHOTO_ASK" else "DETAIL"
            ) 
        }
    }

    fun openCapsuleDetail(capsule: MemoryCapsule) {
        _uiState.update { 
            it.copy(
                selectedCapsule = capsule,
                activeDetailScreen = "CAPSULE_DETAIL"
            ) 
        }
    }

    fun closeDetailScreen() {
        _uiState.update { it.copy(activeDetailScreen = null, selectedMemory = null, selectedCapsule = null) }
    }

    fun unlockVault() {
        viewModelScope.launch {
            val vaultMems = repository.getVaultMemories()
            _uiState.update { 
                it.copy(
                    isVaultUnlocked = true,
                    vaultMemories = vaultMems
                ) 
            }
        }
    }

    fun lockVault() {
        _uiState.update { it.copy(isVaultUnlocked = false, vaultMemories = emptyList()) }
    }

    fun addMemory(title: String, content: String, type: MemoryType, tags: List<String>) {
        viewModelScope.launch {
            val sensitivity = SensitivityDetector.analyzeText(content, title)
            val newMemory = Memory(
                title = title,
                content = content,
                type = type,
                privacyLevel = sensitivity.recommendedPrivacy,
                createdAt = "2026-10-04T02:00:00Z",
                capturedAt = "2026-10-04T02:00:00Z",
                formattedDate = "Oct 04, 2026",
                sourceType = "📱 User Memory Capture",
                extractedText = content,
                tags = tags
            )

            if (sensitivity.isSensitive) {
                _uiState.update { 
                    it.copy(
                        pendingSensitivity = sensitivity,
                        pendingMemory = newMemory
                    ) 
                }
            } else {
                repository.addMemory(newMemory)
                loadInitialData()
            }
        }
    }

    fun confirmSaveSecurely() {
        viewModelScope.launch {
            val pending = _uiState.value.pendingMemory
            if (pending != null) {
                repository.addMemory(pending.copy(privacyLevel = PrivacyLevel.HIGHLY_SENSITIVE))
                loadInitialData()
                if (_uiState.value.isVaultUnlocked) loadVaultMemories()
            }
            _uiState.update { it.copy(pendingSensitivity = null, pendingMemory = null) }
        }
    }

    fun cancelPendingMemory() {
        _uiState.update { it.copy(pendingSensitivity = null, pendingMemory = null) }
    }

    fun deleteMemory(id: String) {
        viewModelScope.launch {
            repository.deleteMemory(id)
            loadInitialData()
            if (_uiState.value.isVaultUnlocked) loadVaultMemories()
            closeDetailScreen()
        }
    }

    private fun loadVaultMemories() {
        viewModelScope.launch {
            val vaultMems = repository.getVaultMemories()
            _uiState.update { it.copy(vaultMemories = vaultMems) }
        }
    }
}
