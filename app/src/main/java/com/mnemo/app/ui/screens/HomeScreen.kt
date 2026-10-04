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
import com.mnemo.app.ui.theme.ElectricViolet
import com.mnemo.app.ui.theme.IndigoAccent
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.SurfaceGlass
import com.mnemo.app.ui.theme.SurfaceVariantDark
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
        // App Header
        item {
            Column(modifier = Modifier.padding(top = 12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                Brush.linearGradient(
                                    listOf(ElectricViolet, IndigoAccent, SoftBlue)
                                )
                            )
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "MNEMO",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            color = TextWhite,
                            letterSpacing = 1.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(ElectricViolet.copy(alpha = 0.15f))
                            .border(1.dp, ElectricViolet.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "🛡 LOCAL-FIRST AI MEMORY",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = ElectricViolet
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Your phone remembers what you forget.",
                    fontSize = 13.sp,
                    color = TextGrayMuted
                )
            }
        }

        // Conversational Search Box Section
        item {
            GlassCard(
                backgroundColor = SurfaceVariantDark.copy(alpha = 0.8f),
                borderColor = ElectricViolet.copy(alpha = 0.4f)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "What do you remember?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )

                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Describe anything... (e.g. gaming laptops, certificates, beach photos)", fontSize = 12.sp, color = TextGrayMuted) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ElectricViolet,
                            unfocusedBorderColor = BorderGlass,
                            focusedContainerColor = Color.Black.copy(alpha = 0.3f),
                            unfocusedContainerColor = Color.Black.copy(alpha = 0.3f),
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
                            colors = ButtonDefaults.buttonColors(containerColor = ElectricViolet),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Ask MNEMO ✨", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                    }

                    // Clickable Prompt Chips
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        items(sampleQueries) { q ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(SurfaceGlass)
                                    .border(1.dp, BorderGlass, RoundedCornerShape(14.dp))
                                    .clickable {
                                        searchQuery = q
                                        onSearch(q)
                                    }
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(text = q, fontSize = 11.sp, color = TextWhite)
                            }
                        }
                    }
                }
            }
        }

        // Quick Capture Action Buttons
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "QUICK CAPTURE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextGrayMuted,
                    letterSpacing = 1.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val actions = listOf(
                        "PHOTO" to "📷 Photo",
                        "DOCUMENT" to "📄 Document",
                        "VOICE" to "🎙 Voice",
                        "NOTE" to "✍ Note"
                    )

                    actions.forEach { (type, label) ->
                        GlassCard(
                            modifier = Modifier.weight(1f),
                            onClick = { onQuickCapture(type) },
                            backgroundColor = SurfaceVariantDark.copy(alpha = 0.6f)
                        ) {
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

        // Recently On Your Mind Section
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "RECENTLY ON YOUR MIND",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextGrayMuted,
                    letterSpacing = 1.sp
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(recentlyOnYourMind) { (topic, count) ->
                        GlassCard(
                            onClick = { onTopicClick(topic) },
                            backgroundColor = SurfaceGlass,
                            borderColor = IndigoAccent.copy(alpha = 0.3f)
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
                                    color = SoftBlue
                                )
                            }
                        }
                    }
                }
            }
        }

        // Recent Memories Header
        item {
            Text(
                text = "RECENT MEMORIES (${memories.size})",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = TextGrayMuted,
                letterSpacing = 1.sp
            )
        }

        // Recent Memories Feed List
        items(memories) { memory ->
            MemoryCard(
                memory = memory,
                onClick = { onMemoryClick(memory) }
            )
        }
    }
}
