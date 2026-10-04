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
import com.mnemo.app.ui.theme.CrimsonSensitive
import com.mnemo.app.ui.theme.ElectricViolet
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.MintEmerald
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.SurfaceVariantDark
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

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(ElectricViolet.copy(alpha = 0.2f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
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
                backgroundColor = SurfaceVariantDark,
                borderColor = BorderGlass
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(text = memory.title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextWhite)

                    Text(text = memory.content, fontSize = 14.sp, color = TextWhite, lineHeight = 20.sp)

                    memory.extractedText?.let { ocr ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.Black.copy(alpha = 0.4f))
                                .padding(12.dp)
                        ) {
                            Column {
                                Text("📄 Extracted Text / OCR", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MintEmerald)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(ocr, fontSize = 11.sp, color = TextGrayMuted)
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
                backgroundColor = SurfaceVariantDark,
                borderColor = BorderGlass
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Ask About This Memory", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextWhite)

                    val questions = listOf(
                        "Where was this?",
                        "Who/what is in this?",
                        "When was this?",
                        "Why did I save this?",
                        "Find similar memories"
                    )

                    questions.forEach { q ->
                        OutlinedButton(
                            onClick = { onAskAbout(q) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(q, fontSize = 12.sp, color = SoftBlue)
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Button(
                        onClick = { onDelete(memory.id) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = CrimsonSensitive.copy(alpha = 0.8f)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Delete Memory", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                    }
                }
            }
        }
    }
}
