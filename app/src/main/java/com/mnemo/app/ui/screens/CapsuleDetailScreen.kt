package com.mnemo.app.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnemo.app.domain.model.Memory
import com.mnemo.app.domain.model.MemoryCapsule
import com.mnemo.app.ui.components.GlassCard
import com.mnemo.app.ui.components.MemoryCard
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.ElectricViolet
import com.mnemo.app.ui.theme.IndigoAccent
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.SurfaceVariantDark
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite

@Composable
fun CapsuleDetailScreen(
    capsule: MemoryCapsule,
    memories: List<Memory>,
    onBack: () -> Unit,
    onMemoryClick: (Memory) -> Unit
) {
    val capsuleMemories = memories.filter { capsule.memoryIds.contains(it.id) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = SurfaceVariantDark),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("← Back", fontSize = 12.sp, color = TextWhite)
                }

                Text("Memory Capsule", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = IndigoAccent)
            }
        }

        item {
            GlassCard(
                backgroundColor = SurfaceVariantDark,
                borderColor = IndigoAccent.copy(alpha = 0.5f)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(capsule.iconEmoji, fontSize = 28.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(capsule.title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                    }

                    Text(capsule.description, fontSize = 13.sp, color = TextGrayMuted)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${capsule.memoryIds.size} total memories", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SoftBlue)
                        Text("${capsule.documentCount} docs • ${capsule.photoCount} photos • ${capsule.noteCount} notes", fontSize = 11.sp, color = TextGrayMuted)
                    }
                }
            }
        }

        // Timeline Section
        if (capsule.timeline.isNotEmpty()) {
            item {
                GlassCard(
                    backgroundColor = SurfaceVariantDark,
                    borderColor = BorderGlass
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("⏳ Timeline Journey", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ElectricViolet)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            capsule.timeline.forEach { t ->
                                Text("• $t", fontSize = 10.sp, color = TextWhite)
                            }
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "CAPSULE SOURCE MEMORIES (${capsuleMemories.size})",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = TextGrayMuted,
                letterSpacing = 1.sp
            )
        }

        items(capsuleMemories) { memory ->
            MemoryCard(
                memory = memory,
                onClick = { onMemoryClick(memory) }
            )
        }
    }
}
