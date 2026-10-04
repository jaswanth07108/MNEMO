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
import androidx.compose.material3.OutlinedButton
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
import com.mnemo.app.ui.theme.ElectricViolet
import com.mnemo.app.ui.theme.IndigoAccent
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.MintEmerald
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.SurfaceGlass
import com.mnemo.app.ui.theme.SurfaceVariantDark
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
        "Show the laptop I was considering.",
        "Show photos from my college trip.",
        "Where did we take this?",
        "Show my certificates.",
        "What was I working on in final year?"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Column(modifier = Modifier.padding(top = 12.dp)) {
                Text(
                    text = "Ask MNEMO ✨",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "Ask anything you remember from your personal memories.",
                    fontSize = 12.sp,
                    color = TextGrayMuted
                )
            }
        }

        // Search Input Bar
        item {
            GlassCard(
                backgroundColor = SurfaceVariantDark,
                borderColor = ElectricViolet.copy(alpha = 0.4f)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
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
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Search Memories", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }

                    // Preset Questions Chips
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        items(presetQuestions) { pq ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(SurfaceGlass)
                                    .border(1.dp, BorderGlass, RoundedCornerShape(12.dp))
                                    .clickable {
                                        queryText = pq
                                        onSearch(pq)
                                    }
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(text = pq, fontSize = 11.sp, color = SoftBlue)
                            }
                        }
                    }
                }
            }
        }

        // AI Answer Card (If Result Available)
        searchResult?.let { result ->
            item {
                GlassCard(
                    backgroundColor = SurfaceVariantDark.copy(alpha = 0.9f),
                    borderColor = MintEmerald.copy(alpha = 0.5f)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(MintEmerald.copy(alpha = 0.2f))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "🤖 AI EVIDENCE ANSWER",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MintEmerald
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = result.confidenceText,
                                fontSize = 10.sp,
                                color = TextGrayMuted
                            )
                        }

                        Text(
                            text = result.answer,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextWhite,
                            lineHeight = 20.sp
                        )

                        // Compare & Show Sources Buttons
                        if (result.comparisonRows.isNotEmpty()) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(
                                    onClick = onCompareClick,
                                    colors = ButtonDefaults.buttonColors(containerColor = IndigoAccent),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text("📊 Compare Specs", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }

            // Results Section Title
            item {
                Text(
                    text = "RELEVANT SOURCE MEMORIES (${result.candidateMemories.size})",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextGrayMuted,
                    letterSpacing = 1.sp
                )
            }

            // Candidates Feed
            items(result.candidateMemories) { memory ->
                MemoryCard(
                    memory = memory,
                    onClick = { onMemoryClick(memory) }
                )
            }
        }
    }
}
