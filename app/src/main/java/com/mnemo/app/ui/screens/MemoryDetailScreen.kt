package com.mnemo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnemo.app.domain.model.Memory
import com.mnemo.app.ui.components.GlassCard
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.CardBackgroundGlass
import com.mnemo.app.ui.theme.CrimsonSensitive
import com.mnemo.app.ui.theme.ElectricViolet
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.MintEmerald
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite

@Composable
fun MemoryDetailScreen(
    memory: Memory,
    onBack: () -> Unit,
    onAskAbout: (String) -> Unit,
    onDelete: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = CardBackgroundGlass),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("← Back to List", fontSize = 12.sp, color = TextWhite)
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(ElectricViolet.copy(alpha = 0.18f))
                        .border(1.dp, ElectricViolet.copy(alpha = 0.35f), RoundedCornerShape(10.dp))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = memory.sourceType,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ElectricViolet
                    )
                }
            }
        }

        item {
            GlassCard(
                cornerRadius = 24.dp,
                backgroundColor = CardBackgroundGlass,
                borderColor = BorderGlass
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(text = memory.title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextWhite)

                    Text(text = memory.content, fontSize = 14.sp, color = TextWhite, lineHeight = 22.sp)

                    memory.extractedText?.let { ocr ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color.Black.copy(alpha = 0.45f))
                                .padding(14.dp)
                        ) {
                            Column {
                                Text("📄 Extracted Text / OCR Data", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MintEmerald)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(ocr, fontSize = 12.sp, color = TextGrayMuted, lineHeight = 18.sp)
                            }
                        }
                    }

                    memory.location?.let { loc ->
                        Column {
                            val headerText = if (loc.isHighConfidence) "📍 Verified Location: ${loc.locationName}" else "📍 Possible Location: ${loc.locationName}"
                            Text(
                                text = headerText,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SoftBlue
                            )
                            loc.evidenceList.forEach { ev ->
                                Text("✓ $ev", fontSize = 11.sp, color = TextGrayMuted)
                            }
                        }
                    }
                }
            }
        }

        item {
            GlassCard(
                cornerRadius = 24.dp,
                backgroundColor = CardBackgroundGlass,
                borderColor = BorderGlass
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Interactive Evidence Q&A", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextWhite)

                    val questions = listOf(
                        "Where was this?",
                        "Who or what is in this memory?",
                        "When was this captured?",
                        "Why did I save this?",
                        "Find similar memories in bank"
                    )

                    questions.forEach { q ->
                        OutlinedButton(
                            onClick = { onAskAbout(q) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text(q, fontSize = 12.sp, color = SoftBlue, fontWeight = FontWeight.Medium)
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Button(
                        onClick = { onDelete(memory.id) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = CrimsonSensitive.copy(alpha = 0.85f)),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Delete Memory from Device", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                    }
                }
            }
        }
    }
}
