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
import com.mnemo.app.ui.theme.ElectricViolet
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
                    text = "Ask MNEMO AI ✨",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "Natural language search across photos, OCR docs, voice & notes.",
                    fontSize = 12.sp,
                    color = TextGrayMuted
                )
            }
        }

        // Search Box Card
        item {
            GlassCard(
                cornerRadius = 22.dp,
                backgroundColor = CardBackgroundGlass,
                borderColor = ElectricViolet.copy(alpha = 0.4f)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = queryText,
                        onValueChange = { queryText = it },
                        placeholder = { Text("Ask your memory naturally...", fontSize = 12.sp, color = TextGrayMuted) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ElectricViolet,
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
                            colors = ButtonDefaults.buttonColors(containerColor = ElectricViolet),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Query Memory Bank", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = TextWhite)
                        }
                    }

                    // Preset Question Chips
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(presetQuestions) { pq ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White.copy(alpha = 0.05f))
                                    .border(1.dp, BorderGlass, RoundedCornerShape(12.dp))
                                    .clickable {
                                        queryText = pq
                                        onSearch(pq)
                                    }
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(text = pq, fontSize = 11.sp, color = SoftBlue, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }
        }

        // AI Evidence Card
        searchResult?.let { result ->
            item {
                GlassCard(
                    cornerRadius = 22.dp,
                    backgroundColor = CardBackgroundGlass,
                    borderColor = MintEmerald.copy(alpha = 0.5f)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(MintEmerald.copy(alpha = 0.18f))
                                    .border(1.dp, MintEmerald.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "🤖 AI EVIDENCE RESPONSE",
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

                        Text(
                            text = result.answer,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextWhite,
                            lineHeight = 22.sp
                        )

                        if (result.comparisonRows.isNotEmpty()) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(
                                    onClick = onCompareClick,
                                    colors = ButtonDefaults.buttonColors(containerColor = IndigoAccent),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text("📊 Open Side-by-Side Comparison", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                                }
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "RELEVANT GROUNDED MEMORIES (${result.candidateMemories.size})",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextGrayMuted,
                    letterSpacing = 1.2.sp
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
