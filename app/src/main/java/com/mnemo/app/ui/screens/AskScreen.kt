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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnemo.app.domain.model.Memory
import com.mnemo.app.domain.model.SearchResult
import com.mnemo.app.ui.components.GlassCard
import com.mnemo.app.ui.components.MemoryCard
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.CardBackgroundGlass
import com.mnemo.app.ui.theme.CyberCyan
import com.mnemo.app.ui.theme.IndigoAccent
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.MintEmerald
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite

@Composable
fun AskScreen(
    searchResult: SearchResult?,
    onSearch: (String) -> Unit,
    onMemoryClick: (Memory) -> Unit,
    onCompareClick: () -> Unit
) {
    var queryText by remember { mutableStateOf("") }

    val presetQuestions = listOf(
        "Show gaming laptops around ₹90,000",
        "Show my B.Tech degree & certificates",
        "Where did we take Puducherry photos?",
        "What was I working on in final year?"
    )

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
                    text = "⚡ Origin AI Intelligence",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "Natural language memory retrieval powered by on-device AI.",
                    fontSize = 12.sp,
                    color = TextGrayMuted
                )
            }
        }

        // Origin Search Capsule
        item {
            GlassCard(
                cornerRadius = 26.dp,
                backgroundColor = CardBackgroundGlass,
                borderColor = CyberCyan.copy(alpha = 0.4f)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = queryText,
                        onValueChange = { queryText = it },
                        placeholder = { Text("Ask your memory naturally...", fontSize = 12.sp, color = TextGrayMuted) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CyberCyan,
                            unfocusedBorderColor = BorderGlass,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
                        )
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { if (queryText.isNotBlank()) onSearch(queryText) },
                            colors = ButtonDefaults.buttonColors(containerColor = CyberCyan),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text("Query Origin AI ⚡", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Black)
                        }
                    }

                    // Preset Atomic Chips
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(presetQuestions) { pq ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(CyberCyan.copy(alpha = 0.06f))
                                    .border(1.dp, CyberCyan.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                                    .clickable {
                                        queryText = pq
                                        onSearch(pq)
                                    }
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(text = pq, fontSize = 11.sp, color = CyberCyan, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }
        }

        // Origin AI Evidence Widget with Local SLM NPU Metrics
        searchResult?.let { result ->
            item {
                GlassCard(
                    cornerRadius = 26.dp,
                    backgroundColor = CardBackgroundGlass,
                    borderColor = MintEmerald.copy(alpha = 0.5f)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(MintEmerald.copy(alpha = 0.15f))
                                    .border(1.dp, MintEmerald.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "⚡ ORIGIN SLM EVIDENCE",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MintEmerald
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = result.confidenceText,
                                fontSize = 10.sp,
                                color = TextGrayMuted,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        // Local SLM NPU Benchmark Performance Chip
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(CyberCyan.copy(alpha = 0.08f))
                                .border(1.dp, CyberCyan.copy(alpha = 0.25f), RoundedCornerShape(12.dp))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "🤖 ${result.llmModelName}",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = CyberCyan
                                )
                                Text(
                                    text = "⚡ ${result.llmLatencyMs} ms • ${result.llmTokensPerSecond} tok/s",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MintEmerald
                                )
                            }
                        }

                        Text(
                            text = result.answer,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextWhite,
                            lineHeight = 22.sp
                        )

                        if (result.comparisonRows.isNotEmpty()) {
                            Button(
                                onClick = onCompareClick,
                                colors = ButtonDefaults.buttonColors(containerColor = IndigoAccent),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Text("📊 Open Atomic Comparison", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "GROUNDED SOURCE MEMORIES (${result.candidateMemories.size})",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextGrayMuted,
                    letterSpacing = 1.5.sp
                )
            }

            items(result.candidateMemories) { memory ->
                MemoryCard(
                    memory = memory,
                    onClick = { onMemoryClick(memory) }
                )
            }
        }
    }
}
