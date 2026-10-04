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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnemo.app.domain.model.Memory
import com.mnemo.app.ui.components.GlassCard
import com.mnemo.app.ui.components.MemoryCard
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.CardBackgroundGlass
import com.mnemo.app.ui.theme.CyberCyan
import com.mnemo.app.ui.theme.ElectricViolet
import com.mnemo.app.ui.theme.IndigoAccent
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.MintEmerald
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite

@Composable
fun HomeScreen(
    memories: List<Memory>,
    recentlyOnYourMind: List<Pair<String, Int>>,
    onSearch: (String) -> Unit,
    onQuickCapture: (String) -> Unit,
    onMemoryClick: (Memory) -> Unit,
    onTopicClick: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val sampleQueries = listOf(
        "Show gaming laptops around ₹90,000",
        "Show my B.Tech certificates",
        "Find my 10th class marks memo",
        "Show Puducherry beach trip photos",
        "What was I working on during final year?"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // OriginOS Atomic Header
        item {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(CyberCyan, IndigoAccent, SoftBlue)
                                    )
                                )
                                .padding(horizontal = 14.dp, vertical = 7.dp)
                        ) {
                            Text(
                                text = "MNEMO",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.Black,
                                letterSpacing = 2.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        // OriginOS NPU Status Badge
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(CyberCyan.copy(alpha = 0.1f))
                                .border(1.dp, CyberCyan.copy(alpha = 0.35f), RoundedCornerShape(20.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(CyberCyan)
                                        .padding(3.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "⚡ ORIGIN AI • NPU",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = CyberCyan,
                                    letterSpacing = 0.5.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Don't remember where. Remember what.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextGrayMuted
                )
            }
        }

        // Origin Atomic Search Capsule
        item {
            GlassCard(
                cornerRadius = 28.dp,
                backgroundColor = CardBackgroundGlass,
                borderColor = CyberCyan.copy(alpha = 0.4f)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "✨ Origin Memory Intelligence",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )

                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Ask naturally... e.g., 'Find laptop under ₹90,000'", fontSize = 12.sp, color = TextGrayMuted) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CyberCyan,
                            unfocusedBorderColor = BorderGlass,
                            focusedContainerColor = Color.Black.copy(alpha = 0.35f),
                            unfocusedContainerColor = Color.Black.copy(alpha = 0.35f),
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
                        ),
                        singleLine = true
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { if (searchQuery.isNotBlank()) onSearch(searchQuery) },
                            colors = ButtonDefaults.buttonColors(containerColor = CyberCyan),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text("Search Origin AI ⚡", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.Black)
                        }
                    }

                    // Atomic Prompt Chips
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(sampleQueries) { q ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(CyberCyan.copy(alpha = 0.06f))
                                    .border(1.dp, CyberCyan.copy(alpha = 0.2f), RoundedCornerShape(14.dp))
                                    .clickable {
                                        searchQuery = q
                                        onSearch(q)
                                    }
                                    .padding(horizontal = 12.dp, vertical = 7.dp)
                            ) {
                                Text(text = q, fontSize = 11.sp, color = CyberCyan, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }
        }

        // Origin Atomic Capture Grid
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "ATOMIC CAPTURE (PRIVACY-FIRST)",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextGrayMuted,
                    letterSpacing = 1.5.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val actions = listOf(
                        "PHOTO" to "📷 Photo",
                        "DOCUMENT" to "📄 SAF",
                        "VOICE" to "🎙 Voice",
                        "NOTE" to "✍ Note"
                    )

                    actions.forEach { (type, label) ->
                        GlassCard(
                            modifier = Modifier.weight(1f),
                            cornerRadius = 18.dp,
                            paddingInside = 12.dp,
                            onClick = { onQuickCapture(type) },
                            backgroundColor = CardBackgroundGlass,
                            borderColor = CyberCyan.copy(alpha = 0.15f)
                        ) {
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = label,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextWhite
                                )
                            }
                        }
                    }
                }
            }
        }

        // Origin Atomic Topic Widgets
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "RECENTLY ON YOUR MIND",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextGrayMuted,
                    letterSpacing = 1.5.sp
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(recentlyOnYourMind) { (topic, count) ->
                        GlassCard(
                            cornerRadius = 20.dp,
                            onClick = { onTopicClick(topic) },
                            backgroundColor = CardBackgroundGlass,
                            borderColor = IndigoAccent.copy(alpha = 0.35f)
                        ) {
                            Column {
                                Text(
                                    text = topic,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextWhite
                                )
                                Text(
                                    text = "$count memories",
                                    fontSize = 11.sp,
                                    color = CyberCyan,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }

        // Atomic Memories Feed
        item {
            Text(
                text = "ATOMIC MEMORY FEED (${memories.size})",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = TextGrayMuted,
                letterSpacing = 1.5.sp
            )
        }

        items(memories) { memory ->
            MemoryCard(
                memory = memory,
                onClick = { onMemoryClick(memory) }
            )
        }
    }
}
