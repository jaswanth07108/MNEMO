package com.mnemo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnemo.app.domain.model.Memory
import com.mnemo.app.domain.model.MemoryCapsule
import com.mnemo.app.domain.model.MemoryType
import com.mnemo.app.ui.components.GlassCard
import com.mnemo.app.ui.components.MemoryCard
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.CardBackgroundGlass
import com.mnemo.app.ui.theme.ElectricViolet
import com.mnemo.app.ui.theme.IndigoAccent
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite

@Composable
fun MemoriesScreen(
    memories: List<Memory>,
    capsules: List<MemoryCapsule>,
    onMemoryClick: (Memory) -> Unit,
    onCapsuleClick: (MemoryCapsule) -> Unit
) {
    var selectedFilter by remember { mutableStateOf("ALL") }

    val filteredMemories = when (selectedFilter) {
        "PHOTOS" -> memories.filter { it.type == MemoryType.PHOTO }
        "DOCUMENTS" -> memories.filter { it.type == MemoryType.DOCUMENT }
        "VOICE" -> memories.filter { it.type == MemoryType.VOICE }
        "NOTES" -> memories.filter { it.type == MemoryType.NOTE }
        "SPECS" -> memories.filter { it.type == MemoryType.PRODUCT_RESEARCH }
        else -> memories
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Header
        item {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                Text(
                    text = "Memories & Capsules",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "Indexed semantic layers over screenshots, notes, docs & audio.",
                    fontSize = 12.sp,
                    color = TextGrayMuted
                )
            }
        }

        // Memory Capsules Section
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "TOPIC CAPSULES",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextGrayMuted,
                    letterSpacing = 1.2.sp
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(capsules) { capsule ->
                        GlassCard(
                            cornerRadius = 20.dp,
                            onClick = { onCapsuleClick(capsule) },
                            backgroundColor = CardBackgroundGlass,
                            borderColor = IndigoAccent.copy(alpha = 0.4f)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text(
                                    text = capsule.title,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextWhite
                                )
                                Text(
                                    text = capsule.description,
                                    fontSize = 11.sp,
                                    color = TextGrayMuted,
                                    maxLines = 2
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "${capsule.memoryIds.size} memories",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SoftBlue
                                    )
                                    Text(
                                        text = "${capsule.documentCount} docs • ${capsule.photoCount} photos",
                                        fontSize = 10.sp,
                                        color = TextGrayMuted
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Memory Type Filter Chips
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                val filters = listOf("ALL", "PHOTOS", "DOCUMENTS", "VOICE", "NOTES", "SPECS")
                items(filters) { f ->
                    val isSel = selectedFilter == f
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (isSel) ElectricViolet else CardBackgroundGlass)
                            .border(1.dp, if (isSel) ElectricViolet else BorderGlass, RoundedCornerShape(14.dp))
                            .clickable { selectedFilter = f }
                            .padding(horizontal = 14.dp, vertical = 7.dp)
                    ) {
                        Text(
                            text = f,
                            fontSize = 11.sp,
                            fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium,
                            color = TextWhite
                        )
                    }
                }
            }
        }

        // Feed Header
        item {
            Text(
                text = "INDEXED MEMORIES (${filteredMemories.size})",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = TextGrayMuted,
                letterSpacing = 1.2.sp
            )
        }

        // Memory Feed Items
        items(filteredMemories) { memory ->
            MemoryCard(
                memory = memory,
                onClick = { onMemoryClick(memory) }
            )
        }
    }
}
