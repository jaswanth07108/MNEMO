package com.mnemo.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mnemo.app.ui.components.MnemoBottomBar
import com.mnemo.app.ui.components.NavTab
import com.mnemo.app.ui.components.SensitivityDialog
import com.mnemo.app.ui.screens.AskScreen
import com.mnemo.app.ui.screens.CapsuleDetailScreen
import com.mnemo.app.ui.screens.CaptureScreen
import com.mnemo.app.ui.screens.CompareScreen
import com.mnemo.app.ui.screens.HomeScreen
import com.mnemo.app.ui.screens.MemoriesScreen
import com.mnemo.app.ui.screens.MemoryDetailScreen
import com.mnemo.app.ui.screens.PhotoAskScreen
import com.mnemo.app.ui.screens.VaultScreen
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.viewmodel.MemoryViewModel

@Composable
fun MainNavigation(
    viewModel: MemoryViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            if (uiState.activeDetailScreen == null) {
                MnemoBottomBar(
                    selectedTab = uiState.selectedTab,
                    onTabSelected = { viewModel.selectTab(it) }
                )
            }
        },
        containerColor = MidnightBackground
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Active Screen Content based on Navigation & Selected Detail Screen
            when (uiState.activeDetailScreen) {
                "DETAIL" -> {
                    uiState.selectedMemory?.let { memory ->
                        MemoryDetailScreen(
                            memory = memory,
                            onBack = { viewModel.closeDetailScreen() },
                            onAskAbout = { q -> viewModel.executeSearch(q) },
                            onDelete = { id -> viewModel.deleteMemory(id) }
                        )
                    }
                }
                "PHOTO_ASK" -> {
                    uiState.selectedMemory?.let { memory ->
                        PhotoAskScreen(
                            memory = memory,
                            onBack = { viewModel.closeDetailScreen() }
                        )
                    }
                }
                "CAPSULE_DETAIL" -> {
                    uiState.selectedCapsule?.let { capsule ->
                        CapsuleDetailScreen(
                            capsule = capsule,
                            memories = uiState.memories,
                            onBack = { viewModel.closeDetailScreen() },
                            onMemoryClick = { viewModel.openMemoryDetail(it) }
                        )
                    }
                }
                "COMPARE" -> {
                    uiState.searchResult?.let { res ->
                        CompareScreen(
                            rows = res.comparisonRows,
                            onBack = { viewModel.closeDetailScreen() }
                        )
                    }
                }
                else -> {
                    when (uiState.selectedTab) {
                        NavTab.HOME -> HomeScreen(
                            memories = uiState.memories,
                            recentlyOnYourMind = uiState.recentlyOnYourMind,
                            onSearch = { viewModel.executeSearch(it) },
                            onQuickCapture = { viewModel.selectTab(NavTab.CAPTURE) },
                            onMemoryClick = { viewModel.openMemoryDetail(it) },
                            onTopicClick = { viewModel.executeSearch("Show memories about $it") }
                        )
                        NavTab.MEMORIES -> MemoriesScreen(
                            memories = uiState.memories,
                            capsules = uiState.capsules,
                            onMemoryClick = { viewModel.openMemoryDetail(it) },
                            onCapsuleClick = { viewModel.openCapsuleDetail(it) }
                        )
                        NavTab.CAPTURE -> CaptureScreen(
                            onSaveMemory = { title, content, type, tags ->
                                viewModel.addMemory(title, content, type, tags)
                            }
                        )
                        NavTab.ASK -> AskScreen(
                            searchResult = uiState.searchResult,
                            onSearch = { viewModel.executeSearch(it) },
                            onMemoryClick = { viewModel.openMemoryDetail(it) },
                            onCompareClick = { viewModel.openMemoryDetail(uiState.memories.first()) }
                        )
                        NavTab.VAULT -> VaultScreen(
                            isUnlocked = uiState.isVaultUnlocked,
                            vaultMemories = uiState.vaultMemories,
                            onUnlock = { viewModel.unlockVault() },
                            onLock = { viewModel.lockVault() },
                            onMemoryClick = { viewModel.openMemoryDetail(it) }
                        )
                    }
                }
            }

            // Sensitivity Detection Warning Dialog
            uiState.pendingSensitivity?.let { sens ->
                SensitivityDialog(
                    result = sens,
                    onSaveSecurely = { viewModel.confirmSaveSecurely() },
                    onDontSave = { viewModel.cancelPendingMemory() }
                )
            }
        }
    }
}
