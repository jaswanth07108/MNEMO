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
import com.mnemo.app.domain.model.MemoryType
import com.mnemo.app.ui.components.GlassCard
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
fun CaptureScreen(
    onSaveMemory: (title: String, content: String, type: MemoryType, tags: List<String>) -> Unit
) {
    var titleText by remember { mutableStateOf("") }
    var contentText by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(MemoryType.NOTE) }
    var tagsText by remember { mutableStateOf("personal, memory") }
    var isUnderstanding by remember { mutableStateOf(false) }

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
                    text = "Explicit Memory Capture",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "USER SELECTS → MNEMO REMEMBERS. Explicit, local ingestion.",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MintEmerald
                )
            }
        }

        // Mode Selector Grid
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val modes = listOf(
                    MemoryType.PHOTO to "📷 Photo",
                    MemoryType.DOCUMENT to "📄 Doc",
                    MemoryType.VOICE to "🎙 Voice",
                    MemoryType.NOTE to "✍ Note"
                )

                modes.forEach { (type, label) ->
                    val isSel = selectedType == type
                    GlassCard(
                        modifier = Modifier.weight(1f),
                        cornerRadius = 16.dp,
                        paddingInside = 12.dp,
                        onClick = { selectedType = type },
                        backgroundColor = if (isSel) ElectricViolet.copy(alpha = 0.25f) else CardBackgroundGlass,
                        borderColor = if (isSel) ElectricViolet else BorderGlass
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = label,
                                fontSize = 11.sp,
                                fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSel) TextWhite else TextGrayMuted
                            )
                        }
                    }
                }
            }
        }

        if (isUnderstanding) {
            item {
                GlassCard(
                    cornerRadius = 18.dp,
                    backgroundColor = CardBackgroundGlass,
                    borderColor = IndigoAccent
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🧠 Indexing and analyzing memory content...", fontSize = 12.sp, color = IndigoAccent, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Input Form Card
        item {
            GlassCard(
                cornerRadius = 24.dp,
                backgroundColor = CardBackgroundGlass,
                borderColor = BorderGlass
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(text = "Memory Parameters", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextWhite)

                    OutlinedTextField(
                        value = titleText,
                        onValueChange = { titleText = it },
                        label = { Text("Memory Title", fontSize = 12.sp) },
                        placeholder = { Text("e.g. ASUS Gaming Laptop / Puducherry Trip", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ElectricViolet,
                            unfocusedBorderColor = BorderGlass,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
                        )
                    )

                    OutlinedTextField(
                        value = contentText,
                        onValueChange = { contentText = it },
                        label = { Text("Memory Content / Notes / OCR Text", fontSize = 12.sp) },
                        placeholder = { Text("Paste specs, receipt text, or notes. Sensitivity & credentials are automatically protected.", fontSize = 12.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ElectricViolet,
                            unfocusedBorderColor = BorderGlass,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
                        )
                    )

                    OutlinedTextField(
                        value = tagsText,
                        onValueChange = { tagsText = it },
                        label = { Text("Tags (comma separated)", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ElectricViolet,
                            unfocusedBorderColor = BorderGlass,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Button(
                        onClick = {
                            if (titleText.isNotBlank() && contentText.isNotBlank()) {
                                isUnderstanding = true
                                val tagList = tagsText.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                                onSaveMemory(titleText, contentText, selectedType, tagList)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricViolet),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Index Memory to Local AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                    }
                }
            }
        }
    }
}
