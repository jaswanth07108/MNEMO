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
import com.mnemo.app.ui.components.GlassCard
import com.mnemo.app.ui.theme.BorderGlass
import com.mnemo.app.ui.theme.CardBackgroundGlass
import com.mnemo.app.ui.theme.CyberCyan
import com.mnemo.app.ui.theme.MidnightBackground
import com.mnemo.app.ui.theme.MintEmerald
import com.mnemo.app.ui.theme.SoftBlue
import com.mnemo.app.ui.theme.TextGrayMuted
import com.mnemo.app.ui.theme.TextWhite

@Composable
fun PhotoAskScreen(
    memory: Memory,
    onBack: () -> Unit
) {
    var aiAnswer by remember { mutableStateOf<String?>(null) }

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
                    colors = ButtonDefaults.buttonColors(containerColor = CardBackgroundGlass),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("← Back", fontSize = 12.sp, color = TextWhite)
                }

                Text("⚡ Photo Intelligence", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = CyberCyan)
            }
        }

        item {
            GlassCard(
                cornerRadius = 24.dp,
                backgroundColor = CardBackgroundGlass,
                borderColor = BorderGlass
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(text = memory.title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                    Text(text = memory.content, fontSize = 13.sp, color = TextWhite)

                    memory.location?.let { loc ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color.Black.copy(alpha = 0.4f))
                                .padding(12.dp)
                        ) {
                            Column {
                                val header = if (loc.isHighConfidence) "📍 Verified Location (EXIF GPS)" else "📍 Possible Location (Visual Reasoning)"
                                Text(header, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = CyberCyan)
                                Text(loc.locationName, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                                Spacer(modifier = Modifier.height(4.dp))
                                loc.evidenceList.forEach { ev ->
                                    Text("✓ $ev", fontSize = 11.sp, color = TextGrayMuted)
                                }
                            }
                        }
                    }
                }
            }
        }

        aiAnswer?.let { answer ->
            item {
                GlassCard(
                    cornerRadius = 24.dp,
                    backgroundColor = CardBackgroundGlass,
                    borderColor = MintEmerald
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("⚡ Origin SLM Answer (Evidence Grounded)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MintEmerald)
                        Text(answer, fontSize = 13.sp, color = TextWhite)
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
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Ask About This Photo Memory", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextWhite)

                    val questions = listOf(
                        "Where was this?" to "📍 Location: Paradise Beach, Puducherry\nConfidence: High\nEvidence:\n✓ Photo EXIF GPS Metadata verified\n✓ Date/Time matches Puducherry travel note\n✓ Beach & landmark visual clues",
                        "Who/what is in this?" to "👥 Photo contains your college friends at Paradise Beach during your August 2025 trip.",
                        "When was this?" to "📅 Captured on August 14, 2025 at 5:30 PM (Sunset).",
                        "Why did I save this?" to "🏖 Saved as part of your 'Puducherry Trip' memory capsule.",
                        "Show other photos from this day" to "Found 5 related photos from Aug 14, 2025 at Paradise Beach."
                    )

                    questions.forEach { (q, ans) ->
                        OutlinedButton(
                            onClick = { aiAnswer = ans },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, CyberCyan.copy(alpha = 0.4f))
                        ) {
                            Text(q, fontSize = 12.sp, color = CyberCyan)
                        }
                    }
                }
            }
        }
    }
}
